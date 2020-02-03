import { Component, OnInit, OnDestroy } from '@angular/core';
import { StockService } from '../service/stock.service';
import { AuthService } from '../service/auth.service';
import { PortfolioData } from '../model/portfoliodata';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit, OnDestroy {
  portfolioData: PortfolioData[];
  email = this.authService.user();
  private firstObsSubscription: Subscription;

  constructor(
    private stockService: StockService,
    private authService: AuthService
  ) {
/*     this.firstObsSubscription = interval(30000).subscribe((stg: any) => {this.stockService.getAllPortfolioByEmail(this.email).subscribe(data => {
        this.portfolioData = data;
        console.log(this.portfolioData);
      });
      }
    ); */
  }

  ngOnInit() {
    this.firstObsSubscription = interval(30000).subscribe((stg: any) => {this.stockService.getAllPortfolioByEmail(this.email).subscribe(data => {
      this.portfolioData = data;
      console.log(this.portfolioData);
    });
    }
  );
/*       this.stockService.getAllPortfolioByEmail(this.email).subscribe(data => {
        this.portfolioData = data;
        console.log(this.portfolioData);
      }); */
  }

    ngOnDestroy(): void {
    this.firstObsSubscription.unsubscribe();
  } 
}
