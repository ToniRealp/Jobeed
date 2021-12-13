package platform.user_pictures.application.find;

import platform.user_pictures.domain.UserPictureRepository;

public class UserPictureFinder {
    private final UserPictureRepository repository;

    public UserPictureFinder(UserPictureRepository repository) {
        this.repository = repository;
    }

    public String findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
