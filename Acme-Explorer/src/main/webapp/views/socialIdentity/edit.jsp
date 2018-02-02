<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="${actionURI}" modelAttribute="socialIdentity">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	
	<form:label path="nick"><spring:message code="socialIdentity.nick"/></form:label>
	<form:input path="nick"/>
	<form:errors path="nick" cssClass="error"/>
	<br/>
	
	<form:label path="nameSocialNetwork"><spring:message code="socialIdentity.nameSocialNetwork"/></form:label>
	<form:input path="nameSocialNetwork"/>
	<form:errors path="nameSocialNetwork" cssClass="error"/>
	<br/>
	
	<form:label path="link"><spring:message code="socialIdentity.link"/></form:label>
	<form:input path="link"/>
	<form:errors path="link" cssClass="error"/>
	<br/>
	
	<form:label path="photo"><spring:message code="socialIdentity.photo"/></form:label>
	<form:input path="photo"/>
	<form:errors path="photo" cssClass="error"/>
	<br/>
	
	<input type="submit" name="save" value="<spring:message code="socialIdentity.save"/>" />
	
	<jstl:if test="${socialIdentity.id != 0}">
		<input type="submit" name="delete" value="<spring:message code="socialIdentity.delete"/>"/>
	</jstl:if>
	
	<input type="button" name="cancel" value="<spring:message code="socialIdentity.cancel" />" 
			onclick="javascript: relativeRedir('${cancelURI}');" />
</form:form>