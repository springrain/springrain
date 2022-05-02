package org.springrain.frame.dao.dialect;

import org.springframework.stereotype.Component;
import org.springrain.frame.util.Page;

@Component("postgresqlDialect")
public class PostgresqlDialect implements IDialect {

    @Override
    public String getPageSql(String sql, Page page) {
        // 设置分页参数
        int pageSize = page.getPageSize();
        int pageNo = page.getPageNo();
        StringBuilder sb = new StringBuilder(sql);

        sb.append(" limit ").append(pageSize).append(" offset ").append(pageSize * (pageNo - 1));

        return sb.toString();
    }

    @Override
    public String getDataBaseType() {
        return "postgresql";
    }

    @Override
    public boolean isRowNumber() {
        return false;
    }

}
