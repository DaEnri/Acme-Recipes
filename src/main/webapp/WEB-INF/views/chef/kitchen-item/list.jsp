<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>	
	<acme:list-column code="chef.kitchenItem.list.label.name" path="name" width="20%"/>
	<acme:list-column code="chef.kitchenItem.list.label.code" path="code" width="20%"/>
	<acme:list-column code="chef.kitchenItem.list.label.description" path="description" width="20%"/>
	<acme:list-column code="chef.kitchenItem.list.label.retailPrice" path="retailPrice" width="20%"/>
	<acme:list-column code="chef.kitchenItem.list.label.optionalLink" path="optionalLink" width="20%"/>
	<acme:list-column code="chef.kitchenItem.list.label.published" path="published" width="20%"/>
</acme:list>
<%-- <acme:button code="chef.kitchenItem.list.button.create" action="/chef/kitchen-item/create"/> --%>
