import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./login/register/register.component";
import {HomeComponent} from "./home/home.component";
import {GameComponent} from "./game/game.component";
import {RatingComponent} from "./game/rating/rating.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {SearchgameComponent} from "./main-nav/searchgame/searchgame.component";
import {Top100Component} from "./game/top100/top100.component";
import {PlatformComponent} from "./game/platform/platform.component";
import {LoginGuard} from "./login/login.guard";
import {ErrorComponent} from "./error/error.component";
import {GamePageComponent} from "./game/game-page/game-page.component";
import {CrgComponent} from "./crg/crg.component";
import {AdminGuard} from "./admin/admin.guard";

const routes: Routes = [
  {path: '', component: CrgComponent},
  {path: 'start', component: HomeComponent, children: [
      {path: 'login', component: LoginComponent},
      {path: 'admin', loadChildren: './admin/admin.module#AdminModule', canLoad: [AdminGuard]},
      {path: 'user', loadChildren: './user/user.module#UserModule', canLoad: [LoginGuard]},
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
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
