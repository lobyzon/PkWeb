<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>

<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
		<table border="0" cellspacing="1" cellpadding="1" width="100%" height="50%">
			<form:form method="POST" commandName="printer" action="${pageContext.request.contextPath}${action}" onsubmit="return confirmImpresion()">
				<tr>
					<td height="50" class="title"><b>Impresoras</b></td>
				</tr>
				<tr>				
					<td style="text-align: center;">	
						<form:select path="printerServiceIndex">
							<c:forEach items="${printers}" varStatus="status" var="printer">
								<form:option value="${status.index}" label="${printer.name}"/>
							</c:forEach>
						</form:select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<input type="submit" value="Imprimir">
					</td>
				</tr>
				<tr>
					<td class="emptyColumn">&nbsp;</td>
				</tr>				
			</form:form>
		</table>
<jsp:include page="/jsp/include/abajo.jsp" />