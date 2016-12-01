/*Profile Controller*/

'use strict';

angular.module('stain').controller("ProfileController", ["$http", "$log","$scope","$location", function($http,$log,$scope,$location) {

  function succUser(response){
    $scope.user = response.data;
    $scope.typeOfFilm = $scope.user.profile.typeOfFilm;
    $scope.typeOfFood = $scope.user.profile.typeOfFood;
    $scope.typeOfMusic = $scope.user.profile.typeOfMusic;
    $scope.limitAmount = $scope.user.profile.limitAmount;
  }

  function fail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }

  $scope.save = function(profile){
    $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/updateProfile/1/' + $scope.typeOfFilm + '/' + $scope.typeOfFood + '/' +  $scope.typeOfMusic + '/' +  $scope.limitAmount).then(succUpdateProfile).catch(fail);
  }

  function succUpdateProfile(response){
    $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/userFrom/1').then(succUser).catch(fail);
    window.alert("El perfil se actualizo correctamente.");
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

  $scope.delete = function(friend) {
    $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/deleteFriend/1/' + friend.id).then(succFriends).catch(fail);
    $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/optionalFriends/1').then(succOthersFriends).catch(fail);
  };

  $scope.add = function(friend) {
    $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/addFriend/1/' + friend.id).then(succFriends).catch(fail);
    $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/optionalFriends/1').then(succOthersFriends).catch(fail);
  };

  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/userFrom/1').then(succUser).catch(fail);
  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/allFriends/1').then(succFriends).catch(fail);
  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/optionalFriends/1').then(succOthersFriends).catch(fail);

}]);