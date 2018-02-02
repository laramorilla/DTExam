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

<form:form action="stage/manager/edit.do" modelAttribute="stage">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="trip" />
	
	<b><spring:message code="stage.trip"/>:&nbsp;</b>
	<input type="text" value="${stage.trip.ticker}" readonly="readonly"/>
	<br/>
	
	<b><form:label path="title"><spring:message code="stage.title"/>:&nbsp;</form:label></b>
	<form:input path="title"/>
	<form:errors path="title" cssClass="error"/>
	<br/>
	
	<b><form:label path="description"><spring:message code="stage.description" />:&nbsp;</form:label></b>
	<form:textarea path="description" />
	<form:errors path="description" cssClass="error"/>
	<br/>
	
	<b><form:label path="price"><spring:message code="stage.price" />:&nbsp;</form:label></b>
	<form:input path="price" placeholder="ex: 100.00"/>
	<form:errors path="price" cssClass="error"/>
	<br/>
	
	<input type="submit" name="save" value="<spring:message code="stage.save"/>"/>
	&nbsp;
	<jstl:if test="${stage.getId() != 0}">
		<input type="submit" name="delete" value="<spring:message code="stage.delete"/>"/>
		&nbsp;
	</jstl:if>
	<input type="button" name="cancel" value="<spring:message code="stage.cancel" />" onclick="javascript: relativeRedir('stage/list.do?tripId=${stage.trip.id}');" />

</form:form>