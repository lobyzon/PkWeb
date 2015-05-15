<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
		<table border="0" cellspacing="1" cellpadding="1" width="100%" height="50%">
			<!-- 
			<tr>							
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/emision.htm">Emisi�n de Facturas Form. Continuo</a></td>
			</tr>
			 -->
			<tr>							
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/emisionA4.htm">Emisi�n de Facturas</a></td>
			</tr>
			<tr>							
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/reImpresionFacturaElectronica.htm">Re Impresi�n de PDF Factura Electr�nica</a></td>
			</tr>
			<tr>	
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/emisionFromRemito.htm">Emisi�n de Facturas a partir de Remitos</a></td>
			</tr>
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/remitos.htm">Emisi�n de Remitos</a></td>
			</tr>			
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/notaCredito.htm">Notas de Cr�dito</a></td>
			</tr>
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/anularFactura.htm">Anular Factura</a></td>
			</tr>
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/parametros.htm">Par�metros</a></td>
			</tr>
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/consulta.htm">Consulta a una Factura</a></td>
			</tr>
		</table>
<jsp:include page="/jsp/include/abajo.jsp" />