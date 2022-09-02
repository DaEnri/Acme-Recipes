<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>	
	<acme:list-column code="chef.memorandum.list.label.serialCode" path="serialCode" width="25%"/>
	<acme:list-column code="chef.memorandum.list.label.instantiationMoment" path="instantiationMoment" width="25%"/>
	<acme:list-column code="chef.memorandum.list.label.report" path="report" width="25%"/>
	<acme:list-column code="chef.memorandum.list.label.optionalLink" path="optionalLink" width="25%"/>
</acme:list>

<acme:button code="chef.memorandum.list.button.create" action="/chef/memorandum/create"/>