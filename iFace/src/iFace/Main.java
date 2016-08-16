package iFace;

import java.util.InputMismatchException;
import java.util.Scanner;
import database.UserManager;
import exceptionsFile.*;

public class Main {

	private static Scanner input;
	private static Scanner scan;

	public static void main(String[] args) {
		try {
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
				try {
					// first menu
					System.out.print("Welcome to iFace!: ");
					System.out.print("1. Login |");
					System.out.print(" 2. Register now |");
					System.out.print(" 3. Close iFace\n");
					System.out.print("-------------------------------------------------");
					input = new Scanner(System.in);
					firstChoice = input.nextInt();
					input.nextLine();

				} catch (InputMismatchException e) {
					System.err.println("You shoud've typed a number\n");
				}

				try {
					// login
					if (firstChoice == 1) {
						try {
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
							}
						} catch (InvalidDataException e) {
							System.err.println(e);
							log = 0;
						}

						while (secondChoice != 6 && log == 1) {
							try {
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

							} catch (InputMismatchException e) {
								System.err.println("You shoud've typed a number\n");
							}

							// show profile
							if (secondChoice == 1) {
								while (thirdChoice != 2) {
									try {
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

									} catch (InputMismatchException e) {
										System.err.println("You shoud've typed a number\n");
									}

									// edit profile
									if (thirdChoice == 1) {
										try {
											User userprofile = uManager.getUserById(uId);

											System.out.print("NAME: ");
											userprofile.setName(input.nextLine());
											System.out.print("USERNAME: ");
											userprofile.setLogin(input.nextLine());
											System.out.print("EMAIL: ");
											userprofile.setEmail(input.nextLine());

											uManager.verifyUserEmptyField(userprofile);
											uManager.updateInstance(userprofile);

										} catch (EmptyFieldException e) {
											System.err.println(e);
										}
									}
								}
							}
							// show communities
							else if (secondChoice == 2) {
								fifthChoice = 0;
								String content;
								User userCommunities = uManager.getUserById(uId);

								while (fifthChoice != 4) {
									try {
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

									} catch (InputMismatchException e) {
										System.err.println("You shoud've typed a number\n");
									}
									
									// create a community
									if (fifthChoice == 1) {
										try {
											System.out.print("iFace: ");
											System.out.println(" Creating a Community");

											Community community = new Community();

											System.out.print("NAME: ");
											community.setName(input.nextLine());
											System.out.print("DESCRIPTION: ");
											community.setInfo(input.nextLine());

											community.setOwner(uManager.getUserById(uId));
											uManager.verifyCommunityEmptyField(community);
											uManager.createCommunity(community);

											// associates the community with the
											// user
											community.addMember(userCommunities);
											userCommunities.addCommunities(community);
											userCommunities.addManagedCommunities(community);

											uManager.updateInstance(userCommunities);

											System.out.println("Community created successfully!\n");
										} catch (EmptyFieldException e) {
											System.err.println(e);
										}
										
									// send message to a community
									} else if (fifthChoice == 2) {
										try {
											uManager.printCommunityByUser(userCommunities);
											System.out.print("Id of the Community you want to send a message: ");

											input = new Scanner(System.in);
											int communityId = input.nextInt();
											input.nextLine();

											uManager.getCommunityById(communityId);
											System.out.print("Content of the message: ");
											content = input.nextLine();

											Message msg = new Message();
											msg.setContent(content);
											msg.setUserReciever(userCommunities);
											msg.setUserSender(userCommunities);
											uManager.verifyMessageEmptyField(msg);
											uManager.verifyCommunityMessage(userCommunities, communityId);
											
											uManager.createMessage(msg);
											System.out.println("Message sent successfully!\n");

										} catch (InputMismatchException e) {
											System.err.println("You shoud've typed a number\n");
										} catch (CommunityNotFoundException e) {
											System.err.println(e);
										} catch (EmptyFieldException e){
											System.err.println(e);
										} catch (NotAMemberException e){
											System.err.println(e);
										}
								    // join a community
									} else if (fifthChoice == 3) {
										try {
											System.out.print("Type the name of the community you want to join: ");

											input = new Scanner(System.in);
											String communityName = input.nextLine();

											Community c = uManager.getCommunityByName(communityName);
											
											uManager.printCommunity(c);
											
											System.out.print("Do you want to join this community: 1. Yes | 2. No ");
											
											input = new Scanner(System.in);
											int accept = input.nextInt();
											input.nextLine();
											
											if (accept == 1){
												uManager.addCommunities(userCommunities, uId, c.getId());
												
												userCommunities.addCommunities(c);
												c.addMember(userCommunities);
											} else if (accept == 2){
												break;
											}

										} catch (InputMismatchException e) {
											System.err.println("You shoud've typed a number\n");
										} catch (CommunityNotFoundException e) {
											System.err.println(e);
										} catch (EmptyFieldException e){
											System.err.println(e);
										} catch (AlreadyAMemberException e){
											System.err.println(e);
										}
									// return menu
									} else if (fifthChoice == 4) {
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

									try {
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
												u2.addFriends(userFriends);
												userFriends.addFriends(u2);
												uManager.updateInstance(u2);

												System.out.println("Friend added successfully!");
												System.out.println("-------------------------------------------------");
												// clear the friendRequest
												// list
												userFriends.friendRequest.clear();
												uManager.updateInstance(userFriends);
											}
										}
									} catch (InputMismatchException e) {
										System.err.println("You shoud've typed a number\n");
									}
								}

								if (userFriends.friends.size() == 0) {
									System.out.println("You have no friends.");

									while (fourthChoice != 2) {
										System.out.print("Do you want to add friends now?");
										System.out.print(" 1. Yes | 2. No\n");
										System.out.println("-------------------------------------------------");

										try {
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

												try {
													uManager.printUserProfile(uManager.getFriendId(friendName));

													System.out.println("Write the id of your friend to add him/her: ");

													try {
														input = new Scanner(System.in);
														friend = input.nextInt();
														input.nextLine();

													} catch (InputMismatchException e) {
														System.err.println("You shoud've typed a number\n");
													}
													try {
														User u = uManager.getUserById(friend);

														uManager.addFriend(u, friend, uId);
														u.addFriendRequest(userFriends);
														uManager.updateInstance(u);
														uManager.updateInstance(userFriends);

														System.out.println("Friend request made successfully!");
														System.out.println(
																"-------------------------------------------------");

													} catch (CantAddYourselfException e) {
														System.err.println(e);
													} catch (AlreadySentRequestException e) {
														System.err.println(e);
													} catch (AlreadyFriendException e) {
														System.err.println(e);
													}

												} catch (UserNotFoundException e) {
													System.err.println(e);
												}
											}

										} catch (InputMismatchException e) {
											System.err.println("You shoud've typed a number\n");
										}

									}
								} else {
									uManager.printUserFriends(userFriends);

									while (fourthChoice != 2) {
										System.out.print("Do you want to add friends now?");
										System.out.print(" 1. Yes | 2. No\n");
										System.out.println("-------------------------------------------------");

										try {
											input = new Scanner(System.in);
											fourthChoice = input.nextInt();
											input.nextLine();

											if (fourthChoice == 1) {
												try {
													int friend = 0;
													String friendName;

													System.out.print("iFace: ");
													System.out.println(" Find Friends \n");
													System.out.println("Write the username of your friend: ");

													friendName = input.nextLine();
													uManager.printUserProfile(uManager.getFriendId(friendName));

													System.out.println("Write the id of your friend to add him/her: ");

													try {
														input = new Scanner(System.in);
														friend = input.nextInt();
														input.nextLine();

													} catch (InputMismatchException e) {
														System.err.println("You shoud've typed a number\n");
													}
													try {
														User u = uManager.getUserById(friend);

														uManager.addFriend(u, friend, uId);
														u.addFriendRequest(userFriends);
														uManager.updateInstance(u);
														uManager.updateInstance(userFriends);

														System.out.println("Friend request made successfully!");
														System.out.println(
																"-------------------------------------------------");

													} catch (CantAddYourselfException e) {
														System.err.println(e);
													} catch (AlreadySentRequestException e) {
														System.err.println(e);
													} catch (AlreadyFriendException e) {
														System.err.println(e);
													}

												} catch (UserNotFoundException e) {
													System.err.println(e);
												}
											}

										} catch (InputMismatchException e) {
											System.err.println("You shoud've typed a number\n");
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
									try {
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

											uManager.verifyFriendship(friend, uId);
											System.out.print("Content of the message: ");
											content = input.nextLine();

											Message msg = new Message();
											msg.setContent(content);
											msg.setUserReciever(uManager.getUserById(friend));
											msg.setUserSender(userMessages);

											uManager.verifyMessageEmptyField(msg);

											uManager.createMessage(msg);

											System.out.println("Message sent successfully!\n");
										}

									} catch (InputMismatchException e) {
										System.err.println("You shoud've typed a number\n");
									} catch (EmptyFieldException e) {
										System.err.println(e);
									} catch (NotYourFriendException e) {
										System.err.println(e);
									}

								}
							}
							// remove account
							else if (secondChoice == 5) {
								System.err.println("We're sorry. You can't delete yourself in this version of iFace.");
								log = 0;
								break;
							}
							// log out
							else if (secondChoice == 6) {
								log = 0;
								System.out.println("You are now logged out!");
								System.out.println("-------------------------------------------------");
							}
						}
					} // register a new user
					else if (firstChoice == 2) {
						try {
							scan = new Scanner(System.in);

							User user = new User();

							System.out.print("NAME: ");
							user.setName(input.nextLine());
							System.out.print("USERNAME: ");
							user.setLogin(input.nextLine());
							System.out.print("EMAIL: ");
							user.setEmail(input.nextLine());
							System.out.print("PASSWORD: ");
							user.setPassword(input.nextLine());

							uManager.verifyUserEmptyField(user);

							uManager.verifyUserName(user.getLogin());
							uManager.addUser(user);
							uId = user.getUserId();

							System.out.println("You are now registered!");
							System.out.println("-------------------------------------------------");

						} catch (UsernameAlreadyExistsException e) {
							System.err.println(e);
						} catch (EmptyFieldException e) {
							System.err.println(e);
						}
					}
					// closing the system
					else if (firstChoice == 3) {
						System.err.println("Bye! See you soon!");
						System.err.println("-------------------------------------------------");
					}
				} catch (Exception e) {
					System.err.println("Ops, something is wrong.\n We're working to fix that for you. \n");
				}
			}

		} catch (InputMismatchException e) {
			System.err.print("You should've typed a number.");
		}
	}
}