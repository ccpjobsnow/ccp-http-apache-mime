package com.ccp.implementations.http.apache.mime;

import com.ccp.dependency.injection.CcpModuleExporter;

public class Http implements CcpModuleExporter {

	@Override
	public Object export() {
		return new HttpRequesterApacheMime();
	}

}
