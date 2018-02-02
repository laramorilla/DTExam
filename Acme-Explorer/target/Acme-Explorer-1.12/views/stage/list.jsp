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

<display:table name="stages" id="row" pagesize="5" class="displaytag" requestURI="stage/list.do">

	<security:authorize access="hasRole('MANAGER')">
		<display:column>
			<jstl:if test="${canCreateEdit == true}">
				<a href="stage/manager/edit.do?stageId=${row.id}"><spring:message code="stage.edit"/></a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
	<display:column>
		<a href="stage/display.do?stageId=${row.id}"><spring:message code="stage.display"/></a>	
	</display:column>
	
	<spring:message var="titleHeader" code="stage.title"/>
	<display:column property="title" title="${titleHeader}"/>
	
	<spring:message var="priceHeader" code="stage.price"/>
	<spring:message var="format" code="event.format.price"/>
	<display:column property="price" title="${priceHeader}" format="${format}" sortable="true" />
	
</display:table>

<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${canCreateEdit == true}">
		<a href="stage/manager/create.do?tripId=${trip.id}"><spring:message code="stage.create"/></a>
		<br/>
	</jstl:if>
</security:authorize>

<input type="button" name="cancel" value="<spring:message code="stage.cancel" />" onclick="javascript: relativeRedir('trip/display.do?tripId=${trip.id}');" />