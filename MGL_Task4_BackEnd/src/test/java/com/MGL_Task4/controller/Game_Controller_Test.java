package com.MGL_Task4.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.MGL_Task4.manager.GameManagerImpl;
import com.MGL_Task4.model.Game;

@RunWith(MockitoJUnitRunner.class)
public class Game_Controller_Test {

    @Mock
    GameManagerImpl gameManagerMock = Mockito.mock(GameManagerImpl.class);

    @InjectMocks
    Game_Controller game_Controller;

    @BeforeMethod
    public void setup() {
	MockitoAnnotations.initMocks(this);
    }

    private Game setGameObjectForTest() {
	Game testGame = new Game();
	testGame.setGame_genre("testGenre");
	testGame.setGame_id(Long.valueOf(1));
	testGame.setGame_name("testName");
	testGame.setGame_releaseDate(new LocalDate());
	testGame.setGame_releaseDate("2000-02-02");
	return testGame;
    }

    @Test
    public void listGamesTest() {
	Game testGame = setGameObjectForTest();
	Game testGame2 = setGameObjectForTest();
	testGame2.setGame_id(Long.valueOf(2));
	List<Game> gameList = new ArrayList<>();
	gameList.add(testGame);
	gameList.add(testGame2);
	Mockito.when(gameManagerMock.listGames()).thenReturn(gameList);
	game_Controller.fetchAllGames();
    }

    @Test
    public void createGameTest() {
	Game testGame = setGameObjectForTest();
	Mockito.when(gameManagerMock.saveGame(ArgumentMatchers.any(Game.class))).thenReturn(testGame);
	ResponseEntity<Game> gameResponseEntity = new ResponseEntity<>(testGame, HttpStatus.CREATED);
	Assertions.assertEquals(gameResponseEntity, game_Controller.createGame(testGame));
    }

    @Test
    public void updateGameTest() {
	Game testGame = setGameObjectForTest();
	Mockito.when(gameManagerMock.updateGame(ArgumentMatchers.any(Game.class))).thenReturn(testGame);
	game_Controller.updateGame(testGame);
    }

    @Test
    public void deleteGameTest() {
	Game testGame = setGameObjectForTest();
	Mockito.when(gameManagerMock.deleteGame(ArgumentMatchers.any(Long.class))).thenReturn(testGame);
	game_Controller.deleteGame(testGame.getGame_id().toString());
    }

}
