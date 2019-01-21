import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from "./user.component";
import { ProfileComponent } from "./profile/profile.component";
import { ReportBugComponent } from "./report-bug/report-bug.component";
import { MygamesComponent } from "./mygames/mygames.component";
import { AddGameComponent } from "./add-game/add-game.component";
import { ContactComponent } from "./contact/contact.component";

const routes: Routes = [
  { path: 'user', component: UserComponent, children: [
      {path: 'profile', component: ProfileComponent},
      {path: 'report-a-bug', component: ReportBugComponent},
      {path: 'my-games', component: MygamesComponent},
      {path: 'add-a-game', component: AddGameComponent},
      {path: 'contact', component: ContactComponent},
    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
