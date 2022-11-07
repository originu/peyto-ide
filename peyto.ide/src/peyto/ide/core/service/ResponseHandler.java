package peyto.ide.core.service;

import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;

public interface ResponseHandler {
	
	public void completed(final SimpleHttpResponse response);
	
	default public void failed(final Exception ex) {
	}
	
	default public void cancelled() {
	}
}