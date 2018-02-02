<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="educationRecord/ranger/edit.do" modelAttribute="educationRecord">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<spring:message var="educationRecordTitle" code="educationRecord.title"/>
	<b><form:label path="title"/>${educationRecordTitle}:&nbsp;</b>
	<form:input path="title"/>
	<form:errors path="title" cssClass="error"/>
	<br/>
	
	<spring:message var="educationRecordStartDate" code="educationRecord.startDate"/>
	<b><form:label path="startDate"/>${educationRecordStartDate}:&nbsp;</b>
	<form:input path="startDate"/>
	<form:errors path="startDate" cssClass="error"/>
	<br/>
	
	<spring:message var="educationRecordEndDate" code="educationRecord.endDate"/>
	<b><form:label path="endDate"/>${educationRecordEndDate}:&nbsp;</b>
	<form:input path="endDate"/>
	<form:errors path="endDate" cssClass="error"/>
	<br/>
	
	<spring:message var="educationRecordInstitution" code="educationRecord.institution"/>
	<b><form:label path="institution"/>${educationRecordInstitution}:&nbsp;</b>
	<form:input path="institution"/>
	<form:errors path="institution" cssClass="error"/>
	<br/>
	
	<spring:message var="educationRecordAttachment" code="educationRecord.attachment"/>
	<b><form:label path="attachment"/>${educationRecordAttachment}:&nbsp;</b>
	<form:input path="attachment"/>
	<form:errors path="attachment" cssClass="error"/>
	<br/>
	
	<spring:message var="educationRecordComments" code="educationRecord.comments"/>
	<b><form:label path="comments"/>${educationRecordComments}:&nbsp;</b>
	<form:input path="comments"/>
	<form:errors path="comments" cssClass="error"/>
	<br/>

	<spring:message var="educationRecordSave" code="educationRecord.save"/>
	<input type="submit" name="save" value="${educationRecordSave}"/>
	
	<jstl:if test="${educationRecord.id != 0}"> 
		<spring:message var="educationRecordDelete" code="educationRecord.delete"/>
		<input type="submit" name="delete" value="${educationRecordDelete}"/>
	</jstl:if>
	<spring:message var="educationRecordCancel" code="educationRecord.cancel"/>
	<input type="button" name="cancel" value="${educationRecordCancel}" onclick="javascript:relativeRedir('${cancelURI}')"/>
</form:form>