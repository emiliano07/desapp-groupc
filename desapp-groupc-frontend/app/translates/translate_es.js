'use strict';

angular.module('app').config(['$translateProvider', function ($translateProvider) {

	$translateProvider.translations('en', {
   		TITLE: 'Upcoming Events',
   		FRIENDS: 'Friends'
	});

	$translateProvider.translations('es', {
   		TITLE: 'Proximos Eventos',
   		FRIENDS: 'Amigos'
	});

	$translateProvider.preferredLanguage('es');

}]);