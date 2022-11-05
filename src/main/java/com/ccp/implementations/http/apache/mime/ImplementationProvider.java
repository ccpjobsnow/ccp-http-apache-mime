package com.ccp.implementations.http.apache.mime;

import com.ccp.dependency.injection.CcpImplementationProvider;

public class ImplementationProvider implements CcpImplementationProvider {

	@Override
	public Object getImplementation() {
		return new HttpRequesterApacheMime();
	}

}
