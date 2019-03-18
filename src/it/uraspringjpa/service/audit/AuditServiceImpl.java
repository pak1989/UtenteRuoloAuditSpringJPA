package it.uraspringjpa.service.audit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.uraspringjpa.dao.audit.AuditDAO;
import it.uraspringjpa.model.Audit;
import it.uraspringjpa.model.Ruolo;
import it.uraspringjpa.model.User;

@Component
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditDAO auditDAO;

	@Transactional(readOnly = true)
	public List<Audit> listAllAudits() {
		return auditDAO.list();
	}

	@Transactional(readOnly = true)
	public Audit caricaSingoloAudit(long id) {
		return auditDAO.get(id);
	}

	@Transactional
	public void aggiorna(Audit auditInstance) {
		auditDAO.update(auditInstance);
	}

	@Transactional
	public void inserisciNuovo(Audit auditInstance) {
		auditDAO.insert(auditInstance);
	}

	@Transactional
	public void rimuovi(Audit auditInstance) {
		auditDAO.delete(auditInstance);
	}

	@Transactional(readOnly = true)
	public List<Audit> findByExample(Audit example) {
		return auditDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public void refresh(Audit auditInstance) {
		auditDAO.refresh(auditInstance);
	}

	@Override
	public List<User> findAllByCodiceErrore(String codiceOperazione) {
		return auditDAO.findAllByCodiceErrore(codiceOperazione);
	}

	@Override
	public List<Audit> listaTuttiAuditRuolo(Ruolo ruoloInput) {
		return auditDAO.listaTuttiAuditRuolo(ruoloInput);
	}

}
