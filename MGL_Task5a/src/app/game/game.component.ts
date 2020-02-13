import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  GameList: any = [];

  ngOnInit() {
    this.loadGames();
  }

  constructor(
    public gameService: GameService
  ) { }

  // Games list
  loadGames() {
    return this.gameService.GetGames().subscribe((data: {}) => {
      this.GameList = data;
    })
  }

  // Delete game
  deleteGame(data) {
    var index = index = this.GameList.map(x => { return x.game_name }).indexOf(data.game_name);
    return this.gameService.DeleteGame(data.id).subscribe(res => {
      this.GameList.splice(index, 1)
    })
  }
}
