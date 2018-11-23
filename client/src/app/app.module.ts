import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { GameComponent } from './game/game.component';
import { MaterialModule } from "./shared/material/material.module";
import { HttpClientModule } from "@angular/common/http";
import { CompanyComponent } from './company/company.component';
import { PlatformComponent } from './game/platform/platform.component';
import { RatingComponent } from './game/rating/rating.component';
import { UserComponent } from './user/user.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MainNavComponent } from './main-nav/main-nav.component';
import { AppRoutingModule } from "./app-routing.module";
import { RegisterComponent } from './login/register/register.component';
import { HomeComponent } from './home/home.component';
import { ReportBugComponent } from './user/report-bug/report-bug.component';
import { AddGameComponent } from './user/add-game/add-game.component';
import { ContactComponent } from './user/contact/contact.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { SearchgameComponent } from './searchgame/searchgame.component';
import { LoginComponent } from "./login/login.component";
import { ProfileComponent } from "./user/profile/profile.component";
import { Top100Component } from './game/top100/top100.component';
import { MygamesComponent } from './user/mygames/mygames.component';
import { ApiService } from "./shared/service/api.service";
import { UrlService } from "./shared/service/url.service";
import { StorageService } from "./shared/service/storage.service";

@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    CompanyComponent,
    PlatformComponent,
    RatingComponent,
    UserComponent,
    MainNavComponent,
    RegisterComponent,
    HomeComponent,
    ReportBugComponent,
    AddGameComponent,
    ContactComponent,
    PageNotFoundComponent,
    SearchgameComponent,
    LoginComponent,
    ProfileComponent,
    Top100Component,
    MygamesComponent
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    HttpClientModule,
    LayoutModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ApiService, UrlService, StorageService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
