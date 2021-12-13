package platform.shared.infrastructure;

import java.sql.*;

public class MySqlConnection {

    private final Connection connection;

    public MySqlConnection() throws Exception {
        String user = "root";
        String password = "password";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://mysql/jobeed?user=" + user + "&password=" + password);
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

}
