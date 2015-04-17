<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<table align="center" border="0" cellspacing="1" cellpadding="1" width="800" height="100%">
			<tr>
				<td class="upperTitle" height="20" colspan="2"><i>Emilio A. LORIS</i></td>
			</tr>
			<tr>
				<td width="220">
					<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
						<tr height="80">
							<td class="title"><b>Menu</b></td>
						</tr>
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/marca/create.htm">Marcas</a></td>
						</tr>
						<tr height="40">							
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/familia.htm">Familias</a></td>							
						</tr>
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/articulo.htm">Articulos</a></td>
						</tr>						
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/clientes.htm">Clientes</a></td>
						</tr>
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/listas.htm">Listas de Precios</a></td>
						</tr>
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/facturacion.htm">Facturación</a></td>
						</tr>
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/estadisticas.htm">Estadísticas</a></td>
						</tr>
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/backup.htm">Copia de Seguridad</a></td>
						</tr>
						<tr>
							<td height="100%" class="labelStyle">&nbsp;</td>
						</tr>
					</table>
				</td>
				<td width="580">