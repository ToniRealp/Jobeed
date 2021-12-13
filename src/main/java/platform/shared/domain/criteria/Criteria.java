package platform.shared.domain.criteria;

import java.util.Optional;

public final class Criteria {
    private final Filters filters;
    private final Orders orders;
    private final Optional<Integer> limit;
    private final Optional<Integer> offset;

    public Criteria(Filters filters, Orders orders) {
        this.filters = filters;
        this.orders = orders;
        this.limit = Optional.empty();
        this.offset = Optional.empty();
    }

    public Criteria(Filters filters, Orders orders, Integer limit, Integer offset) {
        this.filters = filters;
        this.orders = orders;
        this.limit = Optional.of(limit);
        this.offset = Optional.of(offset);
    }

    public Filters filters() {
        return filters;
    }

    public Orders orders() {
        return orders;
    }

    public Integer limit() {
        return limit.orElse(0);
    }

    public Integer offset() {
        return offset.orElse(0);
    }

    public boolean hasFilters() {
        return filters.filters().size() > 0;
    }

    public boolean hasOrders() {
        return orders.orders().size() > 0;
    }

    public boolean hasLimit() {
        return limit.isPresent() && limit.get() > 0;
    }

    public boolean hasOffset() {
        return offset.isPresent() && offset.get() > 0;
    }
}
