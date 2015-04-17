<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>
<tbody id="item1" style="display: none;">
	<tr>
		<td class="emptyColumn" colSpan="8">&nbsp;</td>
	</tr>
	<tr>
		<td class="title" height="5" width="10%">&nbsp;</td>
		<td class="title" height="5" width="15%">Marca</td>
		<td class="title" height="5" width="20%">Familia</td>
		<td class="title" height="5">Cod Art.</td>
		<td class="title" height="5" width="5%">Cantidad</td>
		<td class="title" height="5" width="5%">Precio</td>
		<td class="title" height="5" width="5%">Descuento</td>
		<td class="title" height="5" width="5%">Total</td>
	</tr>
	<tr>
		<td style="text-align: center;" width="10%">
			<input type="checkbox" name="item1Check" id="item1Check"/>
		</td>
		<td width="15%">
			<form:select id="marcaId1" path="items[0].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId1', '#familiaId1');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId1" path="items[0].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId1, familiaId1, codigoId1)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId1" path="items[0].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId1, familiaId1, codigoId1, precioId1); validateCode(marcaId1, familiaId1, codigoId1)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId1" path="items[0].cantidad" size="5" onblur="showItem('#item2', descuentoId2, 2);doCalculos('item1', cantidadId1, descuentoId1, marcaId1, familiaId1, codigoId1, totalId1, precioId1)"/>
			<form:errors path="items[0].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId1" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId1" path="items[0].descuento" size="5" onblur="doCalculos('item1', cantidadId1, descuentoId1, marcaId1, familiaId1, codigoId1, totalId1, precioId1)"/>
			<form:errors path="items[0].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId1" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item2" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId2" path="items[1].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId2', '#familiaId2');deleteItemFactura('item2', '#item2', '#marcaId2')">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId2" path="items[1].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId2, familiaId2, codigoId2)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId2" path="items[1].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId2, familiaId2, codigoId2, precioId2); validateCode(marcaId2, familiaId2, codigoId2)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId2" path="items[1].cantidad" size="5" onblur="showItem('#item3', descuentoId3, 3);doCalculos('item2', cantidadId2, descuentoId2, marcaId2, familiaId2, codigoId2, totalId2, precioId2)"/>
			<form:errors path="items[1].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId2" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId2" path="items[1].descuento" size="5" onblur="doCalculos('item2', cantidadId2, descuentoId2, marcaId2, familiaId2, codigoId2, totalId2, precioId2)"/>
			<form:errors path="items[1].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId2" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item3" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId3" path="items[2].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId3', '#familiaId3');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId3" path="items[2].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId3, familiaId3, codigoId3)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId3" path="items[2].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId3, familiaId3, codigoId3, precioId3); validateCode(marcaId3, familiaId3, codigoId3)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId3" path="items[2].cantidad" size="5" onblur="showItem('#item4', descuentoId4, 4);doCalculos('item3', cantidadId3, descuentoId3, marcaId3, familiaId3, codigoId3, totalId3, precioId3)"/>
			<form:errors path="items[2].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId3" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId3" path="items[2].descuento" size="5" onblur="doCalculos('item3', cantidadId3, descuentoId3, marcaId3, familiaId3, codigoId3, totalId3, precioId3)"/>
			<form:errors path="items[2].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId3" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item4" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId4" path="items[3].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId4', '#familiaId4');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId4" path="items[3].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId4, familiaId4, codigoId4)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId4" path="items[3].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId4, familiaId4, codigoId4, precioId4); validateCode(marcaId4, familiaId4, codigoId4)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId4" path="items[3].cantidad" size="5" onblur="showItem('#item5', descuentoId5, 5);doCalculos('item4', cantidadId4, descuentoId4, marcaId4, familiaId4, codigoId4, totalId4, precioId4)"/>
			<form:errors path="items[3].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId4" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId4" path="items[3].descuento" size="5" onblur="doCalculos('item4', cantidadId4, descuentoId4, marcaId4, familiaId4, codigoId4, totalId4, precioId4)"/>
			<form:errors path="items[3].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId4" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item5" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId5" path="items[4].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId5', '#familiaId5');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId5" path="items[4].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId5, familiaId5, codigoId5)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId5" path="items[4].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId5, familiaId5, codigoId5, precioId5); validateCode(marcaId5, familiaId5, codigoId5)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId5" path="items[4].cantidad" size="5" onblur="showItem('#item6', descuentoId6, 6);doCalculos('item5', cantidadId5, descuentoId5, marcaId5, familiaId5, codigoId5, totalId5, precioId5)"/>
			<form:errors path="items[4].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId5" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId5" path="items[4].descuento" size="5" onblur="doCalculos('item5', cantidadId5, descuentoId5, marcaId5, familiaId5, codigoId5, totalId5, precioId5)"/>
			<form:errors path="items[4].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId5" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item6" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId6" path="items[5].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId6', '#familiaId6');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId6" path="items[5].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId6, familiaId6, codigoId6)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId6" path="items[5].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId6, familiaId6, codigoId6, precioId6); validateCode(marcaId6, familiaId6, codigoId6)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId6" path="items[5].cantidad" size="5" onblur="showItem('#item7', descuentoId7, 7);doCalculos('item6', cantidadId6, descuentoId6, marcaId6, familiaId6, codigoId6, totalId6, precioId6)"/>
			<form:errors path="items[5].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId6" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId6" path="items[5].descuento" size="5" onblur="doCalculos('item6', cantidadId6, descuentoId6, marcaId6, familiaId6, codigoId6, totalId6, precioId6)"/>
			<form:errors path="items[5].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId6" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item7" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId7" path="items[6].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId7', '#familiaId7');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId7" path="items[6].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId7, familiaId7, codigoId7)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId7" path="items[6].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId7, familiaId7, codigoId7, precioId7); validateCode(marcaId7, familiaId7, codigoId7)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId7" path="items[6].cantidad" size="5" onblur="showItem('#item8', descuentoId8, 8);doCalculos('item7', cantidadId7, descuentoId7, marcaId7, familiaId7, codigoId7, totalId7, precioId7)"/>
			<form:errors path="items[6].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId7" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId7" path="items[6].descuento" size="5" onblur="doCalculos('item7', cantidadId7, descuentoId7, marcaId7, familiaId7, codigoId7, totalId7, precioId7)"/>
			<form:errors path="items[6].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId7" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item8" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId8" path="items[7].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId8', '#familiaId8');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId8" path="items[7].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId8, familiaId8, codigoId8)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId8" path="items[7].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId8, familiaId8, codigoId8, precioId8); validateCode(marcaId8, familiaId8, codigoId8)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId8" path="items[7].cantidad" size="5" onblur="showItem('#item9', descuentoId9, 9);doCalculos('item8', cantidadId8, descuentoId8, marcaId8, familiaId8, codigoId8, totalId8, precioId8)"/>
			<form:errors path="items[7].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId8" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId8" path="items[7].descuento" size="5" onblur="doCalculos('item8', cantidadId8, descuentoId8, marcaId8, familiaId8, codigoId8, totalId8, precioId8)"/>
			<form:errors path="items[7].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId8" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item9" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId9" path="items[8].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId9', '#familiaId9');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId9" path="items[8].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId9, familiaId9, codigoId9)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId9" path="items[8].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId9, familiaId9, codigoId9, precioId9); validateCode(marcaId9, familiaId9, codigoId9)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId9" path="items[8].cantidad" size="5" onblur="showItem('#item10', descuentoId10, 10);doCalculos('item9', cantidadId9, descuentoId9, marcaId9, familiaId9, codigoId9, totalId9, precioId9)"/>
			<form:errors path="items[8].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId9" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId9" path="items[8].descuento" size="5" onblur="doCalculos('item9', cantidadId9, descuentoId9, marcaId9, familiaId9, codigoId9, totalId9, precioId9)"/>
			<form:errors path="items[8].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId9" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item10" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId10" path="items[9].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId10', '#familiaId10');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId10" path="items[9].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId10, familiaId10, codigoId10)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId10" path="items[9].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId10, familiaId10, codigoId10, precioId10); validateCode(marcaId10, familiaId10, codigoId10)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId10" path="items[9].cantidad" size="5" onblur="showItem('#item11', descuentoId11, 11);doCalculos('item10', cantidadId10, descuentoId10, marcaId10, familiaId10, codigoId10, totalId10, precioId10)"/>
			<form:errors path="items[9].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId10" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId10" path="items[9].descuento" size="5" onblur="doCalculos('item10', cantidadId10, descuentoId10, marcaId10, familiaId10, codigoId10, totalId10, precioId10)"/>
			<form:errors path="items[9].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId10" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item11" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId11" path="items[10].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId11', '#familiaId11');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId11" path="items[10].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId11, familiaId11, codigoId11)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId11" path="items[10].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId11, familiaId11, codigoId11, precioId11); validateCode(marcaId11, familiaId11, codigoId11)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId11" path="items[10].cantidad" size="5" onblur="showItem('#item12', descuentoId12, 12);doCalculos('item11', cantidadId11, descuentoId11, marcaId11, familiaId11, codigoId11, totalId11, precioId11)"/>
			<form:errors path="items[10].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId11" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId11" path="items[10].descuento" size="5" onblur="doCalculos('item11', cantidadId11, descuentoId11, marcaId11, familiaId11, codigoId11, totalId11, precioId11)"/>
			<form:errors path="items[10].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId11" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item12" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId12" path="items[11].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId12', '#familiaId12');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId12" path="items[11].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId12, familiaId12, codigoId12)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId12" path="items[11].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId12, familiaId12, codigoId12, precioId12); validateCode(marcaId12, familiaId12, codigoId12)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId12" path="items[11].cantidad" size="5" onblur="showItem('#item13', descuentoId13, 13);doCalculos('item12', cantidadId12, descuentoId12, marcaId12, familiaId12, codigoId12, totalId12, precioId12)"/>
			<form:errors path="items[11].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId12" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId12" path="items[11].descuento" size="5" onblur="doCalculos('item12', cantidadId12, descuentoId12, marcaId12, familiaId12, codigoId12, totalId12, precioId12)"/>
			<form:errors path="items[11].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId12" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item13" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId13" path="items[12].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId13', '#familiaId13');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId13" path="items[12].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId13, familiaId13, codigoId13)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId13" path="items[12].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId13, familiaId13, codigoId13, precioId13); validateCode(marcaId13, familiaId13, codigoId13)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId13" path="items[12].cantidad" size="5" onblur="showItem('#item14', descuentoId14, 14);doCalculos('item13', cantidadId13, descuentoId13, marcaId13, familiaId13, codigoId13, totalId13, precioId13)"/>
			<form:errors path="items[12].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId13" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId13" path="items[12].descuento" size="5" onblur="doCalculos('item13', cantidadId13, descuentoId13, marcaId13, familiaId13, codigoId13, totalId13, precioId13)"/>
			<form:errors path="items[12].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId13" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item14" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId14" path="items[13].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId14', '#familiaId14');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId14" path="items[13].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId14, familiaId14, codigoId14)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId14" path="items[13].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId14, familiaId14, codigoId14, precioId14); validateCode(marcaId14, familiaId14, codigoId14)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId14" path="items[13].cantidad" size="5" onblur="showItem('#item15', descuentoId15, 15);doCalculos('item14', cantidadId14, descuentoId14, marcaId14, familiaId14, codigoId14, totalId14, precioId14)"/>
			<form:errors path="items[13].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId14" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId14" path="items[13].descuento" size="5" onblur="doCalculos('item14', cantidadId14, descuentoId14, marcaId14, familiaId14, codigoId14, totalId14, precioId14)"/>
			<form:errors path="items[13].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId14" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>
<tbody id="item15" style="display: none;">
	<tr>
		<td width="15%">
			<form:select id="marcaId15" path="items[14].articulo.marca.id" multiple="false" onchange="getFamiliasItem('#marcaId15', '#familiaId15');">
				<form:option value="">Seleccione...</form:option>
				<form:options itemLabel="marcaComboDesc" itemValue="id" items="${marcas}" />
			</form:select>
		</td>						
		<td width="20%">	
			<form:select id="familiaId15" path="items[14].articulo.familia.codigo" multiple="false" onchange="listCodigosItem(marcaId15, familiaId15, codigoId15)">
				<form:option value="">Seleccione...</form:option>
			</form:select>
		</td>
		<td>	
			<form:select id="codigoId15" path="items[14].articulo.codigo" multiple="false" onchange="showPrecioItem(marcaId15, familiaId15, codigoId15, precioId15); validateCode(marcaId15, familiaId15, codigoId15)">
				<form:option value="">Seleccione...</form:option>								
			</form:select>
		</td>
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="cantidadId15" path="items[14].cantidad" size="5" onblur="showItem('#item16', descuentoId16, 16);doCalculos('item15', cantidadId15, descuentoId15, marcaId15, familiaId15, codigoId15, totalId15, precioId15)"/>
			<form:errors path="items[14].cantidad"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input type="text" class="coutNW" readonly="readonly" id="precioId15" size="5" style="text-align: center">
		</td>											
		<td class="coutNW" width="5%">							
			<form:input cssClass="coutNW" id="descuentoId15" path="items[14].descuento" size="5" onblur="doCalculos('item15', cantidadId15, descuentoId15, marcaId15, familiaId15, codigoId15, totalId15, precioId15)"/>
			<form:errors path="items[14].descuento"></form:errors>
		</td>
		<td class="coutNW" width="5%">
			<input class="coutNW" type="text" readonly="readonly" id="totalId15" style="text-align: center;" size="5">
		</td>						
	</tr>
</tbody>