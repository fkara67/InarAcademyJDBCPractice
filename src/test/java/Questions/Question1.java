package Questions;

import java.sql.*;

public class Question1 {
    public static void answer() throws SQLException {

        // 1- Get a connection to DB
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "527600";
        Connection connection = DriverManager.getConnection(dbURL,username,password);

        // 2- create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // 3- execute a query
        String query = "SELECT DISTINCT CONCAT(c.first_name, ' ',c.last_name) AS full_name,rental_date\n" +
                "FROM film_actor fa\n" +
                "JOIN actor a ON fa.actor_id = a.actor_id\n" +
                "JOIN film f ON fa.film_id = f.film_id\n" +
                "JOIN inventory i ON f.film_id = i.film_id\n" +
                "JOIN customer c ON i.store_id = c.store_id\n" +
                "JOIN rental r ON c.customer_id = r.customer_id\n" +
                "WHERE a.first_name = 'Ed' AND a.last_name = 'Chase'\n" +
                "ORDER BY rental_date DESC\n" +
                "LIMIT 3";
        ResultSet resultSet = statement.executeQuery(query);

        int count = 1;
        // 4- process the result
        while (resultSet.next()) {
            System.out.println(count++ + " " + resultSet.getString("full_name")
                    + " " + resultSet.getString("rental_date"));
        }
    }
}
