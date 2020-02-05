import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  ReviewList: any = [];

  ngOnInit() {
    this.loadReviews();
  }
  
  constructor(
    public gameService: GameService
  ) { }

  // Reviews List
  loadReviews(){
    return this.gameService.GetReviewsForGameId(localStorage.getItem("localStorgeCurrentGame_GameID")).subscribe((data: {}) => {
      this.ReviewList = data;
    })
  }


  // Delete game
  deleteReview(data) {
    var index = index = this.ReviewList.map(x => { return x.review_id }).indexOf(data.review_id);
    return this.gameService.DeleteReview(data.review_id).subscribe(res => {
      this.ReviewList.splice(index, 1)
    })
  }
}
