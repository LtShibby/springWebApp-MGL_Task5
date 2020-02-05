package com.MGL_Task4.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.MGL_Task4.model.Game;
import com.MGL_Task4.service.Game_Service_Impl;

@RunWith(MockitoJUnitRunner.class)
public class Game_Manager_Test {

    @Mock
    Game_Service_Impl gameServiceMock = Mockito.mock(Game_Service_Impl.class);

    @InjectMocks
    GameManagerImpl gameManager;

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
	Mockito.when(gameServiceMock.listGames()).thenReturn(gameList);
	gameManager.listGames();
    }

    @Test
    public void createGameTest() {
	Game testGame = setGameObjectForTest();
	Mockito.when(gameServiceMock.saveGame(ArgumentMatchers.any(Game.class))).thenReturn(testGame);
	Assertions.assertEquals(testGame, gameManager.saveGame(testGame));
    }

    @Test
    public void updateGameTest() {
	Game testGame = setGameObjectForTest();
	Mockito.when(gameServiceMock.updateGame(ArgumentMatchers.any(Game.class))).thenReturn(testGame);
	gameManager.updateGame(testGame);
    }

    @Test
    public void getGameTest() {
	Game testGame = setGameObjectForTest();
	Optional<Game> optionalGame = null;
	Mockito.when(gameServiceMock.getGame(ArgumentMatchers.any(Long.class))).thenReturn(optionalGame);
	Assertions.assertEquals(optionalGame, gameManager.getGame(testGame.getGame_id()));
    }

    @Test
    public void deleteGameTest() {
	Game testGame = setGameObjectForTest();
	Mockito.when(gameServiceMock.deleteGame(ArgumentMatchers.any(Long.class))).thenReturn(testGame);
	gameManager.deleteGame(testGame.getGame_id());
    }

}
