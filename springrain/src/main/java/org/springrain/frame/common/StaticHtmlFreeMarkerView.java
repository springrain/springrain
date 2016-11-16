package org.springrain.frame.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.SpringUtils;

import freemarker.template.SimpleHash;
import freemarker.template.Template;


//@Component("staticHtmlFreeMarkerView")
public class StaticHtmlFreeMarkerView extends FreeMarkerView {
	
	private CacheManager cacheManager=null;
	private Cache cache=null;

	/**
	 * Process the model map by merging it with the FreeMarker template.
	 * Output is directed to the servlet response.
	 * <p>This method can be overridden if custom behavior is needed.
	 */
	@Override
	protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception{

		if(cacheManager==null){
		 cacheManager=(CacheManager) SpringUtils.getBeanByType(CacheManager.class);
		 cache = cacheManager.getCache(GlobalStatic.staticHtmlCacheKey);
		}

		// Expose model to JSP tags (as request attributes).
		exposeModelAsRequestAttributes(model, request);
		// Expose all standard FreeMarker hash models.
		SimpleHash fmModel = buildTemplateModel(model, request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("Rendering FreeMarker template [" + getUrl() + "] in FreeMarkerView '" + getBeanName() + "'");
		}
		// Grab the locale-specific version of the template.
		Locale locale = RequestContextUtils.getLocale(request);
		
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		int i=uri.indexOf(contextPath);
		if(i>-1){
			uri=uri.substring(i+contextPath.length());
		}
		
		
		//cache key,可以根据URI从数据库进行查询资源Id
		String htmlCacheKey="findHtmlPathByURI_"+uri;
				
		String htmlPath=cache.get(htmlCacheKey, String.class);
		
		
		Template template=getTemplate(locale);
		if(StringUtils.isBlank(htmlPath)||htmlPath.equals("error")){//缓存中不存在
			processTemplate(template, fmModel, response);
			return;
		}
		
		File htmlFile = new File(htmlPath);  
		if(htmlFile.exists()){
			response.setContentType("text/html;charset="+GlobalStatic.defaultCharset);
			response.setCharacterEncoding(GlobalStatic.defaultCharset);
		    response.getWriter().write(IOUtils.toString(htmlFile.toURI(), GlobalStatic.defaultCharset));
		}else{
			createHtml(htmlFile, htmlCacheKey, fmModel, model, template, response);
		}
		
		
	}
	/**
	 * 生成静态文件
	 * @param htmlFile
	 * @param htmlCacheKey
	 * @param fmModel
	 * @param model
	 * @param template
	 * @param response
	 * @throws Exception
	 */
	private synchronized void createHtml(File htmlFile,String htmlCacheKey,SimpleHash fmModel,Map<String, Object> model, Template template, HttpServletResponse response)throws Exception{
		String htmlPath=cache.get(htmlCacheKey, String.class);
		if(StringUtils.isBlank(htmlPath)||"error".equals(htmlPath)){//缓存中不存在
			processTemplate(template, fmModel, response);
			return;
		}
		//生成文件 开始期间
		cache.put(htmlCacheKey, "error");
		htmlFile.createNewFile();
		
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile),GlobalStatic.defaultCharset));
		template.process(model, out);
		out.flush();  
	    out.close(); 
	    //生成文件 结束
		cache.put(htmlCacheKey, htmlPath);
		//输出文件
		response.setContentType("text/html;charset="+GlobalStatic.defaultCharset);
		response.setCharacterEncoding(GlobalStatic.defaultCharset);
	    response.getWriter().write(IOUtils.toString(htmlFile.toURI(), GlobalStatic.defaultCharset));
		
	}
	
	
	
	
	
}