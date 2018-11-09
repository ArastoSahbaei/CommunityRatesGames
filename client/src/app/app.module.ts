import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {GameComponent} from './game/game.component';
import {MaterialModule} from "./shared/material/material.module";
import {HttpClientModule} from "@angular/common/http";
import {CompanyComponent} from './company/company.component';
import {PlatformComponent} from './platform/platform.component';
import {RatingComponent} from './rating/rating.component';
import {UserComponent} from './user/user.component';
import {LayoutModule} from '@angular/cdk/layout';
import {MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule} from '@angular/material';
import {MainNavComponent} from './main-nav/main-nav.component';
import {AppRoutingModule, routingComponents} from "./app-routing.module";
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ReportBugComponent } from './report-bug/report-bug.component';
import { AddGameComponent } from './add-game/add-game.component';
import { ContactComponent } from './contact/contact.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    CompanyComponent,
    PlatformComponent,
    RatingComponent,
    UserComponent,
    MainNavComponent,
    routingComponents,
    RegisterComponent,
    HomeComponent,
    ReportBugComponent,
    AddGameComponent,
    ContactComponent,
    PageNotFoundComponent,
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    HttpClientModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
