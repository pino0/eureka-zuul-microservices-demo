package com.globant.microservicedemo;

import com.globant.microservicedemo.controller.ConsumerControllerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EurekaConsumerApplication {

	public static void main(String[] args) throws RestClientException, IOException {
		ApplicationContext ctx = SpringApplication.run(
				EurekaConsumerApplication.class, args);

		ConsumerControllerClient consumerControllerClient=ctx.getBean(ConsumerControllerClient.class);
		System.out.println(consumerControllerClient);

		consumerControllerClient.getAllHeaders();

		List<String> list = Arrays.asList("accept","host");
		consumerControllerClient.getHeadersByName(list);
	}

	@Bean
	public  ConsumerControllerClient  consumerControllerClient()
	{
		return  new ConsumerControllerClient();
	}
}