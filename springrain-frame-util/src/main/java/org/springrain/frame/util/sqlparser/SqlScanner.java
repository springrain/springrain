package org.springrain.frame.util.sqlparser;

/**
 * SQL 字符扫描器,行为参考 zorm/sqlParser.go.
 */
public class SqlScanner {
    private final String sqlStr;
    private final int sqlLen;
    private int index;
    private int depth;

    public SqlScanner(String sqlStr) {
        this.sqlStr = sqlStr;
        this.sqlLen = sqlStr == null ? 0 : sqlStr.length();
    }

    public static SqlPart parseSQL(String sql) {
        return new SqlScanner(sql).parse();
    }

    public static boolean isIdentChar(char c) {
        return (c >= 'a' && c <= 'z')
                || (c >= 'A' && c <= 'Z')
                || (c >= '0' && c <= '9')
                || c == '_';
    }

    public static boolean matchKeyword(String sql, int index, String word) {
        if (sql == null) {
            return false;
        }
        int n = sql.length();
        int wlen = word.length();
        if (index + wlen > n) {
            return false;
        }
        if (index > 0 && isIdentChar(sql.charAt(index - 1))) {
            return false;
        }
        for (int i = 0; i < wlen; i++) {
            char c = sql.charAt(index + i);
            if (c >= 'A' && c <= 'Z') {
                c = (char) (c + 32);
            }
            if (c != word.charAt(i)) {
                return false;
            }
        }
        if (index + wlen < n && isIdentChar(sql.charAt(index + wlen))) {
            return false;
        }
        return true;
    }

    public static boolean matchTwoKeywords(String sql, int index, String first, String second) {
        if (!matchKeyword(sql, index, first)) {
            return false;
        }
        int cursor = index + first.length();
        while (cursor < sql.length()) {
            char c = sql.charAt(cursor);
            if (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
                cursor++;
                continue;
            }
            return matchKeyword(sql, cursor, second);
        }
        return matchKeyword(sql, cursor, second);
    }

    public SqlPart parse() {
        SqlPart parts = new SqlPart();
        SqlSpan current = parts.getSelect();
        current.setStart(0);
        while (index < sqlLen) {
            char c = sqlStr.charAt(index);
            if (c == '\'' || c == '"') {
                skipString();
                continue;
            }
            if ((c == '-' || c == '/') && skipComment()) {
                continue;
            }
            switch (c) {
                case '(':
                    depth++;
                    index++;
                    continue;
                case ')':
                    if (depth > 0) {
                        depth--;
                    }
                    index++;
                    continue;
                default:
                    break;
            }
            if (depth == 0) {
                switch (c) {
                    case 'f':
                    case 'F':
                        if (matchKeyword(sqlStr, index, "from")) {
                            current.setEnd(index);
                            parts.getFrom().setStart(index);
                            current = parts.getFrom();
                        }
                        break;
                    case 'w':
                    case 'W':
                        if (matchKeyword(sqlStr, index, "where")) {
                            current.setEnd(index);
                            parts.getWhere().setStart(index);
                            current = parts.getWhere();
                        }
                        break;
                    case 'g':
                    case 'G':
                        if (matchTwoKeywords(sqlStr, index, "group", "by")) {
                            current.setEnd(index);
                            parts.getGroupBy().setStart(index);
                            current = parts.getGroupBy();
                        }
                        break;
                    case 'o':
                    case 'O':
                        if (matchTwoKeywords(sqlStr, index, "order", "by")) {
                            current.setEnd(index);
                            parts.getOrderBy().setStart(index);
                            current = parts.getOrderBy();
                        }
                        break;
                    case 'd':
                    case 'D':
                        if (matchKeyword(sqlStr, index, "distinct")) {
                            parts.getDistinct().setStart(index);
                            parts.getDistinct().setEnd(index + 8);
                        }
                        break;
                    case 'u':
                    case 'U':
                        if (matchKeyword(sqlStr, index, "union")) {
                            parts.getUnion().setStart(index);
                            parts.getUnion().setEnd(index + 5);
                        }
                        break;
                    case 'i':
                    case 'I':
                        if (matchKeyword(sqlStr, index, "intersect")) {
                            parts.getIntersect().setStart(index);
                            parts.getIntersect().setEnd(index + 9);
                        }
                        break;
                    case 'e':
                    case 'E':
                        if (matchKeyword(sqlStr, index, "except")) {
                            parts.getExcept().setStart(index);
                            parts.getExcept().setEnd(index + 6);
                        }
                        break;
                    default:
                        break;
                }
            }
            index++;
        }
        if (current != null) {
            current.setEnd(sqlLen);
        }
        fillEnd(parts.getFrom());
        fillEnd(parts.getWhere());
        fillEnd(parts.getGroupBy());
        fillEnd(parts.getOrderBy());
        return parts;
    }

    private void fillEnd(SqlSpan span) {
        if (span.getStart() > 0 && span.getEnd() == 0) {
            span.setEnd(sqlLen);
        }
    }

    private void skipString() {
        char quote = sqlStr.charAt(index);
        index++;
        while (index < sqlLen) {
            if (sqlStr.charAt(index) == '\\' && index + 1 < sqlLen) {
                index += 2;
                continue;
            }
            if (sqlStr.charAt(index) == quote) {
                if (index + 1 < sqlLen && sqlStr.charAt(index + 1) == quote) {
                    index += 2;
                    continue;
                }
                index++;
                return;
            }
            index++;
        }
    }

    private boolean skipComment() {
        if (index + 1 < sqlLen && sqlStr.charAt(index) == '-' && sqlStr.charAt(index + 1) == '-') {
            index += 2;
            while (index < sqlLen && sqlStr.charAt(index) != '\n') {
                index++;
            }
            return true;
        }
        if (index + 1 < sqlLen && sqlStr.charAt(index) == '/' && sqlStr.charAt(index + 1) == '*') {
            index += 2;
            while (index + 1 < sqlLen) {
                if (sqlStr.charAt(index) == '*' && sqlStr.charAt(index + 1) == '/') {
                    index += 2;
                    return true;
                }
                index++;
            }
            return true;
        }
        return false;
    }
}
