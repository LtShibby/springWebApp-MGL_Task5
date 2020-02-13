import { Component, OnInit, NgZone } from '@angular/core';
// import { ReviewService } from '../review.service';
import { GameService } from '../game.service';
import { Review } from '../review';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { StarRatingComponent } from 'ng-starrating';

@Component({
  selector: 'app-edit-review',
  templateUrl: './edit-review.component.html',
  styleUrls: ['./edit-review.component.css']
})
export class EditReviewComponent implements OnInit {
  GamesList: any = [];
  editThisReview: Review;
  updateReviewForm: FormGroup;
  currentReview: Review = {
    review_id: null,
    review_body: null,
    review_author: null,
    review_rating: null,
    review_game_id: null
  }

  ngOnInit() {
    this.updateReviewForm = this.fb.group({
      review_id: [null],
      review_body: [null],
      review_author: [null],
      review_rating: [null],
      review_game_id: [null]
    })
    this.populateEditForm();
  }

  constructor(
    private actRoute: ActivatedRoute,
    public gameService: GameService,
    public fb: FormBuilder,
    private ngZone: NgZone,
    private router: Router
  ) { }


  setCurrentReview(review) {
    this.currentReview.review_id = review.review_id;
    this.currentReview.review_body = review.review_body;
    this.currentReview.review_author = review.review_author;
    this.currentReview.review_rating = review.review_rating;
  }

  submitForm() {
    var id = this.actRoute.snapshot.paramMap.get('id');
    this.updateReviewForm.value.review_rating = localStorage.getItem("localStorage_SelectedStarRating");
    this.gameService.UpdateReview(this.updateReviewForm.value).subscribe(res => {
      this.resetReviewForm();
      this.gameService.loadReviews();
      this.gameService.editReviewBoolean = false;
      this.ngZone.run(() => this.router.navigateByUrl('/review'))
    })
  }

  resetReviewForm() {
    this.updateReviewForm = this.fb.group({
      review_id: [''],
      review_body: [''],
      review_author: [''],
      review_rating: [''],
      review_game_id: ['']
    })
    this.gameService.editReviewBoolean = false;

  }

  populateEditForm() {
    this.gameService.reviewToEdit.subscribe(
      (receivedReviewData: Review) => {
        this.editThisReview = receivedReviewData;
        this.updateReviewForm = this.fb.group({
          review_id: [this.editThisReview.review_id],
          review_body: [this.editThisReview.review_body],
          review_author: [this.editThisReview.review_author],
          review_rating: [this.editThisReview.review_rating],
          review_game_id: [this.editThisReview.review_game_id]
        })
        this.setCurrentReview(this.editThisReview);
        this.currentReview.review_rating = this.updateReviewForm.value.review_rating;
      })
  }

  routeToReviewsForGamePage(data) {
    this.gameService.setCurrentGame(data);
    var index = index = this.gameService.GameList.map(x => { return x.game_name }).indexOf(data.game_name);
    this.ngZone.run(() => this.router.navigateByUrl('/review'));
  }
  onRate($event: { oldValue: number, newValue: number, starRating: StarRatingComponent }) {
    this.currentReview.review_rating = $event.newValue;
    localStorage.setItem("localStorage_SelectedStarRating", $event.newValue.toString());
  }
}
