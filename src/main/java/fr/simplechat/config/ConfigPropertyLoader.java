package fr.simplechat.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigPropertyLoader {

	private static final Logger log=LoggerFactory.getLogger(ConfigPropertyLoader.class);
	private static final String CONFIG_FILE_NAME="/simplechat.properties";
	
	
	 private static Properties properties= loadProperties();;

		private static Properties loadProperties() {
			Properties properties = new Properties();
	        try {
	        	InputStream input=ConfigPropertyLoader.class.getResourceAsStream(CONFIG_FILE_NAME);
	        	
	        	if(input==null){
	        		throw new FileNotFoundException(CONFIG_FILE_NAME+" not found");
	        	}
	        		
	        	properties.load(input);
	            return properties;
	        } catch (IOException e) {
	        	log.error("error load properties",e);
	        	return null;
	        }
		}
	
	@Produces
    @ConfigProperty
    public static String produceConfigProperty(InjectionPoint ip) {
        String key = ip.getAnnotated().getAnnotation(ConfigProperty.class).value();

        return properties.getProperty(key);
    }

	
}
