package com.MGL_Task4.manager;

import java.util.List;
import java.util.Optional;

import com.MGL_Task4.model.Game;

public interface GameManager {

    Game saveGame(Game game);

    Game updateGame(Game game);

    Optional<Game> getGame(Long id);

    Game deleteGame(Long id);

    List<Game> listGames();

}
