package org.cassandra;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.cassandra.connector.CassandraClusterConnector;
import org.cassandra.schema.KeyspaceRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CassKeyspaceTests {

    CassandraClusterConnector dbClient;
    private KeyspaceRepository schemaRepository;
    private Session session;

    @Before
    public void connect() {
        dbClient = new CassandraClusterConnector();
        dbClient.connect("127.0.0.1", 9142);
        this.session = dbClient.getSession();
        schemaRepository = new KeyspaceRepository(session);
    }

    @Test
    public void whenCreatingAKeyspace_thenCreated() {
        String keyspaceName = "library";
        schemaRepository.createKeyspace(keyspaceName, "SimpleStrategy", 1);

        ResultSet result =
                session.execute("SELECT * FROM system_schema.keyspaces;");

        List<String> matchedKeyspaces = result.all()
                .stream()
                .filter(r -> r.getString(0).equals(keyspaceName.toLowerCase()))
                .map(r -> r.getString(0)).toList();

        assertEquals(matchedKeyspaces.size(), 1);
        assertEquals(matchedKeyspaces.get(0), keyspaceName.toLowerCase());
    }

    @After
    public void cleanup() {
        dbClient.close();
    }
}
