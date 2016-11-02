/*Events Controller*/

'use strict';

angular.module('app').controller("EventsController", ["$http", "$log","$scope", function($http,$log,$scope) {

  function succEvents(response){
    $scope.events = response.data;
    var event = {}
    return $http.post('http://localhost:8080/rest/user/addEvent/1', event).then(suc).catch(fail);
  }

  function succFriends(response){
    $scope.friends = response.data;
  }

  function fail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }

  function suc(response){
    return response.data;
  }

  $http.get('http://localhost:8080/rest/event/allEvents').then(succEvents).catch(fail);
  
  $http.get('http://localhost:8080/rest/user/allFriends/1').then(succFriends).catch(fail);

}]);