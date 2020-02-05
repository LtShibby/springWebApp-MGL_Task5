// import { Injectable } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Review } from './review';
// import { GameService } from './game.service';
// import { Observable, throwError } from 'rxjs';
// import { retry, catchError } from 'rxjs/operators';


// @Injectable({
//   providedIn: 'root'
// })
// export class ReviewService {

//   ReviewList: any = [];

//   currentReview: Review = {
//     review_id: 0,
//     review_body: '',
//     review_author: '',
//     review_rating: 5,
//     review_game_id: 0
//   }

//   // Base url
//   baseurl = 'http://localhost:8080';
//   BLANK_ENDPOINT = "/MGL_Task4/";
//   HOME = "/MGL_Task4/home";
//   REVIEWS_FOR_GAME = "/MGL_Task4/reviewsForGame";
//   ADD_REVIEW = "/MGL_Task4/addReview";
//   GAMES = "/MGL_Task4/games";
//   FETCH_ALL_GAMES = "/MGL_Task4/fetchAllGames";
//   FETCH_REVIEWS_FOR_GAME = "/MGL_Task4/fetchReviewsForGame";
//   CREATE_GAME = "/MGL_Task4/createGame";
//   UPDATE_GAME = "/MGL_Task4/updateGame";
//   DELETE_GAME = "/MGL_Task4/deleteGame";
//   GET_GAME = "/MGL_Task4/getGame?game_id=";
//   GET_REVIEW = "/MGL_Task4/review/fetchReview?review_id="

//   constructor(
//     private http: HttpClient,
//     ) { }

//   // Http Headers
//   httpOptions = {
//     headers: new HttpHeaders({
//       'Content-Type': 'application/json'
//     })
//   }

//   // GET
//   GetReview(review_id): Observable<Review> {
//     return this.http.get<Review>(this.baseurl + this.GET_REVIEW + review_id)
//     .pipe(
//       retry(1),
//       catchError(this.errorHandl)
//     )
//   }

//     // GET
//     GetReviewsForGameId(game_id): Observable<Review> {
//       return this.http.put<Review>(this.baseurl + this.FETCH_REVIEWS_FOR_GAME, localStorage.getItem("localStorgeCurrentGame_GameID"))
//       .pipe(
//         retry(1),
//         catchError(this.errorHandl)
//       )
//     }

//     // Games list
//     loadReviews() {
//       console.log("inside of loadReviews in review.service.ts");
//       console.log(localStorage.getItem("localStorgeCurrentGame_GameID"));
//       return this.GetReviewsForGameId(localStorage.getItem("localStorgeCurrentGame_GameID")).subscribe((data: {}) => {
//         this.ReviewList = data;
//       })
//     }

//     // Error handling
//     errorHandl(error) {
//       let errorMessage = '';
//       if(error.error instanceof ErrorEvent) {
//         // Get client-side error
//         errorMessage = error.error.message;
//       } else {
//         // Get server-side error
//         errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
//       }
//       console.log(errorMessage);
//       return throwError(errorMessage);
//    }

// }
