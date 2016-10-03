package com.spring.dao.hibernate;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.hibernate.Person;

@Transactional
@Repository
public class PersonDAOImpl implements PersonDAO {
	private static final Logger logger = Logger.getLogger(PersonDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Person getPersonById(int pid) {
		logger.info("person id = " + pid);
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Person person = (Person) session.get(Person.class, pid);
		transaction.commit();
		session.close();
		return person;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAllPersons() {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<Person> personList = session.createQuery("from Person").list();
		transaction.commit();
		session.close();
		return personList;
	}

	@Override
	public boolean addPerson(Person person) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(person);
		transaction.commit();
		session.close();
		return false;
	}

	@Override
	public void updatePerson(Person person) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(person);
		transaction.commit();
		session.close();
	}

	@Override
	public void deletePerson(int pid) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Person p = (Person) session.load(Person.class, pid);
		if (null != p) {
			session.delete(p);
			transaction.commit();
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean personExists(String name, String location) {
		logger.info("person name = " + name + " and location = " + location);
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Person as p WHERE p.name = :name and p.location = :location";
		List<Person> personList = session.createQuery(hql)
				.setString("name", name)
				.setString("location", location)
				.list();
		transaction.commit();
		session.close();
		return personList.size() > 0 ? true : false;
	}
}
