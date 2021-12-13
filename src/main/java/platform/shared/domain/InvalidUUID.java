package platform.shared.domain;

public class InvalidUUID extends ValueObjectException {
    public InvalidUUID() {
        super("Invalid UUID");
    }
}
