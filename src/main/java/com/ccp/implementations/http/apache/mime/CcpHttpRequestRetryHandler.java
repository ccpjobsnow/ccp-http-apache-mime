package com.ccp.implementations.http.apache.mime;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
class CcpHttpRequestRetryHandler implements HttpRequestRetryHandler {

	@Override
	public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        if (executionCount >= 3) {
            // Do not retry if over max retry count
            return false;
        }
        if (exception instanceof InterruptedIOException) {
            // Timeout
            return false;
        }
        if (exception instanceof UnknownHostException) {
            // Unknown host
            return false;
        }
        if (exception instanceof ConnectTimeoutException) {
            // Connection refused
            return false;
        }
        if (exception instanceof SSLException) {
            // SSL handshake exception
            return false;
        }
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        HttpRequest request = clientContext.getRequest();
        boolean b = false == (request instanceof HttpEntityEnclosingRequest);
		return b;
	}

	static CloseableHttpClient getClient() throws Exception{
		 TrustStrategy trustStrategy = new TrustStrategy() {
		      @Override
		      public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
		        return true;
		      }
		    };

		    SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(null, trustStrategy).build();
		    SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext);

		    return HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();
		  }
}
