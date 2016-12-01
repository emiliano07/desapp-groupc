angular.module('stain').controller('MyEventsController', ["$http", "$log","$scope","$routeParams", "$location","MyService", function($http, $log, $scope, $routeParams, $location, MyService) {

  function fail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }

  function succ(response){
    $scope.events = response.data;
  }

  $scope.details = function(eventt) {
      MyService.data.event = eventt;
      $location.path('/eventDetail/');
    };
  
  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/eventsFrom/1').then(succ).catch(fail);

}]);