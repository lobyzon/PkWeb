<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>
<tbody id="item16" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId16" path="items[15].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId16', '#familiaId16');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId16" path="items[15].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId16, familiaId16, codigoId16)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId16" path="items[15].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId16, familiaId16, codigoId16, precioId16); validateCode(marcaId16, familiaId16, codigoId16)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId16" path="items[15].cantidad" size="5" onblur="doCalculos('item16', cantidadId16, descuentoId16, marcaId16, familiaId16, codigoId16, totalId16, precioId16)"/>
			<form:errors path="items[15].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId16" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId16" path="items[15].descuento" size="5" onblur="doCalculos('item16', cantidadId16, descuentoId16, marcaId16, familiaId16, codigoId16, totalId16, precioId16)"/>
			<form:errors path="items[15].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId16" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item17" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId17" path="items[16].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId17', '#familiaId17');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId17" path="items[16].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId17, familiaId17, codigoId17)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId17" path="items[16].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId17, familiaId17, codigoId17, precioId17); validateCode(marcaId17, familiaId17, codigoId17)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId17" path="items[16].cantidad" size="5" onblur="doCalculos('item17', cantidadId17, descuentoId17, marcaId17, familiaId17, codigoId17, totalId17, precioId17)"/>
			<form:errors path="items[16].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId17" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId17" path="items[16].descuento" size="5" onblur="doCalculos('item17', cantidadId17, descuentoId17, marcaId17, familiaId17, codigoId17, totalId17, precioId17)"/>
			<form:errors path="items[16].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId17" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item18" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId18" path="items[17].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId18', '#familiaId18');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId18" path="items[17].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId18, familiaId18, codigoId18)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId18" path="items[17].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId18, familiaId18, codigoId18, precioId18); validateCode(marcaId18, familiaId18, codigoId18)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId18" path="items[17].cantidad" size="5" onblur="doCalculos('item18', cantidadId18, descuentoId18, marcaId18, familiaId18, codigoId18, totalId18, precioId18)"/>
			<form:errors path="items[17].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId18" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId18" path="items[17].descuento" size="5" onblur="doCalculos('item18', cantidadId18, descuentoId18, marcaId18, familiaId18, codigoId18, totalId18, precioId18)"/>
			<form:errors path="items[17].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId18" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item19" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId19" path="items[18].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId19', '#familiaId19');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId19" path="items[18].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId19, familiaId19, codigoId19)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId19" path="items[18].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId19, familiaId19, codigoId19, precioId19); validateCode(marcaId19, familiaId19, codigoId19)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId19" path="items[18].cantidad" size="5" onblur="doCalculos('item19', cantidadId19, descuentoId19, marcaId19, familiaId19, codigoId19, totalId19, precioId19)"/>
			<form:errors path="items[18].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId19" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId19" path="items[18].descuento" size="5" onblur="doCalculos('item19', cantidadId19, descuentoId19, marcaId19, familiaId19, codigoId19, totalId19, precioId19)"/>
			<form:errors path="items[18].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId19" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item20" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId20" path="items[19].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId20', '#familiaId20');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId20" path="items[19].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId20, familiaId20, codigoId20)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId20" path="items[19].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId20, familiaId20, codigoId20, precioId20); validateCode(marcaId20, familiaId20, codigoId20)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId20" path="items[19].cantidad" size="5" onblur="doCalculos('item20', cantidadId20, descuentoId20, marcaId20, familiaId20, codigoId20, totalId20, precioId20)"/>
			<form:errors path="items[19].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId20" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId20" path="items[19].descuento" size="5" onblur="doCalculos('item20', cantidadId20, descuentoId20, marcaId20, familiaId20, codigoId20, totalId20, precioId20)"/>
			<form:errors path="items[19].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId20" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item21" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId21" path="items[20].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId21', '#familiaId21');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="21%">	
			<form:select id="familiaId21" path="items[20].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId21, familiaId21, codigoId21)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId21" path="items[20].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId21, familiaId21, codigoId21, precioId21); validateCode(marcaId21, familiaId21, codigoId21)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId21" path="items[20].cantidad" size="5" onblur="doCalculos('item21', cantidadId21, descuentoId21, marcaId21, familiaId21, codigoId21, totalId21, precioId21)"/>
			<form:errors path="items[20].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId21" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId21" path="items[20].descuento" size="5" onblur="doCalculos('item21', cantidadId21, descuentoId21, marcaId21, familiaId21, codigoId21, totalId21, precioId21)"/>
			<form:errors path="items[20].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId21" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item22" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId22" path="items[21].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId22', '#familiaId22');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="22%">	
			<form:select id="familiaId22" path="items[21].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId22, familiaId22, codigoId22)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId22" path="items[21].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId22, familiaId22, codigoId22, precioId22); validateCode(marcaId22, familiaId22, codigoId22)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId22" path="items[21].cantidad" size="5" onblur="doCalculos('item22', cantidadId22, descuentoId22, marcaId22, familiaId22, codigoId22, totalId22, precioId22)"/>
			<form:errors path="items[21].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId22" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId22" path="items[21].descuento" size="5" onblur="doCalculos('item22', cantidadId22, descuentoId22, marcaId22, familiaId22, codigoId22, totalId22, precioId22)"/>
			<form:errors path="items[21].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId22" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item23" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId23" path="items[22].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId23', '#familiaId23');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="23%">	
			<form:select id="familiaId23" path="items[22].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId23, familiaId23, codigoId23)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId23" path="items[22].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId23, familiaId23, codigoId23, precioId23); validateCode(marcaId23, familiaId23, codigoId23)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId23" path="items[22].cantidad" size="5" onblur="doCalculos('item23', cantidadId23, descuentoId23, marcaId23, familiaId23, codigoId23, totalId23, precioId23)"/>
			<form:errors path="items[22].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId23" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId23" path="items[22].descuento" size="5" onblur="doCalculos('item23', cantidadId23, descuentoId23, marcaId23, familiaId23, codigoId23, totalId23, precioId23)"/>
			<form:errors path="items[22].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId23" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item24" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId24" path="items[23].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId24', '#familiaId24');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="24%">	
			<form:select id="familiaId24" path="items[23].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId24, familiaId24, codigoId24)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId24" path="items[23].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId24, familiaId24, codigoId24, precioId24); validateCode(marcaId24, familiaId24, codigoId24)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId24" path="items[23].cantidad" size="5" onblur="doCalculos('item24', cantidadId24, descuentoId24, marcaId24, familiaId24, codigoId24, totalId24, precioId24)"/>
			<form:errors path="items[23].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId24" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId24" path="items[23].descuento" size="5" onblur="doCalculos('item24', cantidadId24, descuentoId24, marcaId24, familiaId24, codigoId24, totalId24, precioId24)"/>
			<form:errors path="items[23].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId24" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item25" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId25" path="items[24].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId25', '#familiaId25');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="25%">	
			<form:select id="familiaId25" path="items[24].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId25, familiaId25, codigoId25)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId25" path="items[24].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId25, familiaId25, codigoId25, precioId25); validateCode(marcaId25, familiaId25, codigoId25)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId25" path="items[24].cantidad" size="5" onblur="doCalculos('item25', cantidadId25, descuentoId25, marcaId25, familiaId25, codigoId25, totalId25, precioId25)"/>
			<form:errors path="items[24].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId25" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId25" path="items[24].descuento" size="5" onblur="doCalculos('item25', cantidadId25, descuentoId25, marcaId25, familiaId25, codigoId25, totalId25, precioId25)"/>
			<form:errors path="items[24].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId25" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item26" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId26" path="items[25].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId26', '#familiaId26');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="26%">	
			<form:select id="familiaId26" path="items[25].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId26, familiaId26, codigoId26)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId26" path="items[25].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId26, familiaId26, codigoId26, precioId26); validateCode(marcaId26, familiaId26, codigoId26)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId26" path="items[25].cantidad" size="5" onblur="doCalculos('item26', cantidadId26, descuentoId26, marcaId26, familiaId26, codigoId26, totalId26, precioId26)"/>
			<form:errors path="items[25].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId26" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId26" path="items[25].descuento" size="5" onblur="doCalculos('item26', cantidadId26, descuentoId26, marcaId26, familiaId26, codigoId26, totalId26, precioId26)"/>
			<form:errors path="items[25].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId26" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item27" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId27" path="items[26].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId27', '#familiaId27');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="27%">	
			<form:select id="familiaId27" path="items[26].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId27, familiaId27, codigoId27)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId27" path="items[26].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId27, familiaId27, codigoId27, precioId27); validateCode(marcaId27, familiaId27, codigoId27)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId27" path="items[26].cantidad" size="5" onblur="doCalculos('item27', cantidadId27, descuentoId27, marcaId27, familiaId27, codigoId27, totalId27, precioId27)"/>
			<form:errors path="items[26].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId27" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId27" path="items[26].descuento" size="5" onblur="doCalculos('item27', cantidadId27, descuentoId27, marcaId27, familiaId27, codigoId27, totalId27, precioId27)"/>
			<form:errors path="items[26].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId27" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item28" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId28" path="items[27].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId28', '#familiaId28');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="28%">	
			<form:select id="familiaId28" path="items[27].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId28, familiaId28, codigoId28)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId28" path="items[27].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId28, familiaId28, codigoId28, precioId28); validateCode(marcaId28, familiaId28, codigoId28)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId28" path="items[27].cantidad" size="5" onblur="doCalculos('item28', cantidadId28, descuentoId28, marcaId28, familiaId28, codigoId28, totalId28, precioId28)"/>
			<form:errors path="items[27].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId28" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId28" path="items[27].descuento" size="5" onblur="doCalculos('item28', cantidadId28, descuentoId28, marcaId28, familiaId28, codigoId28, totalId28, precioId28)"/>
			<form:errors path="items[27].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId28" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item29" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId29" path="items[28].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId29', '#familiaId29');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="29%">	
			<form:select id="familiaId29" path="items[28].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId29, familiaId29, codigoId29)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId29" path="items[28].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId29, familiaId29, codigoId29, precioId29); validateCode(marcaId29, familiaId29, codigoId29)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId29" path="items[28].cantidad" size="5" onblur="doCalculos('item29', cantidadId29, descuentoId29, marcaId29, familiaId29, codigoId29, totalId29, precioId29)"/>
			<form:errors path="items[28].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId29" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId29" path="items[28].descuento" size="5" onblur="doCalculos('item29', cantidadId29, descuentoId29, marcaId29, familiaId29, codigoId29, totalId29, precioId29)"/>
			<form:errors path="items[28].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId29" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item30" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId30" path="items[29].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId30', '#familiaId30');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="30%">	
			<form:select id="familiaId30" path="items[29].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId30, familiaId30, codigoId30)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId30" path="items[29].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId30, familiaId30, codigoId30, precioId30); validateCode(marcaId30, familiaId30, codigoId30)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId30" path="items[29].cantidad" size="5" onblur="doCalculos('item30', cantidadId30, descuentoId30, marcaId30, familiaId30, codigoId30, totalId30, precioId30)"/>
			<form:errors path="items[29].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId30" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId30" path="items[29].descuento" size="5" onblur="doCalculos('item30', cantidadId30, descuentoId30, marcaId30, familiaId30, codigoId30, totalId30, precioId30)"/>
			<form:errors path="items[29].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId30" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item31" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId31" path="items[30].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId31', '#familiaId31');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="30%">	
			<form:select id="familiaId31" path="items[30].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId31, familiaId31, codigoId31)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId31" path="items[30].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId31, familiaId31, codigoId31, precioId31); validateCode(marcaId31, familiaId31, codigoId31)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId31" path="items[30].cantidad" size="5" onblur="doCalculos('item31', cantidadId31, descuentoId31, marcaId31, familiaId31, codigoId31, totalId31, precioId31)"/>
			<form:errors path="items[30].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId31" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId31" path="items[30].descuento" size="5" onblur="doCalculos('item31', cantidadId31, descuentoId31, marcaId31, familiaId31, codigoId31, totalId31, precioId31)"/>
			<form:errors path="items[30].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId31" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item32" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId32" path="items[31].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId32', '#familiaId32');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="30%">	
			<form:select id="familiaId32" path="items[31].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId32, familiaId32, codigoId32)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId32" path="items[31].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId32, familiaId32, codigoId32, precioId32); validateCode(marcaId32, familiaId32, codigoId32)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId32" path="items[31].cantidad" size="5" onblur="doCalculos('item32', cantidadId32, descuentoId32, marcaId32, familiaId32, codigoId32, totalId32, precioId32)"/>
			<form:errors path="items[31].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId32" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId32" path="items[31].descuento" size="5" onblur="doCalculos('item32', cantidadId32, descuentoId32, marcaId32, familiaId32, codigoId32, totalId32, precioId32)"/>
			<form:errors path="items[31].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId32" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item33" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId33" path="items[32].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId33', '#familiaId33');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="30%">	
			<form:select id="familiaId33" path="items[32].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId33, familiaId33, codigoId33)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId33" path="items[32].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId33, familiaId33, codigoId33, precioId33); validateCode(marcaId33, familiaId33, codigoId33)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId33" path="items[32].cantidad" size="5" onblur="doCalculos('item33', cantidadId33, descuentoId33, marcaId33, familiaId33, codigoId33, totalId33, precioId33)"/>
			<form:errors path="items[32].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId33" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId33" path="items[32].descuento" size="5" onblur="doCalculos('item33', cantidadId33, descuentoId33, marcaId33, familiaId33, codigoId33, totalId33, precioId33)"/>
			<form:errors path="items[32].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId33" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item34" style="display: none;">
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="itemsCheck" id="itemsCheck"/>
		</td>
		<td width="15%">
			<form:select id="marcaId34" path="items[33].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId34', '#familiaId34');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="30%">	
			<form:select id="familiaId34" path="items[33].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId34, familiaId34, codigoId34)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId34" path="items[33].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId34, familiaId34, codigoId34, precioId34); validateCode(marcaId34, familiaId34, codigoId34)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId34" path="items[33].cantidad" size="5" onblur="doCalculos('item34', cantidadId34, descuentoId34, marcaId34, familiaId34, codigoId34, totalId34, precioId34)"/>
			<form:errors path="items[33].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId34" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId34" path="items[33].descuento" size="5" onblur="doCalculos('item34', cantidadId34, descuentoId34, marcaId34, familiaId34, codigoId34, totalId34, precioId34)"/>
			<form:errors path="items[33].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId34" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>