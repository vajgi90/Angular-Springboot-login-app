import { HttpClient, HttpParams} from '@angular/common/http';
import { Observable, interval } from 'rxjs';
import { Injectable } from '@angular/core';
import { BuyData } from '../model/buydata';


@Injectable({providedIn: 'root'})
export class StockService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get('//localhost:8080/api/stocks');
  }

  getAllPortfolioByEmail(email: string): Observable<any> {
    const params = new HttpParams()
    .set('email', email);
    return this.http.get('//localhost:8080/api/portfolio', {params});
  }

  
  getAllStockMonthlyBySymbol(symbol: string): Observable<any> {
    return this.http.get('//localhost:8080/api/stockmonthly/' + symbol);
  }

buyStock(buyData: BuyData): Observable < any > {
    return this.http.post('//localhost:8080/api/portfolio/buy',
    buyData);
  }

getSpecificStock(symbol: string): Observable < any > {
    return this.http.get('//localhost:8080/api/stock/' + symbol);
  }

deleteSpecificStock(symbol: string): Observable < any > {
    return this.http.delete('//localhost:8080/stocks/' + symbol);
  }

}
