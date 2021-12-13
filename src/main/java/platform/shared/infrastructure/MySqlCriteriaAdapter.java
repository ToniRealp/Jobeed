package platform.shared.infrastructure;

import org.apache.commons.lang.NotImplementedException;
import platform.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.stream.Collectors;

public class MySqlCriteriaAdapter {
    public String adapt(Criteria criteria, String table) {
        String query = "SELECT * FROM " + table;

        if (criteria.hasFilters()) {
            String whereClauses = criteria.filters().filters().stream().map(filter -> {
                String value = convertValueToSql(filter.value().value());
                switch (filter.operator()) {
                    case EQUAL:
                        if (filter.value().value() == null) {
                            return filter.field().value() + " IS NULL";
                        }
                        return filter.field().value() + " = " + value;
                    case NOT_EQUAL:
                        if (filter.value().value() == null) {
                            return filter.field().value() + " IS NOT NULL";
                        }
                        return filter.field().value() + " != " + value;
                    case IN:
                        return filter.field().value() + " IN " + "(" + value + ")";
                    default:
                        throw new NotImplementedException(String.format("MySqlCriteria filter not implemented for '%s'", filter.operator().value()));
                }
            }).collect(Collectors.joining(" AND "));
            query += " WHERE " + whereClauses;
        }

        if (criteria.hasOrders()) {
            String orderClauses = criteria.orders().orders().stream().map(order -> order.orderBy().value() + " " + order.orderType().value()).collect(Collectors.joining(", "));
            query += " ORDER BY " + orderClauses;
        }

        if (criteria.hasLimit()) {
            query += " LIMIT " + criteria.limit();
        }

        if (criteria.hasOffset()) {
            query += " OFFSET " + criteria.offset();
        }

        return query;
    }

    private String convertValueToSql(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Integer) {
            return value.toString();
        }
        if (value instanceof List) {
            return ((List<?>) value).stream().map(this::convertValueToSql).collect(Collectors.joining(","));
        }
        throw new NotImplementedException(String.format("MySqlCriteria value converter for instance of value'%s'", value.getClass().getName()));
    }
}
