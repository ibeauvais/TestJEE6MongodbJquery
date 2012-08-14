package fr.simplechat.web.renderer;

import javax.faces.render.FacesRenderer;

import com.sun.faces.renderkit.html_basic.SecretRenderer;


@FacesRenderer(componentFamily ="javax.faces.Input",
rendererType = "javax.faces.Secret")
public class CustomHtml5SecretRenderer extends CustomHtml5Renderer {

	public CustomHtml5SecretRenderer() {
		super(new SecretRenderer());
	}

}
