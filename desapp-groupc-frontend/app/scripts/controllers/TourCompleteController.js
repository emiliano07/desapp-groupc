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
    $location.path('/events');
  };

}]);