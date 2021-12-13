package platform.interests.application;

import platform.interests.domain.Interest;
import platform.interests.domain.InterestId;
import platform.interests.domain.InterestRepository;

import java.util.List;

public class InterestSearcher {

    private final InterestRepository interestRepository;

    public InterestSearcher(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public List<Interest> searchAll() {
        return interestRepository.searchAll();
    }

    public List<Interest> searchAllIn(List<InterestId> ids) {
        return interestRepository.searchAllIn(ids);
    }

}
