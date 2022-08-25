<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-select code="any.kitchenItem.form.label.type" path="type" readonly="">
		<acme:input-option code="INGREDIENT" value="INGREDIENT" selected="${type == 'INGREDIENT'}"/>
		<acme:input-option code="KITCHEN_UTENSIL" value="KITCHEN_UTENSIL" selected="${type == 'KITCHEN_UTENSIL'}"/>
	</acme:input-select>
	<acme:input-textbox code="any.kitchenItem.form.label.name" path="name" readonly="true"/>
	<acme:input-textbox code="any.kitchenItem.form.label.code" path="code" readonly="true"/>	
	<acme:input-textbox code="any.kitchenItem.form.label.description" path="description" readonly="true"/>
	<acme:input-money code="any.kitchenItem.form.label.retailPrice" path="retailPrice" readonly="true"/>
	<acme:input-textbox code="any.kitchenItem.form.label.optionalLink" path="optionalLink" readonly="true"/>
	<acme:input-textbox code="any.kitchenItem.form.label.published" path="published" readonly="true"/>
	
</acme:form>
