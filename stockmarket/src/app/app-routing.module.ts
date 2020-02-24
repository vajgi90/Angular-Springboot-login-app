import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './service/auth-guard';
import { StockexchangeComponent } from './stockexchange/stockexchange.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { StockdataComponent } from './stockdata/stockdata.component';
import { ProfileComponent } from './profile/profile.component';
import { CorporateInfoComponent } from './corporate-info/corporate-info.component';


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent  },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard]  },
  { path: 'stock-exchange', component: StockexchangeComponent, canActivate: [AuthGuard] },
  { path: 'portfolio', component: PortfolioComponent, canActivate: [AuthGuard] },
  { path: 'stock-data', component: StockdataComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'corporate-info', component: CorporateInfoComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
