package platform.shared.infrastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import platform.shared.domain.criteria.*;

import java.util.Arrays;


public class MySqlCriteriaAdapterTest {
    MySqlCriteriaAdapter adapter = new MySqlCriteriaAdapter();

    @Test
    public void whereInQueryIsCreatedCorrectly() {
        Criteria criteria = new Criteria(new Filters(Filter.by("authorId", FilterOperator.IN, Arrays.asList("A", "B"))), Orders.none());
        String query = adapter.adapt(criteria, "tweets");
        assertEquals(query, "SELECT * FROM tweets WHERE authorId IN ('A','B')");
    }

    @Test
    public void orderQueryIsCreatedCorrectly() {
        Criteria criteria = new Criteria(Filters.none(), new Orders(Order.desc("createdOn"), Order.asc("likeCount")));
        String query = adapter.adapt(criteria, "tweets");
        assertEquals(query, "SELECT * FROM tweets ORDER BY createdOn DESC, likeCount ASC");
    }
}
