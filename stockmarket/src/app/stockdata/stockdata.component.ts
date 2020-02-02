import { Component, OnInit } from '@angular/core';
import { StockService } from '../service/stock.service';
import { StockMonthly } from '../model/stockmonthly';
import { Chart } from 'chart.js';


export interface GraphData {
  close: number;
}

@Component({
  selector: 'app-stockdata',
  templateUrl: './stockdata.component.html',
  styleUrls: ['./stockdata.component.css']
})
export class StockdataComponent implements OnInit {
  monthlyDataTSLA: StockMonthly[] = [];
  graphDataTSLA: any[];
  graphLabelTSLA: any[];
  monthlyDataMSFT: StockMonthly[] = [];
  graphDataMSFT: any[];
  graphLabelMSFT: any[];
  monthlyDataAAPL: StockMonthly[] = [];
  graphDataAAPL: any[];
  graphLabelAAPL: any[];
  monthlyDataGOOGL: StockMonthly[] = [];
  graphDataGOOGL: any[];
  graphLabelGOOGL: any[];
  monthlyDataAMZN: StockMonthly[] = [];
  graphDataAMZN: any[];
  graphLabelAMZN: any[];
  monthlyDataFB: StockMonthly[] = [];
  graphDataFB: any[];
  graphLabelFB: any[];


constructor(private stockService: StockService) {}

ngOnInit() {
    this.stockService.getAllStockMonthlyBySymbol('TSLA').subscribe(data => {
      this.monthlyDataTSLA = data;
      this.graphDataTSLA = this.monthlyDataTSLA.map(a => a.close);
      this.graphLabelTSLA = this.monthlyDataTSLA.map(a => a.label);
      const lineChartTSLA = this.chartMaker('canvas1', this.graphLabelTSLA, this.graphDataTSLA, 'TESLA Monthly Closing Prices');
    });
    this.stockService.getAllStockMonthlyBySymbol('MSFT').subscribe(data => {
      this.monthlyDataMSFT = data;
      this.graphDataMSFT = this.monthlyDataMSFT.map(a => a.close);
      this.graphLabelMSFT = this.monthlyDataMSFT.map(a => a.label);
      const lineChartMSFT = this.chartMaker('canvas2', this.graphLabelMSFT, this.graphDataMSFT, 'MICROSOFT Monthly Closing Prices');
    });
    this.stockService.getAllStockMonthlyBySymbol('AAPL').subscribe(data => {
      this.monthlyDataAAPL = data;
      this.graphDataAAPL = this.monthlyDataAAPL.map(a => a.close);
      this.graphLabelAAPL = this.monthlyDataAAPL.map(a => a.label);
      const lineChartAAPL = this.chartMaker('canvas3', this.graphLabelAAPL, this.graphDataAAPL, 'APPLE Monthly Closing Prices');
    });
    this.stockService.getAllStockMonthlyBySymbol('GOOGL').subscribe(data => {
      this.monthlyDataGOOGL = data;
      this.graphDataGOOGL = this.monthlyDataGOOGL.map(a => a.close);
      this.graphLabelGOOGL = this.monthlyDataGOOGL.map(a => a.label);
      const lineChartGOOGL = this.chartMaker('canvas4', this.graphLabelGOOGL, this.graphDataGOOGL, 'GOOGLE Monthly Closing Prices');
    });
    this.stockService.getAllStockMonthlyBySymbol('AMZN').subscribe(data => {
      this.monthlyDataAMZN = data;
      this.graphDataAMZN = this.monthlyDataAMZN.map(a => a.close);
      this.graphLabelAMZN = this.monthlyDataAMZN.map(a => a.label);
      const lineChartAMZN = this.chartMaker('canvas5', this.graphLabelAMZN, this.graphDataAMZN, 'AMAZON Monthly Closing Prices');
    });
    this.stockService.getAllStockMonthlyBySymbol('FB').subscribe(data => {
      this.monthlyDataFB = data;
      this.graphDataFB = this.monthlyDataFB.map(a => a.close);
      this.graphLabelFB = this.monthlyDataFB.map(a => a.label);
      const lineChartFB = this.chartMaker('canvas6', this.graphLabelFB, this.graphDataFB, 'FACEBOOK Monthly Closing Prices');
    });
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
          display: true
        }],
      }
    }
  });
}
}
