<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>

	<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
		<form:form method="POST" commandName="filtros" action="facturadoAUnClientePorFecha.htm">
			<tr>
				<td height="50" class="title" colspan="11"><b>Facturación por Cliente</b></td>
			</tr>			
			<tr>
				<td class="title" height="5" colspan="4">Cliente:</td>
				<td colspan="2">
					<select id="clienteCode" name="clienteCode">
						<c:forEach items="${clientes}" var="cliente">
							<c:choose>
								<c:when test="${cliente.id == clienteCodeValue}">
									<option selected="selected" value="${cliente.id}">${cliente.clienteComboDescription}</option>
								</c:when>
								<c:otherwise>
									<option value="${cliente.id}">${cliente.clienteComboDescription}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="title" height="5" colspan="4">Fecha Desde (dd/mm/aaaa) :</td>					
				<td colspan="2">
					<form:input path="filterFrom" cssClass="textboxStyle" onblur="setFacturaType(filterFrom, typeN)"/>					
				</td>
				<td><form:errors path="filterFrom" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5" colspan="4">Fecha Hasta (dd/mm/aaaa) :</td>
				<td colspan="2">
					<form:input path="filterTo" cssClass="textboxStyle"/>					
				</td>
				<td><form:errors path="filterTo" cssClass="error" /></td>
				<td><input type="hidden" id="typeN" name="typeN"/></td>
			</tr>
			<c:if test="${not empty errorMessage}">
				<tr>
					<td class="error" colspan="11" align="center"><c:out value="${errorMessage}"/></td>
				</tr>
			</c:if>
			<tr>
				<td class="labelStyle" colspan="11"><input type="submit" value="Consultar"></td>
			</tr>
			<tr>
				<td class="emptyColumn" colSpan="11">&nbsp;</td>
			</tr>
			<c:if test="${not empty facturas}">
				<tr>
					<td class="title" height="5" colspan="3">&nbsp;</td>
					<td class="title" height="5" colspan="2">Nro. Factura</td>
					<td class="title" height="5" colspan="2">Fecha</td>
					<td class="title" height="5" colspan="4">Subtotal</td>
				</tr>
				<c:forEach items="${facturas}" var="factura">
					<tr>
						<td style="text-align: center;" colspan="3"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/estadisticas/consultaFactura.htm?id=<c:out value="${factura.id}"/>&clienteCode=<c:out value="${clienteCodeValue}"/>"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/running.gif" height="15" title="Consulta" border="0"/></a></td>
						<td class="cout" colspan="2">
							${factura.nroFactura}
							<c:if test="${factura.isFacturaN}">N</c:if>
						</td>
						<td class="cout" colspan="2"><fmt:formatDate value="${factura.fecha}" pattern="dd/MM/yyyy"/></td>
						<td class="cout" colspan="4">${factura.subTotal}</td>
					</tr>					
				</c:forEach>
				<tr>
					<td class="title" style="text-align: right;" colspan="10" height="5%">Total</td>
					<td class="cout"><c:out value="${totalCliente}"/></td>
				</tr>
			</c:if>
			<c:if test="${not empty factura.nroFactura}">
				<tr>
					<td class="title" height="5" colspan="4">Cliente:</td>
					<td class="coutLeft" colspan="2"><c:out value="${factura.cliente.id}"/></td>											
				</tr>
				<tr>
					<td class="title" height="5" colspan="4">Razón Social:</td>
					<td class="coutLeft" colspan="2"><c:out value="${factura.cliente.razonSocial}"/></td>
				</tr>					
				<tr>
					<td class="title" height="5" colspan="4">CUIT :</td>
					<td class="coutLeft" colspan="2"><c:out value="${factura.cliente.cuit}"/></td>						
				</tr>
				<tr>
					<td class="title" height="5" colspan="4">Regimen :</td>
					<td class="coutLeft" colspan="2"><c:out value="${factura.cliente.regimenForPrint}"/></td>						
				</tr>
				<tr>
					<td class="title" height="5" colspan="4">Descuento :</td>
					<td class="coutLeft" colspan="2"><c:out value="${factura.cliente.descuento}"/></td>						
				</tr>				
				<tr>
					<td class="emptyColumn" colSpan="11">&nbsp;</td>
				</tr>
				<tr>
					<td class="title" height="5" colspan="7">Articulo</td>
					<td class="title" height="5">Cantidad</td>
					<td class="title" height="5">Precio</td>
					<td class="title" height="5">Descuento</td>
					<td class="title" height="5">Total</td>
				</tr>
				<c:forEach items="${factura.items}" var="item">					
					<tr class="cout">
						<td align="left" colspan="7">
							<b><c:out value="${item.articulo.marca.id}"/></b>
							&nbsp;-&nbsp;<c:out value="${item.marcaConsulta}"/>
							&nbsp;-&nbsp;<b><c:out value="${item.articulo.familia.codigo}"/></b>
							&nbsp;<c:out value="${item.familiaConsulta}"/>
							&nbsp;-&nbsp;<b><c:out value="${item.articulo.codigo}"/></b>
							&nbsp;<c:out value="${item.articuloConsulta}"/>
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
						<td class="cout">
							<c:out value="${item.totalItem}"></c:out>
						</td>													
					</tr>
				</c:forEach>
				<tr>
					<td class="emptyColumn" colSpan="11">&nbsp;</td>
				</tr>
				<tr>
					<td class="title" style="text-align: right;" colspan="10" height="5%">Subtotal</td>
					<td class="cout"><c:out value="${factura.subTotal}"/></td>
				</tr>
				<tr>
					<td class="title" style="text-align: right;" colspan="10" height="5%">IVA</td>
					<td class="cout"><c:out value="${iva}"/></td>
				</tr>
				<tr>
					<td class="title" style="text-align: right;" colspan="10" height="5%">Descuento</td>
					<td class="cout"><c:out value="${descuentoTotal}"/></td>
				</tr>
				<tr>
					<td class="title" style="text-align: right;" colspan="10" height="5%">Total</td>
					<td class="cout"><c:out value="${total}"/></td>
				</tr>
			</c:if>
		</form:form>		
	</table>
		
	
<jsp:include page="/jsp/include/abajo.jsp" />