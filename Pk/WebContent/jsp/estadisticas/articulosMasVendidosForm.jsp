<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
		<form:form method="POST" commandName="filtros">				
			<tr>
				<td height="50" class="title" colspan="6"><b>Articulos Más Vendidos</b></td>
			</tr>			
			<tr>
				<td class="title" height="5">Fecha Desde (dd/mm/aaaa) :</td>					
				<td>
					<form:input path="filterFrom" cssClass="textboxStyle"/>					
				</td>
				<td><form:errors path="filterFrom" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">Fecha Hasta (dd/mm/aaaa) :</td>					
				<td>
					<form:input path="filterTo" cssClass="textboxStyle"/>					
				</td>
				<td><form:errors path="filterTo" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="labelStyle" colspan="6"><input type="submit" value="Consultar"></td>
			</tr>
			<tr>
				<td class="emptyColumn" colSpan="6">&nbsp;</td>
			</tr>		
		</form:form>		
	</table>
		
	
<jsp:include page="/jsp/include/abajo.jsp" />