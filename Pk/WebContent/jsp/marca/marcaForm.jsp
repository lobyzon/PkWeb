<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>

	<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
		<form:form method="POST" commandName="marca">		
				<tr>
					<td height="50" class="title" colspan="4"><b>Marca</b></td>
				</tr>			
				<tr>
					<td class="title" height="5">Código :</td>
					<td><form:input cssClass="textboxStyle" path="id" onblur="showMarcaDescripcion()" maxlength="4" /></td>
					<td><form:errors path="id" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="title" height="5">Descripción :</td>
					<td><form:input cssClass="textboxStyle" path="descripcion" maxlength="16"/></td>
					<td><form:errors path="descripcion" cssClass="error" /></td>
				</tr>		
				<tr>
					<td class="labelStyle" colspan="4"><input type="submit" value="Crear"></td>
				</tr>
				<tr height="50%">
					<td class="emptyColumn" colSpan="4">&nbsp;</td>
				</tr>		
		</form:form>
		<c:if test="${not empty marcas}">			
				<tr>
					<td class="title" height="4" colspan="4">Marcas</td>
				</tr>
				<tr>
					<td class="title" height="5">Código</td>
					<td class="title" height="5">Descripción</td>
					<td class="title" height="5">&nbsp;</td>
					<td class="title" height="5">&nbsp;</td>
				</tr>
				<c:forEach items="${marcas}" var="marcaVar" varStatus="control">
					<tr>
						<td class="valueStyle" style="text-align: center;"><c:out value="${marcaVar.id}"></c:out></td>
						<td class="valueStyle" style="text-align: center;"><c:out value="${marcaVar.descripcion}"></c:out></td>
						<td style="text-align: center;"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/marca/edit.htm?id=<c:out value="${marcaVar.id}"/>"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/edit.jpg" height="15" title="Editar" border="0"/></a></td>
						<td style="text-align: center;"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/marca/delete.htm?id=<c:out value="${marcaVar.id}"/>"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/delete.jpg"  height="15" title="Eliminar" border="0"/></a></td>						
					</tr>
				</c:forEach>			
		</c:if>
	</table>
		
	
<jsp:include page="/jsp/include/abajo.jsp" />