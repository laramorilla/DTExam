<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="socialIdentities" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<display:column> 
		<a href="socialIdentity/edit.do?socialIdentityId=${row.id}"><spring:message code="socialIdentity.edit"/></a>
	</display:column>

    <spring:message code="socialIdentity.nick" var="nickHeader"/>
	<display:column property="nick" title="${nickHeader}" sortable="false"/>
	
	<spring:message code="socialIdentity.nameSocialNetwork" var="nameSocialNetworkHeader"/>
	<display:column property="nameSocialNetwork" title="${nameSocialNetworkHeader}" sortable="false"/>
	
	<spring:message code="socialIdentity.link" var="linkHeader"/>
	<display:column property="link" title="${linkHeader}" sortable="false"/>
	
	<spring:message code="socialIdentity.photo" var="photoHeader"/>
	<display:column property="photo" title="${photoHeader}" sortable="false"/>
	
	<display:column> 
		<a href="socialIdentity/display.do?socialIdentityId=${row.id}"><spring:message code="socialIdentity.display"/></a>
	</display:column> 

</display:table>


	<a href="socialIdentity/create.do"><spring:message code="socialIdentity.create"/></a>
