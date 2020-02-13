'use strict';

angular.module('MGL_Task4_app').controller('MGL_Task4_Controller',
		[ 'MGL_Task4_Service',  '$window', function(MGL_Task4_Service, $window) {
			var self = this;
			self.game = {
				game_id : null,
				game_name : null,
				game_genre : null,
				game_releaseDate : null
			};
			self.games = [];
			self.review = {
					review_id : null,
					review_body : null,
					review_author : null,
					review_rating : "1"
				};
			self.reviews = [];
			self.this_review_game_id = null;

			self.fetchAllGames = function() {
				MGL_Task4_Service.fetchAllGames().then(function(data) {
					self.games = data;
				});
			}

			self.addGame = function() {
				if(self.game.game_id===null){
					return MGL_Task4_Service.createGame(self.game).then(function() {
						self.fetchAllGames();
					});
				}
				else{
					return MGL_Task4_Service.updateGame(self.game).then(function() {
						self.fetchAllGames();
					});
				}
			}

			self.gamePage = function() {
				return MGL_Task4_Service.gamePage().then(function() {
					self.fetchAllGames();
				});
			}

			self.updateGame = function() {
				return MGL_Task4_Service.updateGame(game).then(function() {
					self.fetchAllGames();
				});
			}

			self.editGame = function(game) {
				self.game = angular.copy(game);
			}

			self.deleteGame = function(game_id) {
				return MGL_Task4_Service.deleteGame(game_id).then(function() {
					self.fetchAllGames();
				});
				self.fetchAllGames();
			}
			
			self.addGameIdToNewReview = function() {
				self.this_review_game_id="review/saveReview?review_game_id="+new URLSearchParams(location.search).get("review_game_id");
			}
			
			self.fetchAllReviews = function(game_id) {
				self.reviews = MGL_Task4_Service.fetchAllReviews(game_id);
				$window.location.href = "reviewsForGame?review_game_id="+game_id;
			}
			
			self.resetGame = function(){
				self.game = {
						game_id : null,
						game_name : null,
						game_genre : null,
						game_releaseDate : null
					};
			}
			
			self.resetReview = function(){
				self.review = {
						review_id : null,
						review_body : null,
						review_author : null,
						review_rating : "1"
					};
			}
			

			self.fetchAllGames();
			self.addGameIdToNewReview();

		} ]);
