package com.MGL_Task4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MGL_Task4.manager.GameManager;
import com.MGL_Task4.model.Game;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class Game_Controller {

    @Autowired
    private GameManager gameManager;

    @RequestMapping(value = MGL_Endpoint_Constants.FETCH_ALL_GAMES, method = RequestMethod.GET)
    public ResponseEntity<List<Game>> fetchAllGames() {
	return new ResponseEntity<>(gameManager.listGames(), HttpStatus.OK);
    }

    @RequestMapping(value = MGL_Endpoint_Constants.CREATE_GAME, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
	gameManager.saveGame(game);
	return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @RequestMapping(value = MGL_Endpoint_Constants.UPDATE_GAME, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
	gameManager.updateGame(game);
	return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @RequestMapping(value = MGL_Endpoint_Constants.DELETE_GAME, method = RequestMethod.PUT)
    public ResponseEntity<?> deleteGame(@RequestBody String game_id) {
	Optional<Game> gameToDelete = gameManager.getGame(Long.valueOf(game_id));
	gameManager.deleteGame((Long.valueOf(game_id)));
	return new ResponseEntity<>(gameToDelete, HttpStatus.OK);
    }

    @RequestMapping(value = MGL_Endpoint_Constants.GET_GAME, method = RequestMethod.GET)
    public ResponseEntity<?> fetchGame(@RequestParam String game_id) {
	return new ResponseEntity<>(gameManager.getGame(Long.valueOf(game_id)), HttpStatus.OK);
    }

}