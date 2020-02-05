package com.MGL_Task4.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MGL_Task4.model.Game;
import com.MGL_Task4.service.Game_Service;

@Service
public class GameManagerImpl implements GameManager {

    @Autowired
    private Game_Service gameService;

    @Override
    public Game saveGame(Game game) {
	return gameService.saveGame(game);
    }

    @Override
    public Game updateGame(Game game) {
	return gameService.updateGame(game);
    }

    @Override
    public Optional<Game> getGame(Long id) {
	return gameService.getGame(id);
    }

    @Override
    public Game deleteGame(Long id) {
	return gameService.deleteGame(id);
    }

    @Override
    public List<Game> listGames() {
	return gameService.listGames();
    }

}