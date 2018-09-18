package api;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfirguration {

    @Bean
    public ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    @Bean
    public Cluster cluster(@Value("${cassandra.cluster}") String cluster,
                           @Value("${cassandra.addresses}") String[] addresses,
                           @Value("${cassandra.port}") Integer port,
                           @Value("${cassandra.username}") String username,
                           @Value("${cassandra.password}") String password) {
        return Cluster.builder()
                .withClusterName(cluster)
                .addContactPoints(addresses)
                .withPort(port)
                .withCredentials(username, password)
                .build();
    }

    @Bean
    public MappingManager mappingManager(Cluster cluster, @Value("${cassandra.keyspace}") String keyspace) {
        Session session = cluster.connect(keyspace);
        return new MappingManager(session);
    }
}
