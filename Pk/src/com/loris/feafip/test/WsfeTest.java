package com.loris.feafip.test;

import fev1.dif.afip.gov.ar.DummyResponse;
import fev1.dif.afip.gov.ar.Service;
import fev1.dif.afip.gov.ar.ServiceSoap;

public class WsfeTest {
	public static void main(String[] args) {
		Service serviceWsfe = new Service();
		ServiceSoap service = serviceWsfe.getPort(ServiceSoap.class);
		
		DummyResponse dummyResponse = service.feDummy();
		System.out.println("AppServer: " + dummyResponse.getAppServer() + " AuthServer: " + dummyResponse.getAuthServer()
				+ " DBServer: " + dummyResponse.getDbServer());
	}
}
