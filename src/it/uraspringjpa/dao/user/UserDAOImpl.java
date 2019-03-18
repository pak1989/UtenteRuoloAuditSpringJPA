package it.uraspringjpa.dao.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Component;

import it.uraspringjpa.model.Ruolo;
import it.uraspringjpa.model.User;

@Component
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() {
		return entityManager.createQuery("from User").getResultList();
	}

	@Override
	public User get(long id) {
		User result = (User) entityManager.find(User.class, id);
		return result;
	}

	@Override
	public void update(User userInstance) {
		userInstance = entityManager.merge(userInstance);
	}

	@Override
	public void insert(User userInstance) {
		entityManager.persist(userInstance);
	}

	@Override
	public void delete(User userInstance) {
		entityManager.remove(entityManager.merge(userInstance));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByExample(User userInstance) {
		Session session = (Session) entityManager.getDelegate();
		Example userExample = Example.create(userInstance).excludeZeroes();
		Criteria criteria = session.createCriteria(User.class).add(userExample);
		return criteria.list();
	}

	@Override
	public void refresh(User userInstance) {
		entityManager.refresh(entityManager.merge(userInstance));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllByRuolo(Ruolo input) {
		Query query = entityManager.createQuery("SELECT usr FROM User usr JOIN usr.ruoli ru WHERE ru.id = :ruoloId")
				.setParameter("ruoloId", input.getId());
		return (List<User>) query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> listPassByRuolo(Ruolo input) {
		Query query = entityManager.createQuery("SELECT usr.password FROM User usr JOIN usr.ruoli ru WHERE ru.id = :ruoloId")
				.setParameter("ruoloId", input.getId());
		return (List<String>) query.getResultList();
	}

}
