<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.user-account.form.label.username" path="username" readonly="true"/>
	<acme:input-textbox code="any.user-account.form.label.name" path="identity.name" readonly="true"/>
	<acme:input-textbox code="any.user-account.form.label.surname" path="identity.surname" readonly="true"/>
	<acme:input-textbox code="any.user-account.form.label.rol" path="roleList" readonly="true"/>
</acme:form>