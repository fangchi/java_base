package com.fc.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;

import java.sql.*;

/**
 * @author fangchi
 * @date 2019/7/17 16:34
 */
public class DBTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Connecting to the cluster.
        Ignite ignite = Ignition.start();
        // Register JDBC driver.
        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");

// Open JDBC connection.
        Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1/");

// Create database tables.
        try (Statement stmt = conn.createStatement()) {

            // Create table based on REPLICATED template.
            stmt.executeUpdate("CREATE TABLE City (" +
                    " id LONG PRIMARY KEY, name VARCHAR) " +
                    " WITH \"template=replicated\"");

            // Create table based on PARTITIONED template with one backup.
            stmt.executeUpdate("CREATE TABLE Person (" +
                    " id LONG, name VARCHAR, city_id LONG, " +
                    " PRIMARY KEY (id, city_id)) " +
                    " WITH \"backups=1, affinityKey=city_id\"");

            // Create an index on the City table.
            stmt.executeUpdate("CREATE INDEX idx_city_name ON City (name)");

            // Create an index on the Person table.
            stmt.executeUpdate("CREATE INDEX idx_person_name ON Person (name)");

            try (PreparedStatement stmt2 =
                         conn.prepareStatement("INSERT INTO City (id, name) VALUES (?, ?)")) {

                stmt2.setLong(1, 1L);
                stmt2.setString(2, "Forest Hill");
                stmt2.executeUpdate();

                stmt2.setLong(1, 2L);
                stmt2.setString(2, "Denver");
                stmt2.executeUpdate();

                stmt2.setLong(1, 3L);
                stmt2.setString(2, "St. Petersburg");
                stmt2.executeUpdate();
            }

            // Populate Person table
            try (PreparedStatement stmt3 =
                         conn.prepareStatement("INSERT INTO Person (id, name, city_id) VALUES (?, ?, ?)")) {

                stmt3.setLong(1, 1L);
                stmt3.setString(2, "John Doe");
                stmt3.setLong(3, 3L);
                stmt3.executeUpdate();

                stmt3.setLong(1, 2L);
                stmt3.setString(2, "Jane Roe");
                stmt3.setLong(3, 2L);
                stmt3.executeUpdate();

                stmt3.setLong(1, 3L);
                stmt3.setString(2, "Mary Major");
                stmt3.setLong(3, 1L);
                stmt3.executeUpdate();

                stmt3.setLong(1, 4L);
                stmt3.setString(2, "Richard Miles");
                stmt3.setLong(3, 2L);
                stmt3.executeUpdate();
            }

            // Get data
            try (Statement stmt4 = conn.createStatement()) {
                try (ResultSet rs =
                             stmt4.executeQuery("SELECT p.name, c.name " +
                                     " FROM Person p, City c " +
                                     " WHERE p.city_id = c.id")) {

                    while (rs.next())
                        System.out.println(rs.getString(1) + ", " + rs.getString(2));
                }
            }
        }
    }
}
