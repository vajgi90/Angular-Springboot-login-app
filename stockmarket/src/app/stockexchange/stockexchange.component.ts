import { Component, OnInit } from '@angular/core';
import { StockService} from '../service/stock.service';
import { AuthService } from '../service/auth.service';
import { BuyData } from '../model/buydata';


export interface Data {
  'id': number;
  'symbol': string;
  'companyName': string;
  'primaryExchange': string;
  'calculationPrice': string;
  'open': number;
  'openTime': number;
  'close': number;
  'closeTime': number;
  'high': number;
  'low': number;
  'latestPrice': number;
  'latestSource': string;
  'latestTime': Date;
  'latestUpdate': number;
  'latestVolume': number;
  'previousClose': number;
  'previousVolume': number;
  'change': number;
  'changePercent': number;
  'volume': number;
  'avgTotalVolume': number;
  'marketCap': number;
  'peRatio': number;
  'week52High': number;
  'week52Low': number;
  'ytdChange': number;
  'lastTradeTime': number;
  'isUSMarketOpen': false;
}

@Component({
  selector: 'app-stockexchange',
  templateUrl: './stockexchange.component.html',
  styleUrls: ['./stockexchange.component.css']
})
export class StockexchangeComponent implements OnInit {
  email = this.authService.user();
  amount: number;
  symbol = '';
  stockSymbols = ['MSFT', 'TSLA', 'AMZN', 'GOOGL', 'AAPL', 'FB'];
  stock: any;
  stocks: Data[];
  message: any;

  constructor(private stockService: StockService, private authService: AuthService) { }

  ngOnInit() {
  }

  buyStock() {
    const data = new BuyData(this.email, this.amount, this.symbol);
    console.log(data);
    this.stockService.buyStock(data).subscribe(
      res => console.log(res),
      err => console.log(err)
    );
  }

  selectStock() {
    this.stockService.getSpecificStock(this.symbol)
    .subscribe(data => {
      this.stocks = data;
      //this.stocks.push(this.stock);
    });
  }



}
