<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">	
	<acme:list-column code="any.peep.list.label.instantiationMoment" path="instantiationMoment" width="10%"/>
	<acme:list-column code="any.peep.list.label.heading" path="heading" width="10%"/>
	<acme:list-column code="any.peep.list.label.writer" path="writer" width="10%"/>
	<acme:list-column code="any.peep.list.label.text" path="text" width="10%"/>
	<acme:list-column code="any.peep.list.label.optionalEmail" path="optionalEmail" width="10%"/>
</acme:list>

<acme:button code="any.peep.list.button.create" action="/any/peep/create"/>