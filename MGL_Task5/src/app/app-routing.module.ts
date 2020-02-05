import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NavigationBarComponent } from './navigationBar/navigationBar.component';
import { GameComponent } from './game/game.component';
import { AddGameComponent } from './add-game/add-game.component';
import { GameListComponent } from './game-list/game-list.component';
import { ReviewComponent } from './review/review.component';
import { ReviewListComponent } from './review-list/review-list.component'


const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent },
  { path: 'naviationBar', component: NavigationBarComponent },
  { path: 'game', component: GameComponent },
  { path: 'review', component: ReviewComponent },
  { path: 'addGame', component: AddGameComponent },
  { path: 'gameList', redirectTo: 'game' },
  { path: 'reviewList', component: ReviewListComponent  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
