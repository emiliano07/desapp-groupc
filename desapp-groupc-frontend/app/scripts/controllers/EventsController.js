/*Events Controller*/

'use strict';

angular.module('stain').controller("EventsController", ["$http", "$log","$scope","$translate","$location","MyService", function($http,$log,$scope,$translate,$location,MyService) {

  // Paginacion
    $scope.amountPages = 0;
    $scope.page = 0;

    $scope.buscar = function(eventoABuscar) {
      $http.get( 'http://localhost:8080/desapp-groupc-backend/rest/event/eventsSearch/'+ eventoABuscar.buscador).success(function(result) {
        $scope.listOfEvents = result;
      })
    };

    $scope.howMuchEventsRest = function(){
      $http.get( 'http://localhost:8080/desapp-groupc-backend/rest/event/howMuchEvents').success(function(result) {
        $scope.amountPages = result;
      })
    };

    $scope.getPage = function(){
      return scope.page;
    }

    $scope.pageIs = function(number){
      return number === $scope.page;
    }

    $scope.pageIsMax = function(){
      return $scope.amountPages === $scope.page;
    }

    $scope.howMuchEvents = function(){
      return Array.apply(null, {length: $scope.amountPages +1}).map(Number.call, Number);
    }

    $scope.eventsPrevious = function(){
      $scope.events($scope.page -1);
    }

    $scope.eventsNext = function(){
      $scope.events($scope.page +1);
    }

    // Fin Paginacion

    $scope.events = function(page){
      $scope.page = page;
      $http.get( 'http://localhost:8080/desapp-groupc-backend/rest/event/events/'+ page).success(function(result) {
        $scope.listOfEvents = result;
      })
    };

    $scope.getEvents = function(){
      return $scope.listOfEvents;
    };

    $scope.initEvents = function(){
      $scope.events(0);
      $scope.howMuchEventsRest();
    };

    $scope.switchLanguage = function(){
      var langKey = $scope.selected;
    
      switch (langKey) {
        case 'en':
          $translate.use(langKey);
          break;
        case 'es':
          $translate.use(langKey);
          break;
      }
    };

    $scope.details = function(eventt) {
      MyService.data.event = eventt;
      $location.path('/eventDetail/');
    };
    
     $scope.initEvents();    


  /*function succEvents(response){
    $scope.events = response.data;
    var event = {}
  }*/

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

  /*$http.get('http://localhost:8080/desapp-groupc-backend/rest/event/allEvents').then(succEvents).catch(fail);*/
  
  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/allFriends/1').then(succFriends).catch(fail);
}]);