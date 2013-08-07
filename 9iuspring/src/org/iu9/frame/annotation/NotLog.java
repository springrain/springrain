
/**
 * 
 */
package org.iu9.frame.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  用于Entity的注解,不希望记录日志
 * @copyright {@link 9iu.org}
 * @author 9iuspring<9iuorg@gmail.com>
 * @version  2013-03-19 11:08:15
 * @see com.centfor.frame.annotation.PKSequence
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NotLog  {
	

}
