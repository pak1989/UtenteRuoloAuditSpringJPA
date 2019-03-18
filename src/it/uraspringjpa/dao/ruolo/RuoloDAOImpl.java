package it.uraspringjpa.dao.ruolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Component;

import it.uraspringjpa.model.Ruolo;

@Component
public class RuoloDAOImpl implements RuoloDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Ruolo> list() {
		return entityManager.createQuery("from Ruolo").getResultList();
	}

	@Override
	public Ruolo get(long id) {
		Ruolo result = (Ruolo) entityManager.find(Ruolo.class, id);
		return result;
	}

	@Override
	public void update(Ruolo ruoloInstance) {
		ruoloInstance = entityManager.merge(ruoloInstance);
	}

	@Override
	public void insert(Ruolo ruoloInstance) {
		entityManager.persist(ruoloInstance);
	}

	@Override
	public void delete(Ruolo ruoloInstance) {
		entityManager.remove(entityManager.merge(ruoloInstance));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ruolo> findByExample(Ruolo ruoloInstance) {
		Session session = (Session) entityManager.getDelegate();
		Example ruoloExample = Example.create(ruoloInstance).excludeZeroes();
		Criteria criteria = session.createCriteria(Ruolo.class).add(ruoloExample);
		return criteria.list();
	}

	@Override
	public void refresh(Ruolo ruoloInstance) {
		entityManager.refresh(entityManager.merge(ruoloInstance));
	}
}
