package iFace;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue
	protected Integer userId;

	protected String name;
	protected String login;
	protected String email;
	protected String password;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userReciever")
	protected List<Message> receivedMessages = new ArrayList<Message>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "members")
	protected List<Community> communities = new ArrayList<Community>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "owner")
	protected List<Community> managedCommunities = new ArrayList<Community>();

	@ManyToMany(fetch = FetchType.EAGER)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.DELETE})
	@JoinTable(name = "Friendship", joinColumns = @JoinColumn(name = "User1"), inverseJoinColumns = @JoinColumn(name = "User2"))
	protected List<User> friends = new ArrayList<User>();

	@ManyToMany(fetch = FetchType.EAGER)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.DELETE})
	@JoinTable(name = "FriendshipRequest", joinColumns = @JoinColumn(name = "UserRequested"), inverseJoinColumns = @JoinColumn(name = "User2"))
	protected List<User> friendRequest = new ArrayList<User>();

	public User() {

	}

	public User(int userId, String login, String password, List<Message> receivedMessages, List<Community> communities,
			List<Community> managedCommunities, List<User> friends, List<User> friendRequest) {
		super();
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.receivedMessages = receivedMessages;
		this.communities = communities;
		this.managedCommunities = managedCommunities;
		this.friends = friends;
		this.friendRequest = friendRequest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean addFriendRequest(User u) {
		return this.friendRequest.add(u);
	}

	public boolean addFriends(User u) {
		return this.friends.add(u);
	}

	public boolean removeFriendRequest(User u) {
		return this.friendRequest.remove(u);
	}

	public boolean removeFriends(User u) {
		return this.friends.remove(u);
	}

	public void printProfile() {
		System.out.println("NAME:" + this.name);
		System.out.println("USERNAME:" + this.login);
		System.out.println("EMAIL:" + this.email);
		System.out.println("ID:" + this.userId);
		System.out.println("\n");
	}

	public void printFriends() {
		System.out.println(friends.size() > 0 ? "FRIENDS:" : "NO FRIENDS");
		for (int i = 0; i < friends.size(); i++)
			System.out.println(friends.get(i).getName());
		System.out.println("-------------------------------------------------");
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<User> getFriendRequest() {
		return friendRequest;
	}

	public void setFriendRequest(List<User> friendRequest) {
		this.friendRequest = friendRequest;
	}

	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public List<Community> getCommunities() {
		return communities;
	}

	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}

	public List<Community> getManagedCommunities() {
		return managedCommunities;
	}

	public void setManagedCommunities(List<Community> managedCommunities) {
		this.managedCommunities = managedCommunities;
	}

	public void addReceivedMessage(Message message) {
		this.receivedMessages.add(message);
	}

	public void addManagedCommunities(Community community) {
		this.managedCommunities.add(community);
	}

	public void addCommunities(Community community) {
		this.communities.add(community);
	}

	public void removeCommunities(Community community) {
		this.communities.remove(community);
	}

	public void removeManagedCommunities(Community community) {
		this.managedCommunities.remove(community);
	}

	public void removeReceivedMessages(Message message) {
		this.receivedMessages.remove(message);
	}
}
