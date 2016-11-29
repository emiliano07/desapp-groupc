/*Profile Controller*/

'use strict';

angular.module('stain').controller("ProfileController", ["$http", "$log","$scope","$location", function($http,$log,$scope,$location) {
  
  $scope.typeOfFilm = "";
  $scope.typeOfFood = "";
  $scope.typeOfMusic = "";
  $scope.limitAmount = 0;

  function succUser(response){
    $scope.user = response.data;
  }

  function fail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }

  $scope.cancel = function() {
  	/*Reiniciar la pagina*/
    $location.path('/profile');
  };

  $scope.save = function() {
  	/*Hacer un update antes y en lugar de irse de pagina tirar un alert.. luego actualizar el perfil
    habria q pasar por parametro el type q se actualiza o hacer uno para cada uno*/
    $location.path('/events');
  };

  $scope.openTab = function(cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
  }

  function succFriends(response){
    $scope.friends = response.data;
  }

  function fail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }

  $scope.delete = function() {
    /*HACER*/
    $location.path('/events');
  };

  $scope.add = function() {
    /*HACER*/
    $location.path('/events');
  };

  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/userFrom/1').then(succUser).catch(fail);
  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/allFriends/1').then(succFriends).catch(fail);

}]);