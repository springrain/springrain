package org.springrain.demo.service;

import org.springrain.demo.entity.Blog;
import java.io.File;
import java.util.List;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-09-07 09:37:01
 * @see org.springrain.demo.service.Blog
 */
public interface IBlogService extends IBaseDemoService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Blog findBlogById(Object id) throws Exception;
	
	
	
}
