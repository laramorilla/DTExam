<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

	
<display:table name="auditRecords" id="row" requestURI="${requestURI}"
	pagesize="4" class="displaytag">
			  
		<security:authorize access="hasRole('AUDITOR')">
			<jstl:if test="${row.finalVersion == false }">  
				<display:column>
				<a href="auditRecord/auditor/edit.do?auditRecordId=${row.id}"><spring:message code="auditRecord.edit"/></a>
				</display:column>
			</jstl:if>	
		</security:authorize>
		
		<security:authorize access="hasRole('AUDITOR')">		
		<display:column>
				<a href="auditRecord/auditor/display.do?auditRecordId=${row.id}"><spring:message code="auditRecord.display"/></a>
		</display:column>
		</security:authorize>
		
		
		<security:authorize access="!hasRole('AUDITOR')">		
		<display:column>
				<a href="auditRecord/display.do?auditRecordId=${row.id}"><spring:message code="auditRecord.display"/></a>
		</display:column>
		</security:authorize>
		
		<spring:message code="auditRecord.title" var="titleHeader"/>
		<display:column property="title" title="${titleHeader}" sortable="true"/>
		
		<spring:message code="auditRecord.moment" var="momentHeader"/>
		<display:column property="moment" title="${momentHeader}" sortable="true"/>
	
		<spring:message code="auditRecord.trip" var="tripHeader"/>
		<display:column property="trip.ticker" title="${tripHeader}" />

		<spring:message code="auditRecord.description" var="descriptionHeader"/>
		<display:column property="description" title="${descriptionHeader}" />
		
		<spring:message code="auditRecord.attachment" var="attachmentHeader"/>
		<display:column property="attachments" title="${attachmentHeader}" />		
	
</display:table>
		<security:authorize access="hasRole('AUDITOR')">
			<a href="auditRecord/auditor/create.do"><spring:message code="auditRecord.create"></spring:message></a>
		</security:authorize>
