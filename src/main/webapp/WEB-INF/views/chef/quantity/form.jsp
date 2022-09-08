<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="false">
	
	<acme:input-textbox code="chef.quantity.form.label.kitchen-item-code" path="kitchenItemCode"/>
	<acme:input-select code="chef.quantity.form.label.unit" path="unit">
		<acme:input-option code="UNIT" value="UNIT" selected="${status == 'UNIT'}"/>
		<acme:input-option code="GRAM" value="GRAM" selected="${status == 'GRAM'}"/>
		<acme:input-option code="KILO" value="KILO" selected="${status == 'KILO'}"/>
		<acme:input-option code="CM3" value="CM3" selected="${status == 'CM3'}"/>
		<acme:input-option code="GALLON" value="GALLON" selected="${status == 'GALLON'}"/>
		<acme:input-option code="SPOON" value="SPOON" selected="${status == 'SPOON'}"/>
	</acme:input-select>
	<acme:input-url code="chef.quantity.form.label.amount" path="amount"/>
	<acme:hidden-data path="recipeId"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:submit code="chef.quantity.form.button.update" action="/chef/quantity/update"/>
			<acme:submit code="chef.quantity.form.button.delete" action="/chef/quantity/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.quantity.form.button.create" action="/chef/quantity/create"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>