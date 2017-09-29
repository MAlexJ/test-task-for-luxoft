package com.malex.initializer;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


/**
 * Init APP
 */
public class AppInitializer implements WebApplicationInitializer {

	/**
	 * MAX_FILE_SIZE the maximum size allowed for uploaded files
	 */
	private final static long MAX_FILE_SIZE = 2000000L;

	/**
	 * MAX_REQUEST_SIZE the maximum size allowed for
	 * multipart/form-data requests
	 */
	private final static long MAX_REQUEST_SIZE = 10000000L;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext context = getContext();
		servletContext.addListener(new ContextLoaderListener(context));

		servletContext.addFilter("springSecurityFilterChain",
				  new DelegatingFilterProxy("springSecurityFilterChain"))
				  .addMappingForUrlPatterns(null, false, "/*");

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		String MAPPING_URL = "/";
		dispatcher.addMapping(MAPPING_URL);
		dispatcher.setMultipartConfig(getMultiPartConfigElement());
	}

	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.malex.configuration");
		return context;
	}

	private MultipartConfigElement getMultiPartConfigElement() {
		return new MultipartConfigElement("", MAX_FILE_SIZE, MAX_REQUEST_SIZE, 0);
	}

}