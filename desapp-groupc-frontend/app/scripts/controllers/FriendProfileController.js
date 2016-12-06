/*Friend Profile Controller*/

'use strict';

angular.module('stain').controller("FriendProfileController", ["$http", "$log","$scope","$location","$routeParams", function($http,$log,$scope,$location,$routeParams) {

  $scope.id = $routeParams.idFriend;

  function succUser(response){
    $scope.user = response.data;
    $scope.typeOfFilm = $scope.user.profile.typeOfFilm;
    $scope.typeOfFood = $scope.user.profile.typeOfFood;
    $scope.typeOfMusic = $scope.user.profile.typeOfMusic;
    $scope.limitAmount = $scope.user.profile.limitAmount;
  }

  $scope.openTab = function(pes) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(pes).style.display = "block";
  }

  function succFriends(response){
    $scope.friends = response.data;
  }

  function succOthersFriends(response){
    $scope.othersFriends = response.data;
  }

  function fail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }

  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/userFrom/' + $scope.id).then(succUser).catch(fail);
  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/allFriends/' + $scope.id).then(succFriends).catch(fail);
  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/optionalFriends/' + $scope.id).then(succOthersFriends).catch(fail);

}]);