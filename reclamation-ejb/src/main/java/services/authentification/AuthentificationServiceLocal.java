package services.authentification;

import java.util.List;

import javax.ejb.Local;

import persistance.Etudiant;
import persistance.Utilisateur;

@Local
public interface AuthentificationServiceLocal {
	public Utilisateur authentifier(String email, String password);
	
	boolean existeEmail(String email);

	void sauvegarderUtilisateur(Utilisateur utilisateur);
	
	List<Etudiant> listerEtudiants();
}
