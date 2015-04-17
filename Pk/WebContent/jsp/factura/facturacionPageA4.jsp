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
					<td class="title" height="5" width="10%">Cliente :</td>
					<td width="10%">
						<form:select id="id" path="cliente.id" onchange="showClienteAndFirstItem('#item1', descuentoId1, 1)"> 
							<form:option value="">Seleccione...</form:option>
							<form:options itemLabel="clienteComboDescription" itemValue="id" items="${clientes}" />
						</form:select>						
					</td>
					<td><form:errors path="cliente.id" cssClass="error" /></td>					
				</tr>				
				<tbody id="clienteFields" style="display: none;">
					<tr>
						<td class="title" height="5">Razón Social :</td>
						<td width="10%"><form:input id="razonSocial" cssClass="cout" path="cliente.razonSocial"/></td>
						<td><form:hidden id="typeN" path="facturaType.facturaTypeId"/></td>
					</tr>
					<tr>
						<td class="title" height="5">CUIT :</td>
						<td width="10%"><form:input id="cuit" size="11" cssClass="cout" path="cliente.cuit" onkeyup="setNType()"/></td>						
					</tr>
					<tr>
						<td class="title" height="5">Regimen :</td>
						<td width="10%">
							<select id="responsableInsc" name="responsableInsc" disabled="disabled">
			            		<option value="S">Inscripto</option>
			            		<option value="N">No inscripto</option>
			            	</select>						
						</td>						
					</tr>
					<tr>
						<td class="title" height="5">Descuento :</td>
						<td width="10%"><form:input id="descuento" size="5" cssClass="cout" path="cliente.descuento"/></td>						
					</tr>					
				</tbody>				
				
				<!-- ITEMS -->
				<jsp:include page="/jsp/factura/itemsPage.jsp" flush="true"/>
				<jsp:include page="/jsp/factura/itemsPage2.jsp" flush="true"/>
				<br>
				<tr>
					<td class="labelStyle" style="text-align: center;"><input type="button" onclick="addItem()" value="Agregar Item" ></td>
					<td class="labelStyle" colspan="7" style="text-align: left;"><input type="button" onclick="deleteItem()" value="Eliminar Item" ></td>
				</tr>				
				<tbody id="comentariosField" style="display: none;">
					<tr>
						<td class="labelStyle" style="vertical-align: middle;text-align: center;" width="15%">
							Comentarios:
						</td>
						<td class="labelStyle" style="text-align: left;" colspan="7">
							<form:textarea path="comentarios" rows="6" cols="62"/>
						</td>
					</tr>
				</tbody>
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
					<td class="labelStyle" colspan="8" style="text-align: center;"><input type="submit" value="Imprimir Factura Manual" onclick="setTipoFactura('fm')"></td>					
				</tr>
				<tr>
					<td class="labelStyle" colspan="8" style="text-align: center;"><input type="submit" value="Generar FE" onclick="setTipoFactura('fe')"></td>
				</tr>
				<tr>
					<td><input type="hidden" name="tipoFactura" id="tipoFactura" value=""> </td>
				</tr>				
			</form:form>
				<tr>
					<td class="upperTitle" height="20" colspan="8" align="center">© Copyright LOBYZON</td>
				</tr>
		</table>			
	</body>
	<script type="text/javascript">
		var itemCount = [];
		
		preloadItems();
		
		function preloadItems(){					
			itemCount[0] = true;
			for (var index = 1; index < 35; index++) {
				itemCount[index] = false;
			}
		}
		
		function addItem(){
			typeF = $('#typeN').val();
			for (var index = 0; index < itemCount.length; index++) {
				if (itemCount[index] == false && typeF != 2) {
					itemCount[index] = true;
					$('#item' + (index+1)).show();
					$('#descuentoId' + (index+1)).val($('#descuento').val());
					return;
				}
				if (itemCount[index] == false && typeF == 2 && index < 30) {
					itemCount[index] = true;
					$('#item' + (index+1)).show();
					$('#descuentoId' + (index+1)).val($('#descuento').val());
					return;
				}
			}
		}
		
		function deleteItem(){
			var longitud = document.forms[0].itemsCheck.length;		
	   		var checkboxes = document.forms[0].itemsCheck;   		
	  		
	  		for (var i = 0; i < longitud; i++) {
	       		if(document.forms[0].itemsCheck[i].checked){
	       			document.forms[0].itemsCheck[i].checked = false;
	       			itemCount[i] = false;
	       			$('#item' + (i+1)).hide();
	       			//Clean Fields
	       			//Marca
	       			$('#marcaId' + (i+1)).val('');
					//Familia
					$('#familiaId' + (i+1)).children().remove();
					$('#familiaId' + (i+1)).append("<option value='"+ "'>Seleccione...</option>");
					//Codigo
					$('#codigoId' + (i+1)).children().remove();
					$('#codigoId' + (i+1)).append("<option value='"+ "'>Seleccione...</option>");
					//Cantidad
					$('#cantidadId' + (i+1)).val('');
					//Precio
					$('#precioId' + (i+1)).val('');
					//Descuento
					$('#descuentoId' + (i+1)).val('');
					//Total
					$('#totalId' + (i+1)).val('');					
					
	       			//Look up Parameters for delete Item from Session Items 
					idCliente = $('#id').val();
	       			typeN = $('#typeN').val();
	       			$.getJSON("../factura/DeleteItem.json", {itemId:'item' + (i+1), idCliente:idCliente, n:typeN},
	       					function(data){
	       						if(data.success){
	       							$('#subTotal').val(data.subTotal);
	       							$('#iva').val(data.iva);
	       							$('#descuentoTotal').val(data.descuentoTotal);
	       							$('#total').val(data.total);
	       						}
	       					});
	       		}
	  		}
		}
		
		function setTipoFactura(value){
			$('#tipoFactura').val(value);
		}
	</script>
</html>