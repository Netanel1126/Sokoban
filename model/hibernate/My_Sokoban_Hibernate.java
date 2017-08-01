package model.hibernate;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import common.Hibernate_UsersAndQuery;

public class My_Sokoban_Hibernate implements Sokoban_Hibernate {

	private SessionFactory factory;

	public My_Sokoban_Hibernate() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}
//Add user to SQL database
	@Override
	public void AddPlayerToSQL(Hibernate_UsersAndQuery user) {
		Session session = null;
		Transaction tx = null;

		session = factory.openSession();
		try {
			tx = session.beginTransaction();
			session.save(user.getUser_list().getLast());
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
//get all the users from database by level name 
	@Override
	public void ScoreBoard_Level(Hibernate_UsersAndQuery querylist, String prefix) {
		Session session = factory.openSession();
		Query newQuery = session.createQuery("from ScoreBoard where Level_Name like :prefix");
		newQuery.setParameter("prefix","%" + prefix + "%");
		querylist.setQuerylist(newQuery.list());
		session.close();
	}
//get all the users from database whit the same user name
	@Override
	public void ScoreBoard_Player(Hibernate_UsersAndQuery querylist, String prefix) {
		Session session = factory.openSession();
		Query newQuery = session.createQuery("from ScoreBoard where UserName like :prefix");
		newQuery.setParameter("prefix","%" + prefix + "%");
		querylist.setQuerylist(newQuery.list());
		session.close();
	}
}
