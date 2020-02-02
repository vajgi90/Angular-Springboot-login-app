export class StockMonthly {
  constructor(
    public id: number,
    public symbol: string,
    public date: Date,
    public open: number,
    public close: number,
    public high: number,
    public low: number,
    public volume: number,
    public change: number,
    public changePercent: number,
    public label: string,
    public changeOverTime: number,
    public uOpen: number,
    public uClose: number,
    public uHigh: number,
    public uLow: number,
    public uVolume: number
  ) {}
}
