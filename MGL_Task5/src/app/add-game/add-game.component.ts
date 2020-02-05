import { Component, OnInit, NgZone } from '@angular/core';
import { GameService } from '../game.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrls: ['./add-game.component.css']
})

export class AddGameComponent implements OnInit {
  gameForm: FormGroup;
  GameArr: any = [];

  ngOnInit() {
    this.addGame()
  }

  constructor(
    public fb: FormBuilder,
    private ngZone: NgZone,
    private router: Router,
    public gameService: GameService
  ){ }

  addGame() {
    this.gameForm = this.fb.group({
      game_id: [''],
      game_name: [''],
      game_genre: [''],
      game_releaseDate: ['']
    })
  }

  submitForm() {
    this.gameService.CreateGame(this.gameForm.value).subscribe(res => {
      this.resetGameForm();
      this.gameService.loadGames();
      this.ngZone.run(() => this.router.navigateByUrl('/gameList'))
    });
  }

  resetGameForm() {
    this.gameForm = this.fb.group({
      game_id: [''],
      game_name: [''],
      game_genre: [''],
      game_releaseDate: ['']
    })
  }

}