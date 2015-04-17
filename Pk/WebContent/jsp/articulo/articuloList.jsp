<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">		
		<c:if test="${not empty articulos}">			
				<tr>
					<td class="title" height="5" colspan="6">Articulos</td>
				</tr>	
				<tr>
					<td class="title" height="5">Marca</td>
					<td class="title" height="5">Familia</td>
					<td class="title" height="5">Código</td>
					<td class="title" height="5">Descripción</td>
					<td class="title" height="5">Stock</td>
					<td class="title" height="5">Precio Venta</td>										
				</tr>
				<c:forEach items="${articulos}" var="articuloVar" varStatus="control">
					<tr>
						<td class="valueStyle" style="text-align: center;"><c:out value="${articuloVar.marca.id}"></c:out></td>
						<td class="valueStyle" style="text-align: center;" ><c:out value="${articuloVar.familia.familiaComboDesc}"></c:out></td>
						<td class="valueStyle" style="text-align: center;"><c:out value="${articuloVar.codigo}"></c:out></td>
						<td class="valueStyle" style="text-align: center;"><c:out value="${articuloVar.descripcion}"></c:out></td>
						<td class="valueStyle" style="text-align: center;"><c:out value="${articuloVar.stock}"></c:out></td>
						<td class="valueStyle" style="text-align: center;"><c:out value="${articuloVar.precioVenta}"></c:out></td>
					</tr>
				</c:forEach>
				<c:if test="${paginator.moreRecords}">
					<tr>
						<td class="emptyColumn" height="10%" colSpan="6">&nbsp;</td>
					</tr>
					<tr height="10%">
						<c:if test="${paginator.pageNumber > 1}">
							<td class="valueStyle" colspan="3" style="text-align: center;">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/articulo/paginator/previous.htm"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/previous.gif" title="Anterior" border="0" height="24" width="40"/></a>
							</td>
							<td class="valueStyle" colspan="3" style="text-align: center;">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/articulo/paginator/next.htm"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/next.gif" title="Siguiente" height="24" width="40" border="0"/></a>
							</td>
						</c:if>
						<c:if test="${paginator.pageNumber == 1}">
							<td class="valueStyle" colspan="3" style="border: 0">
								&nbsp;
							</td>
							<td class="valueStyle" colspan="3" style="text-align: center;">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/articulo/paginator/next.htm"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/next.gif" title="Siguiente" height="24" width="40" border="0" /></a>
							</td>
						</c:if>						
					</tr>	
				</c:if>
				<c:if test="${!paginator.moreRecords}">
					<c:if test="${paginator.pageNumber > 1}">
						<tr>
							<td class="emptyColumn" height="10%" colSpan="6">&nbsp;</td>
						</tr>
						<tr height="10%">	
							<td class="valueStyle" colspan="3" style="text-align: center;">
								<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/articulo/paginator/previous.htm"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/previous.gif" title="Anterior" border="0" height="24" width="40"/></a>
							</td>
							<td class="valueStyle" colspan="3" style="border: none;">
								&nbsp;
							</td>
						</tr>
					</c:if>
				</c:if>						
		</c:if>
	</table>			
	
<jsp:include page="/jsp/include/abajo.jsp" />