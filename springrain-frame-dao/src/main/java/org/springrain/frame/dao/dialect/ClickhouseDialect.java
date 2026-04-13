package org.springrain.frame.dao.dialect;

import org.springframework.stereotype.Component;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;

@Component("clickhouseDialect")
public class ClickhouseDialect implements IDialect {

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
        return "clickhouse";
    }

    @Override
    public boolean isRowNumber() {
        return false;
    }

    @Override
    public boolean supportTransaction() {
        return false;
    }

    @Override
    public String reUpdateFinderSQL(String updatesql) {
        char[] sqlChars = updatesql.toCharArray();
        WordInfo firstWordInfo = firstOneWord(0, sqlChars);
        if (firstWordInfo.getStart() == -1 || firstWordInfo.getEnd() == -1) {
            return updatesql;
        }

        StringBuilder sqlBuilder = new StringBuilder(updatesql.length() + 20);
        sqlBuilder.append(updatesql, 0, firstWordInfo.getStart());
        sqlBuilder.append("ALTER TABLE ");

        String firstWord = firstWordInfo.getWord().toUpperCase();
        String tableName;
        int remainingSqlStart;
        WordInfo wordInfo;
        switch (firstWord) {
            case "UPDATE":
                wordInfo = firstOneWord(firstWordInfo.getEnd(), sqlChars);
                tableName = wordInfo.getWord();

                wordInfo = firstOneWord(wordInfo.getEnd(), sqlChars);
                if (!"SET".equalsIgnoreCase(wordInfo.getWord())) {
                    throw new IllegalArgumentException("->reUpdateFinderSQL中clickhouse语法异常,请检查sql语句是否标准,-->springrainErrorExecSQL:" + updatesql);
                }
                remainingSqlStart = wordInfo.getEnd();
                break;
            case "DELETE":
                wordInfo = firstOneWord(firstWordInfo.getEnd(), sqlChars);
                if (!"FROM".equalsIgnoreCase(wordInfo.getWord())) {
                    throw new IllegalArgumentException("->reUpdateFinderSQL中clickhouse语法异常,请检查sql语句是否标准,-->springrainErrorExecSQL:" + updatesql);
                }
                wordInfo = firstOneWord(wordInfo.getEnd(), sqlChars);
                tableName = wordInfo.getWord();
                remainingSqlStart = wordInfo.getEnd();
                break;
            default:
                return updatesql;
        }

        if (remainingSqlStart == -1) {
            throw new IllegalArgumentException("->reUpdateFinderSQL中clickhouse语法异常,请检查sql语句是否标准,-->springrainErrorExecSQL:" + updatesql);
        }

        sqlBuilder.append(tableName);
        sqlBuilder.append(' ');
        sqlBuilder.append(firstWord);
        sqlBuilder.append(updatesql.substring(remainingSqlStart));
        return sqlBuilder.toString();
    }


    @Override
    public String updateSQLByTableName(String tableName) {
        return "ALTER TABLE " + tableName + " UPDATE ";
    }

    @Override
    public String deleteSQLByTableName(String tableName) {
        return "ALTER TABLE " + tableName + " DELETE ";
    }



    private static WordInfo firstOneWord(int index, char[] chars) {
        int n = chars == null ? 0 : chars.length;
        if (index < 0 || index >= n) {
            throw new IllegalArgumentException("-->firstOneWord index out of range");
        }

        while (index < n && (chars[index] == ' ' || chars[index] == '(' || chars[index] == ')')) {
            index++;
        }
        if (index >= n) {
            return new WordInfo("", -1, -1);
        }

        int start = index;
        while (index < n && chars[index] != ' ' && chars[index] != '(' && chars[index] != ')') {
            index++;
        }
        if (start >= index) {
            return new WordInfo("", -1, -1);
        }
        return new WordInfo(new String(chars, start, index - start), start, index);
    }

    private static final class WordInfo {
        private final String word;
        private final int start;
        private final int end;

        private WordInfo(String word, int start, int end) {
            this.word = word;
            this.start = start;
            this.end = end;
        }

        public String getWord() {
            return word;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }


}
