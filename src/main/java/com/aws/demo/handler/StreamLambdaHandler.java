package com.aws.demo.handler;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.aws.demo.DemoApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamLambdaHandler implements RequestStreamHandler {

	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	private static ObjectMapper mapper = new ObjectMapper();

//	static {
//		try {
//			AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
//			applicationContext.register(DemoApplication.class);
//			handler = SpringLambdaContainerHandler.getAwsProxyHandler(applicationContext);
//		} catch (ContainerInitializationException e) {
//			e.printStackTrace();
//			throw new RuntimeException("Could not initialize Spring Boot application", e);
//		}
//	}

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
			throws IOException {
		if (handler == null) {
			try {
				handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(DemoApplication.class);
			} catch (ContainerInitializationException e) {
				e.printStackTrace();
				outputStream.close();
				return;
			}
		}
		AwsProxyRequest request = mapper.readValue(inputStream, AwsProxyRequest.class);
		AwsProxyResponse resp = handler.proxy(request, context);
		mapper.writeValue(outputStream, resp);
		// just in case it wasn't closed by the mapper
		outputStream.close();
	}
}