package org.cassandra.schema;

import com.datastax.driver.core.Session;

public class BookRepository {
    private static final String KEYSPACE = "library";
    private static final String TABLE_NAME = "books";

    private Session session;

    public BookRepository(Session session) {
        this.session = session;
    }

    public void createTable() {
        StringBuilder queryBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(KEYSPACE + "." + TABLE_NAME).append("(")
                .append("id uuid PRIMARY KEY, ")
                .append("title text, ")
                .append("subject text);");
        session.execute(queryBuilder.toString());
    }

    public void addBookColumn(String columnName, String columnType) {
        StringBuilder addColQuery = new StringBuilder("ALTER TABLE ")
                .append(KEYSPACE + "." + TABLE_NAME).append(" ADD ")
                .append(columnName).append(" ")
                .append(columnType).append(";");

        String query = addColQuery.toString();
        session.execute(query);
    }

    public void dropBookColumn(String columnName) {
        StringBuilder removeColQuery = new StringBuilder("ALTER TABLE ")
                .append(KEYSPACE + "." + TABLE_NAME).append(" ")
                .append("DROP ").append(columnName);
        String query = removeColQuery.toString();
        session.execute(query);
    }

    public void deleteBookColumnFamily() {
        StringBuilder sb =
                new StringBuilder("DROP TABLE IF EXISTS ").append(KEYSPACE + "." + TABLE_NAME);

        String query = sb.toString();
        session.execute(query);
    }
}
