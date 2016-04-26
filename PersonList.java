import java.util.*;
import java.lang.*;

public class PersonList{

	private static ArrayList<Person> personArray;

	//initiates the array
	public PersonList(){
		this.personArray = new ArrayList<Person>();
	}

	//replaces the array with a new one
	public void setArray(ArrayList<Person> p){
		personArray = p;
	}

	//returns the array
	public ArrayList<Person> getArray(){
		return this.personArray;
	}

	//counts number of objects (Person) the array holds
	public int getNumberOfPersons(){
		return this.personArray.size();
	}

	//returns the Person at a specific index
	public Person getPersonAtIndex(int i){
		return this.personArray.get(i);
	}

	//wipes the array
	public void wipeArray(){
		this.personArray.clear();
	}

	//adds Person to array
	public void addPersonToArray(Person p){
		//makes sure that the Person is given a unique signature
		String str = createSignature(p.getName(), p.getSurname());
		//sets it...
		p.setSignature(str);

		//and adds the Person to the array
		this.personArray.add(p);
	}

	//removes a Person at a specific index
	public void removeObjectAtIndex(int i){
		this.personArray.remove(i);
	}

	//creates a unique signature
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

	//compares the input signature with signatures in the array
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