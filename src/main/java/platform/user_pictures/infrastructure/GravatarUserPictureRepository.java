package platform.user_pictures.infrastructure;

import platform.user_pictures.domain.UserPictureRepository;

import java.security.MessageDigest;

public class GravatarUserPictureRepository implements UserPictureRepository {
    @Override
    public String findByEmail(String email) {
        return "https://www.gravatar.com/avatar/" + md5(email);
    }

    private String hex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }

    private String md5(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("CP1252")));
        } catch (Exception e) {
            return null;
        }
    }
}
