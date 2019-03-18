package it.uraspringjpa.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import it.uraspringjpa.model.Audit;
import it.uraspringjpa.model.Ruolo;
import it.uraspringjpa.model.User;
import it.uraspringjpa.service.audit.AuditService;
import it.uraspringjpa.service.ruolo.RuoloService;
import it.uraspringjpa.service.user.UserService;

@Component
public class BatteriaDiTestService {

	@Autowired
	private RuoloService ruoloService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuditService auditService;

	// casi di test (usare valorizzando la variabile casoDaTestare nel main)
	public static final String INSERISCI_NUOVO_RUOLO = "INSERISCI_NUOVO_RUOLO";
	public static final String CREA_RUOLI = "CREA_RUOLI";	
	public static final String INSERISCI_USER_DATO_UN_RUOLO = "INSERISCI_USER_DATO_UN_RUOLO";
	public static final String INSERISCI_USER_DATO_UN_RUOLO_FAKER_LOOP_10 = "INSERISCI_USER_DATO_UN_RUOLO_FAKER_LOOP_10";
	public static final String ELENCA_USERS_DATO_UN_RUOLO = "ELENCA_USERS_DATO_UN_RUOLO";
	public static final String STAMPA_PASSWORD_DATO_RUOLO = "STAMPA_PASSWORD_DATO_RUOLO";
	public static final String ELENCA_TUTTI_GLI_USER = "ELENCA_TUTTI_GLI_USER";
	public static final String UPDATE_USER_SET_NOME = "UPDATE_USER_SET_NOME";
	public static final String INSERISCI_NUOVO_AUDIT = "INSERISCI_NUOVO_AUDIT";
	public static final String INSERISCI_NUOVO_AUDIT_FAKER_LOOP_10_OK = "INSERISCI_NUOVO_AUDIT_FAKER_LOOP_10_OK";
	public static final String INSERISCI_NUOVO_AUDIT_FAKER_LOOP_10_NOT_OK = "INSERISCI_NUOVO_AUDIT_FAKER_LOOP_10_NOT_OK";
	public static final String ELENCA_UTENTI_DA_CODICE_OPERAZIONE = "ELENCA_UTENTI_DA_CODICE_OPERAZIONE";
	public static final String ELENCA_AUDIT_DATO_RUOLO = "ELENCA_AUDIT_DATO_RUOLO";
	

	public void eseguiBatteriaDiTest(String casoDaTestare) {

		Faker faker = new Faker();

		try {
			switch (casoDaTestare) {
			case INSERISCI_NUOVO_RUOLO:
				Ruolo nuovoRuolo = new Ruolo("nuovo_ruolo", "new");
				ruoloService.inserisciNuovo(nuovoRuolo);
				System.out.println("Ruolo appena inserito: " + nuovoRuolo.toString());
				break;
				
			case CREA_RUOLI:
				Ruolo admRuolo = new Ruolo("administrator", "adm");
				ruoloService.inserisciNuovo(admRuolo);
				System.out.println("Ruolo appena inserito: " + admRuolo.toString());
				
				Ruolo usrRuolo = new Ruolo("user", "usr");
				ruoloService.inserisciNuovo(usrRuolo);
				System.out.println("Ruolo appena inserito: " + usrRuolo.toString());
				
				Ruolo ospRuolo = new Ruolo("ospite", "osp");
				ruoloService.inserisciNuovo(ospRuolo);
				System.out.println("Ruolo appena inserito: " + ospRuolo.toString());
				
				break;

			case INSERISCI_USER_DATO_UN_RUOLO:
				User nuovoUser = new User("Mario", "Rossi", "mar", "passw", new Date());
				nuovoUser.getRuoli().add(ruoloService.caricaSingoloRuolo(1));
				userService.inserisciNuovo(nuovoUser);
				break;

			case INSERISCI_USER_DATO_UN_RUOLO_FAKER_LOOP_10:
				for (int i = 0; i < 10; i++) {
					User nuovoUserTemp = new User(faker.name().firstName(), faker.name().lastName(),
							faker.name().username(), "passw", new Date());
					Ruolo ruoloTemp = ruoloService
							.caricaSingoloRuolo(faker.number().numberBetween(1, 3));
					nuovoUserTemp.getRuoli().add(ruoloTemp);
					userService.inserisciNuovo(nuovoUserTemp);
					System.out.println(nuovoUserTemp.toString() + " appena inserito, con ruolo: "
							+ ruoloTemp.toString());
				}
				break;

			case ELENCA_USERS_DATO_UN_RUOLO:
				for (User user : userService.findAllByRuolo(ruoloService.caricaSingoloRuolo(2))) {
					System.out.println(user);
				}
				break;
			
			case STAMPA_PASSWORD_DATO_RUOLO:
				for (String string : userService.listPassByRuolo(ruoloService.caricaSingoloRuolo(2))) {
					System.out.println(string);
				}
				break;

			case ELENCA_TUTTI_GLI_USER:
				System.out.println("Elenco i municipi:");
				for (User userItem : userService.listAllUsers()) {
					System.out.println(userItem);
				}
				break;

			case UPDATE_USER_SET_NOME:
				User userEsistente1 = userService.caricaSingoloUser(10);
				if (userEsistente1 != null) {
					userEsistente1.setNome("Angela");
					userService.aggiorna(userEsistente1);
				}
				break;
				
			case INSERISCI_NUOVO_AUDIT:
				User userEsistente2 = userService.caricaSingoloUser(2);
				Audit nuovoAudit = new Audit("prova", "ok", userEsistente2);
				auditService.inserisciNuovo(nuovoAudit);
				break;				

			case INSERISCI_NUOVO_AUDIT_FAKER_LOOP_10_OK:
				for (int i = 0; i < 10; i++) {
				User tempUserEsistente = userService.caricaSingoloUser(faker.number().numberBetween(1, 20));
				Audit tempAudit = new Audit(faker.space().planet(), "ok", tempUserEsistente);
				auditService.inserisciNuovo(tempAudit);
				}
				break;				

			case INSERISCI_NUOVO_AUDIT_FAKER_LOOP_10_NOT_OK:
				for (int i = 0; i < 10; i++) {
					User tempUserEsistente = userService.caricaSingoloUser(faker.number().numberBetween(1, 20));
					Audit tempAudit = new Audit(faker.space().planet(), "not_ok", tempUserEsistente);
					tempAudit.setMessaggio_errore(faker.hacker().abbreviation());
					auditService.inserisciNuovo(tempAudit);
				}
				break;			
				
			case ELENCA_UTENTI_DA_CODICE_OPERAZIONE:
				for (User user : auditService.findAllByCodiceErrore("ok")) {
					System.out.println(user);
				}
				break;

			case ELENCA_AUDIT_DATO_RUOLO:
				for (Audit audit : auditService.listaTuttiAuditRuolo(ruoloService.caricaSingoloRuolo(3))) {
					System.out.println(audit.toString());
				}
				break;
	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
