package platform.interests.domain;


public class Interest {
    private final InterestId id;
    private final String name;

    public Interest(InterestId id, String name) {
        this.id = id;
        this.name = name;
    }

    public InterestId id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }
}
