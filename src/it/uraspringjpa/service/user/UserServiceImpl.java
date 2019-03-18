package it.uraspringjpa.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.uraspringjpa.dao.user.UserDAO;
import it.uraspringjpa.model.Ruolo;
import it.uraspringjpa.model.User;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Transactional(readOnly = true)
	public List<User> listAllUsers() {
		return userDAO.list();
	}

	@Transactional(readOnly = true)
	public User caricaSingoloUser(long id) {
		return userDAO.get(id);
	}

	@Transactional
	public void aggiorna(User userInstance) {
		userDAO.update(userInstance);
	}

	@Transactional
	public void inserisciNuovo(User userInstance) {
		userDAO.insert(userInstance);
	}

	@Transactional
	public void rimuovi(User userInstance) {
		userDAO.delete(userInstance);
	}

	@Transactional(readOnly = true)
	public List<User> findByExample(User example) {
		return userDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public void refresh(User userInstance) {
		userDAO.refresh(userInstance);
	}
	
	@Transactional(readOnly = true)
	public List<User> findAllByRuolo(Ruolo ruoloInput) {
		return userDAO.findAllByRuolo(ruoloInput);
	}

	@Transactional(readOnly = true)
	public List<String> listPassByRuolo(Ruolo ruoloInput) {
		return userDAO.listPassByRuolo(ruoloInput);
	}

}
