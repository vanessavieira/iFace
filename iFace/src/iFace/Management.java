package iFace;

import java.util.ArrayList;

public class Management {

	private static Management c_Management;
	public ArrayList<Community> m_communities = new ArrayList<Community>();
	public ArrayList<User> m_users = new ArrayList<User>();;

	public User getUserById(int id) {
		User u = null;
		if (m_users.size() > 0) {
			for (int i = 0; i < m_users.size(); i++) {
				if (m_users.get(i).getUserId() == id) {
					u = m_users.get(i);
					break;
				}
			}
		}
		return u;
	}

	public Community getCommunityById(int id) {
		Community c = null;
		if (m_communities.size() > 0) {
			for (int i = 0; i < m_communities.size(); i++) {
				if (m_communities.get(i).getId() == id) {
					c = m_communities.get(i);
					break;
				}
			}
		}
		return c;
	}

	public static Management getInstanceOf() {
		if (c_Management == null)
			c_Management = new Management();
		return c_Management;
	}

	public boolean addUser(User user) {
		if (getUserById(user.getUserId()) != null)
			return false;
		return m_users.add(user);
	}

	// public boolean removeUser(int uId) {
	// User u = getUserById(uId);
	// if (!u.kill())
	// return false;
	// return m_users.remove(u);
	// }
	//
	// public int loginCheck(String login, String password) {
	// for (int i = 0; i < m_users.size(); i++) {
	// if (m_users.get(i).getLogin().compareTo(login) == 0
	// && m_users.get(i).getPassword().compareTo(password) == 0) {
	// return m_users.get(i).getId();
	// }
	// }
	// return -1;
	// }
	//
	// public boolean addCommunity(Community c) {
	// if (getCommunityById(c.getId()) != null)
	// return false;
	// return m_communities.add(c);
	// }
	//
	// public boolean removeCommunity(int cId) {
	// Community c = getCommunityById(cId);
	// if (!c.kill())
	// return false;
	// return m_communities.remove(c);
	// }
	//
	// public boolean sendMessage(String message, int userID) {
	// User u = getUserById(userID);
	// if (u == null)
	// return false;
	// return u.addMessage(message);
	// }
	//
	// public void printUserById(int id) {
	// Management.getInstanceOf();
	// if (m_users.size() > 0) {
	// for (int i = 0; i < m_users.size(); i++) {
	// if (m_users.get(i).getId() == id) {
	// c_Management.m_users.get(i).printProfile();
	// }
	// }
	// }
	// }
	//
	// public void printUsers() {
	// Management.getInstanceOf();
	// System.out.println(c_Management.m_users.size() > 0 ? "\nUSERS:\n"
	// : "NO USERS");
	// for (int i = 0; i < c_Management.m_users.size(); i++) {
	// c_Management.m_users.get(i).printProfile();
	// }
	// }
	//
	// public void printFriends(int id) {
	// Management.getInstanceOf();
	// // System.out.println(c_Management.m_users.size() > 0 ? "\nFRIENDS:\n"
	// // : "YOU HAVE NO FRIENDS");
	// for (int i = 0; i < c_Management.m_users.size(); i++) {
	// if (m_users.get(i).getId() == id) {
	// c_Management.m_users.get(i).printFriends();
	// }
	// }
	// }
	//
	// public void print() {
	// Management.getInstanceOf();
	// System.out.println(c_Management.m_users.size() > 0 ? "\nUSERS:\n"
	// : "NO USERS");
	// for (int i = 0; i < c_Management.m_users.size(); i++) {
	// c_Management.m_users.get(i).printProfile();
	// // c_Management.m_users.get(i).printMessages();
	// c_Management.m_users.get(i).printCommunities();
	// c_Management.m_users.get(i).printFriends();
	// }
	// System.out
	// .println(c_Management.m_communities.size() > 0 ? "COMMUNITIES:"
	// : "NO COMMUNITIES");
	// for (int i = 0; i < c_Management.m_communities.size(); i++)
	// c_Management.m_communities.get(i).print();
	// System.out.println("-------------------------------------------------");
	// }
}
