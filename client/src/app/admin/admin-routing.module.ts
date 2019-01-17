import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";
import { AdminComponent } from "./admin.component";
import { CompanyComponent } from "./company/company.component";
import { AccountsComponent } from "./accounts/accounts.component";

const routes: Routes = [
  {path: 'admin', component: AdminComponent, children: [
      { path: 'company', component: CompanyComponent },
      { path: 'accounts', component: AccountsComponent }
      ]}
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AdminRoutingModule { }
