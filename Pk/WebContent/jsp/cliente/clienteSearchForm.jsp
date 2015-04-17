<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>				
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="-1">		
		<title>PK WEB</title>			
		<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">		
		<link href="<c:out value="${pageContext.request.contextPath}"/>/jsp/css/pk.css" rel="stylesheet" type="text/css"></link>		
		<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/jquery.js"></script>
		<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/labs_json.js"></script>
		<script type="text/javascript">$.ajaxSetup({cache: false});</script>
		<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/jsp/js/functions.js"></script>
		<style>
			.error {
			color: #ff0000;
			font-style: italic;
			}
		</style>		
	</head>
	<body class="body">
		<table align="center" border="0" cellspacing="1" cellpadding="1" width="800" height="100%">
			<tr>
				<td class="upperTitle" height="20" colspan="2"><i>Emilio A. LORIS</i></td>
			</tr>
			<tr>
				<td width="220">
					<table border="0" cellspacing="1" cellpadding="1" width="100%">
						<tr height="80">
							<td class="title"><b>Menu</b></td>
						</tr>
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/marca/create.htm">Marcas</a></td>
						</tr>
						<tr height="40">							
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/familia.htm">Familias</a></td>							
						</tr>
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/articulo.htm">Articulos</a></td>
						</tr>						
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/clientes.htm">Clientes</a></td>
						</tr>
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/listas.htm">Listas de Precios</a></td>
						</tr>
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/factura/facturacion.htm">Facturación</a></td>
						</tr>
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/estadisticas.htm">Estadísticas</a></td>
						</tr>
						<tr height="40">
							<td class="labelStyle"><a href="<c:out value="${pageContext.request.contextPath}"/>/controller/backup.htm">Copia de Seguridad</a></td>
						</tr>
						<tr>
							<td height="100%" class="labelStyle">&nbsp;</td>
						</tr>
					</table>
				</td>
				<td width="580">
						<table border="0" cellspacing="1" cellpadding="1" width="100%">
							<c:if test="${not empty clientesPage}">
							<tr>
								<td class="title" height="5" colspan="6">Clientes</td>
							</tr>
							<tr>
								<td class="title" height="5">Código :</td>
								<td class="title" height="5">Razón Social :</td>
								<td class="title" height="5">Cod. Postal :</td>	  
								<td class="title" height="5">Teléfono :</td>
								<td class="title" height="5">Cuit :</td>
								<td class="title" height="5">R. Inscripto :</td>				
							</tr>
							<c:forEach items="${clientesPage}" var="clienteVar" varStatus="control">
								<tr>					
									<td class="valueStyle" style="text-align: center;" ><c:out value="${clienteVar.id}"></c:out></td>
									<td class="valueStyle" style="text-align: center;"><c:out value="${clienteVar.razonSocial}"></c:out></td>
									<td class="valueStyle" style="text-align: center;"><c:out value="${clienteVar.direccion.codPostal}"></c:out></td>
									<td class="valueStyle" style="text-align: center;"><c:out value="${clienteVar.telefono}"></c:out></td>
									<td class="valueStyle" style="text-align: center;"><c:out value="${clienteVar.cuit}"></c:out></td>
									<td class="valueStyle" style="text-align: center;"><c:out value="${clienteVar.regimenForPrint}"></c:out></td>
								</tr>
							</c:forEach>
							<c:if test="${paginator.moreRecords}">
								<tr>
									<td class="emptyColumn" height="10%" colSpan="6">&nbsp;</td>
								</tr>
								<tr height="10%">
									<c:if test="${paginator.pageNumber > 1}">
										<td class="valueStyle" style="text-align: center;" colspan="3">
											<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/cliente/paginator/previous.htm"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/previous.gif" title="Anterior" border="0" height="24" width="40"/></a>
										</td>
									</c:if>
									<c:if test="${paginator.pageNumber == 1}">
										<td class="valueStyle" colspan="3">
											&nbsp;
										</td>
									</c:if>
									<td class="valueStyle" style="text-align: center;" colspan="3">
										<a href="<c:out value="${pageContext.request.contextPath}"/>/controller/cliente/paginator/next.htm"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/next.gif" title="Siguiente" border="0" height="24" width="40"/></a>
									</td>
								</tr>	
							</c:if>
							<c:if test="${!paginator.moreRecords}">
								<c:if test="${paginator.pageNumber > 1}">
									<tr height="10%">
										<td class="emptyColumn" height="5%" colSpan="6">&nbsp;</td>
									</tr>
									<tr height="10%">	
										<td class="valueStyle" style="text-align: center;" colspan="3">
											 <a href="<c:out value="${pageContext.request.contextPath}"/>/controller/cliente/paginator/previous.htm"><img src="<c:out value="${pageContext.request.contextPath}"/>/images/previous.gif" title="Anterior" border="0" height="24" width="40"/></a>
										</td>
										<td class="valueStyle" colspan="3">
											&nbsp;
										</td>
									</tr>
								</c:if>
							</c:if>						
						</c:if>
						<tr>
							<td class="emptyColumn" colSpan="6">&nbsp;</td>
						</tr>			
					</table>
				</td>
				<tr>				
					<td height="100%" class="labelStyle" width="220">
						&nbsp;
					</td>
					<td width="580">
					<form:form method="POST" commandName="cliente">				
						<table border="0" cellspacing="1" cellpadding="1" width="100%">				
							<tr>
								<td height="50" class="title" colspan="6"><b>Clientes</b></td>
							</tr>			
							<tr>
								<td class="title" colspan="3" height="5">Cliente :</td>					
								<td colspan="5">				
									<form:select path="id" onchange="showCliente()">	            		
					            		<form:option value="" label="Seleccione..."/>
					            		<form:options itemLabel="clienteComboDescription" items="${clientes}" itemValue="id"/>
					        		</form:select>
								</td>				
							</tr>
							<tbody id="clienteFields" style="display: none;">
								<tr>
									<td class="title" colspan="2" height="5">Razón Social :</td>					
									<td>
										<form:input path="razonSocial" cssClass="textboxStyle"/>					
									</td>					
								</tr>
								<tr>
									<td class="title" colspan="2" height="5">Teléfono :</td>
									<td>
										<form:input path="telefono" cssClass="textboxStyle"/>						
									</td>					
									<td class="title" height="5">Teléfono 2:</td>
									<td>
										<form:input path="telefono2" cssClass="textboxStyle"/>						
									</td>					
								</tr>
								<tr>
									<td class="title" colspan="2" height="5">Teléfono 3:</td>
									<td>
										<form:input path="telefono3" cssClass="textboxStyle"/>						
									</td>					
									<td class="title" height="5">Teléfono 4:</td>
									<td>
										<form:input path="telefono4" cssClass="textboxStyle"/>						
									</td>					
								</tr>
								<tr>
									<td class="title" colspan="2" height="5">Email :</td>
									<td>
										<form:input path="email" cssClass="textboxStyle"/>						
									</td>					
									<td class="title" height="5">Email 2:</td>
									<td>
										<form:input path="email2" cssClass="textboxStyle"/>						
									</td>					
								</tr>
								<tr>				
									<td class="title" colspan="2" height="5">Calle :</td>
									<td><form:input id="calle" cssClass="textboxStyle" path="direccion.calle" /></td>					
									<td class="title" height="5">Número :</td>
									<td><form:input cssClass="textboxStyle" id="numero" path="direccion.numero" /></td>					
								</tr>
								<tr>	
									<td class="title" colspan="2" height="5">Piso :</td>
									<td><form:input cssClass="textboxStyle" id="piso" path="direccion.piso" /></td>					
									<td class="title" height="5">Depto :</td>
									<td><form:input cssClass="textboxStyle" id="depto" path="direccion.depto" /></td>					
								</tr>
								<tr>
									<td class="title" colspan="2" height="5">Cod. Postal :</td>
									<td><form:input cssClass="textboxStyle" id="codPostal" path="direccion.codPostal" /></td>					
									<td class="title" height="5">Localidad :</td>
									<td><form:input cssClass="textboxStyle" id="localidad" path="direccion.localidad" /></td>					
								</tr>				
								<tr>
									<td class="title" colspan="2" height="5">Provincia :</td>
									<td><form:input id="provinciaDesc" path="direccion.provincia.id" /></td>					
									<td class="title" height="5">CUIT :</td>
									<td><form:input cssClass="textboxStyle" path="cuit" /></td>					
								</tr>				
								<tr>
									<td class="title" colspan="2" height="5">Regimen :</td>
									<td>
										<form:select path="responsableInsc">	            		
						            		<form:option value="" label="Seleccione..."/>
						            		<form:option value="S" label="Inscripto"/>
						            		<form:option value="N" label="No inscripto"/>
							            </form:select>
						        	</td>					
									<td class="title" height="5">Descuento :</td>
									<td><form:input cssClass="textboxStyle" path="descuento" /></td>					
								</tr>				
							</tbody>						
							<tr>
								<td class="emptyColumn" colSpan="6">&nbsp;</td>
							</tr>
						</table>
					</form:form>
				</td>
					
			</tr>
			<tr>
				<td class="upperTitle" height="20" colspan="2" align="center">© Copyright LOBYZON</td>
			</tr>
		</table>
	</body>		
</html>