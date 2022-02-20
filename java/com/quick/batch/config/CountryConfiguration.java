package com.quick.batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.quick.batch.soap.client.NumberToDollarsClient;

@Configuration
public class CountryConfiguration {

  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    // this package must match the package in the <generatePackage> specified in
    // pom.xml
    marshaller.setContextPath("com.quick.batch");
    return marshaller;
  }

  @Bean
  public NumberToDollarsClient countryClient(Jaxb2Marshaller marshaller) {
	NumberToDollarsClient client = new NumberToDollarsClient();
    client.setDefaultUri("https://www.dataaccess.com/webservicesserver");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }

}