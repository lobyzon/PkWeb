<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
<%@ taglib uri="/birt.tld" prefix="birt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<table border="0" cellspacing="1" cellpadding="1" width="100%">		
		<tr>
			 <td class="title"><a onclick="openWindow('Original')">Factura Original</a></td>
		</tr>
		<tr>
			 <td class="title"><a onclick="openWindow('Duplicado')">Factura Duplicado</a></td>
		</tr>
	</table>
<jsp:include page="/jsp/include/abajo.jsp"/>
<script type="text/javascript">
	function openWindow(paramOriginalDuplicado){
		var win = window.open("http://localhost:8080/Pk/controller/factura/birtViewer.htm?originalDuplicado=" + paramOriginalDuplicado);
	}
</script>