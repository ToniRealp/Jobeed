package platform.users.domain;

import platform.shared.domain.ValueObjectException;

public class InvalidCredentials extends ValueObjectException {
    public InvalidCredentials() {
        super("Invalid email or password");
    }
}
