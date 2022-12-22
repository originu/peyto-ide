package peyto.ide.core.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.async.methods.SimpleRequestBuilder;
import org.apache.hc.client5.http.async.methods.SimpleRequestProducer;
import org.apache.hc.client5.http.async.methods.SimpleResponseConsumer;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;
import org.eclipse.swt.widgets.Display;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import peyto.ide.core.util.JsonUtil;;

@Service	
public class HttpService {

	private CloseableHttpAsyncClient client;
	
	private String peytoServerUrl = "http://localhost:9090/peyto-ide";

	@PostConstruct
	public void startup() {
		final IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
				.setSoTimeout(Timeout.ofSeconds(20))
				.build();
		
		client = HttpAsyncClients.custom()
				.setIOReactorConfig(ioReactorConfig)
				.build();
		
		client.start();
	}

	@PreDestroy
	public void shutdown() {
        client.close(CloseMode.GRACEFUL);
	}
	
	public <T> void get(String resourcePath, ResponseHandler handler) {
        SimpleHttpRequest request = SimpleRequestBuilder
        		.get(peytoServerUrl + resourcePath)
        		.build();
		process(handler, request);
	}
	
	public <T> void post(String resourcePath, Object body, ResponseHandler handler) {
		JsonNode rawBody = JsonUtil.MAPPER.convertValue(body, JsonNode.class);
		SimpleHttpRequest request = SimpleRequestBuilder
				.post(peytoServerUrl + resourcePath)
				.setBody(rawBody.toString(), ContentType.APPLICATION_JSON)
				.build();
		process(handler, request);
	}

	public <T> void put(String resourcePath, Object body, ResponseHandler handler) {
		JsonNode rawBody = JsonUtil.MAPPER.convertValue(body, JsonNode.class);
		SimpleHttpRequest request = SimpleRequestBuilder
				.put(peytoServerUrl + resourcePath)
				.setBody(rawBody.toString(), ContentType.APPLICATION_JSON)
				.build();
		process(handler, request);
	}
	
	public <T> void delete(String resourcePath, ResponseHandler handler) {
		SimpleHttpRequest request = SimpleRequestBuilder
				.delete(peytoServerUrl + resourcePath)
				.build();
		process(handler, request);
	}

	public <T> void delete(String resourcePath, Object body, ResponseHandler handler) {
		JsonNode rawBody = JsonUtil.MAPPER.convertValue(body, JsonNode.class);
		SimpleHttpRequest request = SimpleRequestBuilder
				.delete(peytoServerUrl + resourcePath)
				.setBody(rawBody.toString(), ContentType.APPLICATION_JSON)
				.build();
		process(handler, request);
	}

	private void process(ResponseHandler handler, SimpleHttpRequest request) {
		final Future<SimpleHttpResponse> future = client.execute(
				SimpleRequestProducer.create(request),
				SimpleResponseConsumer.create(),
				new FutureCallback<SimpleHttpResponse>() {
					@Override
					public void completed(final SimpleHttpResponse response) {
//        				System.out.println(request + "->" + new StatusLine(response));
//        				System.out.println(response.getBody());
//        				System.out.println(response.getBody().getBodyText());
						try {
							Display.getDefault().asyncExec( new Runnable() {
								@Override
								public void run() {
									handler.completed(response);
								}
							});
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					@Override
					public void failed(final Exception ex) {
						// System.out.println(request + "->" + ex);
						handler.failed(ex);;
					}
					
					@Override
					public void cancelled() {
						// System.out.println(request + " cancelled");
						handler.cancelled();
					}
					
				});
		try {
			future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
