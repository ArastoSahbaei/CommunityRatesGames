import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from "../login/login.component";
import { AdminGuard } from "../admin/admin.guard";
import { LoginGuard } from "../login/login.guard";
import { RegisterComponent } from "../login/register/register.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'admin', loadChildren: '../admin/admin.module#AdminModule', canLoad: [AdminGuard]},
  {path: 'user', loadChildren: '../user/user.module#UserModule', canLoad: [LoginGuard]},
  {path: 'register', component: RegisterComponent},


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CrgRoutingModule { }
