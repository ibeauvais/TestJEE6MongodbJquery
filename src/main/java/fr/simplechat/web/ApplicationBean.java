package fr.simplechat.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.google.common.collect.ImmutableMap;


@Named
@ApplicationScoped
public class ApplicationBean {
	
	//private static final Logger log=LoggerFactory.getLogger(ApplicationBean.class);
	
	private static final Map<String,String> menu= new ImmutableMap.Builder<String, String>()
			.put("/index.xhtml",MessagesTools.getMessage("menu.home"))
			.put("/signin.xhtml",MessagesTools.getMessage("menu.registration"))
			.build();
	
	
	public List<Entry<String,String>> getMenu(){
		return new ArrayList<Entry<String,String>>(menu.entrySet());
	}

}
