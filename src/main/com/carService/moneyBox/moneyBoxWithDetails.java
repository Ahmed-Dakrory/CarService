package main.com.carService.moneyBox;

public class moneyBoxWithDetails {

	public float totalCarPrices=0;
	public moneybox moneyBoxItem;
	public moneyBoxWithDetails(float totalCarPrices, moneybox moneyBoxItem) {
		super();
		this.totalCarPrices = totalCarPrices;
		this.moneyBoxItem = moneyBoxItem;
	}
	public float getTotalCarPrices() {
		return totalCarPrices;
	}
	public void setTotalCarPrices(float totalCarPrices) {
		this.totalCarPrices = totalCarPrices;
	}
	public moneybox getMoneyBoxItem() {
		return moneyBoxItem;
	}
	public void setMoneyBoxItem(moneybox moneyBoxItem) {
		this.moneyBoxItem = moneyBoxItem;
	}
	
	
	
	
}
