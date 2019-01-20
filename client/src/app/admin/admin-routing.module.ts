import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";
import { AdminComponent } from "./admin.component";
import { CompanyComponent } from "./company/company.component";
import { UserComponent } from "./user/user.component";
import { AddUserComponent } from "./user/adduser/add-user.component";
import { EditUserComponent } from "./user/edituser/edit-user.component";
import { StatisticComponent } from "./statistic/statistic.component";
import { AllUsersComponent } from "./user/allusers/all-users.component";
import { OverallComponent } from "./statistic/overall/overall.component";
import { SiteStatisticComponent } from "./statistic/sitestatistic/sitestatistic.component";
import { UserStatisticComponent } from "./statistic/userstatistic/userstatistic.component";

const routes: Routes = [
  {path: 'admin', component: AdminComponent, children: [
      { path: 'company', component: CompanyComponent },
      { path: 'statistic', component: StatisticComponent, children: [
          { path: 'overall', component: OverallComponent }
        ] },
      { path: 'user', component: UserComponent, children: [
          { path: 'adduser', component: AddUserComponent },
          { path: 'edituser', component: EditUserComponent },
          { path: 'alluser', component: AllUsersComponent }
        ]},
      { path: 'sitestatistic', component: SiteStatisticComponent },
      { path: 'userstatistic', component: UserStatisticComponent }
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
