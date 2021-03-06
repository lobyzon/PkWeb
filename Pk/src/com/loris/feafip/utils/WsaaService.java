package com.loris.feafip.utils;
// El Departamento de Seguridad Informatica de la AFIP (DeSeIn/AFIP), pone a disposicion
// el siguiente codigo para su utilizacion con el WebService de Autenticacion y Autorizacion
// de la AFIP.
//
// El mismo no puede ser re-distribuido, publicado o descargado en forma total o parcial, ya sea
// en forma electronica, mecanica u optica, sin la autorizacion de DeSeIn/AFIP. El uso no
// autorizado del mismo esta prohibido.
//
// DeSeIn/AFIP no asume ninguna responsabilidad de los errores que pueda contener el codigo ni la
// obligacion de subsanar dichos errores o informar de la existencia de los mismos.
//
// DeSeIn/AFIP no asume ninguna responsabilidad que surja de la utilizacion del codigo, ya sea por
// utilizacion ilegal de patentes, perdida de beneficios, perdida de informacion o cualquier otro
// inconveniente.
//
// Bajo ninguna circunstancia DeSeIn/AFIP podra ser indicada como responsable por consecuencias y/o
// incidentes ya sean directos o indirectos que puedan surgir de la utilizacion del codigo.
//
// DeSeIn/AFIP no da ninguna garantia, expresa o implicita, de la utilidad del codigo, si el mismo es
// correcto, o si cumple con los requerimientos de algun proposito en particular.
//
// DeSeIn/AFIP puede realizar cambios en cualquier momento en el codigo sin previo aviso.
//
// El codigo debera ser evaluado, verificado, corregido y/o adaptado por personal tecnico calificado
// de las entidades que lo utilicen.
//
// EL SIGUIENTE CODIGO ES DISTRIBUIDO PARA EVALUACION, CON TODOS SUS ERRORES Y OMISIONES. LA
// RESPONSABILIDAD DEL CORRECTO FUNCIONAMIENTO DEL MISMO YA SEA POR SI SOLO O COMO PARTE DE
// OTRA APLICACION, QUEDA A CARGO DE LAS ENTIDADES QUE LO UTILICEN. LA UTILIZACION DEL CODIGO
// SIGNIFICA LA ACEPTACION DE TODOS LOS TERMINOS Y CONDICIONES MENCIONADAS ANTERIORMENTE.
//
// Version 1.0
// gp/rg/OF.G. DeSeIn-AFIP
//

import java.io.Reader;
import java.io.StringReader;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class WsaaService {

	public WsaaToken getWsaaToken(){

		WsaaToken wsaaToken = new WsaaToken();
		String LoginTicketResponse = null;
				
		// Read config from phile
		Properties config = new Properties();
		
		try {
			config.load(getClass().getResourceAsStream("wsaa_client.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		String endpoint = config.getProperty("endpoint"); 
		String service = config.getProperty("service");
		String dstDN = config.getProperty("dstdn");
		
		String p12file = config.getProperty("keystore");
		String signer = config.getProperty("keystore-signer");
		String p12pass = config.getProperty("keystore-password");
				
		// Set the keystore used by SSL
		System.setProperty("javax.net.ssl.trustStore", config.getProperty("trustStore"));
		System.setProperty("javax.net.ssl.trustStorePassword",config.getProperty("trustStore_password")); 
		
		Long TicketTime = new Long(config.getProperty("TicketTime","36000"));
	
		// Create LoginTicketRequest_xml_cms
		WsaaAfipClient wsaaAfipClient = new WsaaAfipClient();
		byte [] LoginTicketRequest_xml_cms = wsaaAfipClient.create_cms(p12file, p12pass, signer, dstDN, service, TicketTime);
			
		// Invoke AFIP wsaa and get LoginTicketResponse
		try {
			LoginTicketResponse = wsaaAfipClient.invoke_wsaa ( LoginTicketRequest_xml_cms, endpoint );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Get token & sign from LoginTicketResponse
		try {
			Reader tokenReader = new StringReader(LoginTicketResponse);
			Document tokenDoc = new SAXReader(false).read(tokenReader);
			
			String token = tokenDoc.valueOf("/loginTicketResponse/credentials/token");
			String sign = tokenDoc.valueOf("/loginTicketResponse/credentials/sign");
			
			String generationTime = tokenDoc.valueOf("/loginTicketResponse/header/generationTime");
			String expirationTime = tokenDoc.valueOf("/loginTicketResponse/header/expirationTime");
			
			//Format Example "2009-07-16T19:20:30-05:00";
			
			wsaaToken.setToken(token);
			wsaaToken.setSign(sign);
			wsaaToken.setGenerationTime(generationTime);
			wsaaToken.setExpirationTime(expirationTime);
			
			return wsaaToken;
		} catch (Exception e) {
			System.out.println(e);
		}		
		
		return null;
	}
}