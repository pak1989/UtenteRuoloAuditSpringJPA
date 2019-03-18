package it.uraspringjpa.dao.user;

import java.util.List;

import it.uraspringjpa.dao.IBaseDAO;
import it.uraspringjpa.model.Ruolo;
import it.uraspringjpa.model.User;

public interface UserDAO extends IBaseDAO<User> {
	
	public List<User> findAllByRuolo(Ruolo input);
	
	public List<String> listPassByRuolo(Ruolo input);
}
