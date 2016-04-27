/**
 * PersonList
 * 
 * @author  Babak Toghiani-Rizi
 * @version 1.0
 * @since   2015
 */
import java.util.*;
import java.lang.*;

public class PersonList{

	/** Static array variable */
	private static ArrayList<Person> personArray;

	/**
	 * Initiates the PersonList.
	 * @return PersonList.
	 */
	public PersonList(){
		this.personArray = new ArrayList<Person>();
	}

	/**
	 * Replaces the array with a new one.
	 * @param p New array.
	 */
	public void setArray(ArrayList<Person> p){
		personArray = p;
	}

	/**
	 * Array getter.
	 * @return The array.
	 */
	public ArrayList<Person> getArray(){
		return this.personArray;
	}

	/**
	 * Counts number of objects (Person's) the array holds.
	 * @return Number of objects.
	 */
	public int getNumberOfPersons(){
		return this.personArray.size();
	}

	/**
	 * Returns a Person at a specified index.
	 * @param  i The specified index.
	 * @return   The Person.
	 */
	public Person getPersonAtIndex(int i){
		return this.personArray.get(i);
	}

	/**
	 * Wipes the array.
	 */
	public void wipeArray(){
		this.personArray.clear();
	}

	/**
	 * Adds a Person to the array.
	 * @param p The Person to add.
	 */
	public void addPersonToArray(Person p){
		//makes sure that the Person is given a unique signature
		String str = createSignature(p.getName(), p.getSurname());
		//sets it...
		p.setSignature(str);

		//and adds the Person to the array
		this.personArray.add(p);
	}

	/**
	 * Removes a Person at a specified index.
	 * @param i The specified index.
	 */
	public void removeObjectAtIndex(int i){
		this.personArray.remove(i);
	}

	/**
	 * Generates a unique signature for a Person.
	 * @param  n The name of the Person.
	 * @param  s The surname of the Person.
	 * @return   The unique signature.
	 */
	private String createSignature(String n, String s) {

		//divides the signature into the first 3 letters and the last 3
		String partOne;
		String partTwo;

		String str;

		int nameLength = n.length();
		int surnameLength = s.length();
		//makes sure that there are three letters that can be extracted from the name to create the signature
		if (nameLength > 2) {
			partOne = n.substring(0, 3);
			//fills any blank spaces with x's
			partOne = String.format("%-3s",partOne).replace(" ", "x");
		} else {
			partOne = n.substring(0, nameLength);
			//if the name is less than 3 letters
			//it fills up to three letters by adding x's
			while (partOne.length() < 3) {
				partOne = partOne + "x";
			}
		}
		//same thing as above but for the surname
		if (surnameLength > 2) {
			partTwo = s.substring(0, 3);
			partTwo = String.format("%-3s",partTwo).replace(" ", "x");
		} else {
			partTwo = s.substring(0, surnameLength);
			while (partTwo.length() < 3) {
				partTwo = partTwo + "x";
			}
		}
		//adds the two parts together and lowercases the signature
		String partOneAndTwo = (partOne + partTwo).toLowerCase();
		
		//checks the array for the lowest possible unique number for the current signature
		int checkNumber = checkSignatureDuplicates(partOneAndTwo);

		//sets the lowest possible number to the signature
		String sig = String.format(s + "%02d", checkNumber);

		//and returns it
		return String.format(partOneAndTwo + "%02d", checkNumber);
	}

	/**
	 * Compares the input signature with other signatures in the array.
	 * @param  s The signature to compare with.
	 * @return   Number of similar signatures.
	 */
	private int checkSignatureDuplicates(String s){ 
		String str = "";
		int number = 0;
		int freeNumber = 0;
		int a = 0;
		boolean free = false;
		boolean unique = false;

		//looks only in the array isn't empty
		if (getNumberOfPersons() > 0){
			
			//iterates through the whole array
			for(int i=0;i < getNumberOfPersons(); i++){
				s = String.format(s.substring(0,6) + "%02d", i); 
				
				//and compares the given number with every other object in the array
				for(a=0; a < getNumberOfPersons(); a++){
					str = (getPersonAtIndex(a)).getSignature();
					if(s.equals(str)){
						free = false;
						break;
					} else {
						free = true;
						freeNumber = i;
					}
				}

				number = i+1;
				if (free && (a == (getNumberOfPersons()))){
					//finds the lowest possible unique number available
					break;
				}
			}
			if (free){
				return freeNumber;
			}
		}
		//returns it
		return number;
	}
}