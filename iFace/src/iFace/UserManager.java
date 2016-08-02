package iFace;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserManager {

	// final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	final SessionFactory sessionFactory = new Configuration().configure("./META-INF/hibernate.cfg.xml")
			.buildSessionFactory();
	// Session session = threadLocal.get();

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

	User getUserById(int id) {
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("from User where id = :id");
		query.setParameter("id", id);
		
		User u = (User) query.uniqueResult();
		return u;
	}

	public int loginCheck(String login, String senha) {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from User where login = :login");
		query.setParameter("login", login);

		User u = (User) query.uniqueResult();
		
		if (u == null) return -1;

		if (u.getPassword().equals(senha))
			return u.getId();
		
		return -1;
	}

}
