angular.module('stain').controller('EventsForTourController', ["$http", "$log","$scope","$routeParams", "$location","$window", function($http, $log, $scope, $routeParams, $location,$window) {
  
  $scope.typeOfTour = $routeParams.typeOfTour;
  $scope.scheduler = $routeParams.scheduler;
  $scope.date = 0;
  $scope.limitAmount = $routeParams.limitAmount;
  $scope.friendsSelected = $routeParams.friendsSelected;
  $scope.eventSelected1 = "-";
  $scope.eventSelected2 = "-";

  $scope.getEventsForTour1 = function(){
      return $scope.eventsForTour1;
  };

  $scope.getEventsForTour2 = function(){
      return $scope.eventsForTour2;
  };

  $scope.selectEvent1 = function(event){
    $scope.eventSelected1 = event.nameOfEvent;
    $scope.newLimitAmount = ($scope.limitAmount - event.amount);
    $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/eventsForTour2/1/' + $scope.typeOfTour + '/' + $scope.scheduler + '/' + $scope.newLimitAmount + '/' + $scope.friendsSelected).then(succEvent2).catch(fail);
  };

  $scope.selectEvent2 = function(event){
    $scope.eventSelected2 = event.nameOfEvent;
    $scope.newLimitAmount = ($scope.limitAmount - event.amount);   
    $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/eventsForTour1/1/' + $scope.typeOfTour + '/' + $scope.scheduler + '/' + $scope.newLimitAmount + '/' + $scope.friendsSelected).then(succEvent1).catch(fail);
  };

  function succEvents(response){
    $scope.eventsForTour1 = response.data;
    $scope.eventsForTour2 = response.data;
  }

  function succEvent2(response){
    $scope.eventsForTour2 = response.data;
  }

  function succEvent1(response){
    $scope.eventsForTour1 = response.data;
  }

  function fail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }

  $scope.back = function(path) {
    $location.path(path);
  };

  $scope.next = function() {
    $location.path('/tourComplete/' + $scope.typeOfTour + '/' + $scope.scheduler + '/' + $scope.limitAmount + '/' + $scope.friendsSelected + '/' + $scope.eventSelected1 + '/' + $scope.eventSelected2);
  };

  $scope.verMapa = function() {
      $window.location.href = 'googleMaps.html';
  };

  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/eventsForTour1/1/' + $scope.typeOfTour + '/' + $scope.scheduler + '/' + $scope.limitAmount + '/' + $scope.friendsSelected).then(succEvents).catch(fail);

}]);