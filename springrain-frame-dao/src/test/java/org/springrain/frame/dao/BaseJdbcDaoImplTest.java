package org.springrain.frame.dao;

import org.junit.jupiter.api.Test;
import org.springrain.frame.dao.dialect.IDialect;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.sqlparser.SqlPart;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

class BaseJdbcDaoImplTest {

    @Test
    void usesGeneratedCountSqlForSimplePaging() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Finder finder = new Finder("SELECT id, name FROM demo_user WHERE id IN (SELECT user_id FROM orders ORDER BY created_at DESC) ORDER BY id DESC");
        Page<Object> page = new Page<>(1, 20);

        String pageSql = invokeGetPageSql(dao, page, finder);

        assertGeneratedCountSql("SELECT COUNT(*) FROM demo_user WHERE id IN (SELECT user_id FROM orders ORDER BY created_at DESC) ", dao);
        assertEquals("PAGE::SELECT id, name FROM demo_user WHERE id IN (SELECT user_id FROM orders ORDER BY created_at DESC) ORDER BY id DESC", pageSql);
        assertEquals(3, page.getTotalCount());
    }

    @Test
    void generatesCountSqlForSelectCountSubquery() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Finder finder = new Finder("select ut.*,(SELECT COUNT (*) FROM users us WHERE us.unit_id = ut.unit_id) num from units ut ");
        Page<Object> page = new Page<>(1, 20);

        invokeGetPageSql(dao, page, finder);

        assertGeneratedCountSql("SELECT COUNT(*) from units ut ", dao);
        assertEquals(3, page.getTotalCount());
    }

    @Test
    void wrapsDistinctOrderByCountSqlForPaging() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Finder finder = new Finder("SELECT DISTINCT name FROM users ORDER BY name");
        Page<Object> page = new Page<>(1, 20);

        invokeGetPageSql(dao, page, finder);

        assertGeneratedCountSql("SELECT COUNT(*) AS frame_row_count FROM (SELECT DISTINCT name FROM users ) temp_frame_noob_table_name WHERE 1=1 ", dao);
    }

    @Test
    void wrapsGroupByCountSqlForPaging() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Finder finder = new Finder("SELECT user_id, COUNT(*) total FROM orders WHERE status = 1 GROUP BY user_id ORDER BY user_id DESC");
        Page<Object> page = new Page<>(1, 20);

        invokeGetPageSql(dao, page, finder);

        assertGeneratedCountSql("SELECT COUNT(*) AS frame_row_count FROM (SELECT user_id, COUNT(*) total FROM orders WHERE status = 1 GROUP BY user_id ) temp_frame_noob_table_name WHERE 1=1 ", dao);
    }

    @Test
    void wrapsUnionCountSqlForPaging() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Finder finder = new Finder("SELECT id FROM users UNION SELECT id FROM admins ORDER BY id");
        Page<Object> page = new Page<>(1, 20);

        invokeGetPageSql(dao, page, finder);

        assertGeneratedCountSql("SELECT COUNT(*) AS frame_row_count FROM (SELECT id FROM users UNION SELECT id FROM admins ) temp_frame_noob_table_name WHERE 1=1 ", dao);
    }

    @Test
    void wrapsIntersectCountSqlForPaging() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Finder finder = new Finder("SELECT id FROM users INTERSECT SELECT id FROM admins ORDER BY id");
        Page<Object> page = new Page<>(1, 20);

        invokeGetPageSql(dao, page, finder);

        assertGeneratedCountSql("SELECT COUNT(*) AS frame_row_count FROM (SELECT id FROM users INTERSECT SELECT id FROM admins ) temp_frame_noob_table_name WHERE 1=1 ", dao);
    }

    @Test
    void wrapsExceptCountSqlForPaging() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Finder finder = new Finder("SELECT id FROM users EXCEPT SELECT id FROM admins ORDER BY id");
        Page<Object> page = new Page<>(1, 20);

        invokeGetPageSql(dao, page, finder);

        assertGeneratedCountSql("SELECT COUNT(*) AS frame_row_count FROM (SELECT id FROM users EXCEPT SELECT id FROM admins ) temp_frame_noob_table_name WHERE 1=1 ", dao);
    }

    @Test
    void keepsCustomCountFinderPriority() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Finder finder = new Finder("SELECT DISTINCT name FROM demo_user ORDER BY name");
        finder.setCountFinder(new Finder("SELECT 99"));
        Page<Object> page = new Page<>(1, 20);

        String pageSql = invokeGetPageSql(dao, page, finder);

        assertEquals(null, dao.lastCountSql);
        assertEquals("SELECT 99", dao.lastFinderCountSql);
        assertEquals("PAGE::SELECT DISTINCT name FROM demo_user ORDER BY name", pageSql);
        assertEquals(99, page.getTotalCount());
    }

    @Test
    void cachesSqlPartOnFinderAndReusesIt() throws Exception {
        Finder finder = new Finder("SELECT id FROM demo_user ORDER BY id DESC");

        SqlPart first = finder.getSqlPartCache();
        SqlPart second = finder.getSqlPartCache();

        assertSame(first, second);
    }

    @Test
    void resetsSqlPartCacheAfterSetSql() throws Exception {
        Finder finder = new Finder("SELECT id FROM demo_user ORDER BY id DESC");

        SqlPart first = finder.getSqlPartCache();
        finder.setSql("SELECT id FROM demo_user WHERE status = 1");
        SqlPart second = finder.getSqlPartCache();

        assertNotSame(first, second);
        assertEquals(false, second.getOrderBy().exists());
        assertEquals(true, second.getWhere().exists());
    }

    @Test
    void updatesFinderSqlWhenPageOrderOverridesOriginalOrder() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Finder finder = new Finder("SELECT id, name FROM demo_user ORDER BY create_time DESC");
        Page<Object> page = new Page<>(1, 20);
        page.setOrder("id");
        page.setSort("asc");

        String pageSql = invokeGetPageSql(dao, page, finder);

        assertEquals("SELECT id, name FROM demo_user  order by id asc", finder.getSql());
        assertGeneratedCountSql("SELECT COUNT(*) FROM demo_user  ", dao);
        assertEquals("PAGE::SELECT id, name FROM demo_user  order by id asc", pageSql);
    }

    @Test
    void stripsOnlyTopLevelOrderByWhenGeneratingCountSql() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Finder finder = new Finder("SELECT id, name FROM demo_user WHERE id IN (SELECT user_id FROM orders ORDER BY created_at DESC) ORDER BY id DESC");
        Page<Object> page = new Page<>(1, 20);

        invokeGetPageSql(dao, page, finder);

        assertGeneratedCountSql("SELECT COUNT(*) FROM demo_user WHERE id IN (SELECT user_id FROM orders ORDER BY created_at DESC) ", dao);
    }

    @Test
    void countSqlRegeneratesAfterPageOrderOverridesOriginalOrder() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Finder finder = new Finder("SELECT DISTINCT name FROM users ORDER BY created_at DESC");
        Page<Object> page = new Page<>(1, 20);
        page.setOrder("name");
        page.setSort("desc");

        String pageSql = invokeGetPageSql(dao, page, finder);

        assertEquals("SELECT DISTINCT name FROM users  order by name desc", finder.getSql());
        assertGeneratedCountSql("SELECT COUNT(*) AS frame_row_count FROM (SELECT DISTINCT name FROM users  ) temp_frame_noob_table_name WHERE 1=1 ", dao);
        assertEquals("PAGE::SELECT DISTINCT name FROM users  order by name desc", pageSql);
    }

    private static String invokeGetPageSql(TestBaseJdbcDaoImpl dao, Page<?> page, Finder finder) throws Exception {
        Method getPageSql = BaseJdbcDaoImpl.class.getDeclaredMethod("getPageSql", Page.class, Finder.class);
        getPageSql.setAccessible(true);
        return (String) getPageSql.invoke(dao, page, finder);
    }

    private static void assertSqlEqualsIgnoringWhitespace(String expected, String actual) {
        assertEquals(normalizeSql(expected), normalizeSql(actual));
    }

    private static void assertGeneratedCountSql(String expected, TestBaseJdbcDaoImpl dao) {
        assertSqlEqualsIgnoringWhitespace(expected, dao.lastCountSql);
    }

    private static String normalizeSql(String sql) {
        return sql == null ? null : sql.trim().replaceAll("\\s+", " ");
    }

    private static class TestBaseJdbcDaoImpl extends BaseJdbcDaoImpl {
        private String lastCountSql;
        private String lastFinderCountSql;
        private final NamedParameterJdbcTemplate jdbc = new StubNamedParameterJdbcTemplate();
        private final IDialect dialect = new StubDialect();

        @Override
        public NamedParameterJdbcTemplate getJdbc() {
            return jdbc;
        }

        @Override
        public SimpleJdbcCall getJdbcCall() {
            return null;
        }

        @Override
        public IDialect getDialect() {
            return dialect;
        }

        @Override
        public String getUserName() {
            return "tester";
        }

        @Override
        public <T> T queryForObject(Finder finder, Class<T> clazz) throws Exception {
            lastFinderCountSql = finder.getSql();
            return clazz.cast(Integer.valueOf(99));
        }

        private class StubNamedParameterJdbcTemplate extends NamedParameterJdbcTemplate {
            StubNamedParameterJdbcTemplate() {
                super(new org.springframework.jdbc.datasource.DriverManagerDataSource());
            }

            @Override
            public <T> T queryForObject(String sql, java.util.Map<String, ?> paramMap, Class<T> requiredType) {
                lastCountSql = sql;
                return requiredType.cast(Integer.valueOf(3));
            }
        }

        private static class StubDialect implements IDialect {
            @Override
            public String getPageSql(Finder finder, Page page) throws Exception {
                return "PAGE::" + finder.getSql();
            }

            @Override
            public String getDialect() {
                return "test";
            }

            @Override
            public boolean isRowNumber() {
                return false;
            }
        }
    }
}
