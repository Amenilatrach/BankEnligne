package tn.esprit.ourbank.DAO.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.ourbank.DAO.Entities.CompteCourant;

@Repository
public interface CompteCourantRepository extends CrudRepository<CompteCourant, Integer> {
	
	@Query("Select cc from CompteCourant cc where cc.numeroCompte = :numeroCompte")
	public CompteCourant RetrieveAgencyBynumCompte(@Param("numeroCompte") String numeroCompte);
	
	
	
}
