package platform.users.domain;

import platform.shared.domain.ValueObjectException;

public class InvalidPassword extends ValueObjectException {
    public InvalidPassword() {
        super("Invalid password, check that it contains at least 1 capital, number, special character and a minimum size of 8");
    }
}
