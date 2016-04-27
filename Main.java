/**
 * Command-line Information Managing Prompt (CLIMP) was 
 * created as a part of the Java course Computer Engineering BA (A),
 * Java I, (7.5 Credits), given by the Mid Sweden University
 * 
 * @author  Babak Toghiani-Rizi
 * @version 1.0
 * @since   2015
 */

import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Main class that runs the program.
 */
public class Main {

	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private boolean quit = false;
	private String inputString;
	
	/** Instance variable */
	private static String PROMPT = "input> ";

	/**
	 * Runs the main program
	 * @param  personList  An initiated PersonList.
	 * @throws IOException If an I/O error occurs.
	 */
	public void start(PersonList personList) throws IOException {

		System.out.println("\nMain menu. Type 'help' for more info.\n");
		help();
		//run until the user inputs quit
		do {
			System.out.print(PROMPT);
			inputString = input.readLine();

			switch (inputString.toLowerCase()) {
				case "add":
				add(personList);
				break;

				case "print":
				printAll(personList);
				break;

				case "search":
				search(personList);
				break;

				case "remove":
				remove(personList);
				break;

				case "sort":
				sort(personList);
				break;

				case "randomize":
				randomize(personList);
				break;

				case "save":
				save(personList);
				
				break;

				case "open":
				open(personList);
				break;

				case "quit":
				quit();
				break;

				case "help":
				help();
				break;

				default:
				System.out.println("Unknown commad, type 'help' for all commands or try again.");

			} 
		} while (!quit);
	}

	/**
	 * Quits the program.
	 */
	private void quit(){
		System.out.println("Closing...");
		quit = true;
	}

	/**
	 * Prints the available commands.
	 */
	public void help(){
		System.out.println("You can choose from the following commands:");
		System.out.println("   add			Add a person");
		System.out.println("   print		Print everyone");
		System.out.println("   search 		Search for a person");
		System.out.println("   remove 		Remove a person");
		System.out.println("   sort			Sort based on attribute");
		System.out.println("   randomize 		Randomize the order");
		System.out.println("   save 		Save to a textfile");
		System.out.println("   open 		Open textfile");
		System.out.println("   quit 		Quit program\n");
	}

	// ---------------------------------------------- SAVE METHODS ---------------------------------------------- // 

	/**
	 * Saves the data to a txt-file, encrypted with a specified key.
	 * @param  personList  The PersonList to save.
	 * @throws IOException If an I/O error occurs.
	 */
	public void save(PersonList personList) throws IOException{
		String fileName = "";

		//asks the user to set the filename
		System.out.print("Set file name: ");
		fileName = input.readLine();
		Person p;
		int key;
		//and encryps with the key given by the user
		System.out.print("Set encryption key: ");
		inputString = input.readLine();
		//makes sure that the key was a valid int
		key = makeInputValid(inputString);
		String delim = "$";
		try{
			PrintWriter printW = new PrintWriter(fileName + ".txt");  

			//encryps every single characters and saves it to the .txt-file
			if(personList.getNumberOfPersons() > 0){  
				for (int i=0; i < personList.getNumberOfPersons() ; i++)
				{ 
					p = personList.getPersonAtIndex(i);
					Address a = p.getAddress();
					
					
					//gathers all data
					String row = String.format(p.getName() + delim +p.getSurname()+ delim +p.getSignature()+ delim +p.getLength()+ delim +a.getStreet()+ delim +a.getZipCode()+ delim + a.getCity()); 
					//and ciphers it row by row
					printW.println(cipher(row,key));
				}
				printW.close();

			}
			System.out.println("Saved as " + fileName + ".txt");
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("No such file exists.");
		}
	}

	/**
	 * Implementation for ciphering a string Ceasar Cipher.
	 * @param  m The string to cipher.
	 * @param  s The key to cipher with.
	 * @return   The ciphered string.
	 */
	private String cipher(String m, int s){
		StringBuilder sb = new StringBuilder();
		//String str = "";
		int length = m.length();
		for(int i = 0; i < length; i++){
			char c = (char)(m.charAt(i) + s);
			sb.append(c);
		}
		
		return sb.toString();
	}

	// ---------------------------------------------- OPEN METHODS ---------------------------------------------- //

	/**
	 * Opens a previously saved file and encrypts it with a specified key.
	 * @param  personList  An already initiated PersonList
	 * @throws IOException If an I/O error occurs
	 */
	public void open(PersonList personList) throws IOException{
		String fileName = "";
		//asks the user for which file to open
		System.out.print("Name the file to open (without extension): ");
		fileName = input.readLine();
		int key;
		//and what key to decrypt it with
		System.out.print("Set decryption key: ");
		inputString = input.readLine();
		//makes sure that the key is a valid int
		key = makeInputValid(inputString);

		File textFile = new File(fileName + ".txt");

		if(textFile.exists()){
			Scanner inputFile = new Scanner(textFile);
			StringTokenizer tokenizer;
			String theLine;
			//ciphers the separator so that it can determine what to seperate by in the ciphered rows
			String theID = cipher("$", key);
			String name = "";
			String surname = "";
			String signature = "";
			int length = 0;
			String street = "";
			String zipCode = "";
			String city = "";
			Address a = new Address();
			Person p = new Person();

			//empties the array before adding new objects
			personList.wipeArray();

			try{
				//reads each row in the .txt and deciphers them
				while(inputFile.hasNext()){
					theLine = inputFile.nextLine();
					tokenizer = new StringTokenizer(theLine,theID);

					while(tokenizer.hasMoreTokens()){
						//deciphers each individual part
						name = decipher(tokenizer.nextToken().trim(), key);
						surname = decipher(tokenizer.nextToken().trim(), key);
						signature = decipher(tokenizer.nextToken().trim(), key);
						length = Integer.parseInt(decipher(tokenizer.nextToken().trim(), key));
						street = decipher(tokenizer.nextToken().trim(), key);
						zipCode = decipher(tokenizer.nextToken().trim(), key);
						city = decipher(tokenizer.nextToken().trim(), key);
					}

					//adds all the data to a person and then adds the person to the array
					Address adr = new Address(street,zipCode,city);
					Person pers = new Person(name, surname, length, adr);
					personList.addPersonToArray(pers);
					//sets the signature
					pers.setSignature(signature);

				}
				System.out.println("File succesfully opened.");
			} catch(Exception nse) {
				System.out.println("Wrong key, could not be deciphered correctly.");			
			}

		} else {
			System.out.println("File not found. Make sure that you wrote the correct filename without its extension.");
		}
	}

	/**
	 * Implementation used to deciphering a string using Ceasar Chipher.
	 * @param  m The string to decipher.
	 * @param  s The key to decipher with.
	 * @return   The deciphered string.
	 */
	private String decipher(String m, int s){
		StringBuilder sb = new StringBuilder();
		int length = m.length();
		for(int i = 0; i < length; i++){
			char c = (char)(m.charAt(i) - s);
			sb.append(c);
		}
		return sb.toString();
	}
	// ------------------------------------------- RANDOMIZING METHODS ------------------------------------------- //
	
	/**
	 * Randomizes the order of entities in a PersonList.
	 * @param personList The PersonList to randomize.
	 */
	public void randomize(PersonList personList){
		Collections.shuffle(personList.getArray());
		System.out.println("Array is now randomized.");
	}

	// ---------------------------------------------- SORT METHODS ---------------------------------------------- //

	/**
	 * Sorts a PersonList based on a specified attribute.
	 * @param  personList  The PersonList to sort.
	 * @throws IOException If an I/O error occurs.
	 */
	public void sort(PersonList personList) throws IOException{
		System.out.print("Sort based on... (name/surname/signature/length): ");
		inputString = input.readLine();
		Person p;

		//use of build in Collection.sort-methods to sort depending on the specified part
		switch (inputString) {
			case "name":
			//sorts in name
			Collections.sort(personList.getArray(), new Comparator<Person>(){
				public int compare(Person one, Person other) {
					return (one.getName().toLowerCase()).compareTo(other.getName().toLowerCase());
				}
			});
			System.out.println("Sorted by names in ascending order.");
			break;

			case "surname":
			//sorts on surname
			Collections.sort(personList.getArray(), new Comparator<Person>(){
				public int compare(Person one, Person other) {
					//compares last name in first hand, if they are identical->compare first name
					int sur = (one.getSurname().toLowerCase()).compareTo(other.getSurname().toLowerCase());
					int nam = (one.getName().toLowerCase()).compareTo(other.getName().toLowerCase());
					if(sur == 0){
						//surnames are identical
						return nam;
					} else 
						//surenames not identical
					return sur;
				}
			});
			System.out.println("Sorted by surnames in ascending order.");
			break;

			case "signature":

			Collections.sort(personList.getArray(), new Comparator<Person>(){
				public int compare(Person one, Person other) {
					return (one.getSignature()).compareTo(other.getSignature());
				}
			});
			System.out.println("Sorted by signatures in ascending order.");
			break;

			case "length":
			//sorts on length in descending order
			Collections.sort(personList.getArray(), new Comparator<Person>(){
				public int compare(Person one, Person other) {
					return ((Integer)other.getLength()).compareTo(one.getLength());
				}
			});
			System.out.println("Sorted by signatures in descending order.");
			break;

			default:
			System.out.println("Unknown commad, try again.");

		}	
	}

	// ----------------------------------- ADD METHODS ----------------------------------- //

	/**
	 * Adds a new Person to a PersonList with specified attributes. 
	 * @param  personList  The PersonList to add the Person to.
	 * @throws IOException If an I/O error occurs.
	 */
	public void add(PersonList personList) throws IOException{
		String name;
		String surname;
		int length = 0;
		String street;
		String zipCode;
		String city;

		//collects information from the user to create a Person
		System.out.print("Set persons name: ");
		name = input.readLine();

		System.out.print("Set persons surname: ");
		surname = input.readLine();


		System.out.print("Set persons length in cm: ");
		inputString = (input.readLine());
		//making sure that the input is a valid int
		length = makeInputValid(inputString);


		System.out.print("Set persons street: ");
		street = input.readLine();

		System.out.print("Set persons zip code: ");
		zipCode = input.readLine();

		System.out.print("Set persons city: ");
		city = input.readLine();

		boolean duplicateLoop = true;
		Address adr = new Address(street,zipCode,city);
		Person pers = new Person(name, surname, length, adr);

		do {
			//checks for duplicates in the array
			if (!duplicatePersonsInArray(pers, personList)){
				//no duplicates found, person can be added to the array
				personList.addPersonToArray(pers);
				//breaks out of the loop
				duplicateLoop = false;
			} else {
				//found a duplicate, let's user decide wether to remove the current Person or edit any info regarding him/her
				System.out.print("Person already exists (edit/remove): ");
				inputString = input.readLine();
				switch (inputString) {
					case "edit":
					pers = editedPerson(pers);
					break;

					case "remove":
					duplicateLoop = false;
					break;

					default:
					System.out.println("Unknown commad, try again.");
					break;
				}
			}
		}while(duplicateLoop);
	}

	//returns the edited Person with a new name, surname or length to test if she or he is unique now
	/**
	 * Edits a Person's attributes.
	 * @param  p           The Person to edit.
	 * @return             The edited Person.
	 * @throws IOException If an I/O error occurs.
	 */
	private Person editedPerson(Person p) throws IOException{
		System.out.print("Select which data to edit (name/surname/length): ");
		inputString = input.readLine();
		switch (inputString) {
			case "name":
			System.out.print("Set name: ");
			p.setName(input.readLine());
			break;

			case "surname":
			System.out.print("Set surname: ");
			p.setSurname(input.readLine());
			break;

			case "length":
			System.out.print("Set persons length in cm: ");
			p.setLength(makeInputValid(input.readLine()));
			break;

			default:
			System.out.println("Unknown commad, try again.");
			break;
		}
		return p;
	}

	//loops to check if the input is a valid int
	/**
	 * Loops to check if a given Person length input is valid.
	 * @param  s           The input string.
	 * @return             The length
	 * @throws IOException If an I/O error occurs.
	 */
	private int makeInputValid(String s) throws IOException{
		boolean validIntCheck = true;
		int length = 0;

		do{
			validIntCheck = true;
			try{
				//tries to parse the string as an int to see if it works
				length = Integer.parseInt(s);
			} 
			catch(NumberFormatException nfe){
				//exception caught, int was not valid, but the user can try again
				System.out.print("Not a valid input. Try again: ");				
				validIntCheck = false;
				s = input.readLine();
			}
		} while(!validIntCheck);

		return length;
	}

	//iterates through the array to see if any other person has the same name, surname and length
	/**
	 * Iterates trough an array to see if any other person has the same name, surname and length.
	 * @param  p          The Person to compare with.
	 * @param  personList The PersonList to search through.
	 * @return            If a duplicate was found.
	 */
	private boolean duplicatePersonsInArray(Person p, PersonList personList){
		boolean duplicate = false;

		//get data from the person we're adding
		String name = p.getName().toLowerCase();
		String surname = p.getSurname().toLowerCase();
		int length = p.getLength();

		for(int i=0;i < personList.getNumberOfPersons(); i++) {
			//get data from the person we're looking at in the array
			Person personFromArray = personList.getPersonAtIndex(i);
			//comparison is done without taking any consideration to upper-case letters
			String personFromArrayName = personFromArray.getName().toLowerCase();
			String personFromArraySurname = personFromArray.getSurname().toLowerCase();
			int personFromArrayLength = personFromArray.getLength();

			if(personFromArrayName.equals(name) && personFromArraySurname.equals(surname) && personFromArrayLength == length) {
				//duplicate found, Person is not unique
				duplicate = true;
			}
		}
		return duplicate;
	}

	// ----------------------------------------  REMOVE METHODS ---------------------------------------- //

	//removes person with a specific signature from the array
	/**
	 * Removes a person with a specified signature from the PersonList.
	 * @param  personList  The PersonList to search through.
	 * @throws IOException If an I/O error occurs.
	 */
	public void remove(PersonList personList) throws IOException{
		//make sure that the array is populated to being with
		if(personList.getNumberOfPersons() > 0){
			System.out.print("Type the signature of the person to remove: ");
			inputString = input.readLine();
			removeHelp(inputString, personList);
		} else {
			System.out.println("No persons to remove");
		}
	}

	/**
	 * Iterates through the PersonList to look for a person with the specified signature.
	 * @param s          The signature to look for.
	 * @param personList The PersonList to search through.
	 */
	public void removeHelp(String s, PersonList personList){
		boolean userFound = false;
		Person p;
		//loops through to see if any match is found
		for(int i=0; i < personList.getNumberOfPersons(); i++){
			p = personList.getPersonAtIndex(i);
			//checks if the signature we want to remove equals the signature for the Person at index i
			if(p.getSignature().equals(s)){
				//match found, object gets removed
				personList.removeObjectAtIndex(i);
				System.out.println("Person removed");
				userFound=true;
				break;
			} 
		} 
		//no such signature was found in the array
		if (!userFound) {
			System.out.println("Person not found.");
		}
	}	


	// ------------------------------------------ SEARCH  METHODS ------------------------------------------ //

	/**
	 * Searches for a Person with a specified signature.
	 * @param  personList  The PersonList to look through.
	 * @throws IOException If an I/O error occurs.
	 */
	public void search(PersonList personList) throws IOException{

		//makes sure the array is even populated to begin with
		if(personList.getNumberOfPersons() > 0){
			System.out.print("Type the signature to search for: ");
			inputString = input.readLine();
			searchHelp(inputString, personList);
		} else {
			System.out.println("No persons have been added yet.");
		}
	}

	/**
	 * Searches for a match in PersonList.
	 * @param s          The signature to search for.
	 * @param personList The PersonList to search through.
	 */
	private void searchHelp(String s, PersonList personList){
		Person p;
		boolean found = false;
		//loops through to see if any match is found
		for(int i=0; i < personList.getNumberOfPersons(); i++){
			//checks if the signature we are looking for equals the signature for the Person at index i
			p = personList.getPersonAtIndex(i);
			//match found, object gets printed
			if(p.getSignature().equals(s)){ 
				printOne(p);
				found = true;
				break;
			} 
		}
		if(!found) {
			//no such signature was found int he array
			System.out.println("Person not found.");
		}
	}


	// ------------------------------------------ PRINT METHODS  ------------------------------------------ //

	/**
	 * Prints one specified Person.
	 * @param p The Person's information to print.
	 */
	private void printOne(Person p){
		System.out.println("Person found:");
		//formatting the output
		System.out.printf("%-14s %-28s %-4s", "Sign", "Name", "Length\n");
		p.print();
	}

	/**
	 * Prints all Persons in the PersonList.
	 * @param  personList  The PersonList to print from.
	 * @throws IOException If an I/O error occurs.
	 */
	public void printAll(PersonList personList) throws IOException{
		int extra = 0;
		int a = 0;
		Person p;
		System.out.println("********** LIST **********");
		System.out.println("Number of persons in the list: " + personList.getNumberOfPersons());
		System.out.print("\n");

		if(personList.getNumberOfPersons() == 0){
			System.out.println("No one is in the list right now. Use 'add' to add someone.");
		} else {
			//formatting the output
			System.out.printf("%-4s %-14s %-28s %-4s", "Nr", "Sign", "Name", "Length [m]\n");
			for(int i = 0; i < personList.getNumberOfPersons(); i++){
				p = personList.getPersonAtIndex(i);
				System.out.printf((i+1) + ".   ");
			//prints information from the Person
				p.print();
			//prints 20 entries at the time, asks user to press any key to print the other next 20
				if((((i+1) % 20) == 0) && (i < personList.getNumberOfPersons()) && (i > 0)){
					System.out.println("\nPress any key to continiue.");
					String str = input.readLine();
				}
			}
		}
	}


	// ------------------------------------------------ MAIN ------------------------------------------------ //

	/**
	 * Main method, starts the program.
	 * @param  args        Arguments
	 * @throws IOException If an I/O error occurs.
	 */
	public static void main(String[] args) throws IOException {
		//an array is created to, later on, populate with Person-objects
		PersonList personList = new PersonList();
		Main m = new Main();
		//and off we go!
		m.start(personList);
	}
}