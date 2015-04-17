<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>

	<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
		<form:form method="POST" commandName="familia">		
			<tr>
				<td height="50" class="title" colspan="3"><b>Familia</b></td>
			</tr>				
			<tr>
				<td class="title" height="5">Código :</td>
				<td><form:input cssClass="textboxStyle" path="codigo" maxlength="4"/></td>
				<td><form:errors path="codigo" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">Descripción :</td>
				<td><form:input cssClass="textboxStyle" path="descripcion" maxlength="25"/></td>
				<td><form:errors path="descripcion" cssClass="error" /></td>
			</tr>						
			<tr>
				<td class="labelStyle" colspan="4"><input type="submit" value="Crear"></td>
			</tr>
			<tr>
				<td class="emptyColumn" colSpan="4">&nbsp;</td>
			</tr>		
		</form:form>		
	</table>
		
	
<jsp:include page="/jsp/include/abajo.jsp" />