package platform.shared.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UUID extends StringValueObject {

    public static final Pattern UUID_PATTERN = Pattern.compile("^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$", Pattern.CASE_INSENSITIVE);

    public UUID(String id) throws InvalidUUID {
        super(id);
        if (id != null && !isValidUuid(id)) {
            throw new InvalidUUID();
        }
    }

    private static boolean isValidUuid(String uuid) {
        Matcher matcher = UUID_PATTERN.matcher(uuid);
        return matcher.find();
    }

}
