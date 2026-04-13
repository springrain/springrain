package org.springrain.frame.dao.dialect;

import org.springframework.stereotype.Component;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;

@Component("mysqlDialect")
public class MysqlDialect implements IDialect {

    @Override
    public String getPageSql(Finder finder, Page page) throws Exception {
        // 设置分页参数
        int pageSize = page.getPageSize();
        int pageNo = page.getPageNo();
        StringBuilder sb = new StringBuilder(finder.getSql());
        /*
        if (StringUtils.isNotBlank(orderby)) {
            sb.append(" ").append(orderby);
        }
         */
        sb.append(" limit ").append(pageSize * (pageNo - 1)).append(",").append(pageSize);
        return sb.toString();
    }

    @Override
    public String getDialect() {
        return "mysql";
    }

    @Override
    public boolean isRowNumber() {
        return false;
    }

}
