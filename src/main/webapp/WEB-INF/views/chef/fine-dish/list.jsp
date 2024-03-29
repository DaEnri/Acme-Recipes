<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>	
	<acme:list-column code="chef.fineDish.list.label.status" path="status" width="10%"/>
	<acme:list-column code="chef.fineDish.list.label.code" path="code" width="10%"/>
	<acme:list-column code="chef.fineDish.list.label.request" path="request" width="10%"/>
	<acme:list-column code="chef.fineDish.list.label.budget" path="budget" width="10%"/>
	<acme:list-column code="chef.fineDish.list.label.creationDate" path="creationDate" width="10%"/>
	<acme:list-column code="chef.fineDish.list.label.startDate" path="startDate" width="10%"/>
	<acme:list-column code="chef.fineDish.list.label.finishDate" path="finishDate" width="10%"/>
	<acme:list-column code="chef.fineDish.list.label.moreInfo" path="moreInfo" width="10%"/>
	<acme:list-column code="chef.fineDish.list.label.published" path="published" width="10%"/>
</acme:list>
