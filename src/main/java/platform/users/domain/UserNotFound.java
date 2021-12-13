package platform.users.domain;

import platform.shared.domain.ValueObjectException;

public class UserNotFound extends ValueObjectException {
    public UserNotFound() {
        super("User not found");
    }
}
