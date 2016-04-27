/**
 * Person
 * 
 * @author  Babak Toghiani-Rizi
 * @version 1.0
 * @since   2015
 */

import java.util.*;
import java.lang.*;

public class Person {
	
	/** Name variable */
	private String name;
	/** Surname variable */
	private String surname;
	/** Signature variable */
	private String signature;
	/** Length variable */
	private int length;
	/** Address variable */
	private Address address;

	/**
	 * Class constructor.
	 * @return A Person.
	 */
	public Person() {
		setName("");
		setSurname("");
		setLength(0);
		setAddress(null);
		setSignature("");
	}

	/**
	 * Class constructor.
	 * @param  n Name
	 * @param  s Surname
	 * @param  l Length
	 * @param  a Address
	 * @return   A Person with specified attributes.
	 */
	public Person(String n, String s, int l, Address a) {
		setName(n);
		setSurname(s);
		setLength(l);
		setAddress(a);
	}

	/**
	 * Name setter.
	 * @param n Name.
	 */
	public void setName(String n) {
		this.name = n;
	}

	/**
	 * Surname setter.
	 * @param s Surname.
	 */
	public void setSurname(String s) {
		this.surname = s;
	}

	/**
	 * Signature setter.
	 * @param s Signature.
	 */
	public void setSignature(String s) {
		this.signature = s;
	}
	
	/**
	 * Length setter.
	 * @param l Length.
	 */
	public void setLength(int l){
		//making sure that the length isn't negative
		if(l < 0) {
			l = 0;
		}
		this.length = l;
	}

	/**
	 * Address setter.
	 * @param a Address.
	 */
	public void setAddress(Address a) {
		this.address = a;
	}

	/**
	 * Name getter.
	 * @return Name.
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Surname getter.
	 * @return Surname.
	 */
	public String getSurname(){
		return this.surname;
	}

	/**
	 * Signature getter.
	 * @return Signature.
	 */
	public String getSignature(){
		return this.signature;
	}

	/**
	 * Length getter.
	 * @return Length.
	 */
	public int getLength(){
		return this.length;
	}

	/**
	 * Address getter.
	 * @return Address.
	 */
	public Address getAddress(){
		return address;
	}

	/**
	 * Formats length from cm to m.
	 * @return Formatted length.
	 */
	public String lengthToString(){
		int m = length / 100;
		int cm = length % 100;
		String formatedLengthAsString = (m + "." + cm);
		return formatedLengthAsString;
	}

	/**
	 * Print.
	 */
	public void print() {
		System.out.println(toString());
	}

	/**
	 * Formats Person to string.
	 * @return Formatted string.
	 */
	public String toString() {
		return (String.format("%-14s %-28s %-4s", this.signature, this.name + " " + this.surname, this.lengthToString()));
	}
}