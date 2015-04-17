<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<jsp:include page="include/arriba.jsp" flush="true"/>
					<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">						
						<tr>
							<td class="title"><b><img title="Página en Construcción" src="<c:out value="${pageContext.request.contextPath}"/>/images/construction.jpg" > </b></td>
						</tr>
					</table>
<jsp:include page="include/abajo.jsp" />