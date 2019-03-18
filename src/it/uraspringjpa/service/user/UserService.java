package it.uraspringjpa.service.user;

import java.util.List;

import it.uraspringjpa.model.Ruolo;
import it.uraspringjpa.model.User;

public interface UserService {

	public List<User> listAllUsers();

	public User caricaSingoloUser(long id);

	public void aggiorna(User userInstance);

	public void inserisciNuovo(User userInstance);

	public void rimuovi(User userInstance);

	public List<User> findByExample(User example);
	
	public void refresh(User userInstance);
	
	public List<User> findAllByRuolo(Ruolo ruoloInput);

	public List<String> listPassByRuolo(Ruolo ruoloInput);
	
}
