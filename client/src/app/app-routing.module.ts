import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {ProfileComponent} from "./profile/profile.component";
import {RegisterComponent} from "./register/register.component";
import {HomeComponent} from "./home/home.component";
import {GameComponent} from "./game/game.component";
import {RatingComponent} from "./rating/rating.component";
import {ReportBugComponent} from "./report-bug/report-bug.component";
import {AddGameComponent} from "./add-game/add-game.component";
import {ContactComponent} from "./contact/contact.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'home', component: HomeComponent},
  {path: 'game', component: GameComponent},
  {path: 'rating', component: RatingComponent},
  {path: 'report-a-bug', component: ReportBugComponent},
  {path: 'add-a-game', component: AddGameComponent},
  {path: 'contact', component: ContactComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [LoginComponent, ProfileComponent]
