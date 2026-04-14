package org.springrain.frame.util.sqlparser;

import org.junit.jupiter.api.Test;

    import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SqlScannerTest {
    private static void assertPartContains(String sql, SqlSpan span, String expectedKeyword) {
        assertTrue(span.exists(), () -> expectedKeyword + " should exist");
        String content = sql.substring(span.getStart(), span.getEnd()).trim().toUpperCase().replaceAll("\\s+", " ");
        String normalizedKeyword = expectedKeyword.toUpperCase().replaceAll("\\s+", " ");
        assertTrue(content.contains(normalizedKeyword), () -> "Expected part to contain " + expectedKeyword + ", actual: " + content);
    }

    private static void assertSpanMissing(SqlSpan span, String label) {
        assertFalse(span.exists(), () -> label + " should be missing");
        assertEquals(span.getStart(), span.getEnd(), () -> label + " should have equal start/end");
    }

    private static void assertSpanEquals(String sql, SqlSpan span, String expected) {
        assertTrue(span.exists(), () -> expected + " should exist");
        assertEquals(expected.toUpperCase(), sql.substring(span.getStart(), span.getEnd()).trim().toUpperCase());
    }

    private static void assertTopLevelClauseText(String sql, SqlSpan span, String expectedPrefix) {
        assertTrue(span.exists(), () -> expectedPrefix + " should exist");
        String actual = sql.substring(span.getStart(), span.getEnd()).trim().toUpperCase().replaceAll("\\s+", " ");
        assertTrue(actual.startsWith(expectedPrefix.toUpperCase().replaceAll("\\s+", " ")),
                () -> "Expected clause to start with " + expectedPrefix + ", actual: " + actual);
    }

    @Test
    void parseSqlBasicClauses() {
        String sql = "SELECT name, count(*) FROM user WHERE age > 18 GROUP BY name ORDER BY count(*) DESC";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getGroupBy(), "GROUP BY");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlWithSubquery() {
        String sql = "SELECT name, count(*)\nFROM (SELECT name FROM user GROUP BY name) t\nWHERE age > 18\nGROUP BY name\nORDER BY count(*) DESC";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getGroupBy(), "GROUP BY");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
        assertTrue(sql.substring(parts.getFrom().getStart(), parts.getFrom().getEnd()).contains("SELECT name FROM user GROUP BY name"));
    }

    @Test
    void parseSqlWithoutWhere() {
        String sql = "SELECT * FROM users ORDER BY id";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
        assertSpanMissing(parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlWithoutOrderBy() {
        String sql = "SELECT id, name FROM users WHERE status = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertSpanMissing(parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlWithoutGroupBy() {
        String sql = "SELECT * FROM users WHERE id = 1 ORDER BY created_at";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
        assertSpanMissing(parts.getGroupBy(), "GROUP BY");
    }

    @Test
    void parseSqlWithEscapedQuotes() {
        String sql = "SELECT * FROM users WHERE name = 'O''Brien' AND desc = 'It''s fine'";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlWithBackslashEscapes() {
        String sql = "SELECT * FROM users WHERE path = 'C:\\Users\\test' AND regex = 'a\\b'";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlWithDoubleQuotes() {
        String sql = "SELECT \"name\", \"age\" FROM \"users\" WHERE \"id\" = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlWithLineComment() {
        String sql = "SELECT * FROM users -- comment\nWHERE id = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlWithLineCommentAtEnd() {
        String sql = "SELECT * FROM users -- comment at end without newline";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertSpanMissing(parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlWithLineCommentNoSpace() {
        String sql = "SELECT * FROM users--comment without space\nWHERE id = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlWithMultiLineComment() {
        String sql = "SELECT /* comment */ * FROM users WHERE id = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlWithNestedParentheses() {
        String sql = "SELECT (SELECT COUNT(*) FROM orders WHERE orders.user_id = users.id) AS order_count FROM users WHERE status IN (1, 2, 3)";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlIsCaseInsensitive() {
        String sql = "select * from USERS where ID = 1 group by NAME order by TIME limit 10";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getGroupBy(), "GROUP BY");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlWithMixedCaseKeywords() {
        String sql = "SeLeCt * FrOm users WhErE id = 1 GrOuP By name OrDeR By id";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getGroupBy(), "GROUP BY");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlWithExtraSpaces() {
        String sql = "SELECT    *    FROM    users    WHERE    id = 1    GROUP    BY    name    ORDER    BY    id";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getGroupBy(), "GROUP BY");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlWithNewLines() {
        String sql = "SELECT *\nFROM users\nWHERE id = 1\nGROUP BY name\nORDER BY id";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getGroupBy(), "GROUP BY");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlIgnoresKeywordsInsideStrings() {
        String sql = "SELECT * FROM users WHERE name = 'SELECT FROM WHERE' AND desc = 'ORDER BY LIMIT'";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertSpanMissing(parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlIgnoresKeywordsInsideIdentifiers() {
        String sql = "SELECT select_time, from_addr, where_field FROM table1 WHERE orderby = 1 AND grouping = 2";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertSpanMissing(parts.getOrderBy(), "ORDER BY");
        assertSpanMissing(parts.getGroupBy(), "GROUP BY");
    }

    @Test
    void parseSqlMarksDistinctAndSetOperators() {
        assertTrue(SqlScanner.parseSQL("SELECT DISTINCT name FROM demo_user ORDER BY name").getDistinct().exists());
        assertTrue(SqlScanner.parseSQL("SELECT id FROM a UNION SELECT id FROM b").getUnion().exists());
        assertTrue(SqlScanner.parseSQL("SELECT id FROM a INTERSECT SELECT id FROM b").getIntersect().exists());
        assertTrue(SqlScanner.parseSQL("SELECT id FROM a EXCEPT SELECT id FROM b").getExcept().exists());
    }

    @Test
    void parseSqlDistinctUsesExactSpan() {
        String sql = "SELECT DISTINCT name FROM users WHERE status = 1 GROUP BY name";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertSpanEquals(sql, parts.getDistinct(), "DISTINCT");
    }

    @Test
    void parseSqlDistinctInsideStringDoesNotMatch() {
        String sql = "SELECT * FROM users WHERE note = 'SELECT DISTINCT is a keyword'";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertSpanMissing(parts.getDistinct(), "DISTINCT");
    }

    @Test
    void parseSqlDistinctInsideIdentifierDoesNotMatch() {
        String sql = "SELECT distinct_count, is_distinct FROM metrics WHERE id = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertSpanMissing(parts.getDistinct(), "DISTINCT");
    }

    @Test
    void parseSqlDistinctWithOrderBy() {
        String sql = "SELECT DISTINCT name FROM users ORDER BY name";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
        assertTrue(parts.getDistinct().exists());
    }

    @Test
    void parseSqlUnionUsesExactSpan() {
        String sql = "SELECT id, name FROM users WHERE status = 1 UNION SELECT id, name FROM admins WHERE status = 1 ORDER BY id";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
        assertSpanEquals(sql, parts.getUnion(), "UNION");
    }

    @Test
    void parseSqlWithUnionAll() {
        String sql = "SELECT id FROM users UNION ALL SELECT id FROM orders UNION ALL SELECT id FROM products";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertTrue(parts.getUnion().exists());
    }

    @Test
    void parseSqlUnionInsideSubqueryDoesNotAffectTopLevelFrom() {
        String sql = "SELECT * FROM (SELECT id FROM users UNION SELECT id FROM orders) AS temp WHERE id > 0";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        String fromContent = sql.substring(parts.getFrom().getStart(), parts.getFrom().getEnd());
        assertTrue(fromContent.contains("temp"));
    }

    @Test
    void parseSqlIntersectUsesExactSpan() {
        String sql = "SELECT id FROM users INTERSECT SELECT id FROM admins";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertSpanEquals(sql, parts.getIntersect(), "INTERSECT");
    }

    @Test
    void parseSqlExceptUsesExactSpan() {
        String sql = "SELECT id FROM users EXCEPT SELECT id FROM admins";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertSpanEquals(sql, parts.getExcept(), "EXCEPT");
    }

    @Test
    void parseSqlWithJoin() {
        String sql = "SELECT u.id, u.name, o.amount FROM users u LEFT JOIN orders o ON u.id = o.user_id WHERE u.status = 1 ORDER BY o.created_at";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlWithMultipleJoins() {
        String sql = "SELECT * FROM users u INNER JOIN orders o ON u.id = o.user_id LEFT JOIN products p ON o.product_id = p.id WHERE u.status = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlWithHint() {
        String sql = "SELECT /*+ INDEX(users idx_status) */ * FROM users WHERE status = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlWithCountAggregate() {
        String sql = "SELECT COUNT(*) AS total FROM users WHERE status = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlWithCountSubqueryStillFindsOuterClauses() {
        String sql = "SELECT COUNT(*) temp_zorm_row_count FROM (SELECT DISTINCT name FROM users WHERE age > 18 GROUP BY name ORDER BY name) temp_zorm_noob_table_name WHERE 1=1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        String fromContent = sql.substring(parts.getFrom().getStart(), parts.getFrom().getEnd());
        assertTrue(fromContent.contains("DISTINCT"));
        assertTrue(fromContent.contains("GROUP BY"));
        assertTrue(fromContent.contains("ORDER BY"));
    }

    @Test
    void parseSqlInsertTreatsWholeSqlAsSelectSpan() {
        String sql = "INSERT INTO users (name, age) VALUES ('test', 18)";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertEquals(sql.length(), parts.getSelect().getEnd());
        assertSpanMissing(parts.getFrom(), "FROM");
    }

    @Test
    void parseSqlUpdateParsesWhere() {
        String sql = "UPDATE users SET name = 'test', age = 18 WHERE id = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertTrue(parts.getWhere().exists());
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlHandlesDelete() {
        String sql = "DELETE FROM users WHERE id = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlHandlesEmptyString() {
        SqlPart parts = SqlScanner.parseSQL("");

        assertSpanMissing(parts.getSelect(), "SELECT");
        assertSpanMissing(parts.getFrom(), "FROM");
        assertSpanMissing(parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlHandlesOnlySelect() {
        String sql = "SELECT 1 + 1 AS result";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertSpanMissing(parts.getFrom(), "FROM");
        assertSpanMissing(parts.getWhere(), "WHERE");
        assertSpanMissing(parts.getGroupBy(), "GROUP BY");
        assertSpanMissing(parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlLeavesFromMissingWhenNoTopLevelFrom() {
        SqlPart parts = SqlScanner.parseSQL("SELECT 1");

        assertSpanMissing(parts.getFrom(), "FROM");
    }

    @Test
    void parseSqlHandlesMultipleOrderByColumns() {
        String sql = "SELECT * FROM users ORDER BY status DESC, created_at ASC, id DESC";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
        String orderBy = sql.substring(parts.getOrderBy().getStart(), parts.getOrderBy().getEnd());
        assertTrue(orderBy.contains("status"));
        assertTrue(orderBy.contains("created_at"));
        assertTrue(orderBy.contains("id"));
    }

    @Test
    void parseSqlIgnoresSubqueryOrderByAtTopLevel() {
        String sql = "SELECT id FROM demo_user WHERE id IN (SELECT user_id FROM orders ORDER BY created_at DESC) ORDER BY id DESC";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertTrue(parts.getOrderBy().exists());
        assertTopLevelClauseText(sql, parts.getOrderBy(), "ORDER BY id DESC");
    }

    @Test
    void parseSqlKeepsSubqueryKeywordsOutOfTopLevelDetection() {
        String sql = "SELECT id, (SELECT max(name) FROM dept WHERE dept.id = user.dept_id ORDER BY name DESC) dept_name FROM user WHERE EXISTS (SELECT 1 FROM orders WHERE orders.user_id=user.id) ORDER BY id DESC";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertTrue(parts.getFrom().exists());
        assertTrue(parts.getWhere().exists());
        assertTrue(parts.getOrderBy().exists());
        assertFalse(sql.substring(parts.getFrom().getStart(), parts.getFrom().getEnd()).contains("FROM dept"));
    }

    @Test
    void parseSqlIgnoresKeywordsInsideCommentsAndStrings() {
        String sql = "SELECT ' from order by ' AS txt FROM demo_user /* order by x */ WHERE note = '-- from' ORDER BY id";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertTrue(parts.getFrom().exists());
        assertTrue(parts.getWhere().exists());
        assertTrue(parts.getOrderBy().exists());
        assertTrue(parts.getOrderBy().getStart() > parts.getWhere().getEnd() - 1);
    }

    @Test
    void parseSqlUnclosedStringDoesNotBreakParsing() {
        String sql = "SELECT * FROM users WHERE name = 'unclosed";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertEquals(0, parts.getSelect().getStart());
        assertTrue(parts.getFrom().exists());
        assertTrue(parts.getWhere().exists());
    }

    @Test
    void parseSqlUnclosedCommentDoesNotBreakParsing() {
        String sql = "SELECT /* unclosed comment * FROM users";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertEquals(0, parts.getSelect().getStart());
        assertSpanMissing(parts.getFrom(), "FROM");
    }

    @Test
    void parseSqlNestedBrackets() {
        String sql = "SELECT (((1 + 2) * 3) - 4) AS result FROM (((users)))";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
    }

    @Test
    void parseSqlKeywordInSubqueryDoesNotAffectOuterFrom() {
        String sql = "SELECT (SELECT name FROM inner_table WHERE id = 1) AS name FROM outer_table WHERE status = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertTrue(sql.substring(parts.getFrom().getStart(), parts.getFrom().getEnd()).contains("outer_table"));
    }

    @Test
    void parseSqlCaseWhen() {
        String sql = "SELECT id, CASE WHEN status = 1 THEN 'active' WHEN status = 0 THEN 'inactive' ELSE 'unknown' END AS status_name FROM users WHERE created_at > '2024-01-01'";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertFalse(sql.substring(parts.getSelect().getStart(), parts.getFrom().getStart()).toUpperCase().contains("FROM USERS"));
    }

    @Test
    void parseSqlCaseWhenInWhere() {
        String sql = "SELECT * FROM users WHERE CASE WHEN age > 18 THEN status ELSE 'pending' END = 'active'";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlWindowFunctionWithoutOuterOrderBy() {
        String sql = "SELECT id, name, salary, ROW_NUMBER() OVER (PARTITION BY dept ORDER BY salary DESC) AS rn, AVG(salary) OVER (PARTITION BY dept) AS avg_salary FROM employees WHERE status = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertSpanMissing(parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlHandlesWindowFunctionOrderBy() {
        String sql = "SELECT id, name, ROW_NUMBER() OVER (PARTITION BY dept ORDER BY salary DESC) AS rn FROM employees WHERE status = 1 ORDER BY id DESC";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
        assertTopLevelClauseText(sql, parts.getOrderBy(), "ORDER BY id DESC");
    }

    @Test
    void parseSqlCte() {
        String sql = "WITH active_users AS (SELECT id, name FROM users WHERE status = 1), order_counts AS (SELECT user_id, COUNT(*) AS cnt FROM orders GROUP BY user_id) SELECT u.id, u.name, oc.cnt FROM active_users u LEFT JOIN order_counts oc ON u.id = oc.user_id WHERE oc.cnt > 5";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        String fromContent = sql.substring(parts.getFrom().getStart(), parts.getFrom().getEnd());
        assertTrue(fromContent.contains("active_users"));
        assertTrue(fromContent.contains("order_counts"));
    }

    @Test
    void parseSqlCteWithOrderBy() {
        String sql = "WITH ranked_users AS (SELECT id, name, ROW_NUMBER() OVER (ORDER BY created_at DESC) AS rn FROM users) SELECT * FROM ranked_users WHERE rn <= 10 ORDER BY rn";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlRecursiveCte() {
        String sql = "WITH RECURSIVE hierarchy AS (SELECT id, parent_id, name, 0 AS level FROM categories WHERE parent_id IS NULL UNION ALL SELECT c.id, c.parent_id, c.name, h.level + 1 FROM categories c INNER JOIN hierarchy h ON c.parent_id = h.id) SELECT * FROM hierarchy ORDER BY level, name";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlSelectInto() {
        String sql = "SELECT id, name INTO @var_id, @var_name FROM users WHERE id = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlSelectIntoOutfile() {
        String sql = "SELECT * FROM users WHERE status = 1 INTO OUTFILE '/tmp/users.csv' FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n'";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlInsertSelect() {
        String sql = "INSERT INTO users_backup (id, name, email) SELECT id, name, email FROM users WHERE status = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlUpdateJoin() {
        String sql = "UPDATE users u INNER JOIN orders o ON u.id = o.user_id SET u.total_orders = (SELECT COUNT(*) FROM orders WHERE user_id = u.id) WHERE o.status = 1";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertTrue(parts.getWhere().exists());
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlDeleteJoin() {
        String sql = "DELETE u FROM users u LEFT JOIN orders o ON u.id = o.user_id WHERE o.id IS NULL";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlForUpdate() {
        String sql = "SELECT * FROM users WHERE id = 1 FOR UPDATE";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertTrue(sql.substring(parts.getWhere().getStart(), parts.getWhere().getEnd()).toUpperCase().contains("FOR UPDATE"));
    }

    @Test
    void parseSqlLockInShareMode() {
        String sql = "SELECT * FROM users WHERE id = 1 LOCK IN SHARE MODE";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
    }

    @Test
    void parseSqlValuesInsertDoesNotBreak() {
        String sql = "INSERT INTO users (id, name) VALUES (1, 'test') ON DUPLICATE KEY UPDATE name = VALUES(name)";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertEquals(0, parts.getSelect().getStart());
        assertEquals(sql.length(), parts.getSelect().getEnd());
    }

    @Test
    void parseSqlComplexPaging() {
        String sql = "SELECT u.*, (SELECT COUNT(*) FROM orders WHERE user_id = u.id) AS order_count FROM (SELECT * FROM users WHERE status IN (1,2,3) ORDER BY created_at DESC) u WHERE u.age > 18 ORDER BY u.created_at DESC";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlMultiTableJoin() {
        String sql = "SELECT u.id, u.name, o.id AS order_id, o.amount, p.name AS product_name FROM users u INNER JOIN orders o ON u.id = o.user_id AND o.status = 1 LEFT JOIN order_items oi ON o.id = oi.order_id INNER JOIN products p ON oi.product_id = p.id WHERE u.status = 1 AND u.created_at > '2024-01-01' GROUP BY u.id, o.id HAVING SUM(oi.quantity) > 0 ORDER BY o.created_at DESC";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getGroupBy(), "GROUP BY");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void parseSqlCountForPaging() {
        String sql = "SELECT DISTINCT u.name, u.age FROM users u WHERE u.age > 18 GROUP BY u.name ORDER BY u.name";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertTrue(parts.getDistinct().exists());
        assertTrue(parts.getGroupBy().exists());
        assertTrue(parts.getOrderBy().exists());
        String countCandidate = sql.substring(0, parts.getOrderBy().getStart());
        assertFalse(countCandidate.toUpperCase().contains("ORDER BY"));
    }

    @Test
    void parseSqlEcommerceOrderQuery() {
        String sql = "WITH ranked_orders AS (SELECT o.id, o.user_id, o.amount, o.status, ROW_NUMBER() OVER (PARTITION BY o.user_id ORDER BY o.created_at DESC) AS rn, CASE WHEN o.amount > 1000 THEN 'high' WHEN o.amount > 500 THEN 'medium' ELSE 'low' END AS amount_level FROM orders o WHERE o.created_at >= '2024-01-01' AND o.status IN ('paid', 'shipped')) SELECT u.id AS user_id, u.name, ro.id AS order_id, ro.amount, ro.amount_level, (SELECT COUNT(*) FROM order_items WHERE order_id = ro.id) AS item_count FROM ranked_orders ro INNER JOIN users u ON ro.user_id = u.id WHERE ro.rn <= 5 ORDER BY ro.created_at DESC, u.name ASC";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getWhere(), "WHERE");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
        assertFalse(sql.substring(parts.getOrderBy().getStart(), parts.getOrderBy().getEnd()).contains("PARTITION BY"));
    }

    @Test
    void parseSqlFinancialReport() {
        String sql = "WITH daily_stats AS (SELECT DATE(created_at) AS stat_date, SUM(amount) AS daily_total, COUNT(*) AS daily_count FROM transactions WHERE created_at >= '2024-01-01' GROUP BY DATE(created_at)), moving_avg AS (SELECT stat_date, daily_total, daily_count, AVG(daily_total) OVER (ORDER BY stat_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) AS weekly_avg FROM daily_stats) SELECT stat_date, daily_total, weekly_avg, CASE WHEN daily_total > weekly_avg * 1.2 THEN 'above_normal' WHEN daily_total < weekly_avg * 0.8 THEN 'below_normal' ELSE 'normal' END AS status FROM moving_avg ORDER BY stat_date DESC";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertPartContains(sql, parts.getSelect(), "SELECT");
        assertPartContains(sql, parts.getFrom(), "FROM");
        assertPartContains(sql, parts.getOrderBy(), "ORDER BY");
    }

    @Test
    void matchesKeywordWithWordBoundariesOnly() {
        assertTrue(SqlScanner.matchKeyword("select * from demo", 9, "from"));
        assertFalse(SqlScanner.matchKeyword("select * from_addr", 9, "from"));
        assertFalse(SqlScanner.matchKeyword("select * afrom demo", 10, "from"));
    }

    @Test
    void matchesTwoKeywordsAcrossWhitespace() {
        assertTrue(SqlScanner.matchTwoKeywords("select * from demo group \n\t by dept_id", 19, "group", "by"));
        assertTrue(SqlScanner.matchTwoKeywords("select * from demo order\r\nby id", 19, "order", "by"));
    }
}
