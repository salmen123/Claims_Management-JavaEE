package services.reclamation;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistance.Etudiant;
import persistance.Reclamation;
import persistance.TypeReclamation;

/**
 * Session Bean implementation class ReclamationService
 */
@Stateless
public class ReclamationService implements ReclamationServiceLocal {

    /**
     * Default constructor. 
     */
    public ReclamationService() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext
	EntityManager entityManager;

	@Override
	public void ajouterReclamation(Reclamation reclamation) {
		// TODO Auto-generated method stub
		entityManager.persist(reclamation);
	}

	@Override
	public void ajouterTypeReclamation(TypeReclamation typeReclamation) {
		// TODO Auto-generated method stub
		entityManager.merge(typeReclamation);
	}

	@Override
	public List<Reclamation> listerReclamations() {
		// TODO Auto-generated method stub
		
		//Query query = entityManager.createQuery("select r from Reclalamtion r");
		//return query.getResultList();
		String requete = "select r from Reclalamtion r";
		return entityManager.createQuery(requete, Reclamation.class).getResultList();
	}

	@Override
	public List<TypeReclamation> listerTypeReclamations() {
		// TODO Auto-generated method stub
		
		//Query query = entityManager.createQuery("select r from TypeReclamation r");
		//return query.getResultList();
		String requete = "select r from TypeReclamation r";
		return entityManager.createQuery(requete, TypeReclamation.class).getResultList();
	}

	@Override
	public boolean existeTypeReclalamtion(String type) {
		// TODO Auto-generated method stub
		boolean exists = false;
		String jpql = "select case when (count(u) > 0)  then true else false end from TypeReclamation u where u.type=:type";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("type", type);
		exists = (Boolean) query.getSingleResult();
		return exists;
	}

	@Override
	public TypeReclamation chercherTypeReclamationParType(String type) {
		// TODO Auto-generated method stub
		TypeReclamation found = null;
		Query query = entityManager
				.createQuery("select t from TypeReclamation t where t.type=:type");
		query.setParameter("type", type);
		try {
			found = (TypeReclamation) query.getSingleResult();
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO,"no type found");
		}

		return found;
	}

	@Override
	public List<Reclamation> listerReclamationParEtudiant(Etudiant etudiant) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("select distinct r from Reclamation r where r.etudiant=:e");
		query.setParameter("e", etudiant);
		return query.getResultList();
	}

}
