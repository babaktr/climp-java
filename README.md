# Command-line Information Manager Prompt
Command-line Information Managing Prompt (CLIMP) was created as a part of the Java course **Computer Engineering BA (A), Java I**, (7.5 Credits), given by the Mid Sweden University. The project part of the course (2.5 Credits), in which CLIMP was written, ran from early March to late April.

All implementation was written from scratch and no frameworks were used.


## Information Management
CLIMP is a command-line interface where you can manage personal information. All the information from each run can be stored onto or loaded from a .txt file to simplify continuos use.
To keep it safe, all the saved information is also encrypted with a key of your choice and can only be decrypted with the same key.

### Information
All the personal information is structured as follows:

```no-highlight
Person
——————————
signature
name
surname  
length  
Address
```

```no-highlight
Address
——————————
street
zipcode  
city  
```

### Prompt
You can choose from the following commands in CLIMP.
```no-highlight
  	add				Add a person
   	print			Print everyone
   	search 			Search for a person
   	remove 			Remove a person
   	sort			Sort based on attribute
   	randomize 		Randomize the order
   	save 			Save to a textile
   	open 			Open textfile
   	quit 			Quit program
```


## Encryption

CLIMP uses [Ceasar Cipher](https://en.wikipedia.org/wiki/Caesar_cipher “Wikipedia on Ceasar Cipher”) to encrypt and decrypt any stored information.

Just like the rest of the code, the implementation of Ceasar Cipher was also written from scratch.

# Getting started

In your **shell**, browse to the **climp-java** folder and type:
```
$ javac *.java
```
Then run the program by typing:
```
$ java Main
```

Happy CLIMPing!