package platform.shared.domain.criteria;

public enum OrderType {
    ASC("ASC"),
    DESC("DESC");

    private final String type;

    OrderType(String type) {
        this.type = type;
    }

    public String value() {
        return type;
    }
}

