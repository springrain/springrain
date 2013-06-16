package org.iu9.frame.common;

import org.apache.log4j.Logger;
/**
 *  Log基类,所有的类默认继承此类,可以直接使用 logger 记录日志,例如 logger.error("error");
 * @copyright {@link 9iu.org}
 * @author 9iuspring<9iuorg@gmail.com>
 * @version  2013-03-19 11:08:15
 * @see org.iu9.frame.common.BaseLogger
 */
public class BaseLogger {
	public Logger logger=Logger.getLogger(getClass());
}
