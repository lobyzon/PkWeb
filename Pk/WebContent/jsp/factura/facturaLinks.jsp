<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
		<table border="0" cellspacing="1" cellpadding="1" width="100%" height="50%">
			<!-- 
			<tr>							
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/emision.htm">Emisión de Facturas Form. Continuo</a></td>
			</tr>
			 -->
			<tr>							
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/emisionA4.htm">Emisión de Facturas</a></td>
			</tr>
			<tr>							
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/reImpresionFacturaElectronica.htm">Re Impresión de PDF Factura Electrónica</a></td>
			</tr>
			<tr>	
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/emisionFromRemito.htm">Emisión de Facturas a partir de Remitos</a></td>
			</tr>
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/remitos.htm">Emisión de Remitos</a></td>
			</tr>			
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/notaCredito.htm">Notas de Crédito</a></td>
			</tr>
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/anularFactura.htm">Anular Factura</a></td>
			</tr>
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/parametros.htm">Parámetros</a></td>
			</tr>
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/consulta.htm">Consulta a una Factura</a></td>
			</tr>
		</table>
<jsp:include page="/jsp/include/abajo.jsp" />