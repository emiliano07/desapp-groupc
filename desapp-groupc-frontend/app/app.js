/*APP*/

var app = angular.module("app", ['ngRoute']);
 
app.config(['$routeProvider', function($routeProvider) {

  $routeProvider.when('/', {
    templateUrl: "login.html",
    controller: "LoginController"
  });

  $routeProvider.when('/home', {
    templateUrl: "home.html",
    controller: "EventsController"
  });

  $routeProvider.when('/events', {
    templateUrl: "views/events.html",
    controller: "EventsController"
  });
 
  $routeProvider.when('/profile', {
    templateUrl: "views/profile.html",
    controller: "ProfileController"
  });

  $routeProvider.when('/newEvent', {
    templateUrl: "views/newEvent.html",
    controller: "NewEventController"
  });
   
  $routeProvider.otherwise({
        redirectTo: 'login.html'
  });
   
}]);


/*Login Controller*/

app.controller("LoginController", [function() {

}]);
 
app.controller("ProfileController", ["$http", "$log","$scope", function($http,$log,$scope) {
  
  function succUser(response){
    $scope.user = response.data;
  }

  function failEvents(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }

  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/userFrom/1').then(succUser).catch(failEvents);

}]);


/*New Event Controller*/

app.controller("NewEventController", ["$http", "$log","$scope", function($http,$log,$scope) {
  
  $scope.lucio = 0;

  function agregar(aaa){
    $scope.alert = 10000;
  }

}]);


/*Events Controller*/

app.controller("EventsController", ["$http", "$log","$scope", function($http,$log,$scope) {

  function succEvents(response){
    $scope.events = response.data;
    var event = {}
    return $http.post('http://localhost:8080/desapp-groupc-backend/rest/user/addEvent/1', event).then(suc).catch(fail);
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

  $http.get('http://localhost:8080/desapp-groupc-backend/rest/event/allEvents').then(succEvents).catch(fail);
  
  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/allFriends/1').then(succFriends).catch(fail);

}]);