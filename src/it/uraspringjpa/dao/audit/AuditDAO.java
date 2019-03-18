package it.uraspringjpa.dao.audit;

import java.util.List;

import it.uraspringjpa.dao.IBaseDAO;
import it.uraspringjpa.model.Audit;
import it.uraspringjpa.model.Ruolo;
import it.uraspringjpa.model.User;

public interface AuditDAO extends IBaseDAO<Audit> {
	
	public List<User> findAllByCodiceErrore(String codiceOperazione);
	
	public List<Audit> listaTuttiAuditRuolo (Ruolo ruoloInput);

}
