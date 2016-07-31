package iFace;

import java.util.Scanner;
import iFace.Management;
import iFace.User;

public class Main {

	private static Scanner input;
	private static Scanner scan;

	public static void main(String[] args) {
		int firstChoice = 0;
		int secondChoice = 0;
		int thirdChoice = 0;
		int log = 0;
		int uId = 0;
		// String profile = null;

		Management manager = new Management();

		while (firstChoice != 4) {
			// first menu
			System.out.print("Welcome to iFace!: ");
			System.out.print("1. Login |");
			System.out.print(" 2. Register now |");
			System.out.print(" 3. Show activities |");
			System.out.print(" 4. Close iFace\n");

			input = new Scanner(System.in);
			firstChoice = input.nextInt();
			input.nextLine();

			// login
			if (firstChoice == 1) {
				secondChoice = 0;
				thirdChoice = 0;
				String user;
				String pass;

				System.out.println("\nUSERNAME:");
				user = input.nextLine();
				System.out.println("PASSWORD:");
				pass = input.nextLine();

				if (manager.loginCheck(user, pass) != -1) {
					System.out.println("You are now logged in!\n");
					uId = manager.loginCheck(user, pass);
					log = 1;
				} else {
					System.err.println("Username or password incorrect\n");
					log = 0;
				}

				while (secondChoice != 6 && log == 1) {
					System.out.print("iFace: ");
					System.out.print("1. Profile |");
					System.out.print(" 2. Communities |");
					System.out.print(" 3. Friends |");
					System.out.print(" 4. Messages |");
					System.out.print(" 5. Remove account |");
					System.out.print(" 6. Log out\n");

					input = new Scanner(System.in);
					secondChoice = input.nextInt();
					input.nextLine();

					// show profile
					if (secondChoice == 1) {
						
						while (thirdChoice != 2) {
							System.out.print("iFace: ");
							System.out.println(" User Profile ");
							Management.getInstanceOf().printUserById(uId);
							System.out.println("\nDo you want to edit your info?");
							System.out.println(" 1. Yes | 2. No");
							
							input = new Scanner(System.in);
							thirdChoice = input.nextInt();
							input.nextLine();		
							
							// edit profile
							if (thirdChoice == 1){
								System.out.println("heo");
							}			
						}

					}
					// show communities
					else if (secondChoice == 2) {

					}
					// show friends
					else if (secondChoice == 3) {

					}
					// show messages
					else if (secondChoice == 4) {

					}
					// remove account
					else if (secondChoice == 5) {
						manager.removeUser(uId);
						System.out.println("You have removed your account!\n");
						break;
					}
					// log out
					else if (secondChoice == 6) {
						log = 0;
						System.out.println("You are now logged out!\n");
					}

				}

			}
			// register a new user
			else if (firstChoice == 2) {
				scan = new Scanner(System.in);

				User user = new User(null, null, null, null);

				System.out.println("\nNAME:");
				user.setName(input.nextLine());

				System.out.println("USERNAME:");
				user.setLogin(input.nextLine());

				System.out.println("EMAIL:");
				user.setEmail(input.nextLine());

				System.out.println("PASSWORD:");
				user.setPassword(input.nextLine());

				System.out.println("You are now registered!\n");

				uId = user.getId();
				// profile = user.getProfile();
				manager.addUser(user);

			}
			// print of the system
			else if (firstChoice == 3) {
				Management.getInstanceOf().print();
			}
			// closing the system
			else if (firstChoice == 4) {
				System.err.println("Bye! See you soon!\n");

			}
		}
	}

}
