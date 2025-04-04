package Ecommerce_system;

public class Tv extends Product implements Shippable{
	
	private double weight;
	private int shippingFees;

	public Tv(String name, int price, int quantity, double weight, int shippingFees) {
		super(name, price, quantity);
		this.weight = weight;
		this.shippingFees = shippingFees;
	}
	
	public int getShippingFees() {
		return shippingFees;
	}

	@Override
	public double getWeight() {
		return weight;
	}
}
