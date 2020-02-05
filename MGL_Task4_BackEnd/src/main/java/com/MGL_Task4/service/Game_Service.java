package com.MGL_Task4.service;

import java.util.List;
import java.util.Optional;

import com.MGL_Task4.model.Game;

public interface Game_Service {

    Game saveGame(Game game);

    Game updateGame(Game game);

    Optional<Game> getGame(Long id);

    Game deleteGame(Long id);

    List<Game> listGames();

}
