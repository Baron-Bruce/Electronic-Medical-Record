/**
 creates a new angular module, and new module is tied to variable 'custApp'.
*/
var custApp = angular.module("customerApp",[]);

/**  
 controller is a JavaScript object created by a standard JavaScript object constructor.
 creates the controller name 'customerCtrl' and passes in the $scope object.
 you can add properties to the $scope object, the view can access them.
*/
custApp.controller('customerCtrl', function($scope, $http) {	
	
	$scope.companyName = 'RDP Laboratories';	
	$scope.hideCustomerSearch = true;
	$scope.hideCustomerEditDelete = true;
	$scope.hideCustomerAddPage = true;
	$scope.resource = '/resttest/webapi/customers/';
	
	$scope.hideAll = function() {
		$scope.hideCustomerSearch = true;
		$scope.hideCustomerInfo = true;	
		$scope.hideCustomerSearchResults = true;
		$scope.hideCustomerEditDelete = true;
		$scope.hideCustomerAddPage = true;
	}
	
	$scope.customerSearch = function() {
		$scope.hideAll();
		$scope.hideCustomerSearch = false;
	};
	$scope.updateCustomer = function(selectedCustomer) {
		$scope.hideAll();
		$scope.hideCustomerEditDelete = false;
		
		$scope.customer = selectedCustomer;
	};
	$scope.customerAddPage = function() {
		$scope.hideAll();
		$scope.hideCustomerAddPage = false;
		
		
		$scope.customer = selectedCustomer;
	};
	
	$scope.putCustomer = function() {		
		$scope.jsonObject = angular.toJson($scope.customer, false);
		alert("time to update, customer json: "+$scope.jsonObject);
		
		$http.put($scope.resource, $scope.jsonObject)
		.then(
				function success(response) {
					$scope.updateCustomer = response.data;
					alert("customer updated: " + $scope.updateCustomer.id + ", status: " + response.status);
				},
				function error(response) {
					alert("error, return status: " + response.status)
				}
		);	
	};
	
	$scope.deleteCustomer = function(customerId) {		
		
		alert("time to DELETE, customer id: "+ customerId);
		
		$http.delete($scope.resource + customerId)
		.then(
				function success(response) {
					$scope.rowCount = response.data;
					alert("delete count: " + $scope.rowCount + ", status: " + response.status);
				},
				function error(response) {
					alert("error, return status: " + response.status)
				}
		);	
	};

	
	$scope.getCustomers = function() {
		$scope.hideCustomerSearchResults = false;
		$http.get($scope.resource).then(function(response) {
			$scope.myCustomers = response.data;
		});
	}	
	
 

$scope.createCustomer = function() {	
		
		if ($scope.newCustomerName == undefined) {
			$scope.nameMessage = "name is required";
		} else if ($scope.newCustomerName.length < 4) {
			$scope.nameMessage = "name must be at least 4 characters";
		} else {
			$scope.nameMessage = "";
		}
		
		if ($scope.newCustomerCity == undefined) {
			$scope.cityMessage = "city is required";
		} else if ($scope.newCustomerCity.length < 4) {
			$scope.cityMessage = "city must be at least 4 characters";
		} else {
			$scope.cityMessage = "";
		}
		
		if ($scope.newCustomerEmail == undefined || $scope.newCustomerEmail == "") {
			$scope.emailMessage = "";
			$scope.newCustomerEmail = "";
		} else if ($scope.newCustomerEmail.length < 6) {
			$scope.emailMessage = "email must be at least 6 characters";
		} else {
			$scope.emailMessage = "";
		}			
		
		if ($scope.nameMessage == "" && $scope.cityMessage == "" && $scope.emailMessage == "") 
		{
						
			alert("time to create a customer " +
					", name: " + $scope.newCustomerName +
					", city: " + $scope.newCustomerCity + 					
					", email: " + $scope.newCustomerEmail);					
			
			var newCustomer = { firstName : $scope.newCustomerName,
								city : $scope.newCustomerCity,
								email: $scope.newCustomerEmail };
			
			$http.post("/resttest/webapi/customers", newCustomer)
			.then(
					function success(response) {						
						if (response.data == 1) {						
							alert("rows inserted: " + response.data + ", status: " + response.status);
							$scope.createStatus = "success. press 'Clear' to enter new customer";							
						} else {
							alert("error, return status: " + response.status);
							$scope.createStatus = "error. press 'Clear' to try again";		
						}
					},
					function error(response) {
						alert("error, return status: " + response.status);
						$scope.createStatus = "error. press 'Clear' to try again";						
					}				
			);
			
			$scope.isCreateCustomerDisabled = true;
			$scope.lock = true;
			
		} else {
			$scope.createStatus = "please fix indicated errors";
		}				
			
	};
	
	$scope.clearCreate = function() {	
		
		//clear success or error message
		$scope.createStatus = "";
		
		//clear the input elements
		$scope.newCustomerName = "";
		$scope.newCustomerCity = "";
		$scope.newCustomerEmail = "";
				
		//clear the messages		
		$scope.nameMessage = "";
		$scope.cityMessage = "";
		$scope.emailMessage = "";
		
		//unlock input
		$scope.lock = false;
		$scope.isCreateCustomerDisabled = false;
	}
});	