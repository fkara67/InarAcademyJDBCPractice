package utilities;









import java.sql.*;
import java.util.*;

public class DBUtils {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void createConnection() {
        try{
            connection = DriverManager.getConnection(
                    Config.getProperty("dbURL"),
                    Config.getProperty("username"),
                    Config.getProperty("password"));
            System.out.println("DB Connection is created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void destroy() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

    public static List<Map<String,Object>> getQueryResultMap(String query) {
        executeQuery(query);
        List<Map<String,Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;

        try {
            rsmd = resultSet.getMetaData();

            while (resultSet.next()) {
                Map<String,Object> colNameValueMap = new HashMap<>();

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i),resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList;
    }
}
