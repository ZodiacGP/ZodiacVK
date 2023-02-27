import {BrowserModule} from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from "./app-routing.module";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {initializeKeycloak} from "./init/keycloak-init.factory";
import {ContentComponent} from "./component/content/content.component";
import {ConfigInitService} from "./init/config-init.service";


@NgModule({
  declarations: [
    AppComponent,
    ContentComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    KeycloakAngularModule,
    AppRoutingModule
  ],
  providers: [
    ConfigInitService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      deps: [KeycloakService, ConfigInitService],
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
