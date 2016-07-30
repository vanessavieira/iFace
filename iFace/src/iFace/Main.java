package iFace;
import java.util.ArrayList;
import java.util.Scanner;

import iFace.User;

public class Main {
	
	private static Scanner input;
	public static void main(String[] args) {	
		int firstChoice = 0;
		ArrayList<User> listUsers = new ArrayList<User>();
		
		while (firstChoice != 3){
			//first menu
			System.out.print("Welcome to iFace!: ");
			System.out.print("1. Login |");
			System.out.print(" 2. Register now |");
			System.out.print(" 3. Close iFace");
	
			input = new Scanner(System.in);
			firstChoice = input.nextInt();
	
			input.nextLine();
			
			//login
			if (firstChoice == 1){
			
			}
			//register a new user
			else if (firstChoice == 2){
				Scanner scan = new Scanner(System.in);

				User user = new User();

				System.out.println("Name:");
				user.setName(input.nextLine());
				
				System.out.println("User name:");
				user.setLogin(input.nextLine());

				System.out.println("Email:");
				user.setEmail(input.nextLine());
				
				System.out.println("Password:");
				user.setPassword(input.nextLine());
				
				listUsers.add(user);
			}
		}
	}		

}
