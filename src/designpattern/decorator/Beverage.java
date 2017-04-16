package designpattern.decorator;

public abstract class Beverage {
	String description = "No description";
	String getDescription(){
		return description;
	}
	
	abstract double getCost();

}
