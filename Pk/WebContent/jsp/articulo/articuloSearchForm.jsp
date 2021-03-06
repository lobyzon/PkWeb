<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>	
	<form:form method="POST" commandName="articulo" action="paginator/init.htm">				
		<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">				
			<tr>
				<td height="50" class="title" colspan="3"><b>Articulo</b></td>
			</tr>			
			<tr>
				<td class="title" height="5">Marca :</td>					
				<td>				
					<form:select path="marcaId" onchange="listFamilias()">
	            		<form:option value="" label="Seleccione"/>
	            		<form:options itemLabel="marcaComboDesc" items="${marcas}" itemValue="id"/>
	        		</form:select>
				</td>
				<td><form:errors path="codigo" cssClass="error" /></td>
			</tr>
			<tbody id="familiaBody" style="display: none;">
				<tr>
					<td class="title" height="5">Familia :</td>
					<td>
						<form:select path="familiaId" onchange="listCodigos()" />
					</td>
					<td><form:errors path="codigo" cssClass="error" /></td>
				</tr>
			</tbody>
			<tbody id="familiaInputBody">
				<tr>
					<td class="title" height="5">Familia :</td>
					<td>
						<form:input path="familiaIdSearch" onblur="listCodigos()" ></form:input>						
					</td>
					<td><form:errors path="codigo" cssClass="error" /></td>
				</tr>
			</tbody>
			<tbody id="codigoBody" style="display: none;">
				<tr>				
					<td class="title" height="5">C�digo :</td>
					<td>
						<form:select path="codigo"></form:select>					
					</td>					
					<td><form:errors path="codigo" cssClass="error" /></td>
				</tr>
			</tbody>							
			<tr>
				<td class="labelStyle" colspan="4"><input type="submit" value="Buscar"></td>
			</tr>			
			<tr>
				<td class="emptyColumn" colSpan="4">&nbsp;</td>
			</tr>		
		</table>
	</form:form>
	
<jsp:include page="/jsp/include/abajo.jsp" />