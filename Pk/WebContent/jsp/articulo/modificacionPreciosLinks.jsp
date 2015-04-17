<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
		<table border="0" cellspacing="1" cellpadding="1" width="100%" height="50%">
			<tr>
				<td class="title">Modificaci�n de Precios (En Porcentajes)</td>
			</tr>
			<tr>							
				<td class="subTitle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/articulo/modifCosto.htm">De Costo</a></td>
			</tr>			
			<tr>
				<td class="subTitle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/articulo/modifVenta.htm">De Venta</a></td>
			</tr>
			<tr>
				<td class="subTitle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/articulo/modifVentaTotal.htm">De Venta, Total</a></td>
			</tr>			
		</table>
<jsp:include page="/jsp/include/abajo.jsp" />