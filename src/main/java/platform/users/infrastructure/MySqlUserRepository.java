package platform.users.infrastructure;

import platform.shared.domain.UserId;
import platform.shared.domain.criteria.Criteria;
import platform.shared.infrastructure.MySqlConnection;
import platform.shared.infrastructure.MySqlCriteriaAdapter;
import platform.users.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlUserRepository implements UserRepository {
    private final MySqlConnection connection;
    private final MySqlCriteriaAdapter adapter;

    public MySqlUserRepository(MySqlConnection connection, MySqlCriteriaAdapter adapter) {
        this.connection = connection;
        this.adapter = adapter;
    }

    @Override
    public void save(User user) throws Exception {
        String query = "INSERT INTO users (id,name,email,password,birthday,gender,employment,picture,role) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.id().value());
        statement.setString(2, user.name().value());
        statement.setString(3, user.email().value());
        statement.setString(4, user.password().value());
        statement.setDate(5, new Date(user.birthday().getTime()));
        statement.setString(6, user.gender().toString());
        statement.setString(7, user.employment().toString());
        statement.setString(8, user.picture().value());
        statement.setString(9, user.role().value());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void update(UserId id, UserName name, UserEmail email, UserGender gender, UserEmployment employment) throws Exception {
        String query = "UPDATE users SET name = ?, email = ?, gender = ?, employment = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name.value());
        statement.setString(2, email.value());
        statement.setString(3, gender.toString());
        statement.setString(4, employment.toString());
        statement.setString(5, id.value());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void delete(UserId id) throws Exception {
        String query = "DELETE FROM users WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id.value());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public User findByEmail(UserEmail email) throws Exception {
        String query = "SELECT * FROM users WHERE email = ? LIMIT 1";
        return executeSqlFind(query, email.value());
    }

    @Override
    public User findById(UserId id) throws Exception {
        String query = "SELECT * FROM users WHERE id = ? LIMIT 1";
        return executeSqlFind(query, id.value());
    }

    private User executeSqlFind(String query, String value) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, value);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            throw new UserNotFound();
        }
        User user = deserializeUser(resultSet);
        statement.close();
        return user;
    }

    @Override
    public Optional<User> searchByEmail(UserEmail email) {
        try {
            String query = "SELECT * FROM users WHERE email = ? LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email.value());

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }

            User user = deserializeUser(resultSet);
            statement.close();
            return Optional.of(user);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> matching(Criteria criteria) throws Exception {
        String query = adapter.adapt(criteria, "users");
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(deserializeUser(resultSet));
        }
        statement.close();
        return users;
    }

    private User deserializeUser(ResultSet resultSet) throws SQLException {
        UserId id = new UserId(resultSet.getString("id"));
        UserName name = new UserName(resultSet.getString("name"));
        UserEmail email = new UserEmail(resultSet.getString("email"));
        UserPassword password = new UserPassword(resultSet.getString("password"));
        Date birthday = resultSet.getDate("birthday");
        UserGender gender = UserGender.valueOf(resultSet.getString("gender"));
        UserEmployment employment = UserEmployment.valueOf(resultSet.getString("employment"));
        UserPicture picture = new UserPicture(resultSet.getString("picture"));
        UserRole role = UserRole.valueOf(resultSet.getString("role"));
        return new User(id, name, email, password, picture, birthday, gender, employment, role);
    }

}
