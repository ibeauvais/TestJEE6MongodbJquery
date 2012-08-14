package fr.simplechat.web.renderer;

import javax.faces.render.FacesRenderer;

import com.sun.faces.renderkit.html_basic.TextRenderer;
	


@FacesRenderer(componentFamily ="javax.faces.Input",
rendererType = "javax.faces.Text")
public class CustomHtml5TextRenderer extends CustomHtml5Renderer{
	
	public CustomHtml5TextRenderer(){
		super(new TextRenderer());
	}
	
	

}
