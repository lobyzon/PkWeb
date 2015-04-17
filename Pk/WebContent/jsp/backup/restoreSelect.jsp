<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 

<jsp:include page="/jsp/include/arriba.jsp" flush="true"/>
		<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%">
			<form:form method="POST" action="restoreDataBase.htm">							
				<c:if test="${empty fileNames}">
					<div class="errors">
						<ul>
							<li>
								<c:out value="${error}"/>
							</li>
						</ul>
					</div>
				</c:if>
				<c:if test="${not empty fileNames}">				
					<tr>
						<td class="title" height="5">Archivo:</td>
						<td>						
							<select id="fileName" name="fileName">
								<c:forEach var="name" items="${fileNames}">
									<option value="<c:out value="${name}"/>"><c:out value="${name}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="labelStyle" colspan="6"><input type="submit" value="Restaurar"></td>
					</tr>				
				</c:if>
			</form:form>			
		</table>
<jsp:include page="/jsp/include/abajo.jsp" />