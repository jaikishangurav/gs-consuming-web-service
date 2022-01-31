
package com.example.consumingwebservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

@Configuration
public class CountryConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.example.consumingwebservice.wsdl");
		return marshaller;
	}

	@Bean
	public CountryClient countryClient(Jaxb2Marshaller marshaller) {
		CountryClient client = new CountryClient();
		client.setDefaultUri("http://localhost:8080/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		// register the LogHttpHeaderClientInterceptor
		ClientInterceptor[] interceptors =
				new ClientInterceptor[] {new LogHttpHeaderClientInterceptor()};
		client.setInterceptors(interceptors);
		client.setMessageSender();
		return client;
	}

//	@Bean
//	public WebServiceTemplate webServiceTemplate() {
//		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
//		webServiceTemplate.setMarshaller(jaxb2Marshaller());
//		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
//		webServiceTemplate.setDefaultUri("http://localhost:8080/ws");
//
//		// register the LogHttpHeaderClientInterceptor
//		ClientInterceptor[] interceptors =
//				new ClientInterceptor[] {new LogHttpHeaderClientInterceptor()};
//		webServiceTemplate.setInterceptors(interceptors);
//
//		return webServiceTemplate;
//	}
//
//	@Bean
//	Jaxb2Marshaller jaxb2Marshaller() {
//		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
//		jaxb2Marshaller.setContextPath("org.example.ticketagent");
//
//		return jaxb2Marshaller;
//	}


}
