import { Component, OnInit, OnChanges } from '@angular/core';
import { StockService } from '../service/stock.service';
import { AuthService } from '../service/auth.service';
import { BuyData } from '../model/buydata';
import { Subscription, Observable } from 'rxjs';
import { Router } from '@angular/router';
import * as Chart from 'chart.js';
import { takeLast, take } from 'rxjs/operators';
import { pipe } from 'rxjs';
import { GraphData } from '../stockdata/stockdata.component';

export interface Data {
  id: number;
  symbol: string;
  companyName: string;
  primaryExchange: string;
  calculationPrice: string;
  open: number;
  openTime: number;
  close: number;
  closeTime: number;
  high: number;
  low: number;
  latestPrice: number;
  latestSource: string;
  latestTime: Date;
  latestUpdate: number;
  latestVolume: number;
  previousClose: number;
  previousVolume: number;
  downloadTime: string;
  change: number;
  changePercent: number;
  volume: number;
  avgTotalVolume: number;
  marketCap: number;
  peRatio: number;
  week52High: number;
  week52Low: number;
  ytdChange: number;
  lastTradeTime: number;
  isUSMarketOpen: false;
}

@Component({
  selector: 'app-stockexchange',
  templateUrl: './stockexchange.component.html',
  styleUrls: ['./stockexchange.component.css']
})
export class StockexchangeComponent implements OnInit {
  email = this.authService.user();
  amount: number;
  selectedValue: string;
  stockSymbols = ['MSFT', 'TSLA', 'AMZN', 'GOOGL', 'AAPL', 'FB'];
  stock: any;
  stocks: Data[] = [];
  stockList: Data[] = [];
  graphLabel: string[];
  graphData: number[] = [];
  message: any;
  subs: Subscription;
  min: number;
  max: number;
  lastPrice: number;

  constructor(
    private stockService: StockService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {}

  buyStock() {
    const data = new BuyData(this.email, this.amount, this.selectedValue);
    console.log(data);
    this.stockService.buyStock(data).subscribe(
      res => {
        this.router.navigate(['portfolio']);
      },
      err => console.log(err)
    );
  }

  selectStock() {
    this.subs = this.stockService
      .getSpecificStock(this.selectedValue)
      .subscribe(data => {
        this.stocks = data;
        console.log(this.stocks);
        //this.stocks.push(this.stock);
      });
    this.stockService
      .getSpecificStockList(this.selectedValue)
      .subscribe(data => {
        this.stockList = data;
        this.graphData = this.stockList.map(a => a.latestPrice);
        this.graphLabel = this.stockList.map(a => a.downloadTime).reverse();
        this.lastPrice = this.graphData[this.graphData.length - 1];
        this.min = Math.min(...this.graphData);
        this.max = Math.max(...this.graphData);
        const lineChart = this.chartMaker('graph', this.graphLabel, this.graphData, 'Current Prices');
      });
    console.log(this.stockList);
  }

  public chartMaker(id: string, label: string[], graphData: number[], title: string): Chart {
    return new Chart(id, {
      type: 'line',
      data: {
        labels: label,
        datasets: [
          {
            data: graphData,
            borderColor: '#212121',
            label: title,
            borderWidth: 0.1,
            //backgroundColor: "#0000FF",
          }
        ]
      },
      options: {
        legend: {
          display: true
        },
        scales: {
          xAxes: [{
            display: true
          }],
          yAxes: [{
            ticks: {
              min: this.min - 1,
              max: this.max + 1,
          }
          }],
        }
      }
    });
  }
}
