<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="finder/explorer/edit.do" modelAttribute="finder">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="lastUpdate"/>
	<form:hidden path="trips"/>	
	
	<form:label path="keyword"><spring:message code="finder.keyword"/></form:label>
	<form:input path="keyword"/>
	<form:errors path="keyword" cssClass="error"/>
	<br/>
	
	<form:label path="priceMin"><spring:message code="finder.minimumprice"/></form:label>
	<form:input path="priceMin" placeholder="0.0"/>
	<form:errors path="priceMin" cssClass="error"/>
	<br/>
	
	<form:label path="priceMax"><spring:message code="finder.maximumprice"/></form:label>
	<form:input path="priceMax" placeholder="999.0"/>
	<form:errors path="priceMax" cssClass="error"/>
	<br/>
	
	<form:label path="startDateTripMin"><spring:message code="finder.rangeStartingDate"/>:&nbsp;</form:label>
	<spring:message code="finder.startDate"/>
	<form:input path="startDateTripMax"/>
	<spring:message code="finder.endDate"/>
	<form:input path="startDateTripMin"/>
	<form:errors path="startDateTripMax" cssClass="error"/>
	<form:errors path="startDateTripMin" cssClass="error"/>
	<br/>
	
	<input type="submit" name="save" value="<spring:message code="finder.search"/>"/>
	
	<input type="button" name="cancel" value="<spring:message code="finder.cancel" />" onclick="javascript: relativeRedir('trip/explorer/list.do');" />
	
</form:form>

<jstl:if test="${!trips.isEmpty()}">
<display:table name="trips" id="row" pagesize="5" class="displaytag" requestURI="finder/explorer/edit.do" >
	
	<display:column>
		<a href="trip/display.do?tripId=${row.id}"><spring:message code="trip.display"/></a>
	</display:column>
	
	<spring:message var="tickerHeader" code="trip.ticker"/>
	<display:column property="ticker" title="${tickerHeader}"/>
	
	<spring:message var="titleHeader" code="trip.title"/>
	<display:column property="title" title="${titleHeader}"/>
	
	<spring:message var="categoryHeader" code="trip.category"/>
	<display:column property="category.name" title="${categoryHeader}" sortable="true"/>
	
	<spring:message var="startDateHeader" code="trip.startDate"/>
	<display:column property="startDateTrip" title="${startDateHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message var="endDateHeader" code="trip.endDate"/>
	<display:column property="endDateTrip" title="${endDateHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message var="priceHeader" code="trip.price"/>
	<display:column property="price" title="${priceHeader}" sortable="true"/>
	
	<spring:message var="requirementsHeader" code="trip.requirements"/>
	<display:column title="${requirementsHeader}">
		<jstl:forEach var="requirement" items="${row.requirements}">
			<jstl:out value="${requirement}"/>
			<br/>
		</jstl:forEach>
	</display:column>
	
</display:table>
</jstl:if>