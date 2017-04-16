package designpattern.decorator;

public class MilkDecorator extends CondimentDecorator {

	Beverage beverage;
	public MilkDecorator(Beverage beverage){
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return " Milk + " + beverage.getDescription();
	}

	@Override
	double getCost() {
		return 0.20 + beverage.getCost();
	}

}
