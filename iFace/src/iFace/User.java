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
import javax.persistence.OneToOne;

@Entity
public class User {
	@Id
	@GeneratedValue
	protected Integer userId;

	protected String name;
	protected String login;
	protected String email;
	protected String password;
	
	
	//UserManager uManager = new UserManager();

	// protected ArrayList<String> messages = new ArrayList<String>();
	// protected ArrayList<Integer> communities = new ArrayList<Integer>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Friendship", joinColumns = @JoinColumn(name = "User1"), inverseJoinColumns = @JoinColumn(name = "User2"))
	protected List<User> friends = new ArrayList<User>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "FriendshipRequest", joinColumns = @JoinColumn(name = "UserRequested"), inverseJoinColumns = @JoinColumn(name = "User2"))
	protected List<User> friendRequest = new ArrayList<User>();

	public User() {

	}

	public User(String name, String login, String password, String email) {
		this.name = name;
		this.login = login;
		this.password = password;
		this.email = email;
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
	
	public boolean addFriends(User u){
		return this.friends.add(u);
	}

	// public User getFriendById(int fId) {
	// User u = null;
	// for (int i = 0; i < friends.size(); i++) {
	// if (friends.get(i).getId() == fId)
	// u = friends.get(i);
	// }
	// return u;
	// }
	//
	// public Community getCommunityById(int cId) {
	// Community c = null;
	// for (int i = 0; i < communities.size(); i++) {
	// if (communities.get(i) == cId)
	// c = Management.getInstanceOf().getCommunityById(
	// communities.get(i));
	// }
	// return c;
	// }
	//	
	//
	// public boolean removeFriend(User u) {
	// return friends.remove(u);
	// }
	//
	// public boolean removeFriend(int uId) {
	// return friends.remove(Management.getInstanceOf().getUserById(uId));
	// }
	//
	//
	// public boolean kill() {
	// for (int i = 0; i < communities.size(); i++) {
	// Community c = Management.getInstanceOf().getCommunityById(
	// communities.get(i));
	// if (!c.removeUser(this.getId()))
	// return false;
	// }
	// for (int i = 0; i < friends.size(); i++) {
	// User u = friends.get(i);
	// if (!u.removeFriend(this))
	// return false;
	// }
	// return true;
	// }
	//
	// public boolean addCommunity(int cId) {
	// return communities.add(cId);
	// }
	//
	// public boolean removeCommunity(int cId) {
	// Community c = getCommunityById(cId);
	// if (c == null) {
	// System.out.println("Community not found!");
	// return false;
	// }
	// return communities.remove(c.getId());
	// }
	//
	// public boolean sendMessage(String message, int userID) {
	// return Management.getInstanceOf().sendMessage(message, userID);
	// }
	//
	// public Community createCommunity(String name, String info) {
	// Community c = new Community(name, info, this.id);
	// communities.add(c.getId());
	// return c;
	// }
	//
	// public boolean addUserCommunity(int cId, int uId) {
	// boolean checkCommunity = false;
	// for (int i = 0; i < this.communities.size(); i++) {
	// if (cId == this.communities.get(i)) {
	// checkCommunity = true;
	// break;
	// }
	// }
	// if (!checkCommunity)
	// return false;
	// Community c = Management.getInstanceOf().getCommunityById(cId);
	// if (!c.getOwner().equals(this.getId()))
	// return false;
	// if (c.containsUser(uId))
	// return false;
	// Management.getInstanceOf().getUserById(uId).addCommunity(cId);
	// return c.addUser(uId);
	// }
	//
	// public boolean addMessage(String message) {
	// return messages.add(message);
	// }

	public void printProfile() {
		System.out.println("NAME:" + this.name);
		System.out.println("USERNAME:" + this.login);
		System.out.println("EMAIL:" + this.email);
		System.out.println("ID:" + this.userId);
		System.out.println("\n");
	}

	// public void printMessages() {
	// System.out.println(messages.size() > 0 ? "MESSAGES:" : "NO MESSAGES");
	// for (int i = 0; i < messages.size(); i++)
	// System.out.println(messages.get(i));
	// }
	//
	// public void printCommunities() {
	// System.out.println(communities.size() > 0 ? "COMMUNITIES:"
	// : "NO COMMUNITIES");
	// for (int i = 0; i < communities.size(); i++)
	// System.out.println(Management.getInstanceOf()
	// .getCommunityById(communities.get(i)).getName());
	// }
	//
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
	
//	public boolean addFriend(User user2){
//		return friendRequest.add(user2);
//	}

}
