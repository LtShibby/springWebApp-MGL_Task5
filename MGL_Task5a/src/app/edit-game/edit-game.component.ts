import { Component, OnInit, NgZone } from '@angular/core';
import { GameService } from '../game.service';
import { Game } from '../game';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import * as moment from 'moment'
import { MAT_DATE_LOCALE } from '@angular/material';


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
  submitted = false;

  ngOnInit() {
    this.updateGameForm = this.fb.group({
      game_id: [null],
      game_name: [null, Validators.required],
      game_genre: [null],
      game_releaseDate: [null, Validators.required]
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

  get updateGameFormFields() { return this.updateGameForm.controls; }

  submitForm() {
    var id = this.actRoute.snapshot.paramMap.get('id');
    this.gameService.UpdateGame(this.updateGameForm.value).subscribe(res => {
      this.resetUpdateGameForm();
      this.gameService.loadGames();
      this.gameService.editGameBoolean = false;
      this.ngZone.run(() => this.router.navigateByUrl('/gameList'))
    })
  }

  resetUpdateGameForm() {
    this.updateGameForm = this.fb.group({
      game_id: [''],
      game_name: [''],
      game_genre: [''],
      game_releaseDate: ['']
    })
    this.gameService.editGameBoolean = false;
  }


  populateEditForm() {
    this.gameService.gameToEdit.subscribe(
      (receivedGameData: Game) => {
        this.editThisGame = receivedGameData;
        this.updateGameForm = this.fb.group({
          game_id: [this.editThisGame.game_id, Validators.required],
          game_name: [this.editThisGame.game_name],
          game_genre: [this.editThisGame.game_genre],
          game_releaseDate: [this.editThisGame.game_releaseDate, Validators.required]
        })
      })
  }
}