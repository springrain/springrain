package org.springrain.frame.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class InputSafeUtils {
	private final static Whitelist user_content_filter = Whitelist.relaxed();
	
	static {
	    user_content_filter.addTags("embed","object","param","span","div");
	    user_content_filter.addAttributes(":all", "style", "class", "id", "name");
	    user_content_filter.addAttributes("object", "width", "height","classid","codebase");    
	    user_content_filter.addAttributes("param", "name", "value");
	    user_content_filter.addAttributes("embed", "src","quality","width","height","allowFullScreen","allowScriptAccess","flashvars","name","type","pluginspage");
	}
	 
	
	
	
	
	/**
	 * 对用户输入内容进行过滤,用于普通的文本字段
	 * @param html
	 * @return
	 */
	public static String filterTextContent(String text) {
		if(StringUtils.isBlank(text)){
			return text;
		}
		text=StringEscapeUtils.escapeHtml4(text);
	    return text;
	}
	
	
	/**
	 * 对用户输入富文本内容进行过滤
	 * @param html
	 * @return
	 */
	public static String filterRichTextContent(String html) {
	   return filterRichTextContent(html, null);
	}
	
	
	/**
	 * 对用户输入富文本内容进行过滤
	 * @param html
	 * @param baseUrl
	 * @return
	 */
	public static String filterRichTextContent(String html,String baseUrl) {
	    if(StringUtils.isBlank(html)) {
	    	return html;
	    }
	    if(StringUtils.isBlank(baseUrl)){
	    	return Jsoup.clean(html, user_content_filter);
	    }
	    
	    return Jsoup.clean(html,baseUrl, user_content_filter);
	}
	
	
}
