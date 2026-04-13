package org.springrain.frame.util.sqlparser;

/**
 * SQL 各子句片段.
 */
public class SqlPart {
    private final SqlSpan select = new SqlSpan();
    private final SqlSpan from = new SqlSpan();
    private final SqlSpan where = new SqlSpan();
    private final SqlSpan groupBy = new SqlSpan();
    private final SqlSpan orderBy = new SqlSpan();
    private final SqlSpan distinct = new SqlSpan();
    private final SqlSpan union = new SqlSpan();
    private final SqlSpan intersect = new SqlSpan();
    private final SqlSpan except = new SqlSpan();

    public SqlSpan getSelect() {
        return select;
    }

    public SqlSpan getFrom() {
        return from;
    }

    public SqlSpan getWhere() {
        return where;
    }

    public SqlSpan getGroupBy() {
        return groupBy;
    }

    public SqlSpan getOrderBy() {
        return orderBy;
    }

    public SqlSpan getDistinct() {
        return distinct;
    }

    public SqlSpan getUnion() {
        return union;
    }

    public SqlSpan getIntersect() {
        return intersect;
    }

    public SqlSpan getExcept() {
        return except;
    }
}
