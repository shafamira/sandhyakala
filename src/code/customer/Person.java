package code.customer;

public class Person {
	private String username;
	private String password;
	private String name;
	private String lastName;
	private String gender;
	private int age;

	public Person() {
		username = "";
		password = "";
		name = "";
		lastName = "";
		gender = "";
		age = 0;
	}

	public Person(String username, String password, String name, String lastName, String gender, int age) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}
	
	public int getAge() {
		return age;
	}

	public String toString() {
		return username + ", " + password + ", " + name + " " + lastName + ", " + gender + ", " + age;
	}
}