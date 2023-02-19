package Questions;

import java.sql.*;

public class Question1 {
    public static void answer() throws SQLException {

        // 1- Get a connection to DB
        String dbURL = "jdbc:postgresql://localhost:5432/exercises";
        String username = "postgres";
        String password = "527600";
        Connection connection = DriverManager.getConnection(dbURL,username,password);

        // 2- create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // 3- execute a query
        String query = "SELECT TO_CHAR(joindate,'Month') AS month, COUNT(*)\n" +
                "FROM cd.members\n" +
                "GROUP BY TO_CHAR(joindate,'Month')\n" +
                "ORDER BY COUNT(*) DESC\n" +
                "LIMIT 1\n";
        ResultSet resultSet = statement.executeQuery(query);

        // 4- process the result
        while (resultSet.next()) {
            System.out.print(resultSet.getString("month") + " " + resultSet.getString("count"));
        }
    }
}
