 <%--
 * login.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="user/register.do" modelAttribute="user">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="chirps" />
	<form:hidden path="userAccount.Authorities" />

	<form:label path="userAccount.username">
		<spring:message code="user.username" />
	</form:label>
	<form:input path="userAccount.username" />	
	<form:errors class="error" path="userAccount.username" />
	<br />

	<form:label path="userAccount.password">
		<spring:message code="user.password" />
	</form:label>
	<form:password path="userAccount.password" />	
	<form:errors class="error" path="userAccount.password" />
	<br />
	
	<form:label path="name">
		<spring:message code="user.name" />
	</form:label>
	<form:input path="name" />	
	<form:errors class="error" path="name" />
	<br />
	
	<form:label path="email">
		<spring:message code="user.email" />
	</form:label>
	<form:input path="email" />	
	<form:errors class="error" path="email" />
	<br />
	
	<input type="submit" value="<spring:message code="user.register" />" name="register" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="user.cancel" />"
		onclick="javascript: window.location.replace('chirp/list.do')" />
	<br />

	
</form:form>