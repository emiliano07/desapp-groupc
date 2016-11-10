/*New Tour Controller*/

'use strict';

angular.module('stain').controller("NewTourController", ["$http", "$log","$scope", function($http,$log,$scope) {

	function halfOrange(){
    	$scope.typeOfTour = "POST DE ESTE TYPE";
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