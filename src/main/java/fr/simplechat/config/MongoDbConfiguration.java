package fr.simplechat.config;

import java.net.UnknownHostException;

import javax.enterprise.inject.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDbConfiguration {

	private static final Logger log=LoggerFactory.getLogger(MongoDbConfiguration.class);

	
	
	private Mongo initMongo(String host) throws UnknownHostException, MongoException{
		log.debug(">> initMongo");
		return new Mongo(host);
		
	}
	
	
	private Morphia initMorphia(){
		log.debug(">> initMorphia");
		Morphia morphia=new Morphia();
		morphia.mapPackage("fr.simplechat.model");
		
		return morphia;
		
	}
	@Produces
	public Datastore  initDatastore (@ConfigProperty("mongodb.host") String host,@ConfigProperty("mongodb.dbName")String dbName){
		log.debug(">> initDatastore");
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		try {
			Datastore ds =initMorphia().createDatastore(initMongo(host), dbName);
			ds.ensureIndexes();
			return ds;
		} catch (Exception e) {
			log.error("Error initDataStor with host="+host+" dbName="+dbName,e);
			return null;
		} 
		
	}
}
