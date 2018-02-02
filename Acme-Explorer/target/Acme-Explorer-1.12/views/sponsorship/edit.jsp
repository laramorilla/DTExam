<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="sponsorship/sponsor/edit.do" modelAttribute="sponsorship">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="sponsor"/>
	<form:hidden path="trip"/>
	
	<form:label path="banner"><spring:message code="sponsorship.banner"/></form:label>
	<form:input path="banner"/>
	<form:errors path="banner" cssClass="error"/>
	<br/>
	
	<form:label path="information"><spring:message code="sponsorship.information"/></form:label>
	<form:input path="information"/>
	<form:errors path="information" cssClass="error"/>
	<br/>
	
	<form:label path="creditCard.holder"><spring:message code="creditCard.holder"/></form:label>
	<form:input path="creditCard.holder"/>
	<form:errors path="creditCard.holder" cssClass="error"/>
	<br/>
	
	<form:label path="creditCard.brand"><spring:message code="creditCard.brand"/></form:label>
	<form:input path="creditCard.brand"/>
	<form:errors path="creditCard.brand" cssClass="error"/>
	<br/>
	
	<form:label path="creditCard.number"><spring:message code="creditCard.number"/></form:label>
	<form:input path="creditCard.number"/>
	<form:errors path="creditCard.number" cssClass="error"/>
	<br/>
	
	<form:label path="creditCard.expirationMonth"><spring:message code="creditCard.expirationMonth"/></form:label>
	<form:input path="creditCard.expirationMonth"/>
	<form:errors path="creditCard.expirationMonth" cssClass="error"/>
	<br/>
	
	<form:label path="creditCard.expirationYear"><spring:message code="creditCard.expirationYear"/></form:label>
	<form:input path="creditCard.expirationYear"/>
	<form:errors path="creditCard.expirationYear" cssClass="error"/>
	<br/>
	
	<form:label path="creditCard.cvv"><spring:message code="creditCard.cvv"/></form:label>
	<form:input path="creditCard.cvv"/>
	<form:errors path="creditCard.cvv" cssClass="error"/>
	<br/>

	<input type="submit" name="save" value="<spring:message code="sponsorship.save"/>" />
	
	<jstl:if test="${sponsorship.id != 0}">
		<input type="submit" name="delete" value="<spring:message code="sponsorship.delete"/>"/>
	</jstl:if>
	<input type="button" name="cancel" value="<spring:message code="sponsorship.cancel" />" 
			onclick="javascript: relativeRedir('sponsorship/sponsor/list.do');" />
	
</form:form>