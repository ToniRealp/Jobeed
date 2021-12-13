package platform.users.application.find;

import platform.shared.domain.query.Query;


public class FindUserByEmailQuery implements Query {
    private final String email;

    public FindUserByEmailQuery(String email) {
        this.email = email;
    }

    public String email() {
        return this.email;
    }
}
