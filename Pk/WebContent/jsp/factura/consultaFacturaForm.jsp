<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
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
		<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>		
	</head>
	<body class="body">
		<table align="center" border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
			<tr>
				<td class="upperTitle" height="20" colspan="8"><i>Emilio A. LORIS</i></td>
			</tr>			
			<tr height="50">
				<td class="title" colspan="8"><b>Menu</b></td>
			</tr>
			<tr height="30">
				<td class="title" colspan="8">
					<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
						<tr>
							<td class="labelStyle" style="text-align: center;">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/marca/create.htm">Marcas</a>
							</td>
							<td class="labelStyle" style="text-align: center;">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/familia.htm">Familias</a>
							</td>					
							<td class="labelStyle" style="text-align: center;">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/articulo.htm">Articulos</a>
							</td>
							<td class="labelStyle" style="text-align: center;">				
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/clientes.htm">Clientes</a>
							</td>	
							<td class="labelStyle" style="text-align: center;">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/listas.htm">Listas de Precios</a>
							</td>
							<td class="labelStyle" style="text-align: center;">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/facturacion.htm">Facturación</a>
							</td>
							<td class="labelStyle">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/estadisticas.htm">Estadísticas</a>
							</td>											
							<td class="labelStyle">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/backup.htm">Copia de Seguridad</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>						
			<tr>
				<td height="10%" class="labelStyle" colspan="8">&nbsp;</td>
			</tr>									
			<form:form method="POST" commandName="factura">
				<tr>
					<td height="50" class="title" colspan="8"><b>Consulta Factura</b></td>
				</tr>				
				<tr>
					<td class="title" height="5" style="text-align: right;" colspan="6">Tipo Factura:</td>
					<td width="15%">
						<form:select path="facturaType.facturaTypeId" id="tipoFactura">
							<option value="<%=com.loris.domain.FacturaType.FACTURA_TYPE_ELECTRONIC%>">Factura Electrónica</option>
							<option value="<%=com.loris.domain.FacturaType.FACTURA_TYPE%>">Factura Manual</option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="title" height="5" colspan="6" style="text-align: right;">Nro. Factura:</td>
					<td>
						<form:input path="nroFactura" size="8" id="nroFactura" onblur="showFactura()"/>						
					</td>
					<td><form:errors path="nroFactura" cssClass="error" /></td>
					<td><input type="hidden" name="typeF" id="typeF"/></td>					
				</tr>
				<tr>
					<td class="labelStyle" colspan="8"><input type="submit" value="Consultar"></td>
				</tr>
				<tr>
					<td class="emptyColumn" colSpan="8">&nbsp;</td>
				</tr>
				<c:if test="${factura.cliente != null}">
					<tr>
						<td class="title" height="5">Cliente:</td>
						<td class="coutLeft"><c:out value="${factura.cliente.id}"/></td>											
					</tr>
					<tr>
						<td class="title" height="5">Razón Social:</td>
						<td class="coutLeft"><c:out value="${factura.cliente.razonSocial}"/></td>
					</tr>					
					<tr>
						<td class="title" height="5">CUIT :</td>
						<td class="coutLeft"><c:out value="${factura.cliente.cuit}"/></td>						
					</tr>
					<tr>
						<td class="title" height="5">Regimen :</td>
						<td class="coutLeft"><c:out value="${factura.cliente.regimenForPrint}"/></td>						
					</tr>
					<tr>
						<td class="title" height="5">Descuento :</td>
						<td class="coutLeft"><c:out value="${factura.cliente.descuento}"/></td>						
					</tr>				
					<tr>
						<td class="emptyColumn" colSpan="8">&nbsp;</td>
					</tr>
					<tr>
						<td class="title" height="5">Marca</td>
						<td class="title" height="5">Familia</td>
						<td class="title" height="5">Cod Art.</td>
						<td class="title" height="5">Cantidad</td>
						<td class="title" height="5">Precio</td>
						<td class="title" height="5">Descuento</td>
						<td class="title" height="5" colspan="2">Total</td>
					</tr>
					<c:forEach items="${factura.items}" var="item">					
						<tr class="cout">
							<td>
								<c:out value="${item.articulo.marca.marcaComboDesc}"/>
							</td>
							<td>
								<c:out value="${item.articulo.familia.familiaComboDesc}"></c:out>
							</td>
							<td>
								<c:out value="${item.articulo.articuloComboDesc}"></c:out>
							</td>
							<td class="cout">
								<c:out value="${item.cantidad}"></c:out>
							</td>
							<td class="cout">
								<c:out value="${item.precio}"></c:out>
							</td>
							<td class="cout">
								<c:out value="${item.descuento}"></c:out>
							</td>
							<td class="cout" colspan="2">
								<c:out value="${item.totalItem}"></c:out>
							</td>													
						</tr>
					</c:forEach>
					<tr>
						<td class="emptyColumn" colSpan="8">&nbsp;</td>
					</tr>
					<tr>
						<td class="title" style="text-align: right;" colspan="7" height="5%">Subtotal</td>
						<td class="cout"><c:out value="${factura.subTotal}"/></td>
					</tr>
					<tr>
						<td class="title" style="text-align: right;" colspan="7" height="5%">IVA</td>
						<td class="cout"><c:out value="${iva}"/></td>
					</tr>
					<tr>
						<td class="title" style="text-align: right;" colspan="7" height="5%">Descuento</td>
						<td class="cout"><c:out value="${descuentoTotal}"/></td>
					</tr>
					<tr>
						<td class="title" style="text-align: right;" colspan="7" height="5%">Total</td>
						<td class="cout"><c:out value="${total}"/></td>
					</tr>
				</c:if>
			</form:form>
			<tr>
				<td class="upperTitle" height="20" colspan="8" align="center">© Copyright LOBYZON</td>
			</tr>
	</table>			
	</body>
</html>