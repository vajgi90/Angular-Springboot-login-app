import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable, interval } from "rxjs";
import { Injectable } from "@angular/core";
import { BuyData } from "../model/buydata";
import { forkJoin } from 'rxjs';
import { Data } from '@angular/router';

@Injectable({ providedIn: "root" })
export class StockService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<any> {
    const allStockURL = '//localhost:8080/api/stocks';
    return this.http.get(allStockURL);
  }

  getAllPortfolioByEmail(email: string): Observable<any> {
    const portfolioByEmailURL = '/localhost:8080/api/portfolio';
    const params = new HttpParams().set("email", email);
    return this.http.get(portfolioByEmailURL, { params });
  }

  getAllPortfolioByEmailAndStatus(
    email: string,
    isOpen: boolean
  ): Observable<any> {
    const portfolioByEmailAndStatusURL = '//localhost:8080/api/portfolio/sort';
    const params = new HttpParams()
      .set("email", email)
      .set("isOpen", isOpen.toString());

    return this.http.get(portfolioByEmailAndStatusURL, { params });
  }

  getAllStockMonthlyBySymbol(): Observable<any> {
  const urlTSLA = '//localhost:8080/api/stockmonthly/TSLA';
  const urlMSFT = '//localhost:8080/api/stockmonthly/MSFT';
  const urlAAPL = '//localhost:8080/api/stockmonthly/AAPL';
  const urlGOOGL = '//localhost:8080/api/stockmonthly/GOOGL';
  const urlAMZN = '//localhost:8080/api/stockmonthly/AMZN';
  const urlFB = '//localhost:8080/api/stockmonthly/FB';
  let tsla = this.http.get(urlTSLA);
  let msft = this.http.get(urlMSFT);
  let aapl = this.http.get(urlAAPL);
  let googl = this.http.get(urlGOOGL);
  let amzn = this.http.get(urlAMZN);
  let fb = this.http.get(urlFB);
  return forkJoin([tsla, msft, aapl, googl, amzn, fb]);
  }

  buyStock(buyData: BuyData): Observable<any> {
    return this.http.post("//localhost:8080/api/portfolio/buy", buyData);
  }

  sellStock(id: number): Observable<any> {
    const sellStockURL = '//localhost:8080/api/portfolio/sell/';
    return this.http.get(sellStockURL + id);
  }

  getSpecificStock(symbol: string): Observable<Data[]> {
    const getSpecificStockURL = '//localhost:8080/api/stock/';
    return this.http.get<Data[]>(getSpecificStockURL + symbol);
  }

  getSpecificStockList(symbol: string): Observable<any> {
    const getSpecificStockListURL = '//localhost:8080/api/stocks/';
    return this.http.get(getSpecificStockListURL + symbol);
  }
}
