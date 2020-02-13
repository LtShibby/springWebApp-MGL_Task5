import { Component, OnInit, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { Review } from '../review';

@Component({
  selector: 'app-result-review',
  templateUrl: './result-review.component.html',
  styleUrls: ['./result-review.component.css']
})
export class ResultReviewComponent implements OnInit {

  currentReview: Review = {
    review_id: null,
    review_body: null,
    review_author: null,
    review_rating: null,
    review_game_id: null
  }

  constructor(
    private ngZone: NgZone,
    private router: Router
  ) { }

  ngOnInit() {
    this.currentReview.review_author = localStorage.getItem("localStorageCurrentReview_reviewAuthor");
    this.currentReview.review_body = localStorage.getItem("localStorageCurrentReview_reviewBody");
    this.currentReview.review_rating = +localStorage.getItem("localStorageCurrentReview_reviewRating");
  }


  routeToReviewsForGamePage() {
    this.ngZone.run(() => this.router.navigateByUrl('/review'));
  }

  setCurrentReview(review) {
    this.currentReview.review_id = review.game_id;
    this.currentReview.review_body = review.game_name;
    this.currentReview.review_author = review.game_genre;
    this.currentReview.review_rating = review.game_releaseDate;
  }
}
