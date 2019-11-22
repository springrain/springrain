package org.springrain.config;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springrain.SpringrainApplication;
import org.springrain.frame.common.SpringMVCAnnotationBeanNameGenerator;
import org.springrain.frame.common.StaticHtmlFreeMarkerView;
import org.springrain.frame.util.FrameObjectMapper;

/**
 * MVC的配置,只扫描@Controller注解,带包名命名,避免混淆同名问题.
 * 
 * @author caomei
 *
 */

@Configuration("configuration-SpringMVCConfig")
@ComponentScan(basePackageClasses = SpringrainApplication.class, nameGenerator = SpringMVCAnnotationBeanNameGenerator.class, useDefaultFilters = false, includeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Controller.class) })
public class SpringMVCConfig implements WebMvcConfigurer {

	Charset charset = Charset.forName("UTF-8");

	/*
	 * @Bean public RequestMappingHandlerMapping customMappingHandlerMapping() {
	 * FrameRequestMappingHandlerMapping customMappingHandlerMapping=new
	 * FrameRequestMappingHandlerMapping(); // RequestMappingHandlerMapping
	 * customMappingHandlerMapping=new RequestMappingHandlerMapping(); return
	 * customMappingHandlerMapping; }
	 */

	/**
	 * 重新注册自定义的消息转化
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
		converters.add(stringHttpMessageConverter());
		converters.add(resourceHttpMessageConverter());
		converters.add(byteArrayHttpMessageConverter());

	}

	/*
	 * @Bean("customConverters") public HttpMessageConverters customConverters() {
	 * return new HttpMessageConverters(false, getHttpMessageConverters()); }
	 * 
	 * 
	 * private List<HttpMessageConverter<?>> getHttpMessageConverters() {
	 * List<HttpMessageConverter<?>> converters=new ArrayList<>();
	 * converters.add(mappingJackson2HttpMessageConverter());
	 * converters.add(stringHttpMessageConverter());
	 * converters.add(resourceHttpMessageConverter());
	 * converters.add(byteArrayHttpMessageConverter()); return converters; }
	 * 
	 * @Bean("requestMappingHandlerAdapter") public RequestMappingHandlerAdapter
	 * requestMappingHandlerAdapter() { RequestMappingHandlerAdapter
	 * requestMappingHandlerAdapter=new RequestMappingHandlerAdapter();
	 * requestMappingHandlerAdapter.setMessageConverters(getHttpMessageConverters())
	 * ; return requestMappingHandlerAdapter;
	 * 
	 * }
	 * 
	 * @Bean("exceptionHandlerExceptionResolver") public
	 * ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
	 * ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver=new
	 * ExceptionHandlerExceptionResolver();
	 * exceptionHandlerExceptionResolver.setMessageConverters(
	 * getHttpMessageConverters()); return exceptionHandlerExceptionResolver; }
	 * 
	 * 
	 * @Override public void configureViewResolvers(ViewResolverRegistry registry) {
	 * registry.viewResolver(freeMarkerViewResolver()); }
	 */

	/**
	 * 配置freemarker作为视图,org.springframework.boot.autoconfigure.freemarker.FreeMarkerReactiveWebConfiguration默认注册了bean="freeMarkerViewResolver"的配置,替换默认的bean
	 * 
	 * @return
	 */
	// @Bean("viewResolver")
	@Bean("freeMarkerViewResolver")
	public ViewResolver freeMarkerViewResolver() {
		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();

		freeMarkerViewResolver.setViewClass(StaticHtmlFreeMarkerView.class);
		freeMarkerViewResolver.setRequestContextAttribute("ctp");
		freeMarkerViewResolver.setCache(true);
		freeMarkerViewResolver.setSuffix(".html");
		freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
		return freeMarkerViewResolver;
	}

	/**
	 * 上传文件的配置
	 * 
	 * @return
	 */

	/*
	 * @Bean public CommonsMultipartResolver commonsMultipartResolver() {
	 * CommonsMultipartResolver commonsMultipartResolver=new
	 * CommonsMultipartResolver(); //默认编码 (ISO-8859-1)
	 * commonsMultipartResolver.setDefaultEncoding("UTF-8"); //最大内存大小 (10240)
	 * commonsMultipartResolver.setMaxInMemorySize(10240); //
	 * commonsMultipartResolver.setUploadTempDir(); // 最大文件大小，-1为无限止(-1)
	 * commonsMultipartResolver.setMaxUploadSize(10485760); return
	 * commonsMultipartResolver;
	 * 
	 * }
	 */

	/**
	 * 声明freeMarkerConfigurer,Service要用生成静态文件.
	 * 
	 * @return
	 */
	@Bean("freeMarkerConfigurer")
	public FreeMarkerConfigurer freeMarkerConfigurer() {

		Properties settings = new Properties();
		settings.setProperty("template_update_delay", "0");
		settings.setProperty("default_encoding", "UTF-8");
		settings.setProperty("url_escaping_charset", "UTF-8");
		settings.setProperty("date_format", "yyyy-MM-dd");
		settings.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
		// settings.setProperty("locale", "zh_CN");
		settings.setProperty("whitespace_stripping", "true");
		settings.setProperty("tag_syntax", "auto_detect");
		settings.setProperty("number_format", "#.##");
		settings.setProperty("auto_import", "/common/head.html as h");

		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();

		// freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
		freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/freemarker/");
		freeMarkerConfigurer.setDefaultEncoding("UTF-8");
		freeMarkerConfigurer.setFreemarkerSettings(settings);

		return freeMarkerConfigurer;
	}

	/**
	 * 配置静态文件
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/img/**").addResourceLocations("/img/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/layui/**").addResourceLocations("/layui/");
		registry.addResourceHandler("/upload/**").addResourceLocations("/upload/");
		registry.addResourceHandler("/u/**").addResourceLocations("/u/");
	}

	/**
	 * 使用Bean的方式,用于替换默认的实现
	 * 
	 * @return
	 */

	@Bean("mappingJackson2HttpMessageConverter")
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setDefaultCharset(charset);
		mappingJackson2HttpMessageConverter.setObjectMapper(new FrameObjectMapper());
		return mappingJackson2HttpMessageConverter;
	}

	@Bean("stringHttpMessageConverter")
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(charset);
		return stringHttpMessageConverter;
	}

	@Bean("resourceHttpMessageConverter")
	public ResourceHttpMessageConverter resourceHttpMessageConverter() {
		ResourceHttpMessageConverter resourceHttpMessageConverter = new ResourceHttpMessageConverter();
		return resourceHttpMessageConverter;
	}

	@Bean("byteArrayHttpMessageConverter")
	public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
		ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
		return byteArrayHttpMessageConverter;
	}

}
