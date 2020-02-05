import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthInterceptorService } from './service/auth-interceptor.service';
import { StockexchangeComponent } from './stockexchange/stockexchange.component';
import { StockdataComponent } from './stockdata/stockdata.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { ChartsModule } from 'ng2-charts';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';




@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    HeaderComponent,
    HomeComponent,
    StockexchangeComponent,
    StockdataComponent,
    PortfolioComponent,
    RegisterComponent,
    ProfileComponent,
  ],
  entryComponents: [RegisterComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    ChartsModule,
    FontAwesomeModule,
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }