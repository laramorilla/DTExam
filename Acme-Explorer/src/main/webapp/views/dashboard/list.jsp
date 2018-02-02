<%--
 * list.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<jstl:if test="${!(minMaxAvgStddevOfApplicationsPerTrip[0] eq null)}">
<fieldset>
	<div>
		<b><spring:message code="dashboard.minMaxAvgStddevOfApplicationsPerTrip" /></b>
		<br /> 
		<h4><jstl:out value=" MIN: "/><jstl:out value="${minMaxAvgStddevOfApplicationsPerTrip[0][0]}"/> <br /></h4>
		<h4><jstl:out value=" MAX: "/><jstl:out value="${minMaxAvgStddevOfApplicationsPerTrip[0][1]}"/> <br /> </h4>
		<h4><jstl:out value=" AVG: "/><jstl:out value="${minMaxAvgStddevOfApplicationsPerTrip[0][2]}"/> <br /></h4>
		<h4><jstl:out value=" STDDEV: "/><jstl:out value="${minMaxAvgStddevOfApplicationsPerTrip[0][3]}"/> <br /></h4>
	</div>
</fieldset>
</jstl:if>

<br />

<jstl:if test="${!(minMaxAvgStddevOfTripsPerManager[0] eq null)}">
<fieldset>
	<div>
		<b><spring:message code="dashboard.minMaxAvgStddevOfTripsPerManager" /></b>
		<br /> 
		<h4><jstl:out value=" MIN: "/><jstl:out value="${minMaxAvgStddevOfTripsPerManager[0][0]}"/> <br /></h4>
		<h4><jstl:out value=" MAX: "/><jstl:out value="${minMaxAvgStddevOfTripsPerManager[0][1]}"/> <br /> </h4>
		<h4><jstl:out value=" AVG: "/><jstl:out value="${minMaxAvgStddevOfTripsPerManager[0][2]}"/> <br /></h4>
		<h4><jstl:out value=" STDDEV: "/><jstl:out value="${minMaxAvgStddevOfTripsPerManager[0][3]}"/> <br /></h4>
	</div>
</fieldset>
</jstl:if>

<br />

<jstl:if test="${!(minMaxAvgStddevOfPricePerTrip[0] eq null)}">
<fieldset>
	<div>
		<b><spring:message code="dashboard.minMaxAvgStddevOfPricePerTrip" /></b>
		<br /> 
		<h4><jstl:out value=" MIN: "/><jstl:out value="${minMaxAvgStddevOfPricePerTrip[0][0]}"/> <br /></h4>
		<h4><jstl:out value=" MAX: "/><jstl:out value="${minMaxAvgStddevOfPricePerTrip[0][1]}"/> <br /> </h4>
		<h4><jstl:out value=" AVG: "/><jstl:out value="${minMaxAvgStddevOfPricePerTrip[0][2]}"/> <br /></h4>
		<h4><jstl:out value=" STDDEV: "/><jstl:out value="${minMaxAvgStddevOfPricePerTrip[0][3]}"/> <br /></h4>
	</div>
</fieldset>
</jstl:if>

<br />

<jstl:if test="${!(minMaxAvgOfTripsPerRanger[0] eq null)}">
<fieldset>
	<div>
		<b><spring:message code="dashboard.minMaxAvgOfTripsPerRanger" /></b>
		<br /> 
		<h4><jstl:out value=" MIN: "/><jstl:out value="${minMaxAvgOfTripsPerRanger[0][0]}"/> <br /></h4>
		<h4><jstl:out value=" MAX: "/><jstl:out value="${minMaxAvgOfTripsPerRanger[0][1]}"/> <br /> </h4>
		<h4><jstl:out value=" AVG: "/><jstl:out value="${minMaxAvgOfTripsPerRanger[0][2]}"/> <br /></h4>
		<h4><jstl:out value=" STDDEV: "/><jstl:out value="${minMaxAvgOfTripsPerRanger[0][3]}"/> <br /></h4>
	</div>
</fieldset>
</jstl:if>

<br />

<jstl:if test="${!(ratioApplicationsPending eq null)}">
<fieldset>
	<div>
		<b><spring:message code="dashboard.ratioApplicationsPending" /></b>
		<br /> 
		<h4><jstl:out value=" RATIO: "/><jstl:out value="${ratioApplicationsPending}"/> <br /></h4>
	</div>
</fieldset>
</jstl:if>

<br />

<jstl:if test="${!(ratioApplicationsDue eq null)}">
<fieldset >
	<div >
		<b><spring:message code="dashboard.ratioApplicationsDue" /></b>
		<br /> 
		<h4><jstl:out value=" RATIO: "/><jstl:out value="${ratioApplicationsDue}"/> <br /></h4>
	</div>
</fieldset>
</jstl:if>

<br />

<jstl:if test="${!(ratioApplicationsAccepted eq null)}">
<fieldset >
	<div >
		<b><spring:message code="dashboard.ratioApplicationsAccepted" /></b>
		<br /> 
		<h4><jstl:out value=" RATIO: "/><jstl:out value="${ratioApplicationsAccepted}"/> <br /></h4>
	</div>
</fieldset>
</jstl:if>

<br />

<jstl:if test="${!(ratioApplicationsCancelled eq null)}">
<fieldset >
	<div>
		<b><spring:message code="dashboard.ratioApplicationsCancelled" /></b>
		<br /> 
		<h4><jstl:out value=" RATIO: "/><jstl:out value="${ratioApplicationsCancelled}"/> <br /></h4>
	</div>
</fieldset>
</jstl:if>

<br />

<jstl:if test="${!(ratioTripsCancelled eq null)}">
<fieldset>
	<div>
		<b><spring:message code="dashboard.ratioTripsCancelled" /></b>
		<br /> 
		<h4><jstl:out value=" RATIO: "/><jstl:out value="${ratioTripsCancelled}"/> <br /></h4>
	</div>
</fieldset>
</jstl:if>

<br />

<fieldset>
<display:table name="findTrips10MoreApplications" id="row" pagesize="5" class="displaytag" requestURI="${requestURI}">
	<spring:message var="tickerHeader" code="trip.ticker"/>
	<display:column property="ticker" title="${tickerHeader}"/>
	
	<spring:message var="titleHeader" code="trip.title"/>
	<display:column property="title" title="${titleHeader}"/>
	
	<spring:message var="categoryHeader" code="trip.category"/>
	<display:column property="category.name" title="${categoryHeader}" sortable="true"/>
	
	<spring:message var="startDateHeader" code="trip.startDate"/>
	<spring:message var="formatDate" code="event.format.date"/>
	<display:column property="startDateTrip" title="${startDateHeader}" format="${es_enFormatDate}" sortable="true" />
	
	<spring:message var="endDateHeader" code="trip.endDate"/>
	<display:column property="endDateTrip" title="${endDateHeader}" format="${es_enFormatDate}" sortable="true" />
	
	<spring:message var="priceHeader" code="trip.price"/>
	<spring:message var="formatPrice" code="event.format.price" />
	<display:column property="price" title="${priceHeader}" sortable="true" format="${formatPrice}"/>
	
</display:table>
</fieldset>
<br/>

	<spring:message  code="dashboard.findLegalTextOrderByReferenced"/>

<table>
    
    <jstl:forEach var="legal" items="${findLegalTextOrderByReferenced}">
        <tr><td><jstl:out value="${legal[0]}"/></td> <td><jstl:out value="${legal[1]}"/> </td></tr>
    </jstl:forEach>
</table>

<br>
<br/>