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
        String query = "SELECT CONCAT(first_name,' ',last_name) AS fullname, amount\n" +
                "FROM customer\n" +
                "INNER JOIN payment ON customer.customer_id = payment.customer_id\n" +
                "WHERE amount = (SELECT MAX(amount) FROM payment)\n" +
                "ORDER BY amount DESC\n";
        ResultSet resultSet = statement.executeQuery(query);

        // 4- process the resultSet
        int count = 0;
        while (resultSet.next()) {
            System.out.printf("%d %-10s%10s\n",
                    ++count,resultSet.getObject("fullname"),resultSet.getObject("amount"));
        }
    }
}
