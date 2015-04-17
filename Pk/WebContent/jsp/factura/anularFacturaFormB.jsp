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
			<form:form method="POST" commandName="factura" action="anularFacturaSave.htm" onsubmit="return confirmAnulacion()">
				<tr>
					<td height="50" class="title" colspan="8"><b>Anulación Factura</b></td>
				</tr>				
				<tr>
					<td class="emptyColumn" colSpan="8">&nbsp;</td>
				</tr>
				<!-- DATOS Cliente -->				
				<tr>
					<td class="title" height="5">Cliente:</td>
					<td class="cout">
						<c:out value="${factura.cliente.id}"/>
						<input type="hidden" name="id" id="id" value="${factura.cliente.id}">
					</td>											
				</tr>
				<tr>
					<td class="title" height="5">Razón Social:</td>
					<td class="cout"><c:out value="${factura.cliente.razonSocial}"/></td>
					<td><input type="hidden" name="typeN" id="typeN" value="${factura.facturaType.facturaTypeId}"></td>
				</tr>					
				<tr>
					<td class="title" height="5">CUIT :</td>
					<td class="cout"><c:out value="${factura.cliente.cuit}"/></td>						
				</tr>
				<tr>
					<td class="title" height="5">Regimen :</td>
					<td class="cout"><c:out value="${factura.cliente.regimenForPrint}"/></td>						
				</tr>
				<tr>
					<td class="title" height="5">Descuento :</td>
					<td class="cout">
						<c:out value="${factura.cliente.descuento}"/>						
					</td>
				</tr>				
				<!-- ITEMS Factura -->
				<tr>
					<td class="emptyColumn" colSpan="8">&nbsp;</td>
				</tr>
				<tr>					
					<td class="title" height="5">Marca</td>
					<td class="title" height="5" >Familia</td>
					<td class="title" height="5" >Cod Art.</td>
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
					<td class="labelStyle" colspan="8"><input type="submit" value="Anular"></td>
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
			</form:form>
			<tr>
				<td class="upperTitle" height="20" colspan="8" align="center">© Copyright LOBYZON</td>
			</tr>
		</table>			
	</body>
</html>