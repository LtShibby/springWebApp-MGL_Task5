import { Component, OnInit, NgZone } from '@angular/core';
import { GameService } from '../game.service';
import { Game } from '../game';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-edit-game',
  templateUrl: './edit-game.component.html',
  styleUrls: ['./edit-game.component.css']
})

export class EditGameComponent implements OnInit {
  GamesList: any = [];
  updateGameForm: FormGroup;
  editThisGame: Game;
  subscription: Subscription;

  ngOnInit() {
    this.updateGameForm = this.fb.group({
      game_id: [null],
      game_name: [null],
      game_genre: [null],
      game_releaseDate: [null]
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

  submitForm() {
    var id = this.actRoute.snapshot.paramMap.get('id');
    this.gameService.UpdateGame(this.updateGameForm.value).subscribe(res => {
      this.resetGameForm();
      this.gameService.loadGames();
      this.gameService.editGameBoolean = false;
      this.ngZone.run(() => this.router.navigateByUrl('/gameList'))
    })
  }

  resetGameForm() {
    this.updateGameForm = this.fb.group({
      game_id: [''],
      game_name: [''],
      game_genre: [''],
      game_releaseDate: ['']
    })
  }


  populateEditForm() {
    this.gameService.gameToEdit.subscribe(
      (receivedGameData: Game) => {
        this.editThisGame = receivedGameData;
        this.updateGameForm = this.fb.group({
          game_id: [this.editThisGame.game_id],
          game_name: [this.editThisGame.game_name],
          game_genre: [this.editThisGame.game_genre],
          game_releaseDate: [this.editThisGame.game_releaseDate]
        })
      })
  }
}