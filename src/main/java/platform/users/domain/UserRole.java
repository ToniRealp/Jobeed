package platform.users.domain;

public enum UserRole {
    ANONYMOUS("ANONYMOUS"),
    REGISTERED("REGISTERED"),
    ADMIN("ADMIN");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}
