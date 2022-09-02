<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="chef.recipe.form.label.code" path="code"/>
	<acme:input-textbox code="chef.recipe.form.label.heading" path="heading"/>
	<acme:input-textbox code="chef.recipe.form.label.description" path="description"/>
	<acme:input-textbox code="chef.recipe.form.label.preparation-notes" path="preparationNotes"/>
	<acme:input-money code="chef.recipe.form.label.total-money" path="totalMoneyRecipe"/>
	<acme:input-textbox code="chef.recipe.form.label.optional-link" path="optionalLink"/>
	<acme:input-checkbox code="chef.recipe.form.label.published" path="published"/>
	
	<acme:button code="chef.recipe.form.label.recipe-items" action="/chef/kitchen-item/list-recipe-filter?recipeId=${id}"/>
	
</acme:form>
