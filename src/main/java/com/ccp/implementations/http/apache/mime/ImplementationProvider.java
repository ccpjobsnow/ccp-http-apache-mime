package com.ccp.implementations.http.apache.mime;

import com.ccp.dependency.injection.CcpEspecification.DefaultImplementationProvider;

public class ImplementationProvider extends DefaultImplementationProvider {

	@Override
	public Object getImplementation() {
		return new HttpRequesterApacheMime();
	}

}
