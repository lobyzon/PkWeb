<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
		<table border="0" cellspacing="1" cellpadding="1" width="100%" height="50%">
			<tr>							
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/familia/create.htm">Crear</a></td>
			</tr>
			<tr>	
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/familia/edit.htm">Editar</a></td>
			</tr>
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/familia/delete.htm">Eliminar</a></td>
			</tr>			
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/familia/paginator/init.htm">Consulta General</a></td>
			</tr>
		</table>
<jsp:include page="/jsp/include/abajo.jsp" />