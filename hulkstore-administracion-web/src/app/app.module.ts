import { ToastModule } from 'primeng/toast';
import { JwtInterceptor } from './core/interceptors/jwt.interceptor';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LocationStrategy, PathLocationStrategy } from '@angular/common';
import { AppRoutes } from './app.routing';
import { AppComponent } from './app.component';

import { FlexLayoutModule } from '@angular/flex-layout';
import { FullComponent } from './shared/layouts/full/full.component';
import { AppHeaderComponent } from './shared/layouts/full/header/header.component';
import { AppSidebarComponent } from './shared/layouts/full/sidebar/sidebar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DemoMaterialModule } from './demo-material-module';

import { SharedModule } from './shared/shared.module';
import { SpinnerComponent } from './shared/spinner.component';
import { MatTreeModule } from '@angular/material';
import { LoginComponent } from './components/login/login.component';

import { UserIdleModule } from "angular-user-idle";
import { AdministracionModule } from './components/administracion/administracion.module';




@NgModule({
  declarations: [
    AppComponent,
    FullComponent,
    AppHeaderComponent,
    SpinnerComponent,
    AppSidebarComponent,
    LoginComponent,

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    DemoMaterialModule,
    FormsModule,
    FlexLayoutModule,
    HttpClientModule,
    SharedModule, MatTreeModule,
    SharedModule,
    AdministracionModule,

    UserIdleModule.forRoot({ idle: 6000, timeout: 10, ping: 5 }),
    RouterModule.forRoot(AppRoutes),
    ToastModule,
  ],
  providers: [
    {
      provide: LocationStrategy,
      useClass: PathLocationStrategy,      
    },
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
