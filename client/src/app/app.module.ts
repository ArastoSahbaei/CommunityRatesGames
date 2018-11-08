import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { GameComponent } from './game/game.component';
import { NavigationComponent } from './navigation/navigation.component';
import { MaterialModule } from "./shared/material/material.module";
import {HttpClientModule} from "@angular/common/http";
import { CompanyComponent } from './company/company.component';
import { PlatformComponent } from './platform/platform.component';
import { RatingComponent } from './rating/rating.component';
import { UserComponent } from './user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    NavigationComponent,
    CompanyComponent,
    PlatformComponent,
    RatingComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
