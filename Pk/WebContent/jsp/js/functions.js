function confirmImpresion(){
	return confirm("Confirma Generar la factura?");
}

function confirmAnulacion(){	
	return confirm("Confirma Anulación Factura?");
}

function getFamiliaDescripcion()
{
	codigo = $('#codigo').val();

	$.getJSON("../familia/GetFamiliaDescripcion.json", {code:codigo},
			function(data){

				if(data.success){
					$('#descripcion').val(data.descripcion);					
				}
				else
				{
					alert("Familia no encontrada");
				}
			});		
}

function showMarcaDescripcion()
{					
	codigo = $('#id').val();

	$.getJSON("../marca/GetMarcaDescripcion.json", {code:codigo},
			function(data){

				if(data.success){					
					$('#descripcion').val(data.descripcion);					
				}				
			});		
}

function listFamilias()
{						
	marcaId = $('#marcaId').val();

	$.getJSON("../articulo/GetFamiliaCombo.json", {marca:marcaId},
			function(data){
				if(data.success){
					$('#familiaBody').show();
					if($('#familiaInputBody').val() != null){
						$('#familiaInputBody').hide();
					}
					$("#familiaId").children().remove();
					$("#familiaId").append("<option value='"+ "'>Seleccione...</option>");
					$.each(data.familias, function(i, item){
			            $("#familiaId").append("<option value='"+ item.codigo+"'>"+ item.familiaComboDesc +"</option>");
			        });		
				}else{
					$('#familiaBody').hide();
					$('#codigoBody').hide();
					$('#articuloFields').hide();
					if($('#familiaInputBody').val() != null){
						$('#familiaInputBody').show();
					}
				}				
			});		
}

function listCodigos()
{
	marcaId = $('#marcaId').val();
	familiaId = $('#familiaId').val();

	$.getJSON("../articulo/GetCodigosCombo.json", {marca:marcaId, familia:familiaId},
			function(data){
				if(data.success){
					$('#codigoBody').show();
					$("#codigo").children().remove();
					$("#codigo").append("<option value='"+ "'>Seleccione...</option>");
					$('#articuloFields').hide();
					$.each(data.articulos, function(i, item){
			            $("#codigo").append("<option value='"+ item.codigo+"'>"+ item.articuloComboDesc +"</option>");
			        });		
				}else{
					$('#codigoBody').hide();
					$('#articuloFields').hide();
				}				
			});		
}

function showArticulo()
{
	marcaId = $('#marcaId').val();
	familiaId = $('#familiaId').val();
	codigo = $('#codigo').val();
	
	$.getJSON("../articulo/GetArticulo.json", {marca:marcaId, familia:familiaId, codigo:codigo},
			function(data){
				if(data.success){
					$('#articuloFields').show();
					$('#descripcion').val(data.articulo.descripcion);
					$('#stockMinimo').val(data.articulo.stockMinimo);
					$('#stock').val(data.articulo.stock);
					$('#precioCosto').val(data.articulo.precioCosto);
					$('#precioVenta').val(data.articulo.precioVenta);					
					$('#fechaModificacion').val(data.fechaString);
					$('#fechaModifVenta').val(data.fechaVentaString);
				}else{
					$('#articuloFields').hide();
				}				
			});		
}

function showArticuloDescription()
{
	marcaId = $('#marcaId').val();
	familiaId = $('#familiaId').val();
	codigo = $('#codigo').val();
	
	$.getJSON("../articulo/GetArticulo.json", {marca:marcaId, familia:familiaId, codigo:codigo},
			function(data){
				if(data.success){
					$('#articuloFields').show();
					$('#descripcion').val(data.articulo.descripcion);										
				}else{
					$('#articuloFields').hide();
				}				
			});		
}

function showCliente()
{	
	codigo = $('#id').val();
	
	$.getJSON("../cliente/GetClienteFields.json", {codigo:codigo},
			function(data){
				if(data.success){
					$('#clienteFields').show();
					$('#razonSocial').val(data.cliente.razonSocial);
					$('#telefono').val(data.cliente.telefono);
					$('#telefono2').val(data.cliente.telefono2);
					$('#telefono3').val(data.cliente.telefono3);
					$('#telefono4').val(data.cliente.telefono4);
					$('#email').val(data.cliente.email);
					$('#email2').val(data.cliente.email2);
					$('direccionId').val(data.direccion.id);
					$('#calle').val(data.direccion.calle);
					$('#numero').val(data.direccion.numero);					
					$('#piso').val(data.direccion.piso);					
					$('#depto').val(data.direccion.depto);
					$('#codPostal').val(data.direccion.codPostal);
					$('#localidad').val(data.direccion.localidad);
					if($('#provinciaDesc').val() != null){
						$('#provinciaDesc').val(data.provincia.descripcion);						
					}
					if($('#provincia').val() != null){
						$('#provincia').val(data.provincia.id);
					}					
					$('#cuit').val(data.cliente.cuit);
					$('#responsableInsc').val(data.cliente.responsableInsc);
					$('#descuento').val(data.cliente.descuento);
				}else{
					$('#clienteFields').hide();
				}				
			});		
}

function showItem(itemId, descuentoId, itemNumber){		
	if(itemNumber <= 30){
		$(itemId).show();
		$(descuentoId).val($('#descuento').val());		
	}
	
	typeN = $('#typeN').val();	
	if(itemNumber > 30 && (typeN == 2)){
		$(itemId).show();		
		$(descuentoId).val($('#descuento').val());
	}
}

function deleteItemFactura(itemIdString, itemIdVar, marcaid){
	idCliente = $('#id').val();
	typeN = $('#typeN').val();	
		if($(marcaid).val() == ''){
		$.getJSON("../factura/DeleteItem.json", {itemId:itemIdString, idCliente:idCliente, n:typeN},
			function(data){
				if(data.success){
					$('#subTotal').val(data.subTotal);
					$('#iva').val(data.iva);
					$('#descuentoTotal').val(data.descuentoTotal);					
					$('#total').val(data.total);
					$(itemIdVar).hide();
				}
			});		
	}
}

function addOrDelItem(itemId, cantidadId, descuentoId, marcaId, familiaId, codigoId, totalId, precioId){
	idCliente = $('#id').val();
	typeN = $('#typeN').val();	
	
	if(!$('input[name=' + itemId + ']').is(':checked')){		
		$.getJSON("../factura/DeleteItem.json", {itemId:itemId, idCliente:idCliente, n:typeN},
			function(data){
				if(data.success){
					$('#subTotal').val(data.subTotal);
					$('#iva').val(data.iva);
					$('#descuentoTotal').val(data.descuentoTotal);					
					$('#total').val(data.total);
					
				}
			});
	}else{		
		doCalculos(itemId, cantidadId, descuentoId, marcaId, familiaId, codigoId, totalId, precioId);
	}
}

function deleteItem(itemIds){
	idCliente = $('#id').val();
	typeN = $('#typeN').val();	
	
	if(!$('input[name=' + itemIds + ']').is(':checked')){
		$.getJSON("../factura/DeleteItem.json", {itemId:itemIds, idCliente:idCliente, n:typeN},
			function(data){
				if(data.success){
					$('#subTotal').val(data.subTotal);
					$('#iva').val(data.iva);
					$('#descuentoTotal').val(data.descuentoTotal);					
					$('#total').val(data.total);
					
				}
			});
	}			
}

function getFamiliasItem(marcaId, familiaId1, codigoItem){
	marca = $(marcaId).val();	

	$.getJSON("../articulo/GetFamiliaCombo.json", {marca:marca},
			function(data){
				if(data.success){
					$(familiaId1).children().remove();
					$(familiaId1).append("<option value='"+ "'>Seleccione...</option>");
					$.each(data.familias, function(i, item){
			            $(familiaId1).append("<option value='"+ item.codigo+"'>"+ item.familiaComboDesc +"</option>");
			        });		
				}				
			});		
}

function listCodigosItem(marcaId, familiaId, codigoId)
{
	marcaId = $(marcaId).val();
	familiaId = $(familiaId).val();

	$.getJSON("../articulo/GetCodigosCombo.json", {marca:marcaId, familia:familiaId},
			function(data){
				if(data.success){					
					$(codigoId).children().remove();
					$(codigoId).append("<option value='"+ "'>Seleccione...</option>");					
					$.each(data.articulos, function(i, item){
			            $(codigoId).append("<option value='"+ item.codigo +"'>"+ item.articuloComboDesc +"</option>");
			        });		
				}				
			});		
}

function showPrecioItem(marcaId, familiaId, codigoId, precio)
{
	marca = $(marcaId).val();
	familia = $(familiaId).val();
	codigo = $(codigoId).val();
	
	$.getJSON("../articulo/GetArticulo.json", {marca:marca, familia:familia, codigo:codigo},
			function(data){
				if(data.success){
					$(precio).val(data.articulo.precioVenta);
				}
			});
}

function validateCode(marcaId, familiaId, codigoId){
	marca = $(marcaId).val();
	familia = $(familiaId).val();
	codigo = $(codigoId).val();	
	
	$.getJSON("../factura/ValidateItem.json", {marca:marca, familia:familia, codigo:codigo},
			function(data){
				if(data.existing){
					alert('Art�culo existente');
					$(codigoId).val('');
				}
			});
}

//Validates articulo edit with json to maintain combo values for marca, familia, codigo.
function validateEditArticulo(){
	marca = $(marcaId).val();
	codigo = $(codigo).val();
	familia = $(familiaId).val();
	descripcion = $(descripcion).val();
	stockMin = $(stockMinimo).val();
	stock = $(stock).val();
	precioCosto = $(precioCosto).val();
	precioVenta = $(precioVenta).val();
	fechaModificacion = $(fechaModificacion).val();
	fechaModifVenta = $(fechaModifVenta).val();
	
	alert('Marca: ' + marca + ' Codigo: ' + ' Familia: ' + familia);
	
	$.getJSON("../articulo/ArticuloEditValidation.json", {marca:marca, codigo:codigo, familia:familia, descripcion:descripcion,
		stockMin:stockMin, stock:stock, precioCosto:precioCosto, precioVenta:precioVenta, fechaModificacion:fechaModificacion, fechaModifVenta:fechaModifVenta},
		function(data){
			if(!data.success){
				alert('Error, verifique los campos');
				return false;
			}
			return true;
		});
}

function doCalculos(itemId, cantidadId, descuentoId, marcaId, familiaId, codigoId, totalId, precioId){	
	cantidad = $(cantidadId).val();
	descuento = $(descuentoId).val();
	marca = $(marcaId).val();
	familia = $(familiaId).val();
	codigo = $(codigoId).val();
	precio = $(precioId).val();
	$(totalId).val(cantidad * precio);
	idCliente = $('#id').val();
	typeN = $('#typeN').val();		
	
	$.getJSON("../factura/DoCalculos.json", {itemId:itemId, cantidad:cantidad, descuento:descuento,
		marcaId:marca, familiaId:familia, codigo:codigo, idCliente:idCliente, n:typeN, 
		precio:precio},
		function(data){			
			if(data.success){
				$('#subTotal').val(data.subTotal);
				$('#iva').val(data.iva);
				$('#descuentoTotal').val(data.descuentoTotal);				
				$('#total').val(data.total);
			}
		});
}

function validateCantidadNC(cantidadItemId, cantidadItemDB){	
	var cantidad = $(cantidadItemId).val();
	
	if(cantidad > cantidadItemDB){
		$(cantidadItemId).val(cantidadItemDB);
		alert("La Cantidad no puede ser superior a: " + cantidadItemDB);
	}	
}

function showParamsN(){
	var param = $('#proxNumRemito').val() + '';
	if(param.indexOf('n') >= 0){
		$('#paramsN').show();
		$('#proxNumRemito').val(param.replace('n',''));
	}
	if(param.indexOf('N') >= 0){
		$('#paramsN').show();
		$('#proxNumRemito').val(param.replace('N',''));
	}
}

function setFacturaType(param, paramSet){
	var paramS = $(param).val() + '';	
	
	if(paramS.indexOf('n') >= 0 || paramS.indexOf('N') >= 0){
		$(paramSet).val(2);
	}else{
		$(paramSet).val(1);
	}
	if(paramS.indexOf('n') >= 0){
		$(param).val(paramS.replace('n',''));
	}
	if(paramS.indexOf('N') >= 0){
		$(param).val(paramS.replace('N',''));
	}
}

function setNType(){
	var param = $('#cuit').val() + '';
	
	if(param.indexOf('n') >= 0){
		$('#comentariosField').show();
		$('#impresoras').show();
		$('#typeN').val(2);
		$('#cuit').val(param.replace('n',''));
	}
	if(param.indexOf('N') >= 0){
		$('#comentariosField').show();
		$('#impresoras').show();
		$('#typeN').val(2);
		$('#cuit').val(param.replace('N',''));
	}	
}

function showFactura(){
	var param = $('#nroFactura').val() + '';
	
	if(param.indexOf('n') >= 0){
		$('#typeF').val('2');
		$('#nroFactura').val(param.replace('n',''));
	}
	if(param.indexOf('N') >= 0){
		$('#typeF').val('2');
		$('#nroFactura').val(param.replace('N',''));
	}
}

function showClienteAndFirstItem(itemId, descuentoId1, itemNum){
	showCliente();
	showItem(itemId, descuentoId1, itemNum);
}