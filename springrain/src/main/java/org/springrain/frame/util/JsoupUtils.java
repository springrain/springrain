package org.springrain.frame.util;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class JsoupUtils {
	private final static Whitelist user_content_filter = Whitelist.relaxed();
	
	static {
	    user_content_filter.addTags("embed","object","param","span","div");
	    user_content_filter.addAttributes(":all", "style", "class", "id", "name");
	    user_content_filter.addAttributes("object", "width", "height","classid","codebase");    
	    user_content_filter.addAttributes("param", "name", "value");
	    user_content_filter.addAttributes("embed", "src","quality","width","height","allowFullScreen","allowScriptAccess","flashvars","name","type","pluginspage");
	}
	 
	/**
	 * 对用户输入内容进行过滤
	 * @param html
	 * @return
	 */
	public static String filterUserInputContent(String html) {
	    if(StringUtils.isBlank(html)) return "";
	    return Jsoup.clean(html, user_content_filter);
	    //return filterScriptAndStyle(html);
	}
}
