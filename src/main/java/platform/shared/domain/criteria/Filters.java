package platform.shared.domain.criteria;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Filters {
    private final List<Filter> filters;

    public Filters(List<Filter> filters) {
        this.filters = filters;
    }

    public Filters(Filter... filters) {
        this.filters = Arrays.asList(filters);
    }

    public static Filters none() {
        return new Filters(Collections.emptyList());
    }

    public List<Filter> filters() {
        return filters;
    }
}
