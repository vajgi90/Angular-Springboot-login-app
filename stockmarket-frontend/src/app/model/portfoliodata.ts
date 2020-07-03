export class PortfolioData {
  constructor(
    public id: number,
    public email: string,
    public stockSymbol: string,
    public amount: number,
    public buyingTime: Date,
    public sellingTime: Date,
    public buyingPricePerStock: number,
    public sellingPricePerStock: number,
    public buyingPrice: number,
    public sellingPrice: number,
    public priceDifference: number,
    public priceDifferencePerStock: number,
    public open: boolean,
  ) {}
}
