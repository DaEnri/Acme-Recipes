<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags" %>

<acme:form readonly="true">
	<acme:input-textbox code="authenticated.bulletin.form.label.title" path="heading"/>
	<acme:input-moment code="authenticated.bulletin.form.label.moment" path="instantiationMoment"/>
	<acme:input-select code="authenticated.bulletin.form.label.critical" path="critical">
		<acme:input-option code="CRITICAL" value="CRITICAL" selected="${critical}"/>
		<acme:input-option code="NOT CRITICAL" value="NOT CRITICAL" selected="${!critical}"/>
	</acme:input-select>
	<acme:input-textarea code="authenticated.bulletin.form.label.text" path="text"/>
	<acme:input-url code="authenticated.bulletin.form.label.url" path="optionalLink"/>
</acme:form>