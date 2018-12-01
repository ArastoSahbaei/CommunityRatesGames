import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {ProfileComponent} from "./user/profile/profile.component";
import {RegisterComponent} from "./login/register/register.component";
import {HomeComponent} from "./home/home.component";
import {GameComponent} from "./game/game.component";
import {RatingComponent} from "./game/rating/rating.component";
import {ReportBugComponent} from "./user/report-bug/report-bug.component";
import {AddGameComponent} from "./user/add-game/add-game.component";
import {ContactComponent} from "./user/contact/contact.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {UserComponent} from "./user/user.component";
import {SearchgameComponent} from "./searchgame/searchgame.component";
import {Top100Component} from "./game/top100/top100.component";
import {PlatformComponent} from "./game/platform/platform.component";
import {MygamesComponent} from "./user/mygames/mygames.component";
import {LoginGuard} from "./login/login.guard";
import {ErrorComponent} from "./error/error.component";
import {GamePageComponent} from "./game-page/game-page.component";

const routes: Routes = [
  {path: '', component: HomeComponent, children: [
      {path: 'login', component: LoginComponent},
      {path: 'user', component: UserComponent, canActivate: [LoginGuard], children: [
          {path: 'profile', component: ProfileComponent},
          {path: 'report-a-bug', component: ReportBugComponent},
          {path: 'my-games', component: MygamesComponent},
          {path: 'add-a-game', component: AddGameComponent},
          {path: 'contact', component: ContactComponent},
        ]},
      {path: 'game', component: GameComponent, children: [
          {path: 'rating', component: RatingComponent},
          {path: 'platform', component: PlatformComponent},
          {path: 'top100', component: Top100Component},
          {path: 'gamepage', component: GamePageComponent}
        ]},
      {path: 'search', component: SearchgameComponent}
    ]},
  {path: 'error', component: ErrorComponent},
  {path: 'register', component: RegisterComponent},
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
