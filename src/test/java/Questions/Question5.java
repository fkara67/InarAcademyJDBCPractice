package Questions;

import java.sql.*;

public class Question5 {

    public static void answer() throws SQLException {
        // 1- Get a connection to DB
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "527600";
        Connection connection = DriverManager.getConnection(dbURL,username,password);

        // 2- create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // 3- execute a query
        String query = "SELECT SUM(amount)\n" +
                "FROM inventory\n" +
                "JOIN rental ON rental.inventory_id = inventory.inventory_id\n" +
                "JOIN payment ON payment.rental_id = rental.rental_id\n" +
                "WHERE store_id = 2 AND rental.return_date IS NULL";
        ResultSet resultSet = statement.executeQuery(query);


        // 4- process the result
        int count = 1;
        while (resultSet.next()) {
            System.out.println(count++ + " " + resultSet.getString("sum"));
        }
    }

}
