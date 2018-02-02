<%--
 * list.jsp
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
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="hasValues" id="row" pagesize="5" class="displaytag" requestURI="hasValue/list.do">
	
	<jstl:if test="${belongTrip}">
		<display:column>
			<a href="hasValue/manager/edit.do?hasValueId=${row.id}"><spring:message code="hasValue.edit"/></a>
		</display:column>
	</jstl:if>
	
	<spring:message var="tagHeader" code="hasValue.tag" />
	<display:column title="${tagHeader}">
		<jstl:out value="${row.tag.name}" />
	</display:column>
	
	<spring:message var="valueHeader" code="hasValue.value" />
	<display:column property="value" title="${valueHeader}"/>
		
</display:table>

<jstl:if test="${belongTrip}">
	<a href="hasValue/manager/create.do?tripId=${trip.id}"><spring:message code="hasValue.assign"/></a>
	<br/>
</jstl:if>

<input type="button" name="cancel" value="<spring:message code="hasValue.cancel" />" onclick="javascript: relativeRedir('trip/display.do?tripId=${trip.id}')" />
