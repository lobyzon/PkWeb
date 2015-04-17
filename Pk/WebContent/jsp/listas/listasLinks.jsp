<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
		<table border="0" cellspacing="1" cellpadding="1" width="100%" height="50%">
			<tr>							
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/listas/listaPorMarca.htm">Lista por Marca</a></td>
			</tr>
			<tr>	
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/listas/listaPorFamilia.htm">Lista Por Familia</a></td>
			</tr>
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/listas/listaParcialPorFamilia.htm">Lista Parcial por Familia</a></td>
			</tr>			
			<tr>
				<td class="title"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/listas/listaParcialPorMarca.htm">Lista Parcial por Marca</a></td>
			</tr>
			<tr>
				<td class="title" style="vertical-align: midle;"><a style="vertical-align: middle;" href="<c:out value="${pageContext.request.contextPath}"/>/controller/listas/listaExcel.htm"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/excel.gif" title="Lista Excel" border="0" height="20" width="20" style="vertical-align: middle;"/>&nbsp;Lista Excel</a></td>
			</tr>
		</table>
<jsp:include page="/jsp/include/abajo.jsp" />