package com.ccp.implementations.http.apache.mime;

import com.ccp.dependency.injection.CcpInstanceProvider;

public class CcpApacheMimeHttp implements CcpInstanceProvider {

	
	public Object getInstance() {
		return new ApacheMimeHttpRequester();
	}

}// meu teste 
