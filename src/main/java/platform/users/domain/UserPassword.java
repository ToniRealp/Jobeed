package platform.users.domain;

import platform.shared.domain.StringValueObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserPassword extends StringValueObject {

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", Pattern.CASE_INSENSITIVE);

    private static boolean isValidPassword(String email) {
        Matcher matcher = PASSWORD_PATTERN.matcher(email);
        return matcher.find();
    }

    public UserPassword(String password) throws InvalidPassword {
        super(password);
        if (!isValidPassword(password)) {
            throw new InvalidPassword();
        }
    }

}
