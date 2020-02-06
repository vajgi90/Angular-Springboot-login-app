import { Component, OnInit, OnDestroy } from "@angular/core";
import { StockService } from "../service/stock.service";
import { AuthService } from "../service/auth.service";
import { PortfolioData } from "../model/portfoliodata";
import { interval, Subscription, timer } from "rxjs";

@Component({
  selector: "app-portfolio",
  templateUrl: "./portfolio.component.html",
  styleUrls: ["./portfolio.component.css"]
})
export class PortfolioComponent implements OnInit, OnDestroy {
  portfolioDataOpen: PortfolioData[];
  portfolioDataClose: PortfolioData[];
  email = this.authService.user();
  private firstObsSubscriptionOpen: Subscription;
  private firstObsSubscriptionClosed: Subscription;

  constructor(
    private stockService: StockService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.firstObsSubscriptionOpen = timer(100, 10000).subscribe((stg: any) => {
      this.stockService
        .getAllPortfolioByEmailAndStatus(this.email, true)
        .subscribe(data => {
          this.portfolioDataOpen = data;
        });
    });
    this.firstObsSubscriptionClosed = timer(100, 1000).subscribe(
      (stg: any) => {
        this.stockService
          .getAllPortfolioByEmailAndStatus(this.email, false)
          .subscribe(data => {
            this.portfolioDataClose = data;
          });
      }
    );
  }

  ngOnDestroy(): void {
    this.firstObsSubscriptionOpen.unsubscribe();
    this.firstObsSubscriptionClosed.unsubscribe();
  }

  sellStock(index: number) {
    const id = this.portfolioDataOpen[index].id;
    this.stockService.sellStock(id).subscribe(
      res => console.log(res),
      err => console.log(err)
    );
    this.stockService
      .getAllPortfolioByEmailAndStatus(this.email, true)
      .subscribe(data => {
        this.portfolioDataOpen = data;
      });
  }

  deleteProfile() {
    
  }
}
