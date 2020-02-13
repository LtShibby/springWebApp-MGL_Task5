'use strict';

angular.module('MGL_Task4_app').factory('MGL_Task4_Service', ['$http', function($http) {

		var REST_SERVICE_URI = 'http://localhost:7070/MGL_Task4/';

		var factory = {
			fetchAllGames : fetchAllGames,
			createGame : createGame,
			updateGame : updateGame,
			deleteGame : deleteGame,
			fetchAllReviews: fetchAllReviews
		};

		return factory;

		function fetchAllGames() {
			return $http.get(REST_SERVICE_URI + 'fetchAllGames').then(function(response) {
					return response.data;
				}
			);
		}

		function createGame(game) {
			return $http.post(REST_SERVICE_URI + 'createGame', game).then(function(response) {
					return response.data;
				}
			);
		}
		
		function gamePage() {
			return $http.get(REST_SERVICE_URI + 'games').then(function(response) {
					return response.data;
				}
			);
		}
		
		function gamePage() {
			return $http.get(REST_SERVICE_URI + 'game/list').then(function(response) {
					return response.data;
				}
			);
		}
		
		function updateGame(game) {
			return $http.put(REST_SERVICE_URI + 'updateGame', game).then(function (response) {
	                return response.data;
	            }
	        );
	    }
		
	    function deleteGame(game_id) {
	       return $http.put(REST_SERVICE_URI + 'deleteGame', game_id).then(function (response) {
	                return response.data;
	            }
	        );
	    }

		
	    function fetchAllReviews(review_game_id) {
	    	$http.get(REST_SERVICE_URI + 'reviewsForGame?review_game_id='+review_game_id).then(function (response) {
	                return response.data;
	            }
	        );
	    }
}]);
