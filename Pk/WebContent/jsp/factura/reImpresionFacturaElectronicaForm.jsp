<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>

	<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
		<form:form method="POST" commandName="factura">
			<tr>
				<td height="50" class="title" colspan="7"><b>Re Impresión de Factura Electrónica</b></td>
			</tr>			
			<tr>
				<td class="title" height="5" style="text-align: right;">Nro. Factura:</td>
				<td width="15%">
					<form:input path="nroFactura" size="8" id="nroFactura"/>
				</td>
				<td><form:errors path="nroFactura" cssClass="error" /></td>				
			</tr>
			<tr>
				<td class="labelStyle" colspan="7"><input type="submit" value="Consultar"></td>
			</tr>
			<tr>
				<td class="emptyColumn" colSpan="7">&nbsp;</td>
			</tr>
		</form:form>
	</table>
<jsp:include page="/jsp/include/abajo.jsp" />