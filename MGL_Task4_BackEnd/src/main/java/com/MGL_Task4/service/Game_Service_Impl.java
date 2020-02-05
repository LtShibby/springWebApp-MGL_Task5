package com.MGL_Task4.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MGL_Task4.model.Game;
import com.MGL_Task4.repository.GameRepository;
import com.MGL_Task4.repository.ReviewRepository;

@Service
public class Game_Service_Impl implements Game_Service {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Game saveGame(Game game) {

//	for (int i = 1; i < 250; i++) {
//	    Review review = new Review();
//	    review.setReview_game_id(365);
//	    review.setReview_body("Runescape Review #" + i);
//	    review.setReview_author("Runescape Author #" + i);
//	    review.setReview_rating((5 % i) + 1);
//	    reviewRepository.save(review);
//
//	}
//	for (int i = 1; i < 250; i++) {
//	    Review review = new Review();
//	    review.setReview_game_id(366);
//	    review.setReview_body("Rocket League Review #" + i);
//	    review.setReview_author("Rocket League Author #" + i);
//	    review.setReview_rating(5 % i);
//	    reviewRepository.save(review);
//	}

	return gameRepository.save(game);
    }

    @Override
    @Transactional
    public Game updateGame(Game game) {
	return gameRepository.save(game);
    }

    @Override
    @Transactional
    public Optional<Game> getGame(Long id) {
	return gameRepository.findById(id);
    }

    @Override
    @Transactional
    public Game deleteGame(Long id) {
	Game gameToDelete = gameRepository.getOne(id);
	gameRepository.deleteById(id);
	return gameToDelete;
    }

    @Override
    @Transactional
    public List<Game> listGames() {
	return gameRepository.findAll();
    }

}