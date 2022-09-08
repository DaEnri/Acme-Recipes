<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.quantity.list.label.amount" path="amount" width="15%"/>	
	<acme:list-column code="chef.quantity.list.label.unit" path="unit" width="15%"/>
	<acme:list-column code="chef.quantity.list.label.kitchen-item-type" path="kitchenItemType" width="15%"/>
	<acme:list-column code="chef.quantity.list.label.kitchen-item-code" path="kitchenItemCode" width="15%"/>
	<acme:list-column code="chef.quantity.list.label.kitchen-item-name" path="kitchenItemName" width="15%"/>
	<acme:list-column code="chef.quantity.list.label.kitchen-item-retail-price" path="kitchenItemRetailPrice" width="15%"/>
</acme:list>

<acme:button code="chef.quantity.list.label.create.button" action="/chef/quantity/create?recipeId=${recipeId}"/>