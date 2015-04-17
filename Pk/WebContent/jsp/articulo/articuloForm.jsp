<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
		<form:form method="POST" commandName="articulo">				
				<tr>
					<td height="50" class="title" colspan="3"><b>Articulo</b></td>
				</tr>			
				<tr>
					<td class="title" height="5">Marca :</td>					
					<td>
						<form:select path="marcaId" itemLabel="marcaComboDesc" items="${marcas}" itemValue="id"></form:select>					
					</td>
					<td><form:errors path="codigo" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="title" height="5">Familia :</td>
					<td>
						<form:select path="familiaId" items="${familias}" itemValue="codigo" itemLabel="familiaComboDesc"></form:select>						
					</td>
					<td><form:errors path="codigo" cssClass="error" /></td>
				</tr>
				<tr>				
					<td class="title" height="5">Código :</td>
					<td><form:input cssClass="textboxStyle" path="codigo" maxlength="8"/></td>
					<td><form:errors path="codigo" cssClass="error" /></td>
				</tr>
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
					<td class="labelStyle" colspan="4"><input type="submit" value="Crear"></td>
				</tr>
				<tr>
					<td class="emptyColumn" colSpan="4">&nbsp;</td>
				</tr>		
		</form:form>		
	</table>
		
	
<jsp:include page="/jsp/include/abajo.jsp" />