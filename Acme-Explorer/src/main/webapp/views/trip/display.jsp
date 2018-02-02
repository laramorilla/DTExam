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

<div id="banner">
	<a href="${sponsorship.information}">
		<img src="${sponsorship.banner}" />
	</a>
</div>

<h3><b><spring:message code="trip.ticker"/>:&nbsp;</b><jstl:out value="${trip.ticker}"/></h3>

<h3><b><spring:message code="trip.title"/>:&nbsp;</b><jstl:out value="${trip.title}"/></h3>

<b><spring:message code="trip.description"/>:&nbsp;</b><jstl:out value="${trip.description}"/>
<br/>

<b><spring:message code="trip.category"/>:&nbsp;</b><a href="trip/list-byCategoryId.do?categoryId=${trip.category.id}"><jstl:out value="${trip.category.name}"/></a>
<br/>

<spring:message var="listTags" code="trip.listTags"/>
<b><spring:message code="trip.tags"/>:&nbsp;</b><a href="hasValue/list.do?tripId=${trip.id}"><jstl:out value="${listTags}"/></a>
<br/>

<b><spring:message code="trip.manager"/>:&nbsp;</b><jstl:out value="${trip.manager.name}"/>
<br/>

<b><spring:message code="trip.ranger"/>:&nbsp;</b><a href="ranger/display.do?rangerId=${trip.ranger.id}"><jstl:out value="${trip.ranger.name}"/></a>
<br/>

<b><spring:message code="trip.legalText"/>:&nbsp;</b><a href="legalText/display.do?legalTextId=${trip.legalText.id}"><jstl:out value="${trip.legalText.title}"/></a>
<br/>

<spring:message var="listAuditRecords" code="trip.listAuditRecords"/>
<b><spring:message code="trip.auditRecords"/>:&nbsp;</b><a href="auditRecord/list.do?tripId=${trip.id}"><jstl:out value="${listAuditRecords}"/></a>
<br/>

<spring:message var="listSurvivalClasses" code="trip.listSurvivalClasses"/>
<b><spring:message code="trip.survivalClasses"/>:&nbsp;</b><a href="survivalClass/list.do?tripId=${trip.id}"><jstl:out value="${listSurvivalClasses}"/></a>
<br/>

<jstl:if test="${trip.endDateTrip < date}">
	<spring:message var="listStories" code="trip.listStories"/>
	<b><spring:message code="trip.stories"/>:&nbsp;</b><a href="story/list.do?tripId=${trip.id}"><jstl:out value="${listStories}"/></a>
	<br/>
</jstl:if>

<spring:message var="patternDate" code="event.pattern.date" />
<b><spring:message code="trip.publicationDate"/>:&nbsp;</b><fmt:formatDate value="${trip.publicationDate}" pattern="${patternDate}"/>
<br/>

<b><spring:message code="trip.startDate"/>:&nbsp;</b><fmt:formatDate value="${trip.startDateTrip}" pattern="${patternDate}"/>
<br/>

<b><spring:message code="trip.endDate"/>:&nbsp;</b><fmt:formatDate value="${trip.endDateTrip}" pattern="${patternDate}"/>
<br/>

<b><spring:message code="trip.requirements"/>:&nbsp;</b><jstl:out value="${trip.requirements}" />
<br/>

<spring:message var="patternPrice" code="event.pattern.price" />
<b><spring:message code="trip.price"/>:&nbsp;</b><fmt:formatNumber value="${trip.price}" pattern="${patternPrice}" />
<br/>

<jstl:if test="${trip.cancelledReason == true}">
	<b><spring:message code="trip.cancelledReason"/>:&nbsp;</b><jstl:out value="${trip.cancelledReason}"/>
	<br/>
</jstl:if>

<a href="stage/list.do?tripId=${trip.id}"><spring:message code="trip.listStages"/></a>
<br/>

<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${trip.manager.userAccount.username == pageContext.request.userPrincipal.name}">
		<a href="application/manager/list.do?tripId=${trip.id}"><spring:message code="trip.listApplications"/></a>
		<br/>
	</jstl:if>
</security:authorize>

<security:authorize access="hasRole('EXPLORER')">
	<a href="application/explorer/create.do?tripId=${trip.id}"><spring:message code="trip.createApplication"/></a>
	<br/>
</security:authorize>

<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${trip.manager.userAccount.username == pageContext.request.userPrincipal.name}">
		<a href="note/manager/list.do?tripId=${trip.id}"><spring:message code="trip.listNotes"/></a>
		<br/>
	</jstl:if>
</security:authorize>

<security:authorize access="hasRole('AUDITOR')">
	<a href="note/auditor/create.do?tripId=${trip.id}"><spring:message code="trip.createNote"/></a>
	<br/>
</security:authorize>

<security:authorize access="hasRole('SPONSOR')">
	<a href="sponsorship/sponsor/create.do?tripId=${trip.id}"><spring:message code="trip.createNote"/></a>
	<br/>
</security:authorize>

<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${canEdit}">
		<a href="trip/manager/edit.do?tripId=${trip.id}"><spring:message code="trip.editTrip"/></a>
		<br/>
	</jstl:if>
</security:authorize>

<security:authorize access="hasRole('MANAGER')">
	<input type="button" name="cancel" value="<spring:message code="trip.cancel" />" onclick="javascript: relativeRedir('trip/manager/list.do');" />
</security:authorize>
<security:authorize access="isAnonymous()">
	<input type="button" name="cancel" value="<spring:message code="trip.cancel" />" onclick="javascript: relativeRedir('trip/list.do');" />
</security:authorize>