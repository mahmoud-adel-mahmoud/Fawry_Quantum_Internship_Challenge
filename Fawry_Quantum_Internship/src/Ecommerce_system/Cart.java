package Ecommerce_system;

import java.time.LocalDate;
import java.util.HashMap;

public class Cart {
	
	private HashMap<Product, Integer> listOfItems;
	
	public Cart() {
		listOfItems = new HashMap<Product, Integer>();
	}

	public void add(Product p, int quantity) {
		if (quantity > p.getQuantity()) 
			System.out.println("There is no enough quantity in the inventory!");
		else {
			if (listOfItems.containsKey(p)) {
				listOfItems.put(p, listOfItems.get(p)+quantity);
				p.setQuantity(p.getQuantity()-quantity);
				System.out.println(quantity + "x " + p.getName() + " added to your cart!");
			}
			else {
				if (p instanceof Expirable) {
//					System.out.println("Expiration date of " + p.getName() + ": " + ((Expirable) p).getExpirationDate());
					if (LocalDate.now().isAfter(((Expirable) p).getExpirationDate())) {
						System.out.println(p.getName() + " is Expired! Can't be added to your cart");
					}
					//else add the item to the cart.
					else {
						listOfItems.put(p, quantity);
						p.setQuantity(p.getQuantity() - quantity);
						System.out.println(quantity + "x " + p.getName() + " added to your cart!");
					}
					
				}
				else {
					listOfItems.put(p, quantity);
					p.setQuantity(p.getQuantity() - quantity);
					System.out.println(quantity + "x " + p.getName() + " added to your cart!");
				}
			}
		}
	}
	
	
	public void remove(Product p, int quantity) {
				
		if (listOfItems.containsKey(p)) {
			if (quantity >= listOfItems.get(p)) {
				listOfItems.remove(p);
				p.setQuantity(p.getQuantity() + quantity);
				System.out.println(p.getName() + " removed totally from your cart!");
			}
			else {
				listOfItems.put(p, listOfItems.get(p)-quantity);
				p.setQuantity(p.getQuantity()+quantity);
				System.out.println("You have removed " + quantity + "x " + p.getName() + " from your cart!");
			}
		}
		else {
			System.out.println(p.getName() + " is not in your cart.");
		}
		
	}

	public HashMap<Product, Integer> getListOfItems() {
		return listOfItems;
	}

//	public void setListOfItems(HashMap<Product, Integer> listOfItems) {
//		this.listOfItems = listOfItems;
//	}
	
	
}
