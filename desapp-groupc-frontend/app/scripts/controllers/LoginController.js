(function () {
'use strict';

angular.module('stain').controller('LoginCtrl', loginCtrl );

/* @ngInject */
function loginCtrl($window) {
  var vm = this;
  vm.googleSigin = document.querySelector('google-signin');

  vm.googleSigin.addEventListener('google-signin-offline-success', function() {
      $window.location.href = "main.html";
  }); 
}
  
})()