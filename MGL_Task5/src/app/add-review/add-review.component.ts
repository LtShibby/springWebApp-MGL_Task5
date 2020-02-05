import { Component, OnInit, NgZone } from '@angular/core';
import { GameService } from '../game.service';
// import { ReviewService } from '../review.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {

  reviewForm: FormGroup;
  GameArr: any = [];

  constructor(
    public fb: FormBuilder,
    private ngZone: NgZone,
    private router: Router,
    public gameService: GameService,
    // public reviewService: ReviewService,
  ) { }

  ngOnInit() {
    this.addReview()
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
    console.log("localStorage.getItem(localStorgeCurrentGame_GameID)"+localStorage.getItem("localStorgeCurrentGame_GameID"));
    review.review_id = '';
    review.review_game_id = localStorage.getItem("localStorgeCurrentGame_GameID");
    this.gameService.CreateReview(review).subscribe(res => {
      this.resetReviewForm();
      this.gameService.loadReviews();
      this.ngZone.run(() => this.router.navigateByUrl('/review'))
    });
  }

  resetReviewForm() {
    this.reviewForm = this.fb.group({
      review_id: [''],
      review_body: [''],
      review_author: [''],
      review_rating: [''],
      review_game_id: [localStorage.getItem("localStorgeCurrentGame_GameID")]
    })
  }

}
