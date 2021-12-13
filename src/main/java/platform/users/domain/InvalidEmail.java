package platform.users.domain;

import platform.shared.domain.ValueObjectException;

public class InvalidEmail extends ValueObjectException {
    public InvalidEmail(String email) {
        super(String.format("Invalid email '%s'", email));
    }
}
