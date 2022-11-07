package peyto.ide.core.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.async.methods.SimpleRequestBuilder;
import org.apache.hc.client5.http.async.methods.SimpleRequestProducer;
import org.apache.hc.client5.http.async.methods.SimpleResponseConsumer;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;
import org.eclipse.swt.widgets.Display;


	
public class HttpService {

	private CloseableHttpAsyncClient client;
	
	public void startup() {
		final IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
				.setSoTimeout(Timeout.ofSeconds(20))
				.build();
		
		client = HttpAsyncClients.custom()
				.setIOReactorConfig(ioReactorConfig)
				.build();
		
		client.start();
	}

	public void shutdown() {
        client.close(CloseMode.GRACEFUL);
	}
	
	public <T> void callAsync( String resourcePath, ResponseHandler handler ) {
//		http://localhost:9090/peyto-ide/api/db/catalog/postgres/schema/public
        final HttpHost target = new HttpHost("http://localhost:9090");
//        final HttpHost target = new HttpHost("httpbin.org");
        String url = "http://localhost:9090/peyto-ide" + resourcePath;
        final SimpleHttpRequest request = SimpleRequestBuilder.get(url).build();
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
