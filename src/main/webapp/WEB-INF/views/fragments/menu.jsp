<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-danenrdia" action="https://www.twitch.tv"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-andvarbay" action="https://www.youtube.com/"/>
			<acme:menu-separator/>
      		<acme:menu-suboption code="master.menu.any.ingredients" action="/any/kitchen-item/list-ingredient"/>
			<acme:menu-suboption code="master.menu.any.kitchenUtensils" action="/any/kitchen-item/list-utensil"/>
			<acme:menu-suboption code="master.menu.any.peeps" action="/any/peep/list"/>
			<acme:menu-suboption code="master.menu.any.user-accounts" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.any.recipes" action="/any/recipe/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.any.ingredients" action="/any/kitchen-item/list-ingredient"/>
			<acme:menu-suboption code="master.menu.any.kitchenUtensils" action="/any/kitchen-item/list-utensil"/>
			<acme:menu-suboption code="master.menu.any.peeps" action="/any/peep/list"/>
			<acme:menu-suboption code="master.menu.any.user-accounts" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.any.recipes" action="/any/recipe/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.bulletin" action="/authenticated/bulletin/list"/>
			<acme:menu-suboption code="master.menu.authenticated.system-configuration" action="/authenticated/system-configuration/show"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.chef" access="hasRole('Chef')">
			<acme:menu-suboption code="master.menu.chef.ingredients" action="/chef/kitchen-item/list-ingredient"/>
			<acme:menu-suboption code="master.menu.chef.kitchenUtensils" action="/chef/kitchen-item/list-utensil"/>
			<acme:menu-suboption code="master.menu.chef.fineDishes" action="/chef/fine-dish/list"/>
			<acme:menu-suboption code="master.menu.chef.memorandum" action="/chef/memorandum/list"/>
			<acme:menu-suboption code="master.menu.chef.recipes" action="/chef/recipe/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.epicure" access="hasRole('Epicure')">
			<acme:menu-suboption code="master.menu.epicure.fineDishes" action="/epicure/fine-dish/list"/>
			<acme:menu-suboption code="master.menu.epicure.memorandum" action="/epicure/memorandum/list"/>
			<acme:menu-suboption code="master.menu.epicure.epicure-dashboard" action="/epicure/epicure-dashboard/show"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration" action="/administrator/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.administrator.administrator-dashboard" action="/administrator/admin-dashboard/show"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.user-account.become-chef" action="/authenticated/chef/create" access="!hasRole('Chef')"/>
			<acme:menu-suboption code="master.menu.user-account.chef" action="/authenticated/chef/update" access="hasRole('Chef')"/>
			<acme:menu-suboption code="master.menu.user-account.become-epicure" action="/authenticated/epicure/create" access="!hasRole('Epicure')"/>
			<acme:menu-suboption code="master.menu.user-account.epicure" action="/authenticated/epicure/update" access="hasRole('Epicure')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

