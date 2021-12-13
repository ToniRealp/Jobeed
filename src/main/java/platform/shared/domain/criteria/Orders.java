package platform.shared.domain.criteria;

import java.util.*;

public final class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public Orders(Order... orders) {
        this.orders = Arrays.asList(orders);
    }

    public static Orders from(Order... orders) {
        return new Orders(Arrays.asList(orders));
    }

    public static Orders none() {
        return new Orders(Collections.emptyList());
    }

    public List<Order> orders() {
        return orders;
    }
}
