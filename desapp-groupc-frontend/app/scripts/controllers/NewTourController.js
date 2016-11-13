/*New Tour Controller*/

'use strict';

angular.module('stain').controller("NewTourController", ["$http", "$log","$scope","$location", function($http,$log,$scope,$location) {

  $scope.typeOfTour = 0;
  $scope.scheduler = 0;
  $scope.date = 0;
  $scope.limitAmount = 0;
  $scope.friendsSelected = [];

	$scope.selectTypeOfTour = function(idOfTour){
    $scope.typeOfTour = idOfTour;
  }

  $scope.selectScheduler = function(idOfScheduler){
    $scope.scheduler = idOfScheduler;
  }

  $scope.selectDate = function(idOfDate){
    $scope.date = idOfDate;
  }

  $scope.selectLimitAmount = function(idOfLimitAmount){
    $scope.limitAmount = idOfLimitAmount;
  }

  $scope.selectFriend = function(idOfFiernd){
    $scope.friendsSelected.push(idOfFiernd);
  }

  $scope.cancel = function(path) {
    $location.path(path);
  };

  $scope.aceptar = function() {
    $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/eventsForTour/1' + $scope.typeOfTour + '/' + $scope.date + '/' + $scope.scheduler + '/' + $scope.limitAmount + '/' + $scope.friendsSelected).then(succEvents).catch(fail);
  };

  function succEvents(response){
    $scope.eventsForTour = response.data;
  }

  function succFriends(response){
    $scope.friends = response.data;
  }

  function fail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }
  
  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/allFriends/1').then(succFriends).catch(fail);

}]);