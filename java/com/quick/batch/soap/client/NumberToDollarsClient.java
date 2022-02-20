package com.quick.batch.soap.client;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.quick.batch.NumberToDollars;
import com.quick.batch.NumberToDollarsResponse;

public class NumberToDollarsClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(NumberToDollarsClient.class);

	public NumberToDollarsResponse getDollars(int number) {

		NumberToDollars request = new NumberToDollars();
		request.setDNum(BigDecimal.valueOf(number));

		log.info("Requesting Dollar for the number " + number);

		NumberToDollarsResponse response = (NumberToDollarsResponse) getWebServiceTemplate().marshalSendAndReceive(
				"https://www.dataaccess.com/webservicesserver/NumberConversion.wso", request,
				new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));

		return response;
	}

}