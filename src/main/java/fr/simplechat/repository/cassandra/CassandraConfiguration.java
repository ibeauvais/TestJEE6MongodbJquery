package fr.simplechat.repository.cassandra;

import javax.enterprise.inject.Produces;

import me.prettyprint.cassandra.model.ConfigurableConsistencyLevel;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.cassandra.service.ThriftCluster;
import me.prettyprint.cassandra.service.ThriftKsDef;
import me.prettyprint.hector.api.HConsistencyLevel;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.ddl.ColumnFamilyDefinition;
import me.prettyprint.hector.api.ddl.KeyspaceDefinition;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hom.EntityManagerImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.simplechat.config.ConfigProperty;





public class CassandraConfiguration {
	
	private static final Logger log=LoggerFactory.getLogger(CassandraConfiguration.class);
	
	
	@Produces
	public Keyspace keyspace(@ConfigProperty("cassandra.host")String host,@ConfigProperty("cassandra.cluter")String cluter, @ConfigProperty("cassandra.keyspace")String keyspace){
		log.debug("Create keyspace" );
		CassandraHostConfigurator cassandraHostConfigurator = new CassandraHostConfigurator(host);
        ThriftCluster cluster = new ThriftCluster(cluter, cassandraHostConfigurator);
        KeyspaceDefinition keyspaceDef = cluster.describeKeyspace(keyspace);
        ConfigurableConsistencyLevel consistencyLevelPolicy = new ConfigurableConsistencyLevel();
        consistencyLevelPolicy.setDefaultReadConsistencyLevel(HConsistencyLevel.ONE);
        
        if (keyspaceDef == null) {
            log.warn("Keyspace \"" + keyspace + "\" does not exist, creating it!");
            keyspaceDef = new ThriftKsDef(keyspace);
            cluster.addKeyspace(keyspaceDef, true);

            addColumnFamily(cluster, "User",keyspace);
       
        }
        
        
        return HFactory.createKeyspace(keyspace, cluster,consistencyLevelPolicy);
	}
	
	 private void addColumnFamily(ThriftCluster cluster, String cfName,String keyspace) {

	        

	        ColumnFamilyDefinition cfd =
	                HFactory.createColumnFamilyDefinition(keyspace, cfName);
	        cluster.addColumnFamily(cfd);
	    }
	@Produces
    public EntityManagerImpl entityManager(Keyspace keyspace) {
		log.debug("Create entityManager" );
        return new EntityManagerImpl(keyspace, "fr.simplechat.model");
    }
}
