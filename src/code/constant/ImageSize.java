package code.constant;

public enum ImageSize {
	BIG("big"),
	SMALL("small");

	private String name;

	private ImageSize(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
