

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

<form:form action="message/notification.do" modelAttribute="messageNotification">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="sender" />
	<form:hidden path="folder" />
	<form:hidden path="recipient"/>


	<b><form:label path="subject">
		<spring:message code="message.subject" />:&nbsp;</form:label></b>
		<form:input path="subject" />
	<form:errors path="subject" cssClass="error" />
	<br />

	<b><form:label path="body">
		<spring:message code="message.body" />:&nbsp;</form:label></b>
		<form:textarea path="body" />
	<form:errors path="body" cssClass="error" />
	<br />

	<b><form:label path="priority">
		<spring:message code="message.priority" />:&nbsp;</form:label></b>
		<form:select path="priority">
			<form:options items="${priorities}" />
		</form:select>
	<form:errors path="priority" cssClass="error" />
	<br />


	<input type="submit" name="notify"
		value="<spring:message code="message.notify"/>" />
	&nbsp;

	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="javascript: relativeRedir('folder/list.do');" />

</form:form>