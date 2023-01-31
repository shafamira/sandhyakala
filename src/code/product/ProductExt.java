package code.product;

import code.constant.ProductType;

public class ProductExt extends Product {
	private ProductType type;
	private String size;

	public ProductExt() {
		super();
		type = null;
		size = "";
	}
	
	public ProductExt(String name, ProductType type, String size, int currNumStock, int numRestocks, double price) {
		super(name, price, currNumStock, numRestocks);
		this.type = type;
		this.size = size;
	}

	public ProductExt(String name, String type, String size, String currNumStock, String numRestocks, String price) {
		super(name, price, currNumStock, numRestocks);

		for (ProductType productType : ProductType.values()) {
			if (productType.getName().equalsIgnoreCase(type)) {
				this.type = productType;
			}
		}

		this.size = size;
	}

	public String getSize() {
		return size;
	}


	public ProductType getType() {
		return type;
	}

	public String getTypeToString() {
		return type.getName();
	}

	public void setSize(String size) {
		this.size = size;
	}


	public void setType(ProductType type) {
		this.type = type;
	}

	public Object[] getProductInfo(int element, boolean id) {
		Object[] all = null;

		if (id)
			all = new Object[]{getProductID(), getName(), getTypeToString(), getSize(), Integer.valueOf(getCurrNumStock()), Integer.valueOf(getNumRestocks()), Double.valueOf(getPrice())};
		else {
			all = new Object[]{getName(), getTypeToString(), getSize(), Integer.valueOf(getCurrNumStock()), Integer.valueOf(getNumRestocks()), Double.valueOf(getPrice())};
			element--;
		}

		if (element <= all.length) {
			Object[] temp = new Object[element];
			// copy all into temp with element
			System.arraycopy(all, 0, temp, 0, element);
			return temp;
		}


		return null;
	}

	public Object[] getProductInfoExtra() {
		return new Object[]{getProductID(), getName(), getTypeToString(), getSize(), Double.valueOf(getPrice())};
	}

	public String toStringInformation1() {
		String format = "Name: %s, Type: %s";
		return String.format(format, super.getName(), this.type.getName());
	}

	public String toStringInformation2() {
		String format = "Size: %s";
		return String.format(format, this.size);
	}
	
	public String toString() {
		String format = "%s. %s, %s, %s, %.0f, %d";
		return String.format(format, super.getProductID(), super.getName(), getType(), this.size, super.getPrice(), super.getCurrNumStock());
	}
}
