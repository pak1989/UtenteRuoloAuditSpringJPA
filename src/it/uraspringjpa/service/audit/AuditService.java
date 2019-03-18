package it.uraspringjpa.service.audit;

import java.util.List;

import it.uraspringjpa.model.Audit;
import it.uraspringjpa.model.Ruolo;
import it.uraspringjpa.model.User;

public interface AuditService {

	public List<Audit> listAllAudits();

	public Audit caricaSingoloAudit(long id);

	public void aggiorna(Audit auditInstance);

	public void inserisciNuovo(Audit auditInstance);

	public void rimuovi(Audit auditInstance);

	public List<Audit> findByExample(Audit example);
	
	public void refresh(Audit auditInstance);
	
	public List<User> findAllByCodiceErrore(String codiceOperazione);
	
	public List<Audit> listaTuttiAuditRuolo (Ruolo ruoloInput);

}
