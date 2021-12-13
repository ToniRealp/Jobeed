package platform.shared.domain;

public class UserId extends UUID {
    public UserId(String id) throws InvalidUUID {
        super(id);
    }
}
