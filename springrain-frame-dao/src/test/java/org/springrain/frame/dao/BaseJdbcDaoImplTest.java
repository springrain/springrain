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

class BaseJdbcDaoImplTest {

    @Test
    void usesGeneratedCountSqlForSimplePaging() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Method getPageSql = BaseJdbcDaoImpl.class.getDeclaredMethod("getPageSql", Page.class, Finder.class);
        getPageSql.setAccessible(true);

        Finder finder = new Finder("SELECT id, name FROM demo_user WHERE id IN (SELECT user_id FROM orders ORDER BY created_at DESC) ORDER BY id DESC");
        Page<Object> page = new Page<>(1, 20);

        String pageSql = (String) getPageSql.invoke(dao, page, finder);

        assertEquals(
                "SELECT COUNT(*) FROM demo_user WHERE id IN (SELECT user_id FROM orders ORDER BY created_at DESC) ",
                dao.lastCountSql);
        assertEquals("PAGE::SELECT id, name FROM demo_user WHERE id IN (SELECT user_id FROM orders ORDER BY created_at DESC) ORDER BY id DESC", pageSql);
        assertEquals(3, page.getTotalCount());
    }

    @Test
    void keepsCustomCountFinderPriority() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Method getPageSql = BaseJdbcDaoImpl.class.getDeclaredMethod("getPageSql", Page.class, Finder.class);
        getPageSql.setAccessible(true);

        Finder finder = new Finder("SELECT DISTINCT name FROM demo_user ORDER BY name");
        finder.setCountFinder(new Finder("SELECT 99"));
        Page<Object> page = new Page<>(1, 20);

        String pageSql = (String) getPageSql.invoke(dao, page, finder);

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

        assertEquals(first, second);
    }

    @Test
    void updatesFinderSqlWhenPageOrderOverridesOriginalOrder() throws Exception {
        TestBaseJdbcDaoImpl dao = new TestBaseJdbcDaoImpl();
        Method getPageSql = BaseJdbcDaoImpl.class.getDeclaredMethod("getPageSql", Page.class, Finder.class);
        getPageSql.setAccessible(true);

        Finder finder = new Finder("SELECT id, name FROM demo_user ORDER BY create_time DESC");
        Page<Object> page = new Page<>(1, 20);
        page.setOrder("id");
        page.setSort("asc");

        String pageSql = (String) getPageSql.invoke(dao, page, finder);

        assertEquals("SELECT id, name FROM demo_user  order by id asc", finder.getSql());
        assertEquals("SELECT COUNT(*) FROM demo_user  ", dao.lastCountSql);
        assertEquals("PAGE::SELECT id, name FROM demo_user  order by id asc", pageSql);
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
