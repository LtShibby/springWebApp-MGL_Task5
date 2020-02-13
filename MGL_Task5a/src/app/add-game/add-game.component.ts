import { Component, OnInit, NgZone } from '@angular/core';
import { GameService } from '../game.service';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import * as moment from "moment";

@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrls: ['./add-game.component.css']
})

export class AddGameComponent implements OnInit {
  gameForm: FormGroup;
  GameArr: any = [];
  submitted = false;
  formatted_game_releaseDate = '';
  ngOnInit() {
    this.addGame()
  }

  constructor(
    public fb: FormBuilder,
    private ngZone: NgZone,
    private router: Router,
    public gameService: GameService
  ) { }

  get gameFormFields() { return this.gameForm.controls; }

  addGame() {
    this.gameForm = this.fb.group({
      game_id: [''],
      game_name: ['', Validators.required],
      game_genre: [''],
      game_releaseDate: ['',  Validators.required]
    })
  }

  submitForm() {
    this.submitted = true;

    if(this.gameForm.invalid){
      return;
    }
    
    const momentDate = new Date(this.gameForm.value.game_releaseDate);
    const formattedDate = moment(momentDate).format("YYYY-MM-DD");
    this.gameForm.value.game_releaseDate = formattedDate;

    this.gameService.CreateGame(this.gameForm.value).subscribe(res => {
      this.resetGameForm();
      this.gameService.loadGames();
      this.ngZone.run(() => this.router.navigateByUrl('/gameList'))
    });
  }

  resetGameForm() {
    this.submitted=false;
    this.gameForm.reset();
  }
}