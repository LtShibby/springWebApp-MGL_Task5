import { Component, OnInit, NgZone } from '@angular/core';
// import { ReviewService } from '../review.service';
import { GameService } from '../game.service';
import { Review } from '../review';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-review',
  templateUrl: './edit-review.component.html',
  styleUrls: ['./edit-review.component.css']
})
export class EditReviewComponent implements OnInit {
  GamesList: any = [];
  editThisReview: Review;
  updateReviewForm: FormGroup;

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
    // public reviewService: ReviewService,
    public gameService: GameService,
    public fb: FormBuilder,
    private ngZone: NgZone,
    private router: Router
  ) { }

  submitForm() {
    console.log("inside submitForm in edit-review.component.ts");
    var id = this.actRoute.snapshot.paramMap.get('id');
    this.gameService.UpdateReview(this.updateReviewForm.value).subscribe(res => {
      this.resetReviewForm();
      this.gameService.loadReviews();
      this.gameService.editReviewBoolean = false;
      this.ngZone.run(() => this.router.navigateByUrl('/review'))
    })
  }

  resetReviewForm() {
    console.log("inside resetReviewForm in edit-review.component.ts");
    this.updateReviewForm = this.fb.group({
      review_id: [''],
      review_body: [''],
      review_author: [''],
      review_rating: [''],
      review_game_id: ['']
    })
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
      })
  }

  routeToReviewsForGamePage(data) {
    this.gameService.setCurrentGame(data);
    var index = index = this.gameService.GameList.map(x => { return x.game_name }).indexOf(data.game_name);
    this.ngZone.run(() => this.router.navigateByUrl('/review'));
    }
}
