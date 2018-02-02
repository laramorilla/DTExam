<%-- list.jsp de Application --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="applications" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<div>
		<fmt:formatDate value="${dateSystem}"             pattern="yyyy-MM-dd HH:mm" var="systemDateFormated" />
		<fmt:formatDate value="${dateSystemPlus1Month}"   pattern="yyyy-MM-dd HH:mm" var="systemDateFormatPlus1MonthFormated" />
		<fmt:formatDate value="${row.trip.startDateTrip}" pattern="yyyy-MM-dd HH:mm" var="tripStartDate" />
		
		<jstl:choose>
			<jstl:when test="${tripStartDate > systemDateFormatPlus1MonthFormated and row.status eq 'PENDING'}">  <jstl:set var="style" value="background-color: red; color: white"/> </jstl:when>
			<jstl:when test="${tripStartDate < systemDateFormatPlus1MonthFormated and row.status eq 'PENDING'} "> <jstl:set var="style" value="background-color: white"/> </jstl:when>
			<jstl:when test="${row.status eq 'REJECTED'}">  <jstl:set var="style" value="background-color: gray; color: white"/> </jstl:when>
			<jstl:when test="${row.status eq 'DUE'}">       <jstl:set var="style" value="background-color: yellow; color: black"/> </jstl:when>
			<jstl:when test="${row.status eq 'ACCEPTED'}">  <jstl:set var="style" value="background-color: green; color: white"/> </jstl:when>
			<jstl:when test="${row.status eq 'CANCELLED'}"> <jstl:set var="style" value="background-color: cyan; color: blank" /> </jstl:when>
		</jstl:choose>
		
		<security:authorize access="hasRole('MANAGER')">
			<display:column>
				<a href="application/manager/display.do?applicationId=${row.id}"><spring:message code="application.display"/></a>
				&nbsp;
				<jstl:choose>
					<jstl:when test="${row.status eq 'PENDING'}">
						<a href="application/manager/due.do?applicationId=${row.id}"> <spring:message code="application.dueApplication"/> </a>
						&nbsp;
						<a href="application/manager/edit.do?applicationId=${row.id}"> <spring:message code="application.rejectedApplication"/> </a>
						&nbsp;
					</jstl:when>
					<jstl:when test="${(row.status eq 'DUE' and not empty row.creditCard)}">
						<a href="application/manager/edit.do?applicationId=${row.id}"> <spring:message code="application.edit"/> </a>
					</jstl:when>
				</jstl:choose>
			</display:column>
		</security:authorize>
		
		<security:authorize access="hasRole('EXPLORER')">
			<display:column>
				<jstl:choose>
					<jstl:when test="${row.status eq 'ACCEPTED' and systemDateFormated < tripStartDate}">
						<a href="application/explorer/cancel.do?applicationId=${row.id}"> <spring:message code="application.cancelApplication"/> </a>
					</jstl:when>
					<jstl:when test="${row.status eq 'PENDING'}">
						<a href="application/explorer/edit.do?applicationId=${row.id}"> <spring:message code="application.edit"/> </a>
					</jstl:when>
					<jstl:when test="${row.status eq 'DUE'}">
						<a href="application/explorer/edit.do?applicationId=${row.id}"> <spring:message code="application.addCreditCard"/> </a>
					</jstl:when>
				</jstl:choose>
			</display:column>
			<display:column>
				<a href="application/explorer/display.do?applicationId=${row.id}"><spring:message code="application.display"/></a>
			</display:column>
		</security:authorize>
		
		<security:authorize access="hasRole('MANAGER')">
			<spring:message var="applicationApplicant" code="application.applicant"/>			
			<display:column property="applicant.name" title="${applicationApplicant}" style="${style}"/>
		</security:authorize>
		<spring:message var="applicationTrip" code="application.trip"/>
		<display:column property="trip.title" title="${applicationTrip}" style="${style}"/>
		
		<spring:message var="applicationMoment" code="application.moment"/>
		<spring:message code="application.format.date" var="pattern"></spring:message>
		<display:column property="moment" title="${applicationMoment}" style="${style}"  format="${pattern}"/>
		
		<spring:message var="applicationStatus" code="application.status"/>
		<display:column property="status" title="${applicationStatus}" sortable="true" style="${style}"/>
		
		<spring:message var="applicationRejectReason" code="application.rejectReason"/>
		<display:column property="rejectReason" title="${applicationRejectReason}" style="${style}"/>
	</div>
</display:table>

<jstl:if test="${requestURI == 'application/manager/list.do' }">
	<input type="button" name="cancel" value="<spring:message code="application.cancel" />" onclick="javascript: relativeRedir('trip/display.do?tripId=${tripId}');" />
</jstl:if>


<security:authorize access="hasRole('EXPLORER')">
	<spring:message var="applicationCreate" code="application.create"/> 
	<a href="application/explorer/create.do"> ${applicationCreate}</a>
</security:authorize>