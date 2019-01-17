import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AccountsComponent} from "./accounts/accounts.component";
import {CompanyComponent} from "./company/company.component";
import {DialogComponent} from "./dialog/dialog.component";
import {AdminRoutingModule} from "./admin-routing.module";
import {MaterialModule} from "../shared/material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ChartsModule} from "ng2-charts";
import {AdminComponent} from "./admin.component";

@NgModule({
  declarations: [AccountsComponent, CompanyComponent, DialogComponent, AdminComponent],
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
