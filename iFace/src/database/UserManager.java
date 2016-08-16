package database;

import java.util.List;

import javax.persistence.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import iFace.Community;
import iFace.Message;
import iFace.User;

import exceptionsFile.*;

public class UserManager {

	final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	final SessionFactory sessionFactory = new Configuration().configure("./META-INF/hibernate.cfg.xml")
			.buildSessionFactory();
	Session session = threadLocal.get();

	// Users and Friends
	public void addUser(User instance) {
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.save(instance);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	public void deleteUser(User instance) {
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.delete(instance);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	public void updateInstance(User instance) {
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.update(instance);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	public void addFriend(User friend, int friendId, int yourId) throws Exception {
		if (yourId == friendId){
			throw new CantAddYourselfException("You can't add yourself!");
		}
		
		for (int i = 0; i < friend.friendRequest.size(); i++) {
			if (yourId == friend.friendRequest.get(i).getUserId()) {
				throw new AlreadySentRequestException("You have already sent a request to this user!\n");
			}
		}
		for (int i = 0; i < friend.friends.size(); i++) {
			if (yourId == friend.friends.get(i).getUserId()) {
				throw new AlreadyFriendException("You are already friends!\n");
			}
		}
	}
	
	public int getFriendId(String login) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from User where login = :login");
		query.setParameter("login", login);

		User u = (User) query.uniqueResult();

		session.close();

		if (u == null){
			throw new UserNotFoundException("This user was not found.\n");
		} else {
			return u.getUserId();
		}

	}

	public User getUserById(int id) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from User where id = :id");
		query.setParameter("id", id);

		User u = (User) query.uniqueResult();

		session.close();
		
		if (u == null){
			throw new UserNotFoundException("This user was not found.\n");
		}
		return u;
	}

	public int loginCheck(String login, String senha) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from User where login = :login");
		query.setParameter("login", login);

		User u = (User) query.uniqueResult();

		session.close();
		if (u == null) {
			throw new InvalidDataException("Username or Password incorrect!\n");
		}
		if (u.getPassword().equals(senha)) {
			return u.getUserId();
		}
		return -1;
	}

	public void verifyUserName(String login) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from User where login = :login");
		query.setParameter("login", login);

		User u = (User) query.uniqueResult();

		session.close();

		if (u != null) {
			throw new UsernameAlreadyExistsException("This username is already taken.\n");
		}
	}

	public void verifyUserEmptyField (User user) throws Exception {
		if (user.getName().equals("") || user.getLogin().equals("") || user.getEmail().equals("")
				|| user.getPassword().equals("")) {
			throw new EmptyFieldException("You left an empty field!\n");
		}
	}		
	
	public void verifyFriendship(int friendId, int yourId) throws Exception {
		int check = 0;
		for (int i = 0; i < getUserById(yourId).friends.size(); i++){
			if (getUserById(yourId).friends.get(i).getUserId() == friendId){
				check = -1;
			}
		}
		if (check == 0){
			throw new NotYourFriendException("This user is not your friend.\n");
		}
	}

	public void printUserProfile(int id) {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from User where id = :id");
		query.setParameter("id", id);

		User u = (User) query.uniqueResult();
		System.out.print("NAME: ");
		System.out.println(u.getName());
		System.out.print("USERNAME: ");
		System.out.println(u.getLogin());
		System.out.print("EMAIL: ");
		System.out.println(u.getEmail());
		System.out.print("ID: ");
		System.out.println(u.getUserId());

		session.close();
	}

	public void printUserFriends(User u) {
		System.out.println(u.friends.size() > 0 ? "\nFRIENDS:\n" : "YOU HAVE NO FRIENDS");
		for (User u2 : u.getFriends()) {
			System.out.print("NAME: ");
			System.out.println(u2.getName());
			System.out.print("USERNAME: ");
			System.out.println(u2.getLogin());
			System.out.print("EMAIL: ");
			System.out.println(u2.getEmail());
			System.out.print("ID: ");
			System.out.println(u2.getUserId());
			System.out.println("\n");
		}
	}

	// Communities
	public int createCommunity(Community community) {
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.save(community);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return community.getId();
	}
	
	public Community getCommunityById(int id) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from Community where id = :id");
		query.setParameter("id", id);

		Community c = (Community) query.uniqueResult();
		
		if (c == null){
			throw new CommunityNotFoundException("This community does not exist.\n");
		}

		session.close();
		return c;
	}
	
	public Community getCommunityByName(String name) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from Community where name = :name");
		query.setParameter("name", name);

		Community c = (Community) query.uniqueResult();
		
		if (c == null){
			throw new CommunityNotFoundException("This community does not exist.\n");
		}

		session.close();
		return c;
	}
	
	public void deleteCommunity(Community community) {
		session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.delete(community);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}
	
	public void printCommunityByUser(User u){
		for (Community c : u.getCommunities()) {
			System.out.print("Name: ");
			System.out.println(c.getName());
			System.out.print("Description: ");
			System.out.println(c.getInfo());
			System.out.print("Owner: ");
			System.out.println(c.getOwner().getLogin());
			System.out.print("ID: ");
			System.out.println(c.getId());
			System.out.println("\n");
		}
	}
	
	public void printCommunity(Community c) {	
		System.out.print("Name: ");
		System.out.println(c.getName());
		System.out.print("Description: ");
		System.out.println(c.getInfo());
		System.out.print("Owner: ");
		System.out.println(c.getOwner().getLogin());
		System.out.print("ID: ");
		System.out.println(c.getId());
		System.out.println("\n");
	}
	
	public void addCommunities (User user, int uId, int cId) throws Exception {		
		for (int i = 0; i < user.communities.size(); i++) {
			if (cId == user.communities.get(i).getId()) {
				throw new AlreadyAMemberException("You are already a member in this community!\n");
			}
		}
		for (int i = 0; i < user.managedCommunities.size(); i++) {
			if (cId == user.managedCommunities.get(i).getId()) {
				throw new AlreadyAMemberException("You are already a member in this community!\n");
			}
		}
	}
	
	public void verifyCommunityMessage (User user, int cId) throws Exception {
		int check = 0;
		for (int i = 0; i < user.communities.size(); i++) {
			if (cId == user.communities.get(i).getId()) {
				check = 1;
			}
		}
		for (int i = 0; i < user.managedCommunities.size(); i++) {
			if (cId == user.managedCommunities.get(i).getId()) {
				check = 1;
			}
		}
		
		if (check == 0){
			throw new NotAMemberException("You are not a member in this community.\n");
		}
	}
	
	public void verifyCommunityEmptyField (Community community) throws Exception {
		if (community.getName().equals("") || community.getInfo().equals("")) {
			throw new EmptyFieldException("You left an empty field!\n");
		}
	}
	
	// Messages
	public int createMessage(Message message) {
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.save(message);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return message.getId();
	}
	
	public Message getMessageById(int id) {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from Message where id = :id");
		query.setParameter("id", id);

		Message m = (Message) query.uniqueResult();

		session.close();
		return m;
	}
	
	public void deleteMessage(Message message) {
		session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.delete(message);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}
	
	public void printMessagesByUser(User u){
		for (Message m : u.getReceivedMessages()) {
			System.out.print("From: ");
			System.out.println(m.getUserSender().getLogin());
			System.out.print("Message: ");
			System.out.println(m.getContent());
			System.out.println("\n");
		}
	}
	
	public void verifyMessageEmptyField (Message message) throws Exception {
		if (message.getContent().equals("")){
			throw new EmptyFieldException("You left an empty field!\n");
		}
	}
}