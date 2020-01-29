import { Component, OnInit } from '@angular/core';
import { StockService } from '../service/stock.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  stocks: Array<any>;

  constructor(private stockService: StockService) { }


  ngOnInit() {
    this.stockService.getAll().subscribe(data => {
      this.stocks = data;
    });
  }
}
