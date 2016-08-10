package iFace;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

	public void addFriend(User instance) {
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

	public int getFriendId(String login) {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from User where login = :login");
		query.setParameter("login", login);

		User u = (User) query.uniqueResult();

		session.close();

		if (u == null) {
			return -1;
		} else {
			return u.getUserId();
		}

	}

	public User getUserById(int id) {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from User where id = :id");
		query.setParameter("id", id);

		User u = (User) query.uniqueResult();

		session.close();
		return u;
	}

	public int loginCheck(String login, String senha) {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from User where login = :login");
		query.setParameter("login", login);

		User u = (User) query.uniqueResult();

		session.close();
		if (u == null) {
			return -1;
		}
		if (u.getPassword().equals(senha)) {
			return u.getUserId();
		}
		return -1;
	}

	public int verifyUserName(String login) {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from User where login = :login");
		query.setParameter("login", login);

		User u = (User) query.uniqueResult();

		session.close();

		if (u != null) {
			System.err.println("This username already exists!");
			return -1;
		}
		return 0;
	}

	public int verifyFriendshipRequest(User user, int uId) {
		for (int i = 0; i < user.friendRequest.size(); i++) {
			if (uId == user.friendRequest.get(i).getUserId()) {
				System.err.println("You already have requested this friend!\n");
				return -1;
			}
		}
		return 0;
	}

	public int verifyFriendship(User user, int uId) {
		for (int i = 0; i < user.friends.size(); i++) {
			if (uId == user.friends.get(i).getUserId()) {
				System.err.println("You are already friends!\n");
				return -1;
			}
		}
		return 0;
	}

	// public User deleteUserRelations(User user) {
	// int uId = user.getUserId();
	//
	// user = getUserById(uId);
	//
	// for (User u : user.getFriends())
	// u.getFriends().remove(user);
	//
	// for (User u : user.getFriendRequest())
	// u.getFriendRequest().remove(user);
	//
	// for (Community c : user.getCommunities())
	// c.getMembers().remove(user);
	//
	// user.setFriends(null);
	// user.setFriendRequest(null);
	// user.setCommunities(null);
	//
	// return user;
	// }

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
	
	public Community getCommunityById(int id) {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from Community where id = :id");
		query.setParameter("id", id);

		Community c = (Community) query.uniqueResult();

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
}
