<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>	
	<acme:list-column code="any.kitchenItem.list.label.type" path="type" width="10%"/>
	<acme:list-column code="any.kitchenItem.list.label.name" path="name" width="10%"/>
	<acme:list-column code="any.kitchenItem.list.label.code" path="code" width="10%"/>
	<acme:list-column code="any.kitchenItem.list.label.description" path="description" width="10%"/>
	<acme:list-column code="any.kitchenItem.list.label.retailPrice" path="retailPrice" width="10%"/>
	<acme:list-column code="any.kitchenItem.list.label.defaultRetailPrice" path="defaultRetailPrice" width="10%"/>
	<acme:list-column code="any.kitchenItem.list.label.optionalLink" path="optionalLink" width="10%"/>
	<acme:list-column code="any.kitchenItem.list.label.published" path="published" width="10%"/>
</acme:list>
