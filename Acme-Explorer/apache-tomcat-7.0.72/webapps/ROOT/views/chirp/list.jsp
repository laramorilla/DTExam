<%--
 * list.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
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
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<!-- 
	TODO: add a link so that authenticated users can create their own chirps
	The link must point to "chirp/user/create.do".  Consult the i18n&l10n bundles
	to find which the appropriate message codes are.   
-->


<!--
	TODO: add a link to list all of the chirps (chirp/list.do)  
-->

<!--
	TODO: add a listing with a single column to list recent chirps.  Consult the 
	slides to find out how the chirps must be formatted. 
-->

<security:authorize access="hasRole('USER')">
    <a href="chirp/user/create.do" ><spring:message code="chirp.create"/></a>
</security:authorize>

<br>
<a href = "chirp/list.do"><spring:message code="chirp.list.all"/></a>
<br>

<display:table name="chirps" id="row" requestURI="chirp/list.do" pagesize="10" class="displaytag">
    <display:column>
        <a href="chirp/list.do?userId=${row.user.id}"> ${row.user.name} <spring:message code="chirp.chirped"/> </a><br>
        <a>${row.text}</a><br>
        <a><spring:message code="chirp.on"></spring:message> ${row.moment}</a><br>
    </display:column>

</display:table>