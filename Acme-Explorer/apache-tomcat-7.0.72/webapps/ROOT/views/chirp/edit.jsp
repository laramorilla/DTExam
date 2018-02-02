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
	TODO: create a form to edit a chirp.  Consult the slides to find out how the 
	form must look like.  
-->

<form:form action="chirp/user/edit.do" modelAttribute="chirp">

	<form:hidden path="id"/>
	<form:hidden path="version"/>


	<form:hidden path="moment"/>
	<form:hidden path="user"/>


	<form:label path="text"><spring:message code="chirp.type.chirp"/> </form:label>
	<form:input path="text"/>
	<form:errors path="text" cssClass="error"></form:errors>
	<br>

	<input type="submit" name="publish" value="<spring:message code="chirp.publish"/>" />

	<input type="button" name="cancel" value="<spring:message code="chirp.cancel" />"
		   onclick="javascript: relativeRedir('chirp/list.do');" />
</form:form>