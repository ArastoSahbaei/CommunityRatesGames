import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";
import { AdminComponent } from "./admin.component";
import { CompanyComponent } from "./company/company.component";
import { UserComponent } from "./user/user.component";
import { AddUserComponent } from "./user/adduser/add-user.component";
import { EditUserComponent } from "./user/edituser/edit-user.component";
import { StatisticComponent } from "./statistic/statistic.component";
import {UserStatsComponent} from "./statistic/user-stats/user-stats.component";

const routes: Routes = [
  {path: 'admin', component: AdminComponent, children: [
      { path: 'company', component: CompanyComponent },
      { path: 'statistic', component: StatisticComponent, children: [
          { path: 'user', component: UserStatsComponent }
        ] },
      { path: 'user', component: UserComponent, children: [
          { path: 'adduser', component: AddUserComponent },
          { path: 'edituser', component: EditUserComponent }
        ]},
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
