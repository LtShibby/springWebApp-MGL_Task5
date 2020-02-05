import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Game } from './game';
import { Review } from './review';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { Router } from '@angular/router';
import { Component, OnInit, NgZone } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class GameService {


  GameList: any = [];
  ReviewList: any = [];
  editGameBoolean = false;
  editReviewBoolean = false;

  gameToEdit = new Subject<Game>();

  reviewToEdit = new Subject<Review>();

  currentGame: Game = {
    game_id: null,
    game_name: null,
    game_genre: null,
    game_releaseDate: null
  }

  currentReview: Review = {
    review_id: null,
    review_body: null,
    review_author: null,
    review_rating: null,
    review_game_id: null
  }



  // Base url
  baseurl = 'http://localhost:8080';
  BLANK_ENDPOINT = "/MGL_Task4/";
  HOME = "/MGL_Task4/home";
  REVIEWS_FOR_GAME = "/MGL_Task4/reviewsForGame";
  ADD_REVIEW = "/MGL_Task4/addReview";
  GAMES = "/MGL_Task4/games";
  FETCH_ALL_GAMES = "/MGL_Task4/fetchAllGames";
  FETCH_REVIEWS_FOR_GAME = "/MGL_Task4/fetchReviewsForGame";
  CREATE_GAME = "/MGL_Task4/createGame";
  UPDATE_GAME = "/MGL_Task4/updateGame";
  DELETE_GAME = "/MGL_Task4/deleteGame";
  GET_GAME = "/MGL_Task4/getGame?game_id=";
  UPDATE_REVIEW = "/MGL_Task4/review/updateReview";
  DELETE_REVIEW = "/MGL_Task4/review/deleteReview?review_id=";
  GET_REVIEW = "/MGL_Task4/review/fetchReview?review_id="

  constructor(
    private http: HttpClient,
    private ngZone: NgZone,
    private router: Router,
  ) { }

  // Http Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  // POST
  CreateGame(data): Observable<Game> {
    return this.http.post<Game>(this.baseurl + this.CREATE_GAME, JSON.stringify(data), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  // POST
  CreateReview(data): Observable<Review> {
    data.review_game_id = localStorage.getItem("localStorgeCurrentGame_GameID");
    return this.http.post<Review>(this.baseurl + this.ADD_REVIEW, JSON.stringify(data), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  // GET
  GetGame(game_id): Observable<Game> {
    return this.http.get<Game>(this.baseurl + this.GET_GAME + game_id)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  // GET
  GetGames(): Observable<Game> {
    return this.http.get<Game>(this.baseurl + this.FETCH_ALL_GAMES)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  GetGameId(game_id): Observable<Game> {
    return this.http.get<Game>(this.baseurl + this.GET_GAME + game_id)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  // PUT
  UpdateGame(data): Observable<Game> {
    return this.http.put<Game>(this.baseurl + this.UPDATE_GAME, JSON.stringify(data), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  // PUT
  UpdateReview(data): Observable<Review> {
    return this.http.post<Review>(this.baseurl + this.UPDATE_REVIEW, JSON.stringify(data), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  // DELETE
  DeleteGame(game_id) {
    return this.http.put<Game>(this.baseurl + this.DELETE_GAME, game_id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  // DELETE
  DeleteReview(review_id) {
    return this.http.get<Review>(this.baseurl + this.DELETE_REVIEW + review_id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  // Error handling
  errorHandl(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }

  setCurrentGame(game) {
    this.currentGame.game_id = game.game_id;
    this.currentGame.game_name = game.game_name;
    this.currentGame.game_genre = game.game_genre;
    this.currentGame.game_releaseDate = game.game_releaseDate;
    localStorage.setItem("localStorgeCurrentGame_GameID", game.game_id);
  }

  resetAddGame(game) {
    this.currentGame.game_id = null;
    this.currentGame.game_name = null;
    this.currentGame.game_genre = null;
    this.currentGame.game_releaseDate = null;
  }

  resetEditGame(game) {
    this.currentGame.game_id = game.game_id;
    this.currentGame.game_name = game.game_name;
    this.currentGame.game_genre = game.game_genre;
    this.currentGame.game_releaseDate = game.game_releaseDate;
  }

  // Games list
  loadGames() {
    console.log("inside of loadGames in game.service.ts");
    return this.GetGames().subscribe((data: {}) => {
      this.GameList = data;
    })
  }
  
  // Games list
  loadReviews() {
    console.log("inside of loadReviews in game.service.ts");
    console.log(localStorage.getItem("localStorgeCurrentGame_GameID"));
    return this.GetReviewsForGameId(localStorage.getItem("localStorgeCurrentGame_GameID")).subscribe((data: {}) => {
      this.ReviewList = data;
    })
  }

  editCurrentGame(game) {
    if (this.editGameBoolean == false) {
      this.editGameBoolean = true;
    }
    this.gameToEdit.next(game); //broadcast to the subscribers   
  }

  editCurrentReview(review) {
    if (this.editReviewBoolean == false) {
      this.editReviewBoolean = true;
    }
    this.reviewToEdit.next(review); //broadcast to the subscribers   
  }

  routeToGamePage() {
    this.ngZone.run(() => this.router.navigateByUrl('/game'))
  }

  // GET
  GetReview(review_id): Observable<Review> {
    return this.http.get<Review>(this.baseurl + this.GET_REVIEW + review_id)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  // GET
  GetReviewsForGameId(game_id): Observable<Review> {
    return this.http.put<Review>(this.baseurl + this.FETCH_REVIEWS_FOR_GAME, localStorage.getItem("localStorgeCurrentGame_GameID"))
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

}


