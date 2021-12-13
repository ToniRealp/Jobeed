package platform.shared.infrastructure;

import java.sql.*;
import java.util.Map;

public class MySqlConnection {

    private final Connection connection;

    public MySqlConnection() throws Exception {
        Map<String, String> env = System.getenv();
        String user = env.get("MYSQL_USER");
        String password = env.get("MYSQL_PWD");
        String dbURL = env.get("MYSQL_URL");
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:" + dbURL + "?user=" + user + "&password=" + password);
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

}
