<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/birt.tld" prefix="birt" %>
<html>
	<head>				
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="-1">		
		<title>PK WEB</title>			
		<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">		
		<link href="<c:out value="${pageContext.request.contextPath}"/>/jsp/css/pk.css" rel="stylesheet" type="text/css"></link>		
		<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/jquery.js"></script>
		<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/labs_json.js"></script>
		<script type="text/javascript">$.ajaxSetup({cache: false});</script>
		<style>
			.error {
			color: #ff0000;
			font-style: italic;
			}
		</style>		
	</head>
	<body class="body">
		<table align="center" border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
			<tr>
				<td class="upperTitle" height="20" colspan="2"><i>Emilio A. LORIS</i></td>
			</tr>
				<td width="100%">

					<table border="0" cellspacing="1" cellpadding="1" width="100%">		
						<tr style="text-align: center"> 
							<td style="text-align: center">
								<birt:viewer id="feOriginal" title="Factura Electronica" forceOverwriteDocument="true" width="1050" height="600" reportDesign="http://localhost:8080/Pk/report/feLorisOriginal.rptdesign" format="pdf"></birt:viewer>
							</td>
						</tr>
					</table>
					
				</td>
			</tr>
			<tr>
				<td class="upperTitle" height="20" colspan="2" align="center">© Copyright LOBYZON</td>
			</tr>
		</table>
	</body>		
</html>