import { Component, OnInit, NgZone } from '@angular/core';
import { GameService } from '../game.service';
import { Review } from '../review';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { StarRatingComponent } from 'ng-starrating';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {

  reviewForm: FormGroup;
  GameArr: any = [];
  currentReview: Review = {
    review_id: null,
    review_body: null,
    review_author: null,
    review_rating: null,
    review_game_id: null
  }
  constructor(
    public fb: FormBuilder,
    private ngZone: NgZone,
    private router: Router,
    public gameService: GameService,
  ) { }

  ngOnInit() {
    this.addReview();
  }

  addReview() {
    this.reviewForm = this.fb.group({
      review_id: [''],
      review_body: [''],
      review_author: [''],
      review_rating: [''],
      review_game_id: [localStorage.getItem("localStorgeCurrentGame_GameID")]
    })
  }

  submitForm() {
    var review = this.reviewForm.value;
    review.review_id = '';
    review.review_game_id = localStorage.getItem("localStorgeCurrentGame_GameID");
    review.review_rating = localStorage.getItem("localStorage_SelectedStarRating");
    localStorage.setItem("localStorageCurrentReview_reviewBody", review.review_body);
    if (review.review_author == '') {
      localStorage.setItem("localStorageCurrentReview_reviewAuthor", "anonymous");
    }
    else {
      localStorage.setItem("localStorageCurrentReview_reviewAuthor", review.review_author);
    }
    localStorage.setItem("localStorageCurrentReview_reviewGameId", review.review_game_id);
    localStorage.setItem("localStorageCurrentReview_reviewRating", review.review_rating);
    this.setCurrentReview(review);
    this.gameService.CreateReview(review).subscribe(res => {
      this.resetReviewForm();
      this.gameService.setCurrentReview(review);
      this.gameService.loadReviews();
      this.ngZone.run(() => this.router.navigateByUrl('/submittedReview'))
    });
  }

  setCurrentReview(review) {
    this.currentReview.review_id = review.game_id;
    this.currentReview.review_body = review.game_name;
    this.currentReview.review_author = review.game_genre;
    this.currentReview.review_rating = review.game_releaseDate;
  }

  resetReviewForm() {
    this.reviewForm.reset();
    this.reviewForm = this.fb.group({
      review_id: [''],
      review_body: [''],
      review_author: [''],
      review_rating: [''],
      review_game_id: [localStorage.getItem("localStorgeCurrentGame_GameID")]
    })
  }

  onRate($event: { oldValue: number, newValue: number, starRating: StarRatingComponent }) {
    this.currentReview.review_rating = $event.newValue;
    localStorage.setItem("localStorage_SelectedStarRating", $event.newValue.toString());
  }

}
