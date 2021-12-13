package platform.shared.domain;

import java.util.Objects;

public class IntValueObject {

    protected Integer value;

    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntValueObject)) return false;
        IntValueObject that = (IntValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
