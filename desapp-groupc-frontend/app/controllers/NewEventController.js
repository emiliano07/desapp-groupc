/*New Event Controller*/

'use strict';

angular.module('app').controller("NewEventController", ["$http", "$log","$scope", function($http,$log,$scope) {
  
  $scope.lucio = 0;

  function agregar(aaa){
    $scope.alert = 10000;
  }

}]);