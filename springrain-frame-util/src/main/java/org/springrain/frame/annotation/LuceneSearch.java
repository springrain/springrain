/**
 *
 */
package org.springrain.frame.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 标记可以用于Lucene搜索,根据类名创建索引
 *
 * @author springrain<9 iuorg @ gmail.com>
 * @version 2013-03-19 11:08:15
 * @copyright {@link jiagou.com}
 * @see LuceneSearch
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LuceneSearch {

}
