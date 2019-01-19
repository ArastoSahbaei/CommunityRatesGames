import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import {MygamesComponent} from "./mygames/mygames.component";
import {UserComponent} from "./user.component";
import {ReportBugComponent} from "./report-bug/report-bug.component";
import {ContactComponent} from "./contact/contact.component";
import {ProfileComponent} from "./profile/profile.component";
import {AddGameComponent} from "./add-game/add-game.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MaterialModule} from "../shared/material/material.module";

@NgModule({
  declarations: [
    MygamesComponent,
    UserComponent,
    ReportBugComponent,
    ContactComponent,
    ProfileComponent,
    AddGameComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    UserRoutingModule
  ]
})
export class UserModule { }
