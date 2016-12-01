angular.module('stain').controller('MyToursController', ["$http", "$log","$scope","$routeParams", "$location","MyService", function($http, $log, $scope, $routeParams, $location, MyService) {

  function fail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }

  function succ(response){
    $scope.tours = response.data;
  }

  $scope.details = function(eventt) {
      MyService.data.event = eventt;
      $location.path('/eventDetail/');
    };
  
  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/toursFrom/1').then(succ).catch(fail);

}]);