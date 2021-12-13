package platform.user_interests.infrastructure;

import platform.interests.domain.InterestId;
import platform.shared.domain.UserId;
import platform.shared.domain.criteria.Criteria;
import platform.shared.infrastructure.MySqlConnection;
import platform.shared.infrastructure.MySqlCriteriaAdapter;
import platform.user_interests.domain.UserInterest;
import platform.user_interests.domain.UserInterestRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserInterestRepository implements UserInterestRepository {
    private final MySqlConnection connection;
    private final MySqlCriteriaAdapter adapter;

    public MySqlUserInterestRepository(MySqlConnection connection, MySqlCriteriaAdapter adapter) {
        this.connection = connection;
        this.adapter = adapter;
    }

    @Override
    public void saveAll(List<UserInterest> userInterests) throws Exception {
        try {
            String            query     = "INSERT INTO users_interests (userId, interestId, interestName) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            userInterests.forEach(interest -> {
                try {
                    statement.setString(1, interest.userId().value());
                    statement.setString(2, interest.interestId().value());
                    statement.setString(3, interest.interestName());
                    statement.addBatch();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });

            statement.executeBatch();
            statement.close();
        } catch (Exception exception) {
            throw new Exception("MySQL failed");
        }
    }

    @Override
    public List<UserInterest> matching(Criteria criteria) {
        try {
            String query = adapter.adapt(criteria, "users_interests");
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<UserInterest> userInterests = new ArrayList<>();
            while (resultSet.next()) {
                userInterests.add(deserializeUserInterest(resultSet));
            }
            statement.close();
            return userInterests;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
    }

    private UserInterest deserializeUserInterest(ResultSet resultSet) throws SQLException {
        UserId userId = new UserId(resultSet.getString("userId"));
        InterestId interestId = new InterestId(resultSet.getString("interestId"));
        String interestName = resultSet.getString("interestName");
        return new UserInterest(userId, interestId, interestName);
    }
}
