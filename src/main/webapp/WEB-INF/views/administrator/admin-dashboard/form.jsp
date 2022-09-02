<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h1>
	<acme:message code="administrator.admin-dashboard.form.title"/>
</h1>

<h2>
	<acme:message code="administrator.admin-dashboard.form.ingredients"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.count-ingredients-caption"/>
	</caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.admin-dashboard.form.count-ingredients"/>
		</th>
		<td>
			<acme:print value="${totalNumberIngredients}"/>
		</td>
	</tr>
</table>

<h3>
	<acme:message code="administrator.admin-dashboard.form.average-ingredients"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.average-ingredients-caption"/>
	</caption>
	<jstl:forEach var="averageIngredients" items="${ averageRetailPriceIngredientsByCurrency }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${averageIngredients.key}"/>
			</th>
			<td>
				<acme:print value="${averageIngredients.value}"/>
			</td>
		</tr>
	</jstl:forEach>

</table>

<h3>
	<acme:message code="administrator.admin-dashboard.form.deviation-ingredients"/>
</h3>

<table class="table table-sm">
<caption>
		<acme:message code="administrator.admin-dashboard.form.deviation-ingredients-caption"/>
	</caption>
	<jstl:forEach var="deviationIngredients" items="${ deviationRetailPriceIngredientsByCurrency }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${deviationIngredients.key}"/>
			</th>
			<td>
				<acme:print value="${deviationIngredients.value}"/>
			</td>
		</tr>
	</jstl:forEach>
	
</table>

<h3>
	<acme:message code="administrator.admin-dashboard.form.minimum-ingredients"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.minimum-ingredients-caption"/>
	</caption>
	<jstl:forEach var="minimumIngredients" items="${ minimumRetailPriceIngredientsByCurrency }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${minimumIngredients.key}"/>
			</th>
			<td>
				<acme:print value="${minimumIngredients.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="administrator.admin-dashboard.form.maximum-ingredients"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.maximum-ingredients-caption"/>
	</caption>
	<jstl:forEach var="maximumIngredients" items="${ maximumRetailPriceIngredientsByCurrency }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${maximumIngredients.key}"/>
			</th>
			<td>
				<acme:print value="${maximumIngredients.value}"/>
			</td>
		</tr>
	</jstl:forEach>
	
</table>

<h2>
	<acme:message code="administrator.admin-dashboard.form.kitchen-utensils"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.count-kitchen-utensils-caption"/>
	</caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.admin-dashboard.form.count-kitchen-utensils"/>
		</th>
		<td>
			<acme:print value="${totalNumberKitchenUtensils}"/>
		</td>
	</tr>
</table>

<h3>
	<acme:message code="administrator.admin-dashboard.form.average-kitchen-utensils"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.average-kitchen-utensils-caption"/>
	</caption>
	<jstl:forEach var="averageKitchenUtensils" items="${ averageRetailPriceKitchenUtensilsByCurrency }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${averageKitchenUtensils.key}"/>
			</th>
			<td>
				<acme:print value="${averageKitchenUtensils.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="administrator.admin-dashboard.form.deviation-kitchen-utensils"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.deviation-kitchen-utensils-caption"/>
	</caption>
	<jstl:forEach var="deviationKitchenUtensils" items="${ deviationRetailPriceKitchenUtensilsByCurrency }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${deviationKitchenUtensils.key}"/>
			</th>
			<td>
				<acme:print value="${deviationKitchenUtensils.value}"/>
			</td>
		</tr>
	</jstl:forEach>
	
</table>

<h3>
	<acme:message code="administrator.admin-dashboard.form.minimum-kitchen-utensils"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.minimum-kitchen-utensils-caption"/>
	</caption>
	<jstl:forEach var="minimumKitchenUtensils" items="${ minimumRetailPriceKitchenUtensilsByCurrency }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${minimumKitchenUtensils.key}"/>
			</th>
			<td>
				<acme:print value="${minimumKitchenUtensils.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="administrator.admin-dashboard.form.maximum-kitchen-utensils"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.maximum-kitchen-utensils-caption"/>
	</caption>
	<jstl:forEach var="maximumKitchenUtensils" items="${ maximumRetailPriceKitchenUtensilsByCurrency }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${maximumKitchenUtensils.key}"/>
			</th>
			<td>
				<acme:print value="${maximumKitchenUtensils.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h2>
	<acme:message code="administrator.admin-dashboard.form.fine-dishes"/>
</h2>

<h3>
	<acme:message code="administrator.admin-dashboard.form.count-fine-dishes"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.count-fine-dishes-caption"/>
	</caption>
	<jstl:forEach var="countFineDishes" items="${ totalNumberFineDishesOfStatus }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${countFineDishes.key}"/>
			</th>
			<td>
				<acme:print value="${countFineDishes.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="administrator.admin-dashboard.form.average-fine-dishes"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.average-fine-dishes-caption"/>
	</caption>
	<jstl:forEach var="averageFineDishes" items="${ averageBudgetFineDishesOfStatus }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${averageFineDishes.key}"/>
			</th>
			<td>
				<acme:print value="${averageFineDishes.value}"/>
			</td>
		</tr>
	</jstl:forEach>
	
</table>

<h3>
	<acme:message code="administrator.admin-dashboard.form.deviation-fine-dishes"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.deviation-fine-dishes-caption"/>
	</caption>
	<jstl:forEach var="deviationFineDishes" items="${ deviationBudgetFineDishesOfStatus }">
		<tr>	
			<th scope="row">
				<acme:print value=" - ${deviationFineDishes.key}"/>
			</th>
			<td>
				<acme:print value="${deviationFineDishes.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="administrator.admin-dashboard.form.minimum-fine-dishes"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.minimum-fine-dishes-caption"/>
	</caption>
	<jstl:forEach var="minimumFineDishes" items="${ minimumBudgetFineDishesOfStatus }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${minimumFineDishes.key}"/>
			</th>
			<td>
				<acme:print value="${minimumFineDishes.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="administrator.admin-dashboard.form.maximum-fine-dishes"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.admin-dashboard.form.maximum-fine-dishes-caption"/>
	</caption>
	<jstl:forEach var="maximumFineDishes" items="${ maximumBudgetFineDishesOfStatus }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${maximumFineDishes.key}"/>
			</th>
			<td>
				<acme:print value="${maximumFineDishes.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<acme:return/>

