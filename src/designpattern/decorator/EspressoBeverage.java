package designpattern.decorator;

public class EspressoBeverage extends Beverage {

	public EspressoBeverage() {
		description = "Espresso";
	}

	@Override
	double getCost() {
		return .20;
	}

}
