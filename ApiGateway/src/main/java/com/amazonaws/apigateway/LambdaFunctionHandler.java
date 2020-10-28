package com.amazonaws.apigateway;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.dynamodb.Stackholder;
import com.amazonaws.dynamodb.StackholderRepository;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;

public class LambdaFunctionHandler
		implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

		APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
		StackholderRepository repository = new StackholderRepository();
		Map<Object, Object> responseBody = new HashMap<Object, Object>();
		String respone ;

		try {
			System.out.println("input:::::" + input);
			if (input.getPathParameters() != null) {
				Map<String, String> pathParameters = input.getPathParameters();
				System.out.println(pathParameters);
				if (input.getHttpMethod().contains("DELETE")) {
					responseBody.put("respone", new JSONParser()
							.parse(new Gson().toJson(repository.deleteStackholder(pathParameters.get("shId")))));
				} else {
					responseBody.put("respone", (JSONObject) new JSONParser()
							.parse(new Gson().toJson(repository.getStackholder(pathParameters.get("shId")))));
				}
			} else if (input.getBody() != null) {
				String requestString = input.getBody();

				JSONParser parser = new JSONParser();

				JSONObject requestJsonObject = (JSONObject) parser.parse(requestString);

				Gson gson = new Gson();

				Stackholder stackholder = gson.fromJson(requestJsonObject.toJSONString(), Stackholder.class);

				if (input.getHttpMethod().contains("POST")) {
					responseBody.put("respone", (JSONObject) new JSONParser()
							.parse(new Gson().toJson(repository.addStackholder(stackholder))));
				} else if (input.getHttpMethod().contains("PUT")) {
					responseBody.put("respone", (JSONObject) new JSONParser()
							.parse(new Gson().toJson(repository.addStackholder(stackholder))));
				} else {
					respone = "Method not accepted";
				}

			} else {
				responseBody.put("respone", new JSONParser().parse(new Gson().toJson(repository.getAllStackholders())));
			}

			System.out.println(responseBody.get("response"));

//            String requestMessage = null;
//            String responseMessage = null;
//            if (requestJsonObject != null) {
//                if (requestJsonObject.get("requestMessage") != null) {
//                    requestMessage = requestJsonObject.get("requestMessage").toString();
//                }
//            }
//            responseMessage = new JSONObject(responseBody).toJSONString();

			generateResponse(apiGatewayProxyResponseEvent, new JSONObject(responseBody).toJSONString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return apiGatewayProxyResponseEvent;
	}

	private void generateResponse(APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent, String requestMessage) {
		apiGatewayProxyResponseEvent
				.setHeaders(Collections.singletonMap("timeStamp", String.valueOf(System.currentTimeMillis())));
		apiGatewayProxyResponseEvent.setHeaders(Collections.singletonMap("Access-Control-Expose-Headers",
				"Access-Control-*, Origin, X-Requested-With, Content-Type, Accept, Authorization"));
		apiGatewayProxyResponseEvent
				.setHeaders(Collections.singletonMap("Access-Control-Allow-Origin", "http://localhost:8080"));
		apiGatewayProxyResponseEvent
				.setHeaders(Collections.singletonMap("Access-Control-Allow-Origin", "http://localhost:3000"));
		apiGatewayProxyResponseEvent.setHeaders(Collections.singletonMap("Access-Control-Allow-Methods",
				"HEAD, GET, POST, OPTIONS, PUT, PATCH, DELETE"));
		apiGatewayProxyResponseEvent.setHeaders(Collections.singletonMap("Access-Control-Allow-Headers",
				"Access-Control-*, Origin, X-Requested-With, Content-Type, Accept, Authorization"));
		apiGatewayProxyResponseEvent.setHeaders(Collections.singletonMap("Access-Control-Allow-Credentials", "true"));

		apiGatewayProxyResponseEvent.setStatusCode(200);
		apiGatewayProxyResponseEvent.setBody(requestMessage);
	}

}
