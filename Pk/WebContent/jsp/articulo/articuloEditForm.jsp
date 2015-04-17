<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>
	<form:form method="POST" commandName="articulo">				
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
						<form:select path="familiaId" onchange="listCodigos()" ></form:select>						
					</td>
					<td><form:errors path="codigo" cssClass="error" /></td>
				</tr>
			</tbody>
			<tbody id="familiaDisplay" style="display: none;">
				<tr>
					<td class="title" height="5">Familia :</td>
					<td>
						<c:out value="${articulo.familiaId}"></c:out>						
					</td>
					<td><form:errors path="codigo" cssClass="error" /></td>
				</tr>
			</tbody>
			<tbody id="codigoBody" style="display: none;">
				<tr>				
					<td class="title" height="5">Código :</td>
					<td>
						<form:select path="codigo" onchange="showArticulo()"></form:select>					
					</td>					
					<td><form:errors path="codigo" cssClass="error" /></td>
				</tr>
			</tbody>
			<tbody id="codigoDisplay" style="display: none;">
				<tr>				
					<td class="title" height="5">Código :</td>
					<td>
						<c:out value="${articulo.codigo}"></c:out>					
					</td>					
					<td><form:errors path="codigo" cssClass="error" /></td>
				</tr>
			</tbody>
			<tbody id="articuloFields" style="display: none;">
				<tr>
					<td class="title" height="5">Descripción :</td>
					<td><form:input cssClass="textboxStyle" path="descripcion" maxlength="30"/></td>
					<td><form:errors path="descripcion" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="title" height="5">Stock Mínimo :</td>
					<td><form:input cssClass="textboxStyle" path="stockMinimo" /></td>
					<td><form:errors path="stockMinimo" cssClass="error" /></td>
				</tr>
				<tr>	
					<td class="title" height="5">Stock :</td>
					<td><form:input cssClass="textboxStyle" path="stock" /></td>
					<td><form:errors path="stock" cssClass="error" /></td>
				</tr>						
				<tr>
					<td class="title" height="5">Precio Costo :</td>
					<td><form:input cssClass="textboxStyle" path="precioCosto" /></td>
					<td><form:errors path="precioCosto" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="title" height="5">Precio de Venta :</td>
					<td><form:input cssClass="textboxStyle" path="precioVenta" /></td>
					<td><form:errors path="precioVenta" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="title" height="5">Fecha Modif. Costo(dd/mm/aaaa):</td>																		
					<td><form:input cssClass="textboxStyle" path="fechaModificacion" /></td>
					<td><form:errors path="fechaModificacion" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="title" height="5">Fecha Modif. Venta(dd/mm/aaaa):</td>																		
					<td><form:input cssClass="textboxStyle" path="fechaModifVenta" /></td>
					<td><form:errors path="fechaModifVenta" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="labelStyle" colspan="4"><input type="submit" value="Guardar"></td>
				</tr>
			</tbody>
			<tr>
				<td class="emptyColumn" colSpan="4">&nbsp;</td>
			</tr>		
		</table>
	</form:form>

<script type="text/javascript">
	function validateArtEdit(){
		marca = $('#marcaId').val();		
		familia = $('#familiaId').val();
		codigo = $('#codigo').val();
		
		if(marca != '' && familia != '' && codigo != ''){
			$('#articuloFields').show();
			$('#familiaDisplay').show();
			$('#codigoDisplay').show();
		}		
		//alert("Marca: " + marca + " Codigo: " + codigo + " Familia: " + familia);				
	}
	
	validateArtEdit();

</script>
	
<jsp:include page="/jsp/include/abajo.jsp" />