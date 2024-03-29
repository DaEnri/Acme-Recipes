<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="false">
	<acme:input-select code="epicure.fineDish.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="epicure.fineDish.form.label.code" path="code"/>
	<acme:input-textbox code="epicure.fineDish.form.label.request" path="request"/>	
	<acme:input-money code="epicure.fineDish.form.label.budget" path="budget"/>
	<acme:input-money code="epicure.fineDish.form.label.budget-default-currency" path="budgetDefaultCurrency" readonly="true"/>
	<acme:input-moment code="epicure.fineDish.form.label.creationDate" path="creationDate" readonly="true"/>
	<acme:input-moment code="epicure.fineDish.form.label.startDate" path="startDate"/>
	<acme:input-moment code="epicure.fineDish.form.label.finishDate" path="finishDate"/>
	<acme:input-url code="epicure.fineDish.form.label.moreInfo" path="moreInfo"/>
	<acme:input-checkbox code="epicure.fineDish.form.label.published" path="published" readonly="true"/>
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show') && published == true}">
			<acme:input-textbox code="epicure.fineDish.form.label.chefName" path="chefName" readonly="true"/>	
			<acme:button code="epicure.fineDish.form.button.chef" action="/any/user-account/show?id=${chefId}"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:input-textbox code="epicure.fineDish.form.label.chefName" path="chefName" readonly="true"/>	
			<acme:button code="epicure.fineDish.form.button.chef" action="/any/user-account/show?id=${chefId}"/>
			<acme:submit code="epicure.fineDish.form.button.update" action="/epicure/fine-dish/update"/>
			<acme:submit code="epicure.fineDish.form.button.delete" action="/epicure/fine-dish/delete"/>
			<acme:submit code="epicure.fineDish.form.button.publish" action="/epicure/fine-dish/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:input-select code="epicure.fineDish.form.label.chef" path="chef">
				<jstl:forEach items="${ chefs }" var="chefSelected">
					<acme:input-option code="${ chefSelected.userAccount.username }" value="${ chefSelected.userAccount.username }"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code="epicure.fineDish.form.button.create" action="/epicure/fine-dish/create"/>
		</jstl:when>		
	</jstl:choose>
	
</acme:form>

