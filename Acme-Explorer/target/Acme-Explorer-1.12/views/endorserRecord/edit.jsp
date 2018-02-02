<%-- edit.jsp de EndorserRecord --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="endorserRecord/ranger/edit.do" modelAttribute="endorserRecord">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<spring:message var="endorserRecordFullName" code="endorserRecord.fullName"/>
	<b><form:label path="fullName"/>${endorserRecordFullName}:&nbsp;</b>
	<form:input path="fullName"/>
	<form:errors path="fullName" cssClass="error"/>
	<br/>
	
	<spring:message var="endorserRecordEmail" code="endorserRecord.email"/>
	<b><form:label path="email"/>${endorserRecordEmail}:&nbsp;</b>
	<form:input path="email"/>
	<form:errors path="email" cssClass="error"/>
	<br/>
	
	<spring:message var="endorserRecordPhone" code="endorserRecord.phone"/>
	<b><form:label path="phone"/>${endorserRecordPhone}:&nbsp;</b>
	<form:input path="phone" placeholder="+CC (AC) PN"/>
	<form:errors path="phone" cssClass="error"/>
	<br/>
	
	<spring:message var="endorserRecordLink" code="endorserRecord.link"/>
	<b><form:label path="link"/>${endorserRecordLink}:&nbsp;</b>
	<form:input path="link"/>
	<form:errors path="link" cssClass="error"/>
	<br/>
	
	<spring:message var="endorserRecordComments" code="endorserRecord.comments"/>
	<b><form:label path="comments"/>${endorserRecordComments}:&nbsp;</b>
	<form:input path="comments"/>
	<form:errors path="comments" cssClass="error"/>
	<br/>

	<spring:message var="endorserRecordSave" code="endorserRecord.save"/>
	<input type="submit" name="save" value="${endorserRecordSave}"/>
	
	<jstl:if test="${endorserRecord.id != 0}"> 
		<spring:message var="endorserRecordDelete" code="endorserRecord.delete"/>
		<input type="submit" name="delete" value="${endorserRecordDelete}"/>
	</jstl:if>
	
	<spring:message var="endorserRecordCancel" code="endorserRecord.cancel"/>
	<input type="button" name="cancel" value="${endorserRecordCancel}" onclick="javascript:relativeRedir('${cancelURI}')"/>
</form:form>