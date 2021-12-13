package platform.shared.infrastructure;

import platform.shared.domain.query.Query;
import platform.shared.domain.query.QueryBus;
import platform.shared.domain.query.QueryHandler;
import platform.shared.domain.query.QueryResult;

import java.util.HashMap;
import java.util.Map;

public class MemoryQueryBus implements QueryBus {

    private static final Map<Class<? extends Query>, QueryHandler> handlers = new HashMap<>();

    @Override
    public void register(Class<? extends Query> query, QueryHandler handler) {
        handlers.put(query, handler);
    }

    @Override
    public QueryResult ask(Query query) throws Exception {
        return handlers.get(query.getClass()).handle(query);
    }
}
