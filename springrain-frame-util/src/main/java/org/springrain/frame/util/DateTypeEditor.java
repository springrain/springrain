package org.springrain.frame.util;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期编辑器
 * <p>
 * 根据日期字符串长度判断是长日期还是短日期。只支持yyyy-MM-dd，yyyy-MM-dd HH:mm:ss两种格式。
 * 扩展支持yyyy,yyyy-MM日期格式
 */
public class DateTypeEditor extends PropertyEditorSupport {
    /**
     * 短类型日期长度
     */
    public static final int SHORT_DATE = 10;

    public static final int YEAR_DATE = 4;

    public static final int MONTH_DATE = 7;

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value) : "");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        text = text.trim();
        if (StringUtils.isBlank(text)) {
            setValue(null);
            return;
        }
        try {
            if (text.length() <= YEAR_DATE) {
                setValue(new SimpleDateFormat("yyyy").parse(text));
            } else if (text.length() <= MONTH_DATE) {
                setValue(new SimpleDateFormat("yyyy-MM").parse(text));
            } else if (text.length() <= SHORT_DATE) {
                setValue(new SimpleDateFormat("yyyy-MM-dd").parse(text));
            } else {
                setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(text));
            }
        } catch (ParseException ex) {
            IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + ex.getMessage());
            iae.initCause(ex);
            throw iae;
        }
    }
}
