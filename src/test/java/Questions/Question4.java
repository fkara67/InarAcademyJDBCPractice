package Questions;

import java.sql.*;

public class Question4 {
    // 4-Which countries have made higher payments than 800$?(with their payment amount in desc order)

    public static void answer() throws SQLException {
        // 1- Get a connection to DB
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "527600";
        Connection connection = DriverManager.getConnection(dbURL,username,password);

        // 2- create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // 3- execute a query
        String query = "SELECT country\n" +
                "FROM country\n" +
                "JOIN city ON city.country_id = country.country_id\n" +
                "JOIN address ON address.city_id = city.city_id\n" +
                "JOIN customer ON customer.address_id = address.address_id\n" +
                "JOIN payment ON payment.customer_id = customer.customer_id\n" +
                "GROUP BY country\n" +
                "HAVING SUM(amount) > 800\n" +
                "ORDER BY SUM(amount) DESC\n";
        ResultSet resultSet = statement.executeQuery(query);

        // 4- process the resultSet
        int count = 1;
        while (resultSet.next()) {
            System.out.println(count++ + "  " + resultSet.getObject("country"));
        }
    }
}
