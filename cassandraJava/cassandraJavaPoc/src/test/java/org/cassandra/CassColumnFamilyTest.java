package org.cassandra;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.cassandra.connector.CassandraClusterConnector;
import org.cassandra.schema.BookRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CassColumnFamilyTest {

    CassandraClusterConnector dbConnector;
    private Session session;
    private BookRepository bookRepo;

    @Before
    public void connectSession() {
        dbConnector = new CassandraClusterConnector();
        dbConnector.connect("127.0.0.1", 9042);
        session = dbConnector.getSession();
        bookRepo = new BookRepository(session);
    }

    /**
     * TODO: Fix Column order
     */
    @Test
    public void testBookColumnFamCreation() {
        bookRepo.createTable();
        ResultSet queryResult = session.execute("SELECT * FROM library.books;");
        List<String> columnNames = queryResult.getColumnDefinitions()
                .asList().stream().map(cl -> cl.getName())
                .toList();
        Assert.assertEquals(columnNames.size(), 3);
    }

    @Test
    public void testAddRemoveColumn() {
        bookRepo.createTable();

        bookRepo.addBookColumn("publisher", "text");

        ResultSet result = session.execute(
                "SELECT * FROM library.books" + ";");

        boolean columnExists = result.getColumnDefinitions().asList().stream()
                .anyMatch(cl -> cl.getName().equals("publisher"));

        Assert.assertTrue(columnExists);

        //drop created column
        bookRepo.dropBookColumn("publisher");
        result = session.execute(
                "SELECT * FROM library.books" + ";");

        columnExists = result.getColumnDefinitions().asList().stream()
                .anyMatch(cl -> cl.getName().equals("publisher"));

        Assert.assertFalse(columnExists);
    }

    @After
    public void cleanup() {
        dbConnector.close();
    }

}
