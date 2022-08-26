<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readOnly}">
	<acme:input-select code="chef.kitchenItem.form.label.type" path="type">
		<acme:input-option code="INGREDIENT" value="INGREDIENT" selected="${type == 'INGREDIENT'}"/>
		<acme:input-option code="KITCHEN_UTENSIL" value="KITCHEN_UTENSIL" selected="${type == 'KITCHEN_UTENSIL'}"/>
	</acme:input-select>
	<acme:input-textbox code="chef.kitchenItem.form.label.name" path="name"/>
	<acme:input-textbox code="chef.kitchenItem.form.label.code" path="code"/>	
	<acme:input-textarea code="chef.kitchenItem.form.label.description" path="description"/>
	<acme:input-money code="chef.kitchenItem.form.label.retailPrice" path="retailPrice"/>
	<acme:input-url code="chef.kitchenItem.form.label.optionalLink" path="optionalLink"/>
	<acme:input-checkbox code="chef.kitchenItem.form.label.published" path="published" readonly="true"/>
	
<%--	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="chef.kitchenItem.form.button.update" action="/chef/kitchen-item/update"/>
			<acme:submit code="chef.kitchenItem.form.button.delete" action="/chef/kitchen-item/delete"/>
			<acme:submit code="chef.kitchenItem.form.button.publish" action="/chef/kitchen-item/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.kitchenItem.form.button.create" action="/chef/kitchen-item/create"/>
		</jstl:when>		
	</jstl:choose>
--%>
	
</acme:form>
