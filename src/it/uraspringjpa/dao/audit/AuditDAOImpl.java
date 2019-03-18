package it.uraspringjpa.dao.audit;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Component;

import it.uraspringjpa.model.Audit;
import it.uraspringjpa.model.Ruolo;
import it.uraspringjpa.model.User;

@Component
public class AuditDAOImpl implements AuditDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> list() {
		return entityManager.createQuery("from Audit").getResultList();
	}

	@Override
	public Audit get(long id) {
		Audit result = (Audit) entityManager.find(Audit.class, id);
		return result;
	}

	@Override
	public void update(Audit auditInstance) {
		auditInstance = entityManager.merge(auditInstance);
	}

	@Override
	public void insert(Audit auditInstance) {
		entityManager.persist(auditInstance);
	}

	@Override
	public void delete(Audit auditInstance) {
		entityManager.remove(entityManager.merge(auditInstance));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> findByExample(Audit auditInstance) {
		Session session = (Session) entityManager.getDelegate();
		Example auditExample = Example.create(auditInstance).excludeZeroes();
		Criteria criteria = session.createCriteria(Audit.class).add(auditExample);
		return criteria.list();
	}

	@Override
	public void refresh(Audit auditInstance) {
		entityManager.refresh(entityManager.merge(auditInstance));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllByCodiceErrore(String codiceOperazione) {
		Query query = entityManager.createQuery(
				"SELECT DISTINCT usr FROM Audit aud JOIN aud.user usr WHERE aud.codice_esito_operazione = :codiceOperazione")
				.setParameter("codiceOperazione", codiceOperazione);
		return (List<User>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> listaTuttiAuditRuolo(Ruolo ruoloInput) {
		Query query = entityManager.createQuery(
				"SELECT aud FROM Audit aud JOIN aud.user usr JOIN usr.ruoli ru WHERE ru.id = :ruoloInput")
				.setParameter("ruoloInput", ruoloInput.getId());
		return (List<Audit>) query.getResultList();
	}
}