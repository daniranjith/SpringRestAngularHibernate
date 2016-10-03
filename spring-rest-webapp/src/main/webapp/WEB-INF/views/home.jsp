<!DOCTYPE html>
<html lang="en-US">
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-resource.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/javascript/app.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
<meta charset="UTF-8" />
<title>Welcome to Spring, REST, AngularJS, Hibernate POC</title>
</head>
<body data-ng-app="myApp">
	<div data-ng-controller="PersonController as personCtrl">
		<h1>Welcome to Spring, REST, AngularJS, Hibernate POC</h1>
		<form name="personForm" method="POST">
			<table>
				<tr>
					<td colspan="2">
						<div data-ng-if="personCtrl.flag != 'edit'">
							<h3>Add New Person</h3>
						</div>
						<div data-ng-if="personCtrl.flag == 'edit'">
							<h3>Update Person for ID: {{ personCtrl.person.pid }}</h3>
						</div>
					</td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name"
						data-ng-model="personCtrl.person.name" required /> <span
						data-ng-show="personForm.name.$error.required" class="msg-val">Name
							is required.</span></td>
				</tr>
				<tr>
					<td>Location:</td>
					<td><input type="text" name="location"
						data-ng-model="personCtrl.person.location" required /> <span
						data-ng-show="personForm.location.$error.required" class="msg-val">Location
							is required.</span></td>
				</tr>
				<tr>
					<td colspan="2" class="center-align">
						<div data-ng-if="personCtrl.flag != 'edit'">
							<input type="submit" class="button normal-bg"
								data-ng-click="personCtrl.addPerson()" value="Add Person" /> <input
								type="button" class="button warning-bg"
								data-ng-click="personCtrl.reset()" value="Reset" />
						</div>
						<div data-ng-if="personCtrl.flag == 'edit'">
							<input type="submit" class="button normal-bg"
								data-ng-click="personCtrl.updatePersonDetail()"
								value="Update Person" /> <input type="button"
								class="button warning-bg"
								data-ng-click="personCtrl.cancelUpdate()" value="Cancel" />
						</div>
					</td>
				</tr>
			</table>
			<br /> <span data-ng-if="personCtrl.flag=='created'"
				class="msg-success">Person successfully added.</span> <span
				data-ng-if="personCtrl.flag=='failed'" class="msg-val">Person
				already exists.</span>
		</form>
		<br /> <br /> <br />
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Location</th>
				<th></th>
			</tr>
			<tr data-ng-repeat="row in personCtrl.persons"
				data-ng-class-odd="'odd'" data-ng-class-even="'even'">
				<td><span data-ng-bind="row.pid"></span></td>
				<td><span data-ng-bind="row.name"></span></td>
				<td><span data-ng-bind="row.location"></span></td>
				<td><input type="button" class="button warning-bg"
					data-ng-click="personCtrl.deletePerson(row.pid)" value="Delete" />
					<input type="button" class="button normal-bg"
					data-ng-click="personCtrl.editPerson(row.pid)" value="Edit" />
					
					</td>
			</tr>
		</table>
		<br /> 
		<span data-ng-if="personCtrl.flag=='updated'" class="msg-success">
			Person successfully updated.
		</span> 
		<span data-ng-if="personCtrl.flag=='deleted'" class="msg-success">
			Person successfully deleted.
		</span>
	</div>
</body>
</html>
