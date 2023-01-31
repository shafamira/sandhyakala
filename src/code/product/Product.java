package code.product;

public class Product {
	private String productID;
	private String name;
	private double price;
	// weight is 'g'
	private int currNumStock;
	private boolean restock;
	private int numRestocks;
	private static int numProducts = 0;

	public Product() {
		name = "";
		price = 0;
		currNumStock = 0;
		numRestocks = 0;
		restock = false;

		numProducts++;
		setID();
	}
	
	public Product(String name, double price, int currNumStock, int numRestocks) {
		this.name = name;
		this.price = price;
		this.currNumStock = currNumStock;
		this.numRestocks = numRestocks;
		restock = false;

		numProducts++;
		setID();
	}

	public Product(String name, String price, String currNumStock, String numRestocks) {
		this(name, Double.parseDouble(price), Integer.parseInt(currNumStock), Integer.parseInt(numRestocks));
	}

	private void setID() {
		productID = String.valueOf(Math.abs(name.hashCode()) % 1000) + numProducts;
	}
	
	public String getProductID() {
		return productID;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getCurrNumStock() {
		return currNumStock;
	}

	public boolean isRestock() {
		return restock;
	}

	public static int getNumProducts() {
		return numProducts;
	}

	public int getNumRestocks() {
		return numRestocks;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCurrNumStock(int currNumStock) {
		this.currNumStock = currNumStock;
	}

	public void setRestock(boolean restock) {
		this.restock = restock;
	}

	public static void setNumProducts(int numProducts) {
		Product.numProducts = numProducts;
	}

	public void setNumRestocks(int numRestocks) {
		this.numRestocks = numRestocks;
	}

	public void withdrawStock(int num) {
		currNumStock -= num;
	}
	
	public void returnStock(int num) {
		currNumStock += num;
	}
	
	public void restock() {
		returnStock(numRestocks);
		restock = false;
	}
}
