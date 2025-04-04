package Ecommerce_system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.CheckedOutputStream;
import java.time.LocalDate;

public class Exommerce_system {
	
	public static int shipmentNotice(Cart cart) {
		int shippingCost = 0;
		double totalWeight = 0;
		boolean noItemsToShip = true;
		ArrayList<Shippable> toBeShipped = new ArrayList<>();
	
		for(Product p : cart.getListOfItems().keySet()) {
			if (p instanceof Shippable) {
				toBeShipped.add((Shippable)p);
				
				noItemsToShip = false;
				int quantity = cart.getListOfItems().get(p);
				double currentItemWeight = ((Shippable)p).getWeight()*quantity;
				totalWeight += currentItemWeight;
				System.out.println(quantity + "x " + p.getName() + "    " + currentItemWeight + "g");
				shippingCost += ((Shippable) p).getShippingFees();
			}
		}
		if (noItemsToShip)
			System.out.println("No items are eligble for shipping!");
		else {
			//ShippingService shippingService = new ShippingService();
			//shippingService.processShipment(toBeShipped);
			System.out.println("Total package weight " + ((totalWeight < 1000) ? totalWeight + "g" : (totalWeight/1000) + "kg"));
		}
		return shippingCost;
	}
	
	public static int receipt(Cart cart) {
		int orderSubtotal = 0;
		for(Product p : cart.getListOfItems().keySet()) {
			int quantity = cart.getListOfItems().get(p);
			System.out.println(quantity + "x " + p.getName() + "    " + p.getPrice());
			orderSubtotal += (p.getPrice() * quantity);
		}
		return orderSubtotal;
	}
	
	public static void checkout(Customer customer, Cart cart) {
		
		if (cart.getListOfItems().size() == 0)
			System.out.println("Your cart is empty!");
		else {
			
			System.out.print("\n** Shipment notice **\n---------------------\n");
			
			int shippingCost = shipmentNotice(cart);
				
			System.out.println("\n** Checkout receipt **\n----------------------");
			
			int orderSubTotal = receipt(cart);
			
			int totalAmount = orderSubTotal+shippingCost;
			
			if (customer.getBalance() < totalAmount) {
				System.out.println("Your balance is insufficient!");
			}
			else {
				System.out.println("- - - - - - - - - - - -");
				System.out.println("Subtotal      " + orderSubTotal);
				System.out.println("Shipping      " + shippingCost);
				System.out.println("Amount      " + totalAmount);
				customer.setBalance(customer.getBalance() + totalAmount);
			}

		}
	}

	public static void main(String[] args) {
		
		Cheese cheese = new Cheese("Cheese", 100, 10, LocalDate.of(2026, 1, 1), 200, 10);
		Biscuits biscuits = new Biscuits("biscuits", 150, 10, LocalDate.of(2025, 1, 1), 700, 8);
		Tv tv = new Tv("tv", 200, 10, 1400, 30);
		
		Customer customer = new Customer(2000);
		Cart cart = new Cart();
		
		cart.add(cheese, 1);
		cart.add(tv, 1);
		cart.add(biscuits, 2);
		
		checkout(customer, cart);
		
	}

}
