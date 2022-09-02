<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<acme:form>
	<acme:input-textbox code="administrator.system-configuration.form.label.default-system-currency" path="defaultSystemCurrency" readonly="${readOnly}"/>
	<acme:input-textbox code="administrator.system-configuration.form.label.accepted-currencies" path="acceptedCurrencies" readonly="${readOnly}"/>
	<acme:input-textbox code="administrator.system-configuration.form.label.spam-terms" path="spamTerms" readonly="${readOnly}"/>
	<acme:input-textbox code="administrator.system-configuration.form.label.spam-threshold" path="spamThreshold" readonly="${readOnly}"/>
	
	<acme:link code="authenticated.system-configuration.form.label.money-exchange" action="/authenticated/money-exchange/perform"/>
</acme:form>