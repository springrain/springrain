package org.springrain.frame.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.template.SimpleHash;


//@Component("springRainFreeMarkerView")
public class StaticHtmlFreeMarkerView extends FreeMarkerView {

	/**
	 * Process the model map by merging it with the FreeMarker template.
	 * Output is directed to the servlet response.
	 * <p>This method can be overridden if custom behavior is needed.
	 */
	@Override
	protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception{


		// Expose model to JSP tags (as request attributes).
		exposeModelAsRequestAttributes(model, request);
		// Expose all standard FreeMarker hash models.
		SimpleHash fmModel = buildTemplateModel(model, request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("Rendering FreeMarker template [" + getUrl() + "] in FreeMarkerView '" + getBeanName() + "'");
		}
		// Grab the locale-specific version of the template.
		Locale locale = RequestContextUtils.getLocale(request);
		
		
		String requestURI = request.getRequestURI();
		
		
		
		
		File htmlFile = new File("static/1.html");  
		if(!htmlFile.exists()){
			htmlFile.createNewFile();
		}
		
		
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile),"UTF-8"));
		
		getTemplate(locale).process(model, out);
		 out.flush();  
	     out.close(); 
	     
	     
	     
	     response.setContentType("text/html;charset=UTF-8");
	     request.setCharacterEncoding("UTF-8");
	     response.getWriter().write(IOUtils.toString(htmlFile.toURI(), "UTF-8"));
	     
		
		//processTemplate(getTemplate(locale), fmModel, response);
	
		
		
		
		
		
		
	}
}
