<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
		<form:form method="POST" commandName="cliente">				
			<tr>
				<td height="50" class="title" colspan="6"><b>Cliente</b></td>
			</tr>			
			<tr>
				<td class="title" height="5">Código :</td>					
				<td>
					<form:input path="id" cssClass="textboxStyle"/>					
				</td>
				<td><form:errors path="id" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">Razón Social :</td>					
				<td>
					<form:input path="razonSocial" cssClass="textboxStyle"/>					
				</td>
				<td><form:errors path="razonSocial" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">Teléfono :</td>
				<td>
					<form:input path="telefono" cssClass="textboxStyle"/>						
				</td>
				<td><form:errors path="telefono" cssClass="error" /></td>
				<td class="title" height="5">Teléfono 2:</td>
				<td>
					<form:input path="telefono2" cssClass="textboxStyle"/>						
				</td>
				<td><form:errors path="telefono2" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">Teléfono 3:</td>
				<td>
					<form:input path="telefono3" cssClass="textboxStyle"/>						
				</td>
				<td><form:errors path="telefono3" cssClass="error" /></td>
				<td class="title" height="5">Teléfono 4:</td>
				<td>
					<form:input path="telefono4" cssClass="textboxStyle"/>						
				</td>
				<td><form:errors path="telefono4" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">Email :</td>
				<td>
					<form:input path="email" cssClass="textboxStyle"/>						
				</td>
				<td><form:errors path="email" cssClass="error" /></td>
				<td class="title" height="5">Email 2:</td>
				<td>
					<form:input path="email2" cssClass="textboxStyle"/>						
				</td>
				<td><form:errors path="email2" cssClass="error" /></td>
			</tr>
			<tr>				
				<td class="title" height="5">Calle :</td>
				<td><form:input cssClass="textboxStyle" path="direccion.calle" /></td>
				<td><form:errors path="direccion.calle" cssClass="error" /></td>
				<td class="title" height="5">Número :</td>
				<td><form:input cssClass="textboxStyle" path="direccion.numero" /></td>
				<td><form:errors path="direccion.numero" cssClass="error" /></td>
			</tr>
			<tr>	
				<td class="title" height="5">Piso :</td>
				<td><form:input cssClass="textboxStyle" path="direccion.piso" /></td>
				<td><form:errors path="direccion.piso" cssClass="error" /></td>
				<td class="title" height="5">Depto :</td>
				<td><form:input cssClass="textboxStyle" path="direccion.depto" /></td>
				<td><form:errors path="direccion.depto" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="title" height="5">Cod. Postal :</td>
				<td><form:input cssClass="textboxStyle" path="direccion.codPostal" /></td>
				<td><form:errors path="direccion.codPostal" cssClass="error" /></td>
				<td class="title" height="5">Localidad :</td>
				<td><form:input cssClass="textboxStyle" path="direccion.localidad" /></td>
				<td><form:errors path="direccion.localidad" cssClass="error" /></td>
			</tr>			
			<tr>
				<td class="title" height="5">Provincia :</td>
				<td><form:select path="direccion.provincia.id" items="${provincias}" itemLabel="descripcion" itemValue="id" /></td>
				<td><form:errors path="direccion.provincia.id" cssClass="error" /></td>
				<td class="title" height="5">CUIT :</td>
				<td><form:input cssClass="textboxStyle" path="cuit" /></td>
				<td><form:errors path="cuit" cssClass="error" /></td>
			</tr>			
			<tr>
				<td class="title" height="5">Regimen :</td>
				<td>
					<form:select path="responsableInsc">	            		
	            		<form:option value="" label="Seleccione..."/>
	            		<form:option value="S" label="Inscripto"/>
	            		<form:option value="N" label="No inscripto"/>
		            </form:select>
		        </td>
				<td><form:errors path="responsableInsc" cssClass="error" /></td>
				<td class="title" height="5">Descuento :</td>
				<td><form:input cssClass="textboxStyle" path="descuento" /></td>
				<td><form:errors path="descuento" cssClass="error" /></td>
			</tr>			
			<tr>
				<td class="labelStyle" colspan="6"><input type="submit" value="Crear"></td>
			</tr>
			<tr>
				<td class="emptyColumn" colSpan="6">&nbsp;</td>
			</tr>		
		</form:form>		
	</table>
		
	
<jsp:include page="/jsp/include/abajo.jsp" />