package iFace;

import java.util.ArrayList;

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

//	private static UserManager c_uManager;
//
//	public static UserManager getInstanceOf() {
//		if (c_uManager == null)
//			c_uManager = new UserManager();
//		return c_uManager;
//	}

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
			System.out.println("\n");
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

	public void deleteUserRelations(User user) {
		int uId = user.getUserId();

		for (User u : user.getFriendRequest()) {
				user.removeFriendRequest(u);
				u.removeFriendRequest(user);	
		}
		
		for (User u : user.getFriends()) {
				u.removeFriends(user);		
		}
		user.friends.clear();
		user.friendRequest.clear();
	}

}
