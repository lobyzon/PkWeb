<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>

<form:form method="POST" commandName="familia" action="delete.htm">		
	<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
			<tr>
				<td height="50" class="title" colspan="3"><b>Familia</b></td>
			</tr>			
			<tr>
				<td class="title" height="5">Código :</td>
				<td><form:input cssClass="textboxStyle" path="codigo" onblur="getFamiliaDescripcion()"/></td>
				<td><form:errors path="codigo" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">Descripción :</td>
				<td><form:input cssClass="textboxStyle" path="descripcion" id="descripcion"/></td>
				<td><form:errors path="descripcion" cssClass="error" /></td>
			</tr>					
			<tr height="15%">								
				<td class="labelStyle" colspan="3"><input type="submit" value="Eliminar"></td>
			</tr>
			<tr>
				<td class="emptyColumn" height="60%" colspan="3">&nbsp;</td>
			</tr>					
	</table>
</form:form>
<jsp:include page="/jsp/include/abajo.jsp" />