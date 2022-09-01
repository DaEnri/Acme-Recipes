<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h1>
	<acme:message code="epicure.epicure-dashboard.form.title"/>
</h1>

<h3>
	<acme:message code="epicure.epicure-dashboard.form.count-fineDishes"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="epicure.epicure-dashboard.form.count-fineDishes-caption"/>
	</caption>
	<jstl:forEach var="countFineDish" items="${ totalNumberFineDishesOfStatus }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${countFineDish.key}"/>
			</th>
			<td>
				<acme:print value="${countFineDish.value}"/>
			</td>
		</tr>
	</jstl:forEach>

</table>

<h3>
	<acme:message code="epicure.epicure-dashboard.form.average-fineDishes"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="epicure.epicure-dashboard.form.average-fineDishes-caption"/>
	</caption>
	<jstl:forEach var="averageFineDishes" items="${ averageBudgetFineDishesOfStatusByCurrency }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${averageFineDishes.key.first} | ${averageFineDishes.key.second}"/>
			</th>
			<td>
				<acme:print value="${averageFineDishes.value}"/>
			</td>
		</tr>
	</jstl:forEach>
	
</table>

<h3>
	<acme:message code="epicure.epicure-dashboard.form.deviation-fineDishes"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="epicure.epicure-dashboard.form.deviation-fineDishes-caption"/>
	</caption>
	<jstl:forEach var="deviationFineDishes" items="${ deviationBudgetFineDishesOfStatusByCurrency }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${deviationFineDishes.key.first} | ${deviationFineDishes.key.second}"/>
			</th>
			<td>
				<acme:print value="${deviationFineDishes.value}"/>
			</td>
		</tr>
	</jstl:forEach>
	
</table>

<h3>
	<acme:message code="epicure.epicure-dashboard.form.minimum-fineDishes"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="epicure.epicure-dashboard.form.minimum-fineDishes-caption"/>
	</caption>
	<jstl:forEach var="minimumFineDishes" items="${ minimumBudgetFineDishesOfStatusByCurrency }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${minimumFineDishes.key.first} | ${minimumFineDishes.key.second}"/>
			</th>
			<td>
				<acme:print value="${minimumFineDishes.value}"/>
			</td>
		</tr>
	</jstl:forEach>
	
</table>

<h3>
	<acme:message code="epicure.epicure-dashboard.form.maximum-fineDishes"/>
</h3>

<table class="table table-sm">
	<caption>
		<acme:message code="epicure.epicure-dashboard.form.maximum-fineDishes-caption"/>
	</caption>
	<jstl:forEach var="maximumFineDishes" items="${ maximumBudgetFineDishesOfStatusByCurrency }">
		<tr>
			<th scope="row">
				<acme:print value=" - ${maximumFineDishes.key.first} | ${maximumFineDishes.key.second}"/>
			</th>
			<td>
				<acme:print value="${maximumFineDishes.value}"/>
			</td>
		</tr>
	</jstl:forEach>
	
</table>

<acme:return/>

