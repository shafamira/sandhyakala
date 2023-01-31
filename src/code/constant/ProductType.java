package code.constant;

public enum ProductType {
	ATASAN("Atasan"),
	BAWAHAN("Bawahan"),
	KAIN("Kain");

	private String name;

	private ProductType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
