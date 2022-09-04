<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="false">
	<acme:input-textbox code="chef.recipe.form.label.code" path="code"/>
	<acme:input-textbox code="chef.recipe.form.label.heading" path="heading"/>
	<acme:input-textbox code="chef.recipe.form.label.description" path="description"/>
	<acme:input-textbox code="chef.recipe.form.label.preparation-notes" path="preparationNotes"/>
	<acme:input-money code="chef.recipe.form.label.total-money" path="totalMoneyRecipe" readonly="true"/>
	<acme:input-textbox code="chef.recipe.form.label.optional-link" path="optionalLink"/>
	<acme:input-checkbox code="chef.recipe.form.label.published" path="published"/>
	
	<acme:button code="chef.recipe.form.label.recipe-items" action="/chef/kitchen-item/list-recipe-filter?recipeId=${id}"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && !published}">
			<acme:button code="chef.recipe.form.button.edit-items" action="/chef/quantity/list?toolKitId=${id}"/>
			<acme:submit code="chef.recipe.form.button.update" action="/chef/recipe/update"/>
			<acme:submit code="chef.recipe.form.button.delete" action="/chef/recipe/delete"/>
			<acme:submit code="chef.recipe.form.button.publish" action="/chef/recipe/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.recipe.form.button.create" action="/chef/recipe/create"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>
