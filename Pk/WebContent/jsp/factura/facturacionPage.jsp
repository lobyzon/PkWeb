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
			<form:form method="POST" commandName="factura" onsubmit="return confirmImpresion()">
				<tr>
					<td height="50" class="title" colspan="8"><b>Facturación</b></td>
				</tr>				
				<tr>
					<td class="title" height="5" width="15%">Cliente :</td>
					<td width="20%">
						<form:select id="id" path="cliente.id" onchange="showCliente();showItem('#item1', descuentoId1, 1)"> 
							<form:option value="">Seleccione...</form:option>
							<form:options itemLabel="clienteComboDescription" itemValue="id" items="${clientes}" />
						</form:select>						
					</td>
					<td><form:errors path="cliente.id" cssClass="error" /></td>					
				</tr>				
				<tbody id="clienteFields" style="display: none;">
					<tr>
						<td class="title" height="5">Razón Social :</td>
						<td width="20%"><form:input id="razonSocial" cssClass="cout" path="cliente.razonSocial"/></td>
						<td><form:hidden id="typeN" path="facturaType.facturaTypeId"/></td>						
					</tr>
					<tr>
						<td class="title" height="5">CUIT :</td>
						<td width="20%"><form:input id="cuit" size="11" cssClass="cout" path="cliente.cuit" onkeyup="setNType()"/></td>						
					</tr>
					<tr>
						<td class="title" height="5">Regimen :</td>
						<td width="20%">
							<select id="responsableInsc" name="responsableInsc" disabled="disabled">
			            		<option value="S">Inscripto</option>
			            		<option value="N">No inscripto</option>
			            	</select>						
						</td>						
					</tr>
					<tr>
						<td class="title" height="5">Descuento :</td>
						<td width="20%"><form:input id="descuento" size="5" cssClass="cout" path="cliente.descuento"/></td>						
					</tr>					
				</tbody>				
				
				<!-- ITEMS -->
				<jsp:include page="/jsp/factura/itemsPage.jsp" flush="true"/>
				<jsp:include page="/jsp/factura/itemsPage2.jsp" flush="true"/>
				
				<tr>
					<td class="labelStyle" style="vertical-align: middle;text-align: center;" width="15%">
						Comentarios:
					</td>
					<td class="labelStyle" style="text-align: left;" colspan="7">
						<form:textarea path="comentarios" rows="6" cols="62"/>
					</td>
				</tr>				
				<tr>
					<td class="emptyColumn" colSpan="8">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="8">
						<table cellspacing="1" cellpadding="1" width="100%" height="100%">
							<tr>
								<td class="labelStyle" style="text-align: right;" colspan="7" height="5%">Subtotal</td>
								<td style="text-align: center;" width="5%" class="coutNW"><form:input cssClass="coutNW" path="subTotal" id="subTotal" readonly="readonly"/></td>
							</tr>
							<tr>
								<td class="labelStyle" style="text-align: right;" colspan="7" height="5%">Descuento</td>
								<td style="text-align: center;" width="5%" class="coutNW"><input class="coutNW" style="text-align: center;" type="text" readonly="readonly" id="descuentoTotal"> </td>
							</tr>							
							<tr>
								<td class="labelStyle" style="text-align: right;" colspan="7" height="5%">IVA</td>
								<td style="text-align: center;" width="5%" class="coutNW"><input class="coutNW" style="text-align: center;" type="text" readonly="readonly" id="iva"> </td>
							</tr>
							<tr>
								<td class="labelStyle" style="text-align: right;" colspan="7" height="5%">Total</td>
								<td style="text-align: center;" width="5%" class="coutNW"><input class="coutNW" style="text-align: center;" type="text" readonly="readonly" id="total"> </td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="emptyColumn" colSpan="8">&nbsp;</td>
				</tr>
				<tbody id="impresoras" style="display: none;">
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
				</tbody>
				<tr>
					<td class="labelStyle" colspan="8" style="text-align: center;"><input type="submit" value="Imprimir"></td>
				</tr>	
			</form:form>
				<tr>
					<td class="upperTitle" height="20" colspan="8" align="center">© Copyright LOBYZON</td>
				</tr>
		</table>			
	</body>
</html>