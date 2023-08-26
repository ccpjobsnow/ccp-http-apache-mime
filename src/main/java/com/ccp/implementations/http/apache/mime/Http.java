package com.ccp.implementations.http.apache.mime;

import com.ccp.dependency.injection.CcpInstanceProvider;

public class Http implements CcpInstanceProvider {

	@Override
	public Object getInstance() {
		return new HttpRequesterApacheMime();
	}

}// meu teste 
