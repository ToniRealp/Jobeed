package platform.user_pictures.domain;

public interface UserPictureRepository {
    String findByEmail(String email);
}
