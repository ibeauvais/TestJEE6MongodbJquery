package fr.simplechat.web.renderer;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.context.ResponseWriterWrapper;
import javax.faces.convert.ConverterException;
import javax.faces.render.Renderer;

public abstract class CustomHtml5Renderer  extends Renderer{
	private static final String TYPE_ATTRIBUTE = "type";
	private static final List<String> html5Attribute=Arrays.asList("placeholder");

	
	private Renderer originalRenderer;
	
	public CustomHtml5Renderer(Renderer originalRenderer){
		this.originalRenderer=originalRenderer;
	}
	
	@Override
    public void encodeBegin(FacesContext context, UIComponent component) 
    throws IOException {
        
		originalRenderer.encodeBegin(context, component);
    }


    @Override
    public void decode(FacesContext context, UIComponent component) {
    	originalRenderer.decode(context, component);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) 
    throws IOException {
    	 final ResponseWriter originalResponseWriter = context.getResponseWriter();
    	
    	 final String type=  (String) component.getAttributes().get(TYPE_ATTRIBUTE);
    	 final Map<String,String> additionalAttributes=getadditionalAttributes(component);
    	 
    	 context.setResponseWriter(new ResponseWriterWrapper() {
             @Override
             public ResponseWriter getWrapped() {
                 return originalResponseWriter;
             }
			@Override
			public void writeAttribute(String name, Object value,
					String property) throws IOException {
				
				if(type!=null && isTypeAttribute(name)){
					super.writeAttribute(name, type, property);
				}else
					super.writeAttribute(name, value, property);
			}

			@Override
			public void endElement(String name) throws IOException {
				
				for(Entry<String,String> attributeValue:additionalAttributes.entrySet())
					super.writeAttribute(attributeValue.getKey(), attributeValue.getValue(), attributeValue.getKey());
					
				super.endElement(name);
			}


             
         });

    	 originalRenderer.encodeEnd(context, component); // Now, render it!
         context.setResponseWriter(originalResponseWriter); // Restore original writer.
    	
    	
    }

	private Map<String, String> getadditionalAttributes(UIComponent component) {
		Map<String, String> result=new HashMap<String,String>();
		for(String additionalAttribute:html5Attribute){
			String value=(String)component.getAttributes().get(additionalAttribute);
			if(value!=null)
				result.put(additionalAttribute, value);
		}
		return result;
		
	}


	private boolean isTypeAttribute(String name) {
		return TYPE_ATTRIBUTE.equalsIgnoreCase(name);
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
		originalRenderer.encodeChildren(context, component);
	}


	@Override
	public String convertClientId(FacesContext context, String clientId) {
		return originalRenderer.convertClientId(context, clientId);
	}


	@Override
	public boolean getRendersChildren() {
		return originalRenderer.getRendersChildren();
	}


	@Override
	public Object getConvertedValue(FacesContext context,
			UIComponent component, Object submittedValue)
			throws ConverterException {
		return originalRenderer.getConvertedValue(context, component, submittedValue);
	}

}
