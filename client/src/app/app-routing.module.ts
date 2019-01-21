import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {ErrorComponent} from "./error/error.component";
import {GameComponent} from "./game/game.component";
import {RatingComponent} from "./game/rating/rating.component";
import {PlatformComponent} from "./game/platform/platform.component";
import {Top100Component} from "./game/top100/top100.component";
import {GamePageComponent} from "./game/game-page/game-page.component";
import {CrgComponent} from "./crg/crg.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: CrgComponent},
  {path: 'game', component: GameComponent, children: [
      {path: 'rating', component: RatingComponent},
      {path: 'platform', component: PlatformComponent},
      {path: 'top100', component: Top100Component},
      {path: 'gamepage', component: GamePageComponent}
    ]},
  {path: 'error', component: ErrorComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
