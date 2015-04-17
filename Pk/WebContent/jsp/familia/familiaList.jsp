<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>

	<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">		
		<c:if test="${not empty allFamilias}">
			<tr>
				<td class="title" height="5" colspan="2">Listado Familias</td>
			</tr>
			<tr>
				<td class="title" height="5">Familias :</td>
				<td>
					<select id="codigo">
						<c:forEach items="${allFamilias}" var="allFamiliaVar" varStatus="control">
							<option value="${allFamiliaVar.codigo}"><c:out value="${allFamiliaVar.familiaComboDesc}"></c:out></option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</c:if>
		<c:if test="${not empty familias}">			
				<td class="emptyColumn" height="10%" colspan="2">
					&nbsp;
				</td>
				<tr>
					<td class="title" height="5" colspan="2">Familias</td>
				</tr>
				<tr>
					<td class="title" height="5">Código</td>
					<td class="title" height="5">Descripción</td>					
				</tr>
				<c:forEach items="${familias}" var="familiaVar" varStatus="control">
					<tr>
						<td class="valueStyle" style="text-align: center;" ><c:out value="${familiaVar.codigo}"></c:out></td>
						<td class="valueStyle" style="text-align: center;"><c:out value="${familiaVar.descripcion}"></c:out></td>						
					</tr>
				</c:forEach>
				<c:if test="${paginator.moreRecords}">
					<tr>
						<td class="emptyColumn" height="10%" colSpan="2">&nbsp;</td>
					</tr>
					<tr height="10%">
						<c:if test="${paginator.pageNumber > 1}">
							<td class="valueStyle" style="text-align: center;">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/familia/paginator/previous.htm"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/previous.gif" title="Anterior" border="0" height="24" width="40"/></a>
							</td>
						</c:if>
						<c:if test="${paginator.pageNumber == 1}">
							<td class="valueStyle">
								&nbsp;
							</td>
						</c:if>
						<td class="valueStyle" style="text-align: center;">
							<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/familia/paginator/next.htm"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/next.gif" title="Siguiente" border="0" height="24" width="40"/></a>
						</td>
					</tr>	
				</c:if>
				<c:if test="${!paginator.moreRecords}">					
					<c:if test="${paginator.pageNumber > 1}">
						<tr height="10%">
							<td class="emptyColumn" height="10%" colSpan="2">&nbsp;</td>
						</tr>
						<tr height="10%">	
							<td class="valueStyle" style="text-align: center;">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/familia/paginator/previous.htm"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/previous.gif" title="Anterior" border="0" height="24" width="40"/></a>
							</td>
							<td class="valueStyle">
								&nbsp;
							</td>
						</tr>
					</c:if>
				</c:if>						
		</c:if>
	</table>			
	
<jsp:include page="/jsp/include/abajo.jsp" />