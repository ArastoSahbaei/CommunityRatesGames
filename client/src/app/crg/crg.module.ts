import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from "../login/login.component";
import { CrgRoutingModule } from './crg-routing.module';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MaterialModule } from "../shared/material/material.module";
import { AdminModule } from "../admin/admin.module";
import { UserModule } from "../user/user.module";
import { RegisterComponent } from "../login/register/register.component";

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    CommonModule,
    CrgRoutingModule,
   // AdminModule,
   // UserModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule
  ]
})
export class CrgModule { }
