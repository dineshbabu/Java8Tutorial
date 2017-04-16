package designpattern.decorator;

public class MochaDecorator extends CondimentDecorator {

	Beverage beverage;
	public MochaDecorator(Beverage beverage){
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return " Mocha + " + beverage.getDescription();
	}

	@Override
	double getCost() {
		return 0.20 + beverage.getCost();
	}

}
