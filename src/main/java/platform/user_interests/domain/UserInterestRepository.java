package platform.user_interests.domain;

import platform.shared.domain.criteria.Criteria;

import java.util.List;

public interface UserInterestRepository {
    void saveAll(List<UserInterest> userInterests) throws Exception;
    List<UserInterest> matching(Criteria criteria);
}
