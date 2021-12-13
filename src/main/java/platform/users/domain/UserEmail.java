package platform.users.domain;

import platform.shared.domain.StringValueObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserEmail extends StringValueObject {

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public UserEmail(String email) throws InvalidEmail {
        super(email);
        if (!isValidEmail(email)) {
            throw new InvalidEmail(email);
        }
    }

    private static boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.find();
    }

}
