package org.springrain.frame.util.sqlparser;

/**
 * SQL 中片段的位置范围,左闭右开.
 */
public class SqlSpan {
    private int start;
    private int end;

    public SqlSpan() {
    }

    public SqlSpan(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public boolean exists() {
        return start != end;
    }
}
