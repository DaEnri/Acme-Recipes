<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-select code="chef.fineDish.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="chef.fineDish.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="chef.fineDish.form.label.request" path="request" readonly="true"/>	
	<acme:input-money code="chef.fineDish.form.label.budget" path="budget" readonly="true"/>
	<acme:input-moment code="chef.fineDish.form.label.creationDate" path="creationDate" readonly="true"/>
	<acme:input-moment code="chef.fineDish.form.label.startDate" path="startDate" readonly="true"/>
	<acme:input-moment code="chef.fineDish.form.label.finishDate" path="finishDate" readonly="true"/>
	<acme:input-url code="chef.fineDish.form.label.moreInfo" path="moreInfo" readonly="true"/>
	<acme:input-checkbox code="chef.fineDish.form.label.published" path="published" readonly="true"/>
	
	<acme:button code="chef.fineDish.form.button.epicure" action="/any/user-account/show?id=${epicureId}"/>
	
<%--
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update') && published == true && status == 'PROPOSED'}">
			<acme:submit code="chef.fineDish.form.button.update" action="/chef/fine-dish/update"/>
		</jstl:when>		
	</jstl:choose>
--%>	

</acme:form>

