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

<form:form action="hasValue/manager/edit.do" modelAttribute="hasValue">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="trip"/>
	
	<b><label><spring:message code="hasValue.trip"/>:&nbsp;</label></b>
	<input value="<jstl:out value="${hasValue.trip.ticker}"/>" readonly="readonly"/>
	<br/>
	
	<b><form:label path="tag"><spring:message code="hasValue.tag"/>:&nbsp;</form:label></b>
	<form:select path="tag">
		<form:option label="----" value="0"/>
		<form:options items="${tags}" itemLabel="name" itemValue="id"/>
	</form:select>
	<form:errors path="tag" cssClass="error"/>
	<br/>
	
	<b><form:label path="value"><spring:message code="hasValue.value"/>:&nbsp;</form:label></b>
	<form:input path="value"/>
	<form:errors path="value" cssClass="error"/>
	<br/>
	
	<input type="submit" name="save" value="<spring:message code="hasValue.save"/>"/>
	&nbsp;
	<jstl:if test="${hasValue.id != 0}">
		<input type="submit" name="delete" value="<spring:message code="hasValue.delete"/>"/>
		&nbsp;
	</jstl:if>
	<input type="button" name="cancel" value="<spring:message code="hasValue.cancel" />" onclick="javascript: relativeRedir('hasValue/list.do?tripId=${hasValue.trip.id}');" />

</form:form>