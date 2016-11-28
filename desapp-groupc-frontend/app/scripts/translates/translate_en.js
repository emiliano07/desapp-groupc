'use strict';

angular.module('stain')
.config(function($translateProvider) {

	$translateProvider.useSanitizeValueStrategy(null);

	$translateProvider.translations('en', {
   		Spanish : 'Spanish',
   		English : 'English',
   		HOME : 'HOME',
   		SignOut : 'SIGN OUT',
   		Friends : 'Friends',
   		Options : 'Options',
   		MyProfile : 'My Profile',
   		MyEvents : 'My Events',
   		AddEvent : 'Add Event',
   		NewTour : 'New Tour',
   		Text1: 'Stain S.A. has more than 100 years of experience in organizing events. Founded by Alan Marino in 1907. It has more than 1000 branches worldwide.',
   		Text2: 'Stain - Where we go? Is a social network created by the company Stain S.A., which provides the possibility for its users to organize different types of outings, whether alone, as a couple or with friends. It has thousands of different events and is nowadays the main web page chosen by users who want to guarantee an unforgettable outing.',
		UpcomingEvents : 'Upcoming Events',
		Coin : 'U$D',
		NewEvent : 'New Event',
		Where : 'Place for the Event',
		Price : 'Price',
		Limit : 'Limits of Persons',
		Description : 'Description',
		Scheduler : 'Scheduler',
		MORNING2 : 'MORNING',
		AFTERNOON2 : 'AFTERNOON',
		NIGHT2 : 'NIGTH',
		Image : 'Image',
		Morning : 'Morning',
		Afternoon : 'Afternoon',
		Night : 'Night',
		Image : 'Image',
		Accept : 'Accept',
		Cancel : 'Cancel',
		Add : 'Add New Event',
		Username : 'User name',
		Mail : 'Mail',
		Profile : 'Profile',
		TypeOfFilm : 'Type of Film',
		TypeOfMusic : 'Type of Music',
		TypeOfFood : 'Type of Food',
		LimitAmount : 'Limit Amount',
		Half : 'Half',
		Orange: 'Orange',
		Saturday : 'Saturday',
		NightSaturday : 'Night',
		Fever : 'Fever',
		Gasolera : 'Gasolera',
		With : 'With',
		SurpriseMe : 'Surprise Me',
		TypeOfTourSelected : 'Type of Tour Selected',
		Date : 'Date',
		AvailableEvents : 'Available Events',
		Option1 : 'Option 1',
		Option2 : 'Option 2',
		SelectEvent : 'Select Event',
		EventSelected : 'Event Selected',
		Next : 'Next',
		Back : 'Back',
		Finish : 'Finish',
		TourFinished : 'Tour Finished',
		Ubicacion : 'Location',
		NameEvent : "Name of Event"
	});

	var language = (navigator.language || navigator.browserLanguage).split('-')[0];
    $translateProvider.preferredLanguage(language);
});