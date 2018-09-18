package api;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CassandraHealthIndicator extends AbstractHealthIndicator {

    private final Session session;

    public CassandraHealthIndicator(Cluster cluster) {
        this.session = Objects.requireNonNull(cluster.newSession());
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        Select select = QueryBuilder.select("release_version").from("system", "local");
        ResultSet result = session.execute(select);
        if (result.isExhausted()) {
            builder.up();
            return;
        }
        String version = result.one().getString(0);
        builder.up().withDetail("version", version);
    }
}
