<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="curriculum/ranger/edit.do" modelAttribute="curriculum">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="ticker"/>
	<form:hidden path="educationRecords"/>
	<form:hidden path="endorserRecords"/>
	<form:hidden path="miscellaneousRecords"/>
	<form:hidden path="professionalRecords"/>
	
	<spring:message var="personalRecordFullName" code="curriculum.personalRecord.fullName"/>
	<b><form:label path="personalRecord.fullName"/><jstl:out value="${personalRecordFullName}"/>:&nbsp;</b>
	<form:input path="personalRecord.fullName"/>
	<form:errors path="personalRecord.fullName" cssClass="error"/>
	<br/>
	
	<spring:message var="personalRecordPhoto" code="curriculum.personalRecord.photo"/>
	<b><form:label path="personalRecord.photo"/><jstl:out value="${personalRecordPhoto}"/>:&nbsp;</b>
	<form:input path="personalRecord.photo"/>
	<form:errors path="personalRecord.photo" cssClass="error"/>
	<br/>
	
	<spring:message var="personalRecordEmail" code="curriculum.personalRecord.email"/>
	<b><form:label path="personalRecord.email"/><jstl:out value="${personalRecordEmail}"/>:&nbsp;</b>
	<form:input path="personalRecord.email"/>
	<form:errors path="personalRecord.email" cssClass="error"/>
	<br/>
	
	<spring:message var="personalRecordPhone" code="curriculum.personalRecord.phone"/>
	<b><form:label path="personalRecord.phone"/><jstl:out value="${personalRecordPhone}"/>:&nbsp;</b>
	<form:input path="personalRecord.phone"/>
	<form:errors path="personalRecord.phone" cssClass="error"/>
	<br/>

	<spring:message var="personalRecordLink" code="curriculum.personalRecord.link"/>
	<b><form:label path="personalRecord.link"/><jstl:out value="${personalRecordLink}"/>:&nbsp;</b>
	<form:input path="personalRecord.link"/>
	<form:errors path="personalRecord.link" cssClass="error"/>
	<br/>

	<spring:message var="personalRecordSave" code="personalRecord.save"/>
	<input type="submit" name="save" value="${personalRecordSave}"/>
	
	<spring:message var="personalRecordCancel" code="personalRecord.cancel"/>
	<input type="button" name="cancel" value="${personalRecordCancel}" onclick="javascript:relativeRedir('${cancelURI}')"/>
</form:form>