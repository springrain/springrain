package org.springrain.frame.dao.dialect;

import org.springframework.stereotype.Component;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;

@Component("postgresqlDialect")
public class PostgresqlDialect implements IDialect {

    @Override
    public String getPageSql(Finder finder, Page page) throws Exception {
        // 设置分页参数
        int pageSize = page.getPageSize();
        int pageNo = page.getPageNo();
        StringBuilder sb = new StringBuilder(finder.getSql());

        sb.append(" limit ").append(pageSize).append(" offset ").append(pageSize * (pageNo - 1));

        return sb.toString();
    }

    @Override
    public String getDialect() {
        return "postgresql";
    }

    @Override
    public boolean isRowNumber() {
        return false;
    }

}
