/*New Event Controller*/

'use strict';

angular.module('stain').controller("NewEventController", ["$http","$scope","$window","$location", function($http,$scope,$window,$location) {

	$scope.newEvent = function(newEvent){
	$http.post('http://localhost:8080/desapp-groupc-backend/rest/user/addEvent/1' , {
        types: null,
		date: null,
		scheduler: null,
		address: newEvent.address,
		amount: newEvent.amount,
		limitOfPersons: newEvent.limitOfPersons,
		suggestions: null,
		nameOfEvent: newEvent.name,
		description: newEvent.description,
		image: newEvent.image,
    }).success(function(){
    	$scope.updateEvents();
    	$location.path("/events");
    	window.alert("El evento se agrego correctamente.");
    })
  }

  $scope.updateEvents = function(){
    $http.get('http://localhost:8080/desapp-groupc-backend/rest/event/allEvents').success(function(result) { 
      $scope.events = result;      
    }) 
  }

  $scope.cancel = function(path) {
    $location.path(path);
  };
}]);