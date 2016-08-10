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
		int fifthChoice = 0;
		int log = 0;
		int uId = 0;

		// creating the manager system of iFace
		UserManager uManager = new UserManager();

		while (firstChoice != 3) {
			// first menu
			System.out.print("Welcome to iFace!: ");
			System.out.print("1. Login |");
			System.out.print(" 2. Register now |");
			System.out.print(" 3. Close iFace\n");
			System.out.print("-------------------------------------------------");

			input = new Scanner(System.in);
			firstChoice = input.nextInt();
			input.nextLine();

			// login
			if (firstChoice == 1) {
				secondChoice = 0;
				thirdChoice = 0;
				String user;
				String pass;

				System.out.print("USERNAME: ");
				user = input.nextLine();
				System.out.print("PASSWORD: ");
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
					System.out.print("-------------------------------------------------");

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

								System.out.print("NAME: ");
								userprofile.setName(input.nextLine());

								System.out.print("USERNAME: ");
								userprofile.setLogin(input.nextLine());

								System.out.print("EMAIL: ");
								userprofile.setEmail(input.nextLine());

								uManager.updateInstance(userprofile);
							}
						}
					}
					// show communities
					else if (secondChoice == 2) {
						int accept = 0;
						fifthChoice = 0;
						String content;
						User userCommunities = uManager.getUserById(uId);
			
						while (fifthChoice != 4){
							System.out.print("iFace: ");
							System.out.println(" User Communities ");
							
							if (userCommunities.getCommunities().size() == 0) {
								System.out.println("You have no communities.");
							} else {
								System.out.println("Here is/are your communities:\n");

								uManager.printCommunityByUser(userCommunities);
							}
							
							System.out.print("1. Create a Community |");
							System.out.print(" 2. Send a Message to a Community |");
							System.out.print(" 3. Join a Community |");
							System.out.print(" 4. Return\n");
							System.out.print("-------------------------------------------------");
							
							input = new Scanner(System.in);
							fifthChoice = input.nextInt();
							input.nextLine();
							
							if (fifthChoice == 1){
								System.out.print("iFace: ");
								System.out.println(" Creating a Community");
								
								Community community = new Community();

								System.out.print("NAME: ");
								community.setName(input.nextLine());

								System.out.print("DESCRIPTION: ");
								community.setInfo(input.nextLine());
								
								community.setOwner(uManager.getUserById(uId));
								
								// associates the community with the user
								userCommunities.addCommunities(community);		
								userCommunities.addManagedCommunities(community);
								
								uManager.createCommunity(community);
								
								System.out.println("Community created successfully!\n");
							}
							else if (fifthChoice == 2){
								uManager.printCommunityByUser(userCommunities);
								System.out.print("Id of the Community you want to send a message: ");

								input = new Scanner(System.in);
								int communityId = input.nextInt();
								input.nextLine();

								System.out.print("Content of the message: ");
								content = input.nextLine();

								Message msg = new Message();
								msg.setContent(content);
								msg.setUserReciever(userCommunities);
								msg.setUserSender(userCommunities);

								uManager.createMessage(msg);
								System.out.println("Message sent successfully!\n");
							}
							else if (fifthChoice == 3){
								// it should print all communities and the user should choose one to be in
							}
							else if (fifthChoice == 4){
								break;
							}
						}
					}
					// show friends
					else if (secondChoice == 3) {
						User userFriends = uManager.getUserById(uId);
						fourthChoice = 0;
						int accept = 0;

						System.out.print("iFace: ");
						System.out.println(" User Friends \n");

						if (userFriends.friendRequest.size() == 0) {
							System.out.println("You have no new friend requests.");
						} else {
							System.out.println("-------------------------------------------------");
							System.out.print("iFace: ");
							System.out.println(" User Friends \n");
							System.out.println("Here is your friend request:");

							for (User u2 : userFriends.getFriendRequest()) {
								System.out.print("NAME: ");
								System.out.println(u2.getName());
								System.out.print("USERNAME: ");
								System.out.println(u2.getLogin());
								System.out.print("ID: ");
								System.out.println(u2.getUserId());

								System.out.println("Do you want to add this friend?  1. Yes | 2. No");

								input = new Scanner(System.in);
								accept = input.nextInt();
								input.nextLine();

								if (accept == 1) {

									if (uManager.verifyFriendship(u2, uId) == 0) {
										u2.addFriends(userFriends);
										userFriends.addFriends(u2);

										uManager.addFriend(u2);
										uManager.addFriend(userFriends);

										System.out.println("Friend added successfully!");
										System.out.println("-------------------------------------------------");
									}
								}
							}

							// clear the friendRequest list
							userFriends.friendRequest.clear();
							uManager.updateInstance(userFriends);

						}

						if (userFriends.friends.size() == 0) {
							System.out.println("You have no friends.");

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

									System.out.print("iFace: ");
									System.out.println(" Find Friends \n");
									System.out.println("Write the username of your friend: ");

									friendName = input.nextLine();

									if (uManager.getFriendId(friendName) != -1) {
										uManager.printUserProfile(uManager.getFriendId(friendName));

										System.out.println("Write the id of your friend to add him/her: ");

										input = new Scanner(System.in);
										friend = input.nextInt();
										input.nextLine();

										User u = uManager.getUserById(friend);

										if (uManager.verifyFriendshipRequest(u, uId) == 0
												&& uManager.verifyFriendship(u, uId) == 0) {
											// adds to the friendRequest list of
											// the
											// person you want to add
											u.addFriendRequest(userFriends);

											// adds to the data base
											uManager.addFriend(u);

											System.out.println("Friend request made successfully!");
											System.out.println("-------------------------------------------------");
										}

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

									System.out.print("iFace: ");
									System.out.println(" Find Friends \n");
									System.out.println("Write the username of your friend:");

									friendName = input.nextLine();

									if (uManager.getFriendId(friendName) != -1) {
										uManager.printUserProfile(uManager.getFriendId(friendName));

										System.out.println("Write the id of your friend to add him/her: ");

										input = new Scanner(System.in);
										friend = input.nextInt();
										input.nextLine();

										User u = uManager.getUserById(friend);

										if (uManager.verifyFriendshipRequest(u, uId) == 0
												&& uManager.verifyFriendship(u, uId) == 0) {
											// adds to the friendRequest list of
											// the
											// person you want to add
											u.addFriendRequest(userFriends);

											// adds to the data base
											uManager.addFriend(u);

											System.out.println("Friend request made successfully!");
											System.out.println("-------------------------------------------------");
										}
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
						int accept = 0;
						int friend = 0;
						String content;
						User userMessages = uManager.getUserById(uId);

						System.out.print("iFace: ");
						System.out.println(" User Messages \n");

						if (userMessages.receivedMessages.size() == 0) {
							System.out.println("You have no new messages.");
						} else {
							System.out.println("Here is/are your messages:\n");

							uManager.printMessagesByUser(userMessages);
						}

						while (accept != 2) {
							System.out.println("Do you want to send a new message? 1. Yes | 2. No");

							input = new Scanner(System.in);
							accept = input.nextInt();
							input.nextLine();

							if (accept == 1) {
								uManager.printUserFriends(userMessages);
								System.out.print("Id of the friend you want to send a message: ");

								input = new Scanner(System.in);
								friend = input.nextInt();
								input.nextLine();

								System.out.print("Content of the message: ");
								content = input.nextLine();

								Message msg = new Message();
								msg.setContent(content);
								msg.setUserReciever(uManager.getUserById(friend));
								msg.setUserSender(userMessages);

								uManager.createMessage(msg);

								System.out.println("Message sent successfully!\n");
							}
						}
					}
					// remove account
					else if (secondChoice == 5) {
						System.out.println("You have removed your account!");
						System.out.println("-------------------------------------------------");

						User userremove = uManager.getUserById(uId);

						// userremove =
						// uManager.deleteUserRelations(userremove);
						// uManager.updateInstance(userremove);
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

				User user = new User();

				System.out.print("\nNAME: ");
				user.setName(input.nextLine());

				System.out.print("USERNAME: ");
				user.setLogin(input.nextLine());

				System.out.print("EMAIL: ");
				user.setEmail(input.nextLine());

				System.out.print("PASSWORD: ");
				user.setPassword(input.nextLine());

				if (uManager.verifyUserName(user.getLogin()) == 0) {

					System.out.println("You are now registered!");
					System.out.println("-------------------------------------------------");

					uManager.addUser(user);
					uId = user.getUserId();
				}

			}

			// closing the system
			else if (firstChoice == 3) {
				System.err.println("Bye! See you soon!");
				System.err.println("-------------------------------------------------");
			}
		}
	}
}
