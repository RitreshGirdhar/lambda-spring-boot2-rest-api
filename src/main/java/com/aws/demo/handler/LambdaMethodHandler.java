package com.aws.demo.handler;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws.demo.controller.config.MvcConfig;
import com.aws.demo.model.GatewayResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//public class LambdaMethodHandler {
////	public String handleRequest(String input, Context context) {
////		context.getLogger().log("Input: " + input);
////		return "Hello World - " + input;
////	}
//
//	public Object handleRequest(final Object input, final Context context) {
//		Map<String, String> headers = new HashMap<>();
//		headers.put("Content-Type", "application/json");
//		headers.put("X-Custom-Header", "application/json");
//		try {
//			final String pageContents = this.getPageContents("https://checkip.amazonaws.com");
//			String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pageContents);
//			return new GatewayResponse(output, headers, 200);
//		} catch (IOException e) {
//			return new GatewayResponse("{}", headers, 500);
//		}
//	}
//
//	private String getPageContents(String address) throws IOException{
//		URL url = new URL(address);
//		try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
//			return br.lines().collect(Collectors.joining(System.lineSeparator()));
//		}
//	}
//}

public class LambdaMethodHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {

	private SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler = SpringLambdaContainerHandler.getAwsProxyHandler(MvcConfig.class);

	public LambdaMethodHandler() throws ContainerInitializationException {
	}

	@Override public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
		return handler.proxy(awsProxyRequest, context);
	}
}