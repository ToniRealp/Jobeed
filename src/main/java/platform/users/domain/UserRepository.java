package platform.users.domain;

import platform.shared.domain.UserId;
import platform.shared.domain.criteria.Criteria;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user) throws Exception;
    void update(UserId id, UserName name, UserEmail email, UserGender gender, UserEmployment employment) throws SQLException, Exception;
    void delete(UserId id) throws Exception;
    User findByEmail(UserEmail email) throws Exception;
    User findById(UserId id) throws Exception;
    Optional<User> searchByEmail(UserEmail email);
    List<User> matching(Criteria criteria) throws Exception;

}
