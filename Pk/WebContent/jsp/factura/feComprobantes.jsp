<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="/birt.tld" prefix="birt" %>
	<table border="0" cellspacing="1" cellpadding="1" width="100%">		
		<tr>
			<td>
				<birt:viewer id="feOriginal" title="Factura Electronica" reportDesign="feLorisOriginal.rptdesign" width="700" height="500" format="pdf"></birt:viewer>
			</td>
		</tr>			
		<tr>
			<td>
				<birt:viewer id="feDuplicado" title="Factura Electronica" reportDesign="feLorisDuplicado.rptdesign" width="700" height="500" format="pdf"></birt:viewer>
			</td>
		</tr>					
	</table>
<jsp:include page="/jsp/include/abajo.jsp"/>