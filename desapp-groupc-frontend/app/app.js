'use strict';

angular.module("app", ['ngRoute'])
 
.config(['$routeProvider', function($routeProvider) {

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