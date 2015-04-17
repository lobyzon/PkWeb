<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>

	<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
		<form:form method="POST" commandName="params">		
			<tr>
				<td height="50" class="title" colspan="6"><b>Parámetros</b></td>
			</tr>			
			<tr>
				<td class="title" height="5">Prox. Num. Factura:</td>
				<td><form:input  path="proxNumFactura" size="10"/></td>
				<td><form:errors path="proxNumFactura" cssClass="error" /></td>
				<td class="title" height="5">Cant. Copias Factura:</td>
				<td><form:input  path="cantCopiasFactura" size="10"/></td>
				<td><form:errors path="cantCopiasFactura" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">Prox. Num. Remito:</td>
				<td><form:input  path="proxNumRemito" id="proxNumRemito" onkeyup="showParamsN()" size="10"/></td>
				<td><form:errors path="proxNumRemito" cssClass="error" /></td>
				<td class="title" height="5">Cant. Copias Remito:</td>
				<td><form:input  path="cantCopiasRemito" size="10"/></td>
				<td><form:errors path="cantCopiasRemito" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">Prox. Num. N/C:</td>
				<td><form:input  path="proxNumNC" size="10"/></td>
				<td><form:errors path="proxNumNC" cssClass="error" /></td>
				<td class="title" height="5">Cant. Copias N/C:</td>
				<td><form:input  path="cantCopiasNC" size="10"/></td>
				<td><form:errors path="cantCopiasNC" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">IVA Inscripto:</td>
				<td><form:input  path="ivaInscripto.IVA" size="10"/></td>
				<td><form:errors path="ivaInscripto.IVA" cssClass="error" /></td>
				<td class="title" height="5">IVA No Inscripto:</td>
				<td><form:input  path="ivaNoInscripto.IVA" size="10"/></td>
				<td><form:errors path="ivaNoInscripto.IVA" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">Cotización Dolar:</td>
				<td><form:input  path="cotizacionDolar" size="10"/></td>
				<td><form:errors path="cotizacionDolar" cssClass="error" /></td>
				<td class="title" height="5">Matriz de Punto:</td>
				<td><form:input  path="matrixPrinterName" size="10"/></td>
				<td><form:errors path="matrixPrinterName" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">Impresora Laser:</td>
				<td><form:input  path="laserPrinterName" size="10"/></td>
				<td><form:errors path="laserPrinterName" cssClass="error" /></td>
			</tr>
			<tbody id="paramsN" style="display: none;">
				<tr>
					<td class="title" height="5">Prox. Num. Factura N:</td>
					<td><form:input  path="proxNumFacturaN" size="10"/></td>
					<td><form:errors path="proxNumFacturaN" cssClass="error" /></td>
					<td class="title" height="5">Cant. Copias Factura N:</td>
					<td><form:input  path="cantCopiasFacturaN" size="10"/></td>
					<td><form:errors path="cantCopiasFacturaN" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="title" height="5">Prox. Num. Factura D:</td>
					<td><form:input  path="proxNumFacturaD" size="10"/></td>
					<td><form:errors path="proxNumFacturaD" cssClass="error" /></td>
					<td class="title" height="5">Cant. Copias Factura D:</td>
					<td><form:input  path="cantCopiasFacturaD" size="10"/></td>
					<td><form:errors path="cantCopiasFacturaD" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="title" height="5">Prox. Num. N/C-D:</td>
					<td><form:input  path="proxNumNCD" size="10"/></td>
					<td><form:errors path="proxNumNCD" cssClass="error" /></td>
					<td class="title" height="5">Cant. Copias N/C-D:</td>
					<td><form:input  path="cantCopiasNCD" size="10"/></td>
					<td><form:errors path="cantCopiasNCD" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="title" height="5">Prox. Num. Remito D:</td>
					<td><form:input  path="proxNumRemitoD" size="10"/></td>
					<td><form:errors path="proxNumRemitoD" cssClass="error" /></td>
					<td class="title" height="5">Cant. Copias Remito D:</td>
					<td><form:input  path="cantCopiasRemitoD" size="10"/></td>
					<td><form:errors path="cantCopiasRemitoD" cssClass="error" /></td>
				</tr>				
			</tbody>
			<tr>
				<td class="labelStyle" colspan="4"><input type="submit" value="Guardar"></td>
			</tr>
			<tr>
				<td class="emptyColumn" colSpan="4">&nbsp;</td>
			</tr>		
		</form:form>		
	</table>
		
	
<jsp:include page="/jsp/include/abajo.jsp" />