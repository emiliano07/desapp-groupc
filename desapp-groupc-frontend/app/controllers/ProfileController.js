/*Profile Controller*/

'use strict';

angular.module('app').controller("ProfileController", ["$http", "$log","$scope", function($http,$log,$scope) {
  
  function succUser(response){
    $scope.user = response.data;
  }

  function failEvents(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }

  $http.get('http://localhost:8080/rest/user/userFrom/1').then(succUser).catch(failEvents);

}]);