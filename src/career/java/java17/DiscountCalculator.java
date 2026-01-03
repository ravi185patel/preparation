package career.java.java17;

/*
If a customer is a "Premium" member and purchases more than 5 items, give 20% discount. If
not premium but buys more than 10 items, give 10% discount. Otherwise, no discount.
 */
public class DiscountCalculator {
public static void main(String[] args) { 
String membershipType = "Premium"; // Change to "Regular" to test 
int itemsPurchased = 7;             
// Change to test different cases 
int discount = switch (membershipType) { 
case "Premium" -> (itemsPurchased > 5) ? 20 : 0; 
default        -> (itemsPurchased > 10) ? 10 : 0; 
}; 
System.out.println("Membership: " + membershipType); 
System.out.println("Items purchased: " + itemsPurchased); 
System.out.println("Discount: " + discount + "%"); 
} 
} 