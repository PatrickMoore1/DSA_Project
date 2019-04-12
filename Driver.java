/*
 * Purpose: Data Structure and Algorithms Final Project
 * Status: Incomplete
 * Last update: 04/12/19
 * Submitted:  04/12/19
 * @author: Patrick Moore, Kevin Nguyen
 * @version: 2019.04.12
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {

	static BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException{

		System.out.println("Welcome to the Wonderful Movie Theater program!");
		System.out.println("	Tonight's features are:");
		System.out.println("		'Shazam!' and 'Dumbo\n'");
		
		System.out.println("Please specify the size of the Movie Theaters");
		
			System.out.println("	Enter the information about the Dumbo Movie Theater:");		
			System.out.print("		>>Enter number of rows: ");
				int dumboRows = Integer.parseInt(stdin.readLine());
				System.out.println(dumboRows);   
			System.out.print("		>>Enter number of seats in a row: ");
				int dumboSeats = Integer.parseInt(stdin.readLine());
				System.out.println(dumboSeats);   
				
			System.out.println("	Enter the information about the Shazam! Movie Theater:");		
			System.out.print("		>>Enter number of rows: ");
				int shazamRows = Integer.parseInt(stdin.readLine());
				System.out.println(shazamRows);   
			System.out.print("		>>Enter number of seats in a row: ");
				int shazamSeats = Integer.parseInt(stdin.readLine());
				System.out.println(shazamSeats + "\n");   
				
			System.out.print("		>>Enter the price of a ticket: ");
				double ticketPrice = Double.parseDouble(stdin.readLine());
				System.out.println(ticketPrice + "\n");
		
		Theater dumbo = new Theater(dumboRows, dumboSeats);
		Theater shazam = new Theater(shazamRows, shazamSeats);
		
		MovieTheater movieTheater = new MovieTheater(ticketPrice, dumbo, shazam);

		System.out.println("Select from the following menu: ");
		System.out.println("		0. End the program.");
		System.out.println("		1. Customer(s) enter(s) Movie Theater.");
		System.out.println("		2. Customer buys ticket(s).");
		System.out.println("		3. Customer(s) leave(s) the theater.");
		System.out.println("		4. Display info about customers waiting for tickets.");
		System.out.println("		5. Display seating chart for Shazam! Movie Theater.");
		System.out.println("		6. Display seating chart for Dumbo Movie Theater.");
		System.out.println("		7. Display number of tickets sold and total earnings.");

		selection();
	}

	public static void selection() throws NumberFormatException, IOException {

		System.out.println(" ");
		System.out.print(">>Make your menu selection now: ");
		int choice = Integer.parseInt(stdin.readLine());

		switch(choice)
		{
		case 0 :
			System.out.println(choice);   
			System.out.print("Exiting program...Good Bye");

			break;		
		case 1 :
			System.out.println(choice);
			
			System.out.print("		>>Enter number of rows: ");
			int shazamRows = Integer.parseInt(stdin.readLine());
			System.out.println(shazamRows); 

			selection();
			break;
		case 2 :
			System.out.println(choice);

			selection();
			break;
		case 3 :
			System.out.println(choice);

			selection();
			break;
		case 4 :
			System.out.println(choice);

			selection();
			break;
		case 5 :
			System.out.println(choice);

			selection();				
			break;
		case 6 :
			System.out.println(choice);

			selection();
			break;
		case 7 :
			System.out.println(choice);

			selection();
		}
	}	

}

