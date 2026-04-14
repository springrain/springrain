package org.springrain.frame.dao.dialect;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClickhouseDialectTest {

    private final ClickhouseDialect dialect = new ClickhouseDialect();

    @Test
    void rewritesUpdateSqlInAlterTableStyle() {
        String sql = "UPDATE demo_user SET name=:name WHERE id=:id";

        assertEquals("ALTER TABLE demo_user UPDATE name=:name WHERE id=:id", dialect.reUpdateFinderSQL(sql));
    }

    @Test
    void rewritesDeleteSqlInAlterTableStyle() {
        String sql = "DELETE FROM demo_user WHERE id=:id";

        assertEquals("ALTER TABLE demo_user DELETE WHERE id=:id", dialect.reUpdateFinderSQL(sql));
    }

    @Test
    void keepsLeadingSpacesWhenRewritingSql() {
        String sql = "  UPDATE demo_user SET status=:status";

        assertEquals("  ALTER TABLE demo_user UPDATE status=:status", dialect.reUpdateFinderSQL(sql));
    }

    @Test
    void throwsWhenUpdateMissesSetKeyword() {
        String sql = "UPDATE demo_user name=:name WHERE id=:id";

        assertThrows(IllegalArgumentException.class, () -> dialect.reUpdateFinderSQL(sql));
    }

    @Test
    void throwsWhenDeleteMissesFromKeyword() {
        String sql = "DELETE demo_user WHERE id=:id";

        assertThrows(IllegalArgumentException.class, () -> dialect.reUpdateFinderSQL(sql));
    }
}
