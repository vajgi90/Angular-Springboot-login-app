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
  graphLabel: any[];
  monthlyDataMSFT: StockMonthly[] = [];
  graphDataMSFT: any[];
  monthlyDataAAPL: StockMonthly[] = [];
  graphDataAAPL: any[];
  monthlyDataGOOGL: StockMonthly[] = [];
  graphDataGOOGL: any[];
  monthlyDataAMZN: StockMonthly[] = [];
  graphDataAMZN: any[];
  monthlyDataFB: StockMonthly[] = [];
  graphDataFB: any[];

constructor(private stockService: StockService) {}

ngOnInit() {
    this.stockService.getAllStockMonthlyBySymbol().subscribe(data => {
      this.monthlyDataTSLA = data[0];
      this.graphDataTSLA = this.monthlyDataTSLA.map(a => a.close);
      this.graphLabel = this.monthlyDataTSLA.map(a => a.label);
      const lineChartTSLA = this.chartMaker('tsla-graph', this.graphLabel, this.graphDataTSLA, 'TESLA Monthly Closing Prices')
      this.monthlyDataMSFT = data[1];
      this.graphDataMSFT = this.monthlyDataMSFT.map(a => a.close);
      const lineChartMSFT = this.chartMaker('msft-graph', this.graphLabel, this.graphDataMSFT, 'MICROSOFT Monthly Closing Prices');
      this.monthlyDataAAPL = data[2];
      this.graphDataAAPL = this.monthlyDataAAPL.map(a => a.close);
      const lineChartAAPL = this.chartMaker('aapl-graph', this.graphLabel, this.graphDataAAPL, 'APPLE Monthly Closing Prices');
      this.monthlyDataGOOGL = data[3];
      this.graphDataGOOGL = this.monthlyDataGOOGL.map(a => a.close);
      const lineChartGOOGL = this.chartMaker('googl-graph', this.graphLabel, this.graphDataGOOGL, 'GOOGLE Monthly Closing Prices');
      this.monthlyDataAMZN = data[4];
      this.graphDataAMZN = this.monthlyDataAMZN.map(a => a.close);
      const lineChartAMZN = this.chartMaker('amzn-graph', this.graphLabel, this.graphDataAMZN, 'AMAZON Monthly Closing Prices');
      this.monthlyDataFB = data[5];
      this.graphDataFB = this.monthlyDataFB.map(a => a.close);
      const lineChartFB = this.chartMaker('fb-graph', this.graphLabel, this.graphDataFB, 'FACEBOOK Monthly Closing Prices');
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
