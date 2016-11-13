'use strict';

angular.module("stain", ['ngRoute','pascalprecht.translate'])
 
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

  $routeProvider.when('/newTour', {
    templateUrl: "views/newTour.html",
    controller: "NewTourController"
  });

  $routeProvider.when('/eventsForTour', {
    templateUrl: "views/eventsForTour.html",
    controller: "NewTourController"
  });
   
  $routeProvider.otherwise({
        redirectTo: 'login.html'
  });
   
}]);
