package platform.users.application.find;


import platform.shared.domain.query.Query;

public class FindUserByIdQuery implements Query {
    private final String id;

    public FindUserByIdQuery(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
