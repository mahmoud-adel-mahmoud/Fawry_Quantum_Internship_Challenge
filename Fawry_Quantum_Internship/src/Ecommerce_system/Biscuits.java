package Ecommerce_system;

import java.time.LocalDate;

public class Biscuits extends Product implements Expirable, Shippable{
	
	private LocalDate expirationDate;
	private double weight;
	private int shippingFees;

	public Biscuits(String name, int price, int quantity, LocalDate expirationDate, double weight, int shippingFees) {
		super(name, price, quantity);
		this.weight = weight;
		this.expirationDate = expirationDate;
		this.shippingFees = shippingFees;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	
	public int getShippingFees() {
		return shippingFees;
	}

	@Override
	public double getWeight() {
		return weight;
	}
}
