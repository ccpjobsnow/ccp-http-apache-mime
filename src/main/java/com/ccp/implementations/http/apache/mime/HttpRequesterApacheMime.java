package com.ccp.implementations.http.apache.mime;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.ccp.decorators.CcpMapDecorator;
import com.ccp.especifications.http.CcpHttpRequester;
import com.ccp.especifications.http.CcpHttpResponse;

class HttpRequesterApacheMime implements CcpHttpRequester {

	@Override
	public CcpHttpResponse executeHttpRequest(String url, String method, CcpMapDecorator headers, String body) {
		HttpMethod verb = HttpMethod.valueOf(method);
		HttpRequestBase metodo = verb.getMethod(url, body);
		try {
			CloseableHttpClient client =CcpHttpRequestRetryHandler.getClient();

			CloseableHttpResponse response = client.execute(metodo);

			HttpEntity entity = response.getEntity();
	
			String string = EntityUtils.toString(entity);
			
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			CcpHttpResponse ccpHttpResponse = new CcpHttpResponse(string, statusCode);
			return ccpHttpResponse;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
