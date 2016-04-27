/**
 * Address
 * 
 * @author  Babak Toghiani-Rizi
 * @version 1.0
 * @since   2015
 */

public class Address {
	
	/** Street variable */
	private String street;
	/** ZipCode variable */
	private String zipCode;
	/** City variable */
	private String city;

	/**
	 * Class constructor.
	 * @return An Address.
	 */
	public Address() {
		setStreet("");
		setZipCode("");
		setCity("");
	}

	/**
	 * Class constructor.
	 * @param  s Street.
	 * @param  z ZipCode.
	 * @param  c City
	 * @return   An Address with specified attributes.
	 */
	public Address(String s, String z, String c) {
		setStreet(s);
		setZipCode(z);
		setCity(c);
	}

	/**
	 * Street setter.
	 * @param s Street.
	 */
	public void setStreet(String s) {
		this.street = s;
	}

	/**
	 * ZipCode setter.
	 * @param s ZipCode.
	 */
	public void setZipCode(String z) {
		this.zipCode = z;
	}

	/**
	 * City setter.
	 * @param c City.
	 */
	public void setCity(String c) {
		this.city = c;
	}

	/**
	 * Street getter.
	 * @return Street.
	 */
	public String getStreet(){
		return street;
	}

	/**
	 * ZipCode getter.
	 * @return ZipCode.
	 */
	public String getZipCode(){
		return zipCode;
	}

	/**
	 * City getter.
	 * @return City.
	 */
	public String getCity(){
		return city;
	}

	/**
	 * Prints.
	 */
	public void print() {
		System.out.println(toString());
	}

	/**
	 * Formats Address to string.
	 * @return Formatted string.
	 */
	public String toString() {
		return (street + "\n" + zipCode + ", " + city);
	}
}