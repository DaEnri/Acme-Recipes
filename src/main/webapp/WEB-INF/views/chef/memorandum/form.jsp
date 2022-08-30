<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readOnly}">
	
	<acme:input-moment code="chef.memorandum.form.label.instantiationMoment" path="instantiationMoment" readonly="true"/>
	<acme:input-textbox code="chef.memorandum.form.label.report" path="report"/>
	<acme:input-url code="chef.memorandum.form.label.optionalLink" path="optionalLink"/>
	<jstl:choose>
<%--		<jstl:when test="${command == 'create'}">
			<acme:input-select code="chef.memorandum.form.label.fineDish" path="fineDish">
				<jstl:forEach items="${ fineDishes }" var="fineDishSelected">
					<acme:input-option code="${fineDishSelected.code}" value="${fineDishSelected.code}"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:input-checkbox code="chef.memorandum.form.label.confirmation" path="confirmation"/>
			<acme:submit code="chef.memorandum.form.button.create" action="/chef/memorandum/create"/>
		</jstl:when> --%>
		<jstl:when test="${command == 'show'}">
			<acme:input-textbox code="chef.memorandum.form.label.serialCode" path="serialCode"/>
			<acme:input-textbox code="chef.memorandum.form.label.fineDishCode" path="fineDishCode"/>
			<acme:input-textbox code="chef.memorandum.form.label.fineDishRequest" path="fineDishRequest"/>		
		</jstl:when>			
	</jstl:choose>	

</acme:form>

