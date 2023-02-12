package tn.esprit.ourbank.DAO.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.ourbank.DAO.Entities.ProduitsDerives;
import tn.esprit.ourbank.DAO.Entities.Userr;

import java.util.List;



@Repository
public interface ProduitsDerivesRepository extends CrudRepository<ProduitsDerives, Integer> {

	@Query("Select p from ProduitsDerives p where p.userr = :userr")
	public List<ProduitsDerives> RetrievemyProduitsDerive(@Param("userr") Userr userr);
	
}
