package it.uraspringjpa.service.ruolo;

import java.util.List;

import it.uraspringjpa.model.Ruolo;

public interface RuoloService {

	public List<Ruolo> listAllRuoli();

	public Ruolo caricaSingoloRuolo(long id);

	public void aggiorna(Ruolo ruoloInstance);

	public void inserisciNuovo(Ruolo ruoloInstance);

	public void rimuovi(Ruolo ruoloInstance);

	public List<Ruolo> findByExample(Ruolo example);

	public void refresh(Ruolo ruoloInstance);
}
