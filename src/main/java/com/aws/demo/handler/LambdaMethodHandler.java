package com.aws.demo.handler;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws.demo.DemoApplication;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class LambdaMethodHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {

	private SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

	public LambdaMethodHandler() throws ContainerInitializationException {
//		handler = SpringLambdaContainerHandler.getAwsProxyHandler(DemoApplication.class);
		if (handler == null) {
			try {
				SpringApplication springBootApplication = new SpringApplication(DemoApplication.class);
//				springBootApplication.setWebEnvironment(false);
				springBootApplication.setBannerMode(Banner.Mode.OFF);

				// create a new empty context and set the spring boot application as a parent of it
				ConfigurableWebApplicationContext wrappingContext = new AnnotationConfigWebApplicationContext();
				wrappingContext.setParent(springBootApplication.run());

				// now we can initialize the framework with the wrapping context
				handler = SpringLambdaContainerHandler.getAwsProxyHandler(wrappingContext);
			} catch (ContainerInitializationException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
		System.out.println("i am in handler "+handler);

		if (handler == null) {
			try {
				SpringApplication springBootApplication = new SpringApplication(DemoApplication.class);
				//				springBootApplication.setWebEnvironment(false);
				springBootApplication.setBannerMode(Banner.Mode.OFF);

				// create a new empty context and set the spring boot application as a parent of it
				ConfigurableWebApplicationContext wrappingContext = new AnnotationConfigWebApplicationContext();
				wrappingContext.setParent(springBootApplication.run());

				// now we can initialize the framework with the wrapping context
				handler = SpringLambdaContainerHandler.getAwsProxyHandler(wrappingContext);
			} catch (ContainerInitializationException e) {
				e.printStackTrace();
			}
		}

		System.out.println(awsProxyRequest.getBody());
		System.out.println(awsProxyRequest.getHttpMethod());

		return handler.proxy(awsProxyRequest, context);
	}

}