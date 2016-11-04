'use strict';

angular.module('stain')
.config(function($translateProvider) {

	$translateProvider.useSanitizeValueStrategy(null);

	$translateProvider.translations('en', {
   		Details : 'Details'
	});

	$translateProvider.translations('es', {
   		Details : 'Detalle'
	});

	var language = (navigator.language || navigator.browserLanguage).split('-')[0];
    $translateProvider.preferredLanguage(language);
	
	/*$translateProvider.preferredLanguage('es');*/
	// {{n.timestamp | date: 'EEE, dd MMM hh:mm'}}

});