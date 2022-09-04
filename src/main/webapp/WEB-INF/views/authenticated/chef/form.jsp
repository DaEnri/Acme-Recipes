<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h1>
	<acme:message code="authenticated.chef.form.title"/>
</h1>

<acme:form>
	<acme:input-textbox code="authenticated.chef.form.label.organisation" path="organisation"/>
	<acme:input-textbox code="authenticated.chef.form.label.assertion" path="assertion"/>
	<acme:input-textbox code="authenticated.chef.form.label.info" path="info"/>
	
	<acme:submit test="${command == 'create'}" code="authenticated.chef.form.button.create" action="/authenticated/chef/create"/>
	<acme:submit test="${command == 'update'}" code="authenticated.chef.form.button.update" action="/authenticated/chef/update"/>
</acme:form>