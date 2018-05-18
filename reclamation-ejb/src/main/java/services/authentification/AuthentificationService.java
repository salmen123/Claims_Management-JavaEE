package services.authentification;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistance.Etudiant;
import persistance.Utilisateur;

/**
 * Session Bean implementation class AuthentificationService
 */
@Stateless
public class AuthentificationService implements AuthentificationServiceLocal {

	@PersistenceContext
	EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public AuthentificationService() {
        // TODO Auto-generated constructor stub
    }
    
    public Utilisateur authentifier(String email, String password) {
    	Utilisateur utilisateur = null;
    	
    	Query query = entityManager
				.createQuery("select u from Utilisateur u where u.email=:email and u.password=:password");
		query.setParameter("email", email).setParameter("password", password);
		
		try {
			utilisateur = (Utilisateur) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "authentication failed with email="+email+" and password="+password);
		}
		
		return utilisateur;
	}

	@Override
	public boolean existeEmail(String email) {
		// TODO Auto-generated method stub
		boolean exists = false;
		String jpql = "select case when (count(u) > 0)  then true else false end from Utilisateur u where u.email=:email";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("email", email);
		exists = (Boolean) query.getSingleResult();
		return exists;
	}

	@Override
	public void sauvegarderUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		entityManager.merge(utilisateur);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Etudiant> listerEtudiants() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select e from Etudiant e ").getResultList();
	}

}
