import java.util.*;
import java.lang.*;

public class Person{

	private String name;
	private String surname;
	private String signature;
	private int length;
	private Address address;

	public Person() {
		setName("");
		setSurname("");
		setLength(0);
		setAddress(null);
		setSignature("");
	}

	public Person(String n, String s, int l, Address a) {
		setName(n);
		setSurname(s);
		setLength(l);
		setAddress(a);
	}

	//set name
	public void setName(String n) {
		this.name = n;
	}

	//set surname
	public void setSurname(String s) {
		this.surname = s;
	}

	//set signature
	public void setSignature(String s) {
		this.signature = s;
	}
	
	//set length
	public void setLength(int l){
		//making sure that the length isn't negative
		if(l < 0) {
			l = 0;
		}
		this.length = l;
	}

	//set address
	public void setAddress(Address a) {
		this.address = a;
	}

	//get name	
	public String getName(){
		return this.name;
	}

	//get surname
	public String getSurname(){
		return this.surname;
	}

	//get signature
	public String getSignature(){
		return this.signature;
	}

	//get length
	public int getLength(){
		return this.length;
	}

	//get address
	public Address getAddress(){
		return address;
	}

	//formatting length from cm to m
	public String lengthToString(){
		int m = length / 100;
		int cm = length % 100;
		String formatedLengthAsString = (m + "." + cm);
		return formatedLengthAsString;
	}

	//prints from the toString method
	public void print() {
		System.out.println(toString());
	}

	//formats the information of a Person
	public String toString() {
		return (String.format("%-14s %-28s %-4s", this.signature, this.name + " " + this.surname, this.lengthToString()));
	}
}