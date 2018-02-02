<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<h1><spring:message code="socialIdentity.nick"/>:&nbsp;<jstl:out value="${socialIdentity.nick}" /></h1>
<br/>

<b><spring:message code="socialIdentity.nameSocialNetwork"/>:&nbsp;</b><jstl:out value="${socialIdentity.nameSocialNetwork}" />
<br/>

<b><spring:message code="socialIdentity.link"/>:&nbsp;</b><jstl:out value="${socialIdentity.link}" />
<br/>


<b><spring:message code="socialIdentity.photo"/>:&nbsp;</b>
<br/>
<img src="https://fjgsmultimedia.files.wordpress.com/2015/05/trabajo-11-montaje-02.jpg"/>


<input type="button" name="cancel" value="<spring:message code="socialIdentity.cancel" />"
        onclick="javascript: relativeRedir('${cancelURI}');" />