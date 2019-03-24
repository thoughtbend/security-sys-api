package com.thoughtbend.securitysysapi;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecuritySysApiApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SecuritySysApiApplication.class);
	
	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {

			@Override
			public void contextInitialized(ServletContextEvent sce) {
				LOG.info("ServletContext initialized");
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				LOG.info("ServletContext destroyed");
			}

		};
	}
	
	@Bean
	protected ServletWebServerFactory servletContainer() {
		
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		
		tomcat.addAdditionalTomcatConnectors(ajpConnector());
		
		return tomcat;
	}
	
	private Connector ajpConnector() {
		
		Connector connector = new Connector("AJP/1.3");
		connector.setScheme("http");
		connector.setPort(9004);
		connector.setSecure(false);
		connector.setAllowTrace(false);
		
		return connector;
	}

	public static void main(String[] args) {
		SpringApplication.run(SecuritySysApiApplication.class);
	}

}
