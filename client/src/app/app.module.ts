import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { GameComponent } from './game/game.component';
import { MaterialModule } from "./shared/material/material.module";
import { HttpClientModule } from "@angular/common/http";
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
import { SearchgameComponent } from './main-nav/searchgame/searchgame.component';
import { LoginComponent } from "./login/login.component";
import { ProfileComponent } from "./user/profile/profile.component";
import { Top100Component } from './game/top100/top100.component';
import { MygamesComponent } from './user/mygames/mygames.component';
import { ApiService } from "./shared/service/api.service";
import { UrlService } from "./shared/service/url.service";
import { StorageService } from "./shared/service/storage.service";
import { ErrorComponent } from './error/error.component';
import { GamePageComponent } from './game/game-page/game-page.component';
import { GlobalErrorHandlingService } from "./shared/service/global-error-handling.service";
import { AdminComponent } from './admin/admin.component';
import { CrgComponent } from './crg/crg.component';
import { DialogComponent } from './admin/dialog/dialog.component';
import { ChartModule } from "./shared/chart/chart.module";
import { BarRatingModule } from "ngx-bar-rating";
import { UserdialogComponent } from './admin/accounts/userdialog/userdialog.component';
import { FooterComponent } from './main-nav/footer/footer.component';
import { VotingComponent } from './game/game-page/voting/voting.component';
import {AdminModule} from "./admin/admin.module";


@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
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
    MygamesComponent,
    ErrorComponent,
    GamePageComponent,
    AdminComponent,
    CrgComponent,
    UserdialogComponent,
    FooterComponent,
    VotingComponent
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    HttpClientModule,
    LayoutModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    ChartModule,
    BarRatingModule,
    AdminModule
  ],
  entryComponents: [DialogComponent, UserdialogComponent],
  providers: [ApiService, UrlService, StorageService, GlobalErrorHandlingService, {provide: ErrorHandler, useClass: GlobalErrorHandlingService}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
