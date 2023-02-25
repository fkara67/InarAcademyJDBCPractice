package Questions;

import java.sql.*;

public class Question3 {
    // 14- What is the name, surname and amount paid by the customers who have made the highest
    //payment so far?

    public static void answer() throws SQLException {
        // 1- Get a connection to DB
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "527600";
        Connection connection = DriverManager.getConnection(dbURL, username, password);

        // 2- create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // 3- execute a query
        String query = "SELECT name\n" +
                "FROM category c\n" +
                "JOIN film_category fc ON c.category_id = fc.category_id\n" +
                "JOIN inventory i ON fc.film_id = i.film_id\n" +
                "JOIN store s ON i.store_id = s.store_id\n" +
                "WHERE s.store_id = 1\n" +
                "GROUP BY name\n" +
                "ORDER BY COUNT(*) DESC\n" +
                "LIMIT 5";
        ResultSet resultSet = statement.executeQuery(query);

        // 4- process the resultSet
        int count = 0;
        while (resultSet.next()) {
            System.out.println(++count + " " + resultSet.getObject("name"));
        }
    }
}
