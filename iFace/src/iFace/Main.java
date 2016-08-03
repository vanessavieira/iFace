package iFace;

import java.util.Scanner;

public class Main {

	private static Scanner input;
	private static Scanner scan;

	public static void main(String[] args) {
		int firstChoice = 0;
		int secondChoice = 0;
		int thirdChoice = 0;
		int fourthChoice = 0;
		int log = 0;
		int uId = 0;

		// creating the manager system of iFace
		UserManager uManager = new UserManager();

		while (firstChoice != 4) {
			// first menu
			System.out.print("Welcome to iFace!: ");
			System.out.print("1. Login |");
			System.out.print(" 2. Register now |");
			System.out.print(" 3. Show activities |");
			System.out.print(" 4. Close iFace\n");
			System.out.println("-------------------------------------------------");

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

				if (uManager.loginCheck(user, pass) != -1) {
					System.out.println("You are now logged in!");
					System.out.println("-------------------------------------------------");
					uId = uManager.loginCheck(user, pass);
					log = 1;
				} else {
					System.err.println("Username or password incorrect");
					System.out.println("-------------------------------------------------");
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
					System.out.println("-------------------------------------------------");

					input = new Scanner(System.in);
					secondChoice = input.nextInt();
					input.nextLine();

					// show profile
					if (secondChoice == 1) {
						while (thirdChoice != 2) {
							System.out.print("iFace: ");
							System.out.println(" User Profile \n");

							// prints user
							uManager.printUserProfile(uId);

							System.out.print("\nDo you want to edit your info?");
							System.out.println(" 1. Yes | 2. No");
							System.out.println("-------------------------------------------------");

							input = new Scanner(System.in);
							thirdChoice = input.nextInt();
							input.nextLine();

							// edit profile
							if (thirdChoice == 1) {
								User userprofile = uManager.getUserById(uId);

								System.out.println("\nNAME:");
								userprofile.setName(input.nextLine());

								System.out.println("USERNAME:");
								userprofile.setLogin(input.nextLine());

								System.out.println("EMAIL:");
								userprofile.setEmail(input.nextLine());

								uManager.updateInstance(userprofile);
							}
						}

					}
					// show communities
					else if (secondChoice == 2) {

					}
					// show friends
					else if (secondChoice == 3) {
						User userFriends = uManager.getUserById(uId);
						fourthChoice = 0;

						System.out.print("iFace: ");
						System.out.println(" User Friends \n");

						if (userFriends.friends.size() == 0) {
							System.out.println("You have no friends. \n");

							while (fourthChoice != 2) {
								System.out.print("Do you want to add friends now?");
								System.out.print(" 1. Yes | 2. No\n");
								System.out.println("-------------------------------------------------");

								input = new Scanner(System.in);
								fourthChoice = input.nextInt();
								input.nextLine();

								if (fourthChoice == 1) {
									int friend = 0;
									String friendName;
									User userFriend = new User();

									System.out.print("iFace: ");
									System.out.println(" Find Friends \n");
									System.out.println("Write the username of your friend:\n");

									friendName = input.nextLine();

									if (uManager.getFriendId(friendName) != -1) {
										uManager.printUserProfile(uManager.getFriendId(friendName));

										System.out.println("\nWrite the id of your friend to add him/her: ");
										System.out.println("\nYOUR FRIEND'S ID:");

										input = new Scanner(System.in);
										friend = input.nextInt();
										input.nextLine();

										// de fato adicionar o amigo no array
										// dos coisa la

										System.out.println("Friend added successfully!");
										System.out.println("-------------------------------------------------");
									} else {
										System.out.println("This user was not found");
										System.out.println("-------------------------------------------------");
									}

								}
							}

						} else {
							uManager.printUserFriends(userFriends);
							
							while (fourthChoice != 2) {
								System.out.print("Do you want to add friends now?");
								System.out.print(" 1. Yes | 2. No\n");
								System.out.println("-------------------------------------------------");

								input = new Scanner(System.in);
								fourthChoice = input.nextInt();
								input.nextLine();

								if (fourthChoice == 1) {
									int friend = 0;
									String friendName;
									User userFriend = new User();

									System.out.print("iFace: ");
									System.out.println(" Find Friends \n");
									System.out.println("Write the username of your friend:\n");

									friendName = input.nextLine();

									if (uManager.getFriendId(friendName) != -1) {
										uManager.printUserProfile(uManager.getFriendId(friendName));

										System.out.println("\nWrite the id of your friend to add him/her: ");
										System.out.println("\nYOUR FRIEND'S ID:");

										input = new Scanner(System.in);
										friend = input.nextInt();
										input.nextLine();
										System.out.println("\nworking");

										// de fato adicionar o amigo no array
										// dos coisa la

										System.out.println("Friend added successfully!");
										System.out.println("-------------------------------------------------");
									} else {
										System.out.println("This user was not found");
										System.out.println("-------------------------------------------------");
									}

								}
							}
							
						}
					}
					// show messages
					else if (secondChoice == 4) {

					}
					// remove account
					else if (secondChoice == 5) {
						System.out.println("You have removed your account!");
						System.out.println("-------------------------------------------------");

						User userremove = uManager.getUserById(uId);
						uManager.deleteUser(userremove);

						break;
					}
					// log out
					else if (secondChoice == 6) {
						log = 0;
						System.out.println("You are now logged out!");
						System.out.println("-------------------------------------------------");
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

				System.out.println("You are now registered!");
				System.out.println("-------------------------------------------------");

				uManager.addUser(user);
				uId = user.getUserId();

			}
			// print everything that happened in the system
			else if (firstChoice == 3) {
			}
			// closing the system
			else if (firstChoice == 4) {
				System.err.println("Bye! See you soon!");
				System.err.println("-------------------------------------------------");

			}
		}
	}
}
