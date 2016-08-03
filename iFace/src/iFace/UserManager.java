package iFace;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserManager {

	final SessionFactory sessionFactory = new Configuration().configure("./META-INF/hibernate.cfg.xml")
			.buildSessionFactory();

	public void addUser(User instance) {
		Session session = sessionFactory.openSession();

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
		Session session = sessionFactory.openSession();

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
		Session session = sessionFactory.openSession();

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
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from User where id = :id");
		query.setParameter("id", id);

		User u = (User) query.uniqueResult();
		System.out.println("NAME:");
		System.out.println(u.getName());
		System.out.println("USERNAME:");
		System.out.println(u.getLogin());
		System.out.println("EMAIL:");
		System.out.println(u.getEmail());
		System.out.println("ID:");
		System.out.println(u.getUserId());

	}

	public void printUserFriends(User u) {

		System.out.println(u.friends.size() > 0 ? "\nFRIENDS:\n" : "YOU HAVE NO FRIENDS");

		for (int i = 0; i < u.friends.size(); i++) {
			if (u.friends.get(i).isFriend == true) {
				System.out.println("NAME:");
				System.out.println(u.friends.get(i).user2.getName());
				System.out.println("USERNAME:");
				System.out.println(u.friends.get(i).user2.getLogin());
				System.out.println("EMAIL:");
				System.out.println(u.friends.get(i).user2.getEmail());
			}
		}

	}

	public int getFriendId(String login) {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from User where login = :login");
		query.setParameter("login", login);

		User u = (User) query.uniqueResult();

		if (u == null) {
			return -1;
		} else {
			return u.getUserId();
		}
	}

	public User getUserById(int id) {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from User where id = :id");
		query.setParameter("id", id);

		User u = (User) query.uniqueResult();
		System.out.println(u.getName());
		return u;
	}

	public int loginCheck(String login, String senha) {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from User where login = :login");
		query.setParameter("login", login);

		User u = (User) query.uniqueResult();

		if (u == null){
			return -1;
		}
		if (u.getPassword().equals(senha)){
			return u.getUserId();
		}
		return -1;
	}
	
	

}
