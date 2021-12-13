package platform.shared.domain;

public class ValueObjectException extends RuntimeException {
    public ValueObjectException(String value) {
        super(value);
    }
}
