<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>

<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
	<table border="0" cellspacing="1" cellpadding="1" width="100%" height="50%">
		<form:form method="POST" commandName="marcaChecks" onsubmit="return confirmImpresion()">							
			<tr>
				<td style="text-align: center;"><form:errors path="checkBoxes" cssClass="error"/></td>
			</tr>
			<c:forEach items="${marcas}" var="marca">
				<tr>
					<td class="cout" style="text-align: left;">
						<form:checkbox label="${marca.marcaComboDesc}" value="${marca.id}" path="checkBoxes"/>
					</td>
				</tr>
			</c:forEach>							
			<tr>
				<td class="title" height="5"><b>Impresora</b></td>
			</tr>
			<tr>	
				<td style="text-align: center;">	
					<select id="printerServiceIndex" name="printerServiceIndex">
						<c:forEach items="${printers}" varStatus="status" var="printer">
							<option value="${status.index}"><c:out value="${printer.name}"/></option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>	
				<td class="labelStyle"><input type="submit" value="Imprimir"></td>
			</tr>				
		</form:form>
	</table>
<jsp:include page="/jsp/include/abajo.jsp" />