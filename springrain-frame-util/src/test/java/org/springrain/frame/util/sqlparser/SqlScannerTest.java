package org.springrain.frame.util.sqlparser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SqlScannerTest {
    @Test
    void parseSqlIgnoresSubqueryOrderByAtTopLevel() {
        String sql = "SELECT id FROM demo_user WHERE id IN (SELECT user_id FROM orders ORDER BY created_at DESC) ORDER BY id DESC";

        SqlPart parts = SqlScanner.parseSQL(sql);

        assertTrue(parts.getOrderBy().exists());
        assertTrue(sql.substring(parts.getOrderBy().getStart(), parts.getOrderBy().getEnd()).startsWith("ORDER BY id DESC"));
    }

    @Test
    void parseSqlMarksDistinctAndSetOperators() {
        assertTrue(SqlScanner.parseSQL("SELECT DISTINCT name FROM demo_user ORDER BY name").getDistinct().exists());
        assertTrue(SqlScanner.parseSQL("SELECT id FROM a UNION SELECT id FROM b").getUnion().exists());
        assertTrue(SqlScanner.parseSQL("SELECT id FROM a INTERSECT SELECT id FROM b").getIntersect().exists());
        assertTrue(SqlScanner.parseSQL("SELECT id FROM a EXCEPT SELECT id FROM b").getExcept().exists());
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
    void parseSqlLeavesFromMissingWhenNoTopLevelFrom() {
        SqlPart parts = SqlScanner.parseSQL("SELECT 1");

        assertFalse(parts.getFrom().exists());
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
