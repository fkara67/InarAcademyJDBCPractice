package Questions;

import java.sql.*;

public class Question2 {
    // 13- What are the top 3 most rented movies which have a category of ‘Action’?

    public static void answer() throws SQLException{
        //1- Get a connection to DB
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "527600";
        Connection connection = DriverManager.getConnection(dbURL,username,password);

        // 2- create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // 3- execute a query
        String query = "SELECT title,COUNT(*) AS rent_count\n" +
                "FROM rental\n" +
                "INNER JOIN inventory ON rental.inventory_id = inventory.inventory_id\n" +
                "INNER JOIN film_category ON inventory.film_id = film_category.film_id\n" +
                "INNER JOIN category ON film_category.category_id = category.category_id\n" +
                "INNER JOIN film ON inventory.film_id = film.film_id\n" +
                "WHERE name = 'Action'\n" +
                "GROUP BY title\n" +
                "ORDER BY COUNT(rental_id) DESC\n" +
                "LIMIT 3\n";
        ResultSet resultSet = statement.executeQuery(query);

        // 4- process the resultSet
        int count = 1;
        while (resultSet.next()) {
            System.out.println(count++ + " " + resultSet.getObject("title") +
                    " " + resultSet.getObject("rent_count"));
        }
    }
}
