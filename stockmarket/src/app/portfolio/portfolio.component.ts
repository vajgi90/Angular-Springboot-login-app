import { Component, OnInit } from '@angular/core';
import { StockService } from '../service/stock.service';
import { AuthService } from '../service/auth.service';
import { PortfolioData } from '../model/portfoliodata';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {
  portfolioData: PortfolioData[];
  email = this.authService.user();

  constructor(private stockService: StockService, private authService: AuthService) {}

  ngOnInit() {
    this.stockService.getAllPortfolioByEmail(this.email).subscribe(data => {
      this.portfolioData = data;
    });
  }
}
