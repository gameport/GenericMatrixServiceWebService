package com.ram.spring.matrix.config;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Created by gamport.
 */
@EnableWs
@Configuration
public class MatrixWebServiceConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean
	public ServletRegistrationBean RsRegistrationBean(ApplicationContext applicationContext) {
		DispatcherServlet servlet = new DispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		return new ServletRegistrationBean(servlet, "/rest/*");
	}

	@Bean(name = "matrixOperations")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema matrixSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("MatrixPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://com.ram.spring/matrixOperations");
		wsdl11Definition.setSchema(matrixSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema matrixOperationsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/xsd/MatrixOperations.xsd"));
	}

	@Bean
	public XsdSchema matrixSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/xsd/matrix.xsd"));
	}
}
