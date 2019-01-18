import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UserComponent} from "./user/user.component";
import {CompanyComponent} from "./company/company.component";
import {DialogComponent} from "./dialog/dialog.component";
import {AdminRoutingModule} from "./admin-routing.module";
import {MaterialModule} from "../shared/material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ChartsModule} from "ng2-charts";
import {AdminComponent} from "./admin.component";
import { AddUserComponent } from './user/adduser/add-user.component';
import { EditUserComponent } from './user/edituser/edit-user.component';
import { StatisticComponent } from './statistic/statistic.component';
import { UserStatsComponent } from './statistic/user-stats/user-stats.component';

@NgModule({
  declarations: [
    UserComponent,
    CompanyComponent,
    DialogComponent,
    AdminComponent,
    AddUserComponent,
    EditUserComponent,
    StatisticComponent,
    UserStatsComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    ChartsModule
  ]
})
export class AdminModule { }
