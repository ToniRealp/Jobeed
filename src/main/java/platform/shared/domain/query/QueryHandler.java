package platform.shared.domain.query;

public interface QueryHandler<Q extends Query, R extends QueryResult> {
    public R handle(Q query) throws Exception;
}
