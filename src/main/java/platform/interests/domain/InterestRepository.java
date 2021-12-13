package platform.interests.domain;


import java.util.List;

public interface InterestRepository {

    List<Interest> searchAll();

    List<Interest> searchAllIn(List<InterestId> ids);

}
