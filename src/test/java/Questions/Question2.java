package Questions;

import java.sql.*;

public class Question2 {
    // 2-Find the staff’s name, username and password who is working in the store that sells “Glass Dying” film

    public static void answer() throws SQLException{
        //1- Get a connection to DB
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "527600";
        Connection connection = DriverManager.getConnection(dbURL,username,password);

        // 2- create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // 3- execute a query
        String query = "SELECT DISTINCT CONCAT(staff.first_name, ' ',staff.last_name) AS full_name,username,password\n" +
                "FROM staff\n" +
                "JOIN store ON staff.store_id = store.store_id\n" +
                "JOIN inventory ON store.store_id = inventory.store_id\n" +
                "JOIN film ON inventory.film_id = film.film_id\n" +
                "WHERE title = 'Glass Dying'\n";
        ResultSet resultSet = statement.executeQuery(query);

        // 4- process the resultSet
        int count = 1;
        while (resultSet.next()) {
            System.out.println(count++ + " " + resultSet.getObject("full_name") +
                    " " + resultSet.getObject("username") +
                    " " + resultSet.getObject("password"));
        }
    }
}
