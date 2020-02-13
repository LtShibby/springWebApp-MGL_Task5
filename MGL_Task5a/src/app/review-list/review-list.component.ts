import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';
// import { ReviewService } from '../review.service';
import { Game } from '../game';
import { Review } from '../review';


@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css']
})
export class ReviewListComponent implements OnInit {

  currentReview: Review = {
    review_id: 0,
    review_body: '',
    review_author: '',
    review_rating: 5,
    review_game_id: 0
  }

  ngOnInit() {
    this.gameService.loadReviews();
    this.gameService.currentGame.game_name = localStorage.getItem("localStorgeCurrentGame_GameName");
    // .loadReviews(localStorage.getItem("localStorgeCurrentGame_GameID"));
  }

  constructor(
    public gameService: GameService,
  ) { }

  // Delete game
  deleteReview(data) {
    var index = index = this.gameService.ReviewList.map(x => { return x.review_id }).indexOf(data.review_id);
    return this.gameService.DeleteReview(data.review_id).subscribe(res => {
      this.gameService.ReviewList.splice(index, 1)
    })
  }

}
