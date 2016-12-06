/*Event Detail Controller*/

angular.module('stain').factory("MyService", function() {
  return {
    data: {}
  };
});

angular.module('stain').controller('EventDetailController', ["$http", "$log","$scope","$routeParams", "$location","MyService", function($http, $log, $scope, $routeParams, $location, MyService) {

$scope.event = MyService.data.event;

$scope.assistt = function() {
  $http.post('http://localhost:8080/desapp-groupc-backend/rest/user/assist/1/' + $scope.event.id , {}).success(function(){
    $scope.assist = "Asistire";
  })
};

$scope.notAssist = function() {
  $http.post('http://localhost:8080/desapp-groupc-backend/rest/user/notAssist/1/' + $scope.event.id , {}).success(function(){
    $scope.assist = "No Asistire";
  })
};

$scope.getSuggestions = function() {
  return $scope.event.suggestions;
};

$scope.details = function(eventt) {
  MyService.data.event = eventt;
  $location.path('/eventDetail/');
};

/*-------------------------------------------------------------------------------------------*/
/*Google Maps*/

  $scope.initMap = function() {
  var origin_place_id = null;
  var destination_place_id = null;
  var travel_mode = google.maps.TravelMode.WALKING;
  var buenosAires = {
        lat : -34.6261939,
        lng : -58.410998
    };
   var map = new google.maps.Map(document.getElementById('map'), {
        mapTypeControl : false,
        center : buenosAires,
        zoom : 11
    });

  var marker = new google.maps.Marker({
          map: map,
          position: {lat:-34.5858548,lng:-58.4335074},
          title: 'Rosebar'
  });

  var directionsService = new google.maps.DirectionsService;
  var directionsDisplay = new google.maps.DirectionsRenderer;
  directionsDisplay.setMap(map);

  var origin_input = document.getElementById('origin-input');
  var destination_input = document.getElementById('destination-input');
  var modes = document.getElementById('mode-selector');

  map.controls[google.maps.ControlPosition.TOP_LEFT].push(origin_input);
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(destination_input);
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(modes);

  var origin_autocomplete = new google.maps.places.Autocomplete(origin_input);
  origin_autocomplete.bindTo('bounds', map);
  var destination_autocomplete =
      new google.maps.places.Autocomplete(destination_input);
  destination_autocomplete.bindTo('bounds', map);

  // Sets a listener on a radio button to change the filter type on Places
  // Autocomplete.
  function setupClickListener(id, mode) {
    var radioButton = document.getElementById(id);
    radioButton.addEventListener('click', function() {
      travel_mode = mode;
    });
  }
  setupClickListener('changemode-walking', google.maps.TravelMode.WALKING);
  setupClickListener('changemode-driving', google.maps.TravelMode.DRIVING);

  function expandViewportToFitPlace(map, place) {
    if (place.geometry.viewport) {
      map.fitBounds(place.geometry.viewport);
    } else {
      map.setCenter(place.geometry.location);
      map.setZoom(17);
    }
  }

  origin_autocomplete.addListener('place_changed', function() {
    var place = origin_autocomplete.getPlace();
    if (!place.geometry) {
      window.alert("Autocomplete's returned place contains no geometry");
      return;
    }
    expandViewportToFitPlace(map, place);

    // If the place has a geometry, store its place ID and route if we have
    // the other place ID
    origin_place_id = place.place_id;
    route(origin_place_id, destination_place_id, travel_mode,
          directionsService, directionsDisplay);
  });

  destination_autocomplete.addListener('place_changed', function() {
    var place = destination_autocomplete.getPlace();
    if (!place.geometry) {
      window.alert("Autocomplete's returned place contains no geometry");
      return;
    }
    expandViewportToFitPlace(map, place);

    // If the place has a geometry, store its place ID and route if we have
    // the other place ID
    destination_place_id = place.place_id;
    route(origin_place_id, destination_place_id, travel_mode,
          directionsService, directionsDisplay);
  });

  function route(origin_place_id, destination_place_id, travel_mode,
                 directionsService, directionsDisplay) {
    if (!origin_place_id || !destination_place_id) {
      return;
    }
    directionsService.route({
      origin: {'placeId': origin_place_id},
      destination: {'placeId': destination_place_id},
      travelMode: travel_mode
    }, function(response, status) {
      if (status === google.maps.DirectionsStatus.OK) {
        directionsDisplay.setDirections(response);
      } else {
        window.alert('Directions request failed due to ' + status);
      }
    });
  }
}

  $scope.initMap();

/*-------------------------------------------------------------------------------------------*/

function succ(response){
  if (!response.data) {
    $scope.assist = "Asistire";
  } else {
    $scope.assist = "No Asistire";
  }
}

  function fail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
  }

  $http.get('http://localhost:8080/desapp-groupc-backend/rest/user/assistEvent/1/' + $scope.event.id).then(succ).catch(fail);

}]);