package it.uraspringjpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "audit")
public class Audit implements Serializable {

	private static final long serialVersionUID = 6848359105769176105L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@Column(name = "descrizione_operazione")
	private String descrizione_operazione;
	@Column(name = "codice_esito_operazione")
	private String codice_esito_operazione;
	@Column(name = "messaggio_errore")
	private String messaggio_errore;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	public Audit() {
	}

	public Audit(String descrizione_operazione, String codice_esito_operazione, User user) {
		super();
		this.descrizione_operazione = descrizione_operazione;
		this.codice_esito_operazione = codice_esito_operazione;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescrizione_operazione() {
		return descrizione_operazione;
	}

	public void setDescrizione_operazione(String descrizione_operazione) {
		this.descrizione_operazione = descrizione_operazione;
	}

	public String getCodice_esito_operazione() {
		return codice_esito_operazione;
	}

	public void setCodice_esito_operazione(String codice_esito_operazione) {
		this.codice_esito_operazione = codice_esito_operazione;
	}

	public String getMessaggio_errore() {
		return messaggio_errore;
	}

	public void setMessaggio_errore(String messaggio_errore) {
		this.messaggio_errore = messaggio_errore;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Audit [id=" + id + ", descrizione_operazione=" + descrizione_operazione + ", codice_esito_operazione="
				+ codice_esito_operazione + ", messaggio_errore=" + messaggio_errore + ", user=" + user +"]";
	}

}
