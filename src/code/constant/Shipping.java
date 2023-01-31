package code.constant;

public enum Shipping {
	REGULAR("Regular"),
	SAME_DAY("Same Day"),
	EXPRESS("Express");

	private String name;
	private double price;

	private Shipping(String name) {
		this.name = name;
		if (name.equals("Regular")) {
			price = 10000;
		} else if (name.equals("Same Day")) {
			price = 15000;
		} else if (name.equals("Express")) {
			price = 25000;
		}
	}

	private int calculateShippingFee() {
		int shippingFee = 10000;
		if (this.equals(Shipping.SAME_DAY)) {
			shippingFee += 5000;
		} else if (this.equals(Shipping.EXPRESS)) {
			shippingFee += 15000;
		}
		return shippingFee;
	}
	
	public String getName() {
		return name;
	}

	public int getPrice() {
		return calculateShippingFee();
	}


	public static Shipping check(String shipping) {
		for (Shipping name : Shipping.values()) {
			if (name.getName().equals(shipping)) return name;
		}
		System.err.println("Don't have this " + shipping + " yet.");
		return null;
	}

	public String toString() {
		return name + " : Rp " + calculateShippingFee();
	}
}
