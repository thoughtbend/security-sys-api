package com.thoughtbend.securitysysapi;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	
	public static void main(String[] args) {
		SpringApplication.run(SecuritySysApiApplication.class);
	}

}
