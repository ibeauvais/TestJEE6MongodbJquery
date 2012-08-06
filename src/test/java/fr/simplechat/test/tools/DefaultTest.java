package fr.simplechat.test.tools;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import fr.simplechat.config.ConfigProperty;
import fr.simplechat.config.ConfigPropertyLoader;
import fr.simplechat.config.MongoDbConfiguration;
import fr.simplechat.model.User;
import fr.simplechat.repository.UserRepository;
import fr.simplechat.repository.mondodb.UserRepositoryImpl;
import fr.simplechat.services.UserService;


@RunWith(Arquillian.class)
public abstract class DefaultTest {

	
	
	

	@Deployment
    public static JavaArchive createDeployment()  {

        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(ConfigProperty.class,ConfigPropertyLoader.class,MongoDbConfiguration.class,UserRepository.class,
            		UserRepositoryImpl.class, User.class,
            		UserService.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
           ;


   }
}
