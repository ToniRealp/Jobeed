package platform.followed_users.infraestructure;

import platform.followed_users.domain.FollowedUser;
import platform.followed_users.domain.FollowedUsersRepository;
import platform.shared.domain.UserId;
import platform.shared.infrastructure.MySqlConnection;
import platform.shared.infrastructure.MySqlCriteriaAdapter;
import platform.users.domain.UserEmail;
import platform.users.domain.UserEmployment;
import platform.users.domain.UserName;
import platform.users.domain.UserPicture;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlFollowedUsersRepository implements FollowedUsersRepository {

    private final MySqlConnection connection;
    private final MySqlCriteriaAdapter adapter;

    public MySqlFollowedUsersRepository(MySqlConnection connection, MySqlCriteriaAdapter adapter) {
        this.connection = connection;
        this.adapter = adapter;
    }

    @Override
    public void save(UserId followerId, UserId followedId, UserEmail email, UserName name, UserPicture picture, java.util.Date birthday, UserEmployment employment) throws Exception {
        try {
            String query = "INSERT INTO followed_users (followerId, followedId, email, name, picture, birthday, employment) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, followerId.value());
            statement.setString(2, followedId.value());
            statement.setString(3, email.value());
            statement.setString(4, name.value());
            statement.setString(5, picture.value());
            statement.setString(6, birthday.toString());
            statement.setString(7, employment.toString());
            statement.executeUpdate();

            statement.close();
        } catch (Exception exception) {
            throw new Exception("MySQL failed");
        }
    }

    public void delete(UserId followerId, UserId followedId) throws Exception {
        try {
            String query = "DELETE FROM followed_users WHERE followerId = ? AND followedId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, followerId.value());
            statement.setString(2, followedId.value());
            statement.executeUpdate();

            statement.close();
        } catch (Exception exception) {
            throw new Exception("MySQL failed");
        }
    }

    public Optional<FollowedUser> search(UserId followerId, UserId followedId) throws Exception {
        String query = "SELECT * FROM followed_users WHERE followerId = ? AND followedId = ? LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, followerId.value());
        statement.setString(2, followedId.value());
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()){
            return Optional.empty();
        }
        FollowedUser followedUser = deserializeFollowedUser(resultSet);
        return Optional.of(followedUser);
    }

    @Override
    public List<FollowedUser> searchAll(UserId id) throws Exception {
        String query = "SELECT * FROM followed_users WHERE followerId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id.value());
        ResultSet resultSet = statement.executeQuery();

        List<FollowedUser> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(deserializeFollowedUser(resultSet));
        }
        statement.close();
        return users;

    }

    private FollowedUser deserializeFollowedUser(ResultSet resultSet) throws SQLException {
        UserId id = new UserId(resultSet.getString("followedId"));
        UserName name = new UserName(resultSet.getString("name"));
        UserEmail email = new UserEmail(resultSet.getString("email"));
        Date birthday = resultSet.getDate("birthday");
        UserEmployment employment = UserEmployment.valueOf(resultSet.getString("employment"));
        UserPicture picture = new UserPicture(resultSet.getString("picture"));
        return new FollowedUser(id, name, email, picture, birthday, employment);
    }
}
