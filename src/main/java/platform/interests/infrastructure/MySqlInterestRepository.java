package platform.interests.infrastructure;

import platform.interests.domain.Interest;
import platform.interests.domain.InterestId;
import platform.interests.domain.InterestRepository;
import platform.shared.infrastructure.MySqlConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlInterestRepository implements InterestRepository {

    private final MySqlConnection connection;

    public MySqlInterestRepository(MySqlConnection connection) {
        this.connection = connection;
    }

    @Override
    public List<Interest> searchAll() {
        try {
            String            query     = "SELECT * FROM interests";
            PreparedStatement statement = connection.prepareStatement(query);
            return deserializeInterests(statement);
        } catch (SQLException throwables) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Interest> searchAllIn(List<InterestId> ids) {
        try {
            StringBuilder parameters = new StringBuilder("(");
            for (int i = 0; i < ids.size(); i++) {
                parameters.append("?,");
            }
            parameters = new StringBuilder(parameters.substring(0, parameters.length() - 1));
            parameters.append(")");

            String            query     = "SELECT * FROM interests WHERE id IN " + parameters;
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < ids.size(); i++) {
                statement.setString(i + 1, ids.get(i).value());
            }

            return deserializeInterests(statement);
        } catch (SQLException throwables) {
            return new ArrayList<>();
        }
    }

    private List<Interest> deserializeInterests(PreparedStatement statement) throws SQLException {
        ResultSet      resultSet = statement.executeQuery();
        List<Interest> interests = new ArrayList<>();
        while (resultSet.next()) {
            InterestId id       = new InterestId(resultSet.getString(1));
            String     name     = resultSet.getString(2);
            Interest   interest = new Interest(id, name);
            interests.add(interest);
        }
        statement.close();
        return interests;
    }

}
