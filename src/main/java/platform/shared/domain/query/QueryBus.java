package platform.shared.domain.query;

public interface QueryBus {
    void register(Class<? extends Query> query, QueryHandler handler);
    <R extends QueryResult> R ask(Query query) throws Exception;
}
