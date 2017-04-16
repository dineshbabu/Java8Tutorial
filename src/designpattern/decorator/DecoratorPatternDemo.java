package designpattern.decorator;

public class DecoratorPatternDemo {

	public static void main(String[] args) {
		Beverage beverage = new EspressoBeverage();
		beverage = new MochaDecorator(beverage);
		beverage = new MilkDecorator(beverage);
		beverage = new MochaDecorator(beverage);
		System.out.println(beverage.getDescription());
		System.out.println(beverage.getCost());
	}

}
