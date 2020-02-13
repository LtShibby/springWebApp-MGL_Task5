import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { GameComponent } from './game/game.component';
import { ReviewComponent } from './review/review.component';
import { NavigationBarComponent } from './navigationBar/navigationBar.component';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { GameService } from './game.service';
import { AddGameComponent } from './add-game/add-game.component';
import { GameListComponent } from './game-list/game-list.component';
import { EditGameComponent } from './edit-game/edit-game.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EditReviewComponent } from './edit-review/edit-review.component';
import { ResultReviewComponent } from './result-review/result-review.component';
import { ReviewListComponent } from './review-list/review-list.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { RatingModule } from 'ng-starrating';
import { 
  MatDatepickerModule, 
  MatNativeDateModule,
  MatFormFieldModule,
  MatInputModule,
  MAT_DATE_LOCALE,
} from '@angular/material';


@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    ReviewComponent,
    NavigationBarComponent,
    HomeComponent,
    AddGameComponent,
    GameListComponent,
    EditGameComponent,
    EditReviewComponent,
    ResultReviewComponent,
    ReviewListComponent,
    AddReviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    RatingModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  exports: [
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  // providers: [GameService],
  providers: [GameService,
    {provide: MAT_DATE_LOCALE, useValue: 'en-GB'},
],
  bootstrap: [AppComponent]
})
export class AppModule { }
