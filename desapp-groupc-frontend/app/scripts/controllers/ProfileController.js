/*Profile Controller*/

'use strict';

angular.module('stain').controller("ProfileController", ["$http", "$log","$scope", function($http,$log,$scope) {
  
  function succUser(response){
    $scope.user = response.data;
  }

  function fail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }

  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/userFrom/1').then(succUser).catch(fail);

}]);