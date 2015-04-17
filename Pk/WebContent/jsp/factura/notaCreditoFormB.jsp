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
			<form:form method="POST" commandName="factura" action="notaCreditoSave.htm" onsubmit="return confirmImpresion()">
				<tr>
					<td height="50" class="title" colspan="8"><b>Nota de Crédito</b></td>
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
					<td class="title" height="5">&nbsp;</td>
					<td class="title" height="5">Marca</td>
					<td class="title" height="5" >Familia</td>
					<td class="title" height="5" >Cod Art.</td>
					<td class="title" height="5">Cantidad</td>
					<td class="title" height="5">Precio</td>
					<td class="title" height="5">Descuento</td>
					<td class="title" height="5">Total</td>
				</tr>
				<c:forEach items="${factura.items}" var="item" varStatus="status">
					<tr>
						<td style="text-align: center;">
							<input type="checkbox" name="items${status.index + 1}" id="items${status.index + 1}" value="${item.id}" onclick="addOrDelItem('items${status.index + 1}', cantidadItem${item.id}, descuento${status.index + 1}, marca${status.index + 1}, familia${status.index + 1}, codigo${status.index + 1}, totalId${status.index + 1}, precio${status.index + 1})"/>
						</td>
						<td class="cout">
							<c:out value="${item.articulo.marca.marcaComboDesc}"></c:out>
							<input type="hidden" name="marca${status.index + 1}" id="marca${status.index + 1}" value="${item.articulo.marca.id}">
						</td>
						<td class="cout">
							<c:out value="${item.articulo.familia.familiaComboDesc}"></c:out>
							<input type="hidden" name="familia${status.index + 1}" id="familia${status.index + 1}" value="${item.articulo.familia.codigo}">
						</td>
						<td class="cout">
							<c:out value="${item.articulo.articuloComboDesc}"></c:out>
							<input type="hidden" name="codigo${status.index + 1}" id="codigo${status.index + 1}" value="${item.articulo.codigo}">
						</td>
						<td class="cout">
							<input type="text" class="cout" size="5" name="cantidadItem${item.id}" id="cantidadItem${item.id}" value="${item.cantidad}" onblur="doCalculos('items${status.index + 1}', cantidadItem${item.id}, descuento${status.index + 1}, marca${status.index + 1}, familia${status.index + 1}, codigo${status.index + 1}, totalId${status.index + 1}, precio${status.index + 1});validateCantidadNC(cantidadItem${item.id},${item.cantidad})"/> 
						</td>
						<td class="cout">
							<c:out value="${item.precio}"></c:out>
							<input type="hidden" name="precio${status.index + 1}" id="precio${status.index + 1}" value="${item.precio}">
						</td>
						<td class="cout">
							<c:out value="${item.descuento}"></c:out>
							<input type="hidden" name="descuento${status.index + 1}" id="descuento${status.index + 1}" value="${item.descuento}">
						</td>
						<td class="cout">
							<input type="text" style="text-align: center;" value="${item.totalItem}" readonly="readonly" id="totalId${status.index + 1}" name="totalId${status.index + 1}" size="5">							
						</td>													
					</tr>
				</c:forEach>
				<tr>
					<td class="emptyColumn" colSpan="8">&nbsp;</td>
				</tr>
				<tr>
					<td class="labelStyle" style="vertical-align: middle;text-align: center;" width="15%">
						Comentarios:
					</td>
					<td class="labelStyle" style="text-align: left;" colspan="7">
						<textarea name="comentarios" id="comentarios" rows="6" cols="62"></textArea>
					</td>
				</tr>
				<tr>
					<td class="emptyColumn" colSpan="8">&nbsp;</td>
				</tr>
				<tr>
					<td class="title" height="5" style="text-align: center;" colspan="8"><b>Impresora</b></td>
				</tr>
				<tr>	
					<td style="text-align: center;" colspan="8">	
						<select id="printerServiceIndex" name="printerServiceIndex">
							<c:forEach items="${printers}" varStatus="status" var="printer">
								<option value="${status.index}"><c:out value="${printer.name}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="labelStyle" colspan="8"><input type="submit" value="Imprimir"></td>
				</tr>
				
				<tr>
					<td class="title" style="text-align: right;" colspan="7" height="5%">Subtotal</td>
					<td class="cout"><form:input cssClass="cout" size="8" path="subTotal" id="subTotal" readonly="readonly"/></td>
				</tr>
				<tr>
					<td class="title" style="text-align: right;" colspan="7" height="5%">IVA</td>
					<td class="cout"><input type="text" class="cout" size="8" id="iva" readonly="readonly"> </td>
				</tr>
				<tr>
					<td class="title" style="text-align: right;" colspan="7" height="5%">Descuento</td>
					<td class="cout"><input type="text" size="8" class="cout" id="descuentoTotal" readonly="readonly"> </td>
				</tr>				
				<tr>
					<td class="title" style="text-align: right;" colspan="7" height="5%">Total</td>
					<td class="cout"><input type="text" class="cout" size="8" id="total" readonly="readonly"> </td>
				</tr>				
			</form:form>
			<tr>
				<td class="upperTitle" height="20" colspan="8" align="center">© Copyright LOBYZON</td>
			</tr>
		</table>			
	</body>
</html>