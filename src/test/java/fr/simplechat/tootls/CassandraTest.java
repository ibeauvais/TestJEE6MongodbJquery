package fr.simplechat.tootls;

import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.simplechat.config.ConfigProperty;
import fr.simplechat.config.ConfigPropertyLoader;
import fr.simplechat.model.User;
import fr.simplechat.repository.UserRepository;
import fr.simplechat.repository.cassandra.CassandraConfiguration;
import fr.simplechat.repository.cassandra.UserRepositoryCassandraImpl;
import fr.simplechat.services.UserService;

@RunWith(Arquillian.class)
public abstract class CassandraTest {
	
	
	private static final Logger log=LoggerFactory.getLogger(CassandraTest.class);
	
	@Deployment
    public static JavaArchive createDeployment()  {
		initCassandra();
	
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(ConfigProperty.class,ConfigPropertyLoader.class,CassandraConfiguration.class,UserRepository.class,
            		UserRepositoryCassandraImpl.class, User.class,
            		UserService.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
           ;


   }
 @After
 public void close(){
	 log.debug("close cassandra ");
	 EmbeddedCassandraServerHelper.stopEmbeddedCassandra();
 }

 public  static void initCassandra() {
	 try {
	 log.debug("init cassandra ");
	 EmbeddedCassandraServerHelper.startEmbeddedCassandra();
	 } catch (Exception e) {
			log.error("error",e);
		} 

 }

}
