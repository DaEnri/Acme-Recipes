<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.peep.form.label.heading" path="heading"/>
	<acme:input-textarea code="any.peep.form.label.text" path="text"/>
	<acme:input-moment code="any.peep.form.label.instantiationMoment" path="instantiationMoment" readonly="true"/>
	<acme:input-textbox code="any.peep.form.label.writer" path="writer"/>
	<acme:input-textbox code="any.peep.form.label.optionalEmail" path="optionalEmail"/>
	<acme:input-checkbox code="any.peep.form.label.confirmation" path="confirmation"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="any.peep.form.button.create" action="/any/peep/create"/>
		</jstl:when>		
	</jstl:choose>		
</acme:form>