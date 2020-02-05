package com.MGL_Task4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.LocalDate;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.MGL_Task4.model.Game;
import com.MGL_Task4.repository.GameRepository;

@RunWith(MockitoJUnitRunner.class)
public class Game_Service_Test {

    @Mock
    GameRepository gameRepositoryMock = Mockito.mock(GameRepository.class);

    @InjectMocks
    Game_Service_Impl gameService;

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
	testGame.getGame_genre();
	testGame.getGame_id();
	testGame.getGame_name();
	testGame.getGame_releaseDate();
	testGame2.setGame_id(Long.valueOf(2));
	List<Game> gameList = new ArrayList<>();
	gameList.add(testGame);
	gameList.add(testGame2);
	Mockito.when(gameRepositoryMock.findAll()).thenReturn(gameList);
	Assert.assertEquals(gameList, gameService.listGames());
    }

    @Test
    public void createGameTest() {
	Game testGame = setGameObjectForTest();
	Mockito.when(gameRepositoryMock.save(ArgumentMatchers.any(Game.class))).thenReturn(testGame);
	Assert.assertEquals(testGame, gameService.saveGame(testGame));
    }

    @Test
    public void updateGameTest() {

	Game testGame = setGameObjectForTest();
	Mockito.when(gameRepositoryMock.save(ArgumentMatchers.any(Game.class))).thenReturn(testGame);
	Assert.assertEquals(testGame, gameService.updateGame(testGame));
    }

    @Test
    public void deleteGameTest() {
	Game testGame = setGameObjectForTest();
	gameService.deleteGame(testGame.getGame_id());
    }

    @Test
    public void getGameTest() {
	Game testGame = setGameObjectForTest();
	Optional<Game> optionalGame = null;
	Mockito.when(gameRepositoryMock.findById(ArgumentMatchers.any(Long.class))).thenReturn(optionalGame);
	Assert.assertEquals(optionalGame, gameService.getGame(testGame.getGame_id()));
    }

}
