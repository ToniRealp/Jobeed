package platform.shared.domain.criteria;



public final class FilterValue {
    private final Object value;

    public FilterValue(Object value) {
        this.value = value;
    }

    public Object value() {
        return value;
    }
}

