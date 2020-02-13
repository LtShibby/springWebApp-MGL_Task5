import { Component, OnInit, NgZone } from '@angular/core';
import { GameService } from '../game.service';
import { Game } from '../game';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {

  currentGame: Game = {
    game_id: 0,
    game_name: '',
    game_genre: '',
    game_releaseDate: undefined
  }

  ngOnInit() {
    this.gameService.loadGames();
  }

  constructor(
    public gameService: GameService,
    private ngZone: NgZone,
    private router: Router
  ) { }

  // Delete game
  deleteGame(data) {
    var index = index = this.gameService.GameList.map(x => { return x.game_name }).indexOf(data.game_name);
    return this.gameService.DeleteGame(data.game_id).subscribe(res => {
      this.gameService.GameList.splice(index, 1)
    })
  }

  editGame(data) {
    var index = index = this.gameService.GameList.map(x => { return x.game_id }).indexOf(data.game_id);
    return this.gameService.GetGame(data.id).subscribe(res => {
      this.gameService.GameList.splice(index, 1)
    })
  }

  routeToReviewsForGamePage(data) {
    this.gameService.setCurrentGame(data);
    var index = index = this.gameService.GameList.map(x => { return x.game_name }).indexOf(data.game_name);
    this.ngZone.run(() => this.router.navigateByUrl('/review'));
  }

}