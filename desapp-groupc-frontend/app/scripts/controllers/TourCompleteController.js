angular.module('stain').controller('TourCompleteController', ["$http", "$log","$scope","$routeParams", "$location", function($http, $log, $scope, $routeParams, $location) {
  
  $scope.typeOfTour = $routeParams.typeOfTour;
  $scope.scheduler = $routeParams.scheduler;
  $scope.date = 0;
  $scope.limitAmount = $routeParams.limitAmount;
  $scope.friendsSelected = $routeParams.friendsSelected;
  $scope.eventSelected1 = $routeParams.eventSelected1;
  $scope.eventSelected2 = $routeParams.eventSelected2;

$scope.back = function() {
    $location.path('/eventsForTour/' + $scope.typeOfTour + '/' + $scope.scheduler + '/' + $scope.limitAmount + '/' + $scope.friendsSelected);
  };

$scope.finish = function() {
  $http.post('http://localhost:8080/desapp-groupc-backend/rest/user/addTour/1' , {
    type: $scope.typeOfTour,
    date: $scope.date,
    scheduler: $scope.scheduler,
    amount: null,
    limitAmount:$scope.limitAmount,
    friends: $scope.friendsSelected,
    eventOptions1:null,
    eventOptions2:null,
    event1: null,
    event2:null,
    }).success(function(){
      $scope.updateTour();
      $location.path('/events');
      window.alert("El tour se agrego correctamente.");
    })
  }

  $scope.updateTour = function(){
    $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/myTours/1').success(function(result) { 
      $scope.tours = result;      
    }) 
  }

}]);