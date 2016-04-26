public class Address {

	private String street;
	private String zipCode;
	private String city;

	public Address() {
		setStreet("");
		setZipCode("");
		setCity("");
	}

	public Address(String s, String z, String c) {
		setStreet(s);
		setZipCode(z);
		setCity(c);
	}

	public void setStreet(String s) {
		this.street = s;
	}

	public void setZipCode(String z) {
		this.zipCode = z;
	}

	public void setCity(String c) {
		this.city = c;
	}

	public String getStreet(){
		return street;
	}

	public String getZipCode(){
		return zipCode;
	}

	public String getCity(){
		return city;
	}

	public void print() {
		System.out.println(toString());
	}

	public String toString() {
		return (street + "\n" + zipCode + ", " + city);
	}
}