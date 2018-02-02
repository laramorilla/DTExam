<%--
 * edit.jsp
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

<h3><b><spring:message code="stage.title" />:&nbsp;</b><jstl:out value="${stage.title}" /></h3>

<b><spring:message code="stage.trip" />:&nbsp;</b><jstl:out value="${stage.trip.ticker}" />
<br/>

<b><spring:message code="stage.description" />:&nbsp;</b><jstl:out value="${stage.description}" />
<br/>

<spring:message var="pattern" code="event.pattern.price"/>
<b><spring:message code="stage.price" />:&nbsp;</b><fmt:formatNumber value="${stage.price}" pattern="${pattern}"/>
<br/>

<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${canEdit == true}">
		<a href="stage/manager/edit.do?stageId=${stage.id}"></a>
	</jstl:if>
</security:authorize>

<input type="button" name="cancel" value="<spring:message code="stage.cancel" />" onclick="javascript: relativeRedir('stage/list.do?tripId=${stage.trip.id}');" />