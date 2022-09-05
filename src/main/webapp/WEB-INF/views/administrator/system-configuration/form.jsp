<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<acme:form readonly="false">
	<acme:input-textbox code="administrator.system-configuration.form.label.default-system-currency" path="defaultSystemCurrency"/>
	<acme:input-textbox code="administrator.system-configuration.form.label.accepted-currencies" path="acceptedCurrencies"/>
	<acme:input-textbox code="administrator.system-configuration.form.label.spam-terms" path="spamTerms"/>
	<acme:input-textbox code="administrator.system-configuration.form.label.spam-threshold" path="spamThreshold"/>
	
	<acme:submit code="administrator.system-configuration.submit.label.update" action="/administrator/system-configuration/update"/>
	
	<acme:link code="authenticated.system-configuration.form.label.money-exchange" action="/authenticated/money-exchange/perform"/>
</acme:form>