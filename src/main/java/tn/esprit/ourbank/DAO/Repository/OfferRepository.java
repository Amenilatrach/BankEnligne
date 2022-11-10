package tn.esprit.ourbank.DAO.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.ourbank.DAO.Entities.Offer;



@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer> {

	@Query("Select o from Offer o where o.nbajout > :nb")
	public List<Offer> RetrieveOffersSup(@Param("nb") int nb);
	
	@Transactional
	@Modifying
	@Query("UPDATE Offer o SET o.score = "
            + " CASE "
            + " WHEN (o.nbajout = 0) THEN ( o.score *  1 ) "
            + " WHEN (o.nbajout > 0) AND (o.nbajout < 3) THEN ( o.score *  1.05 ) "
            + " WHEN (o.nbajout > 4) THEN ( o.score *1.15  ) "
            + " END")
			void updateScore(Offer o);
	
	@Transactional
	@Modifying
	@Query("UPDATE Offer o SET o.risque = "
            + " CASE "
            + " WHEN (o.domain = 'Education') AND (o.score > 0) AND (o.score < 0.4) THEN ( 'Elevée' ) "
            + " WHEN (o.domain = 'Education') AND (o.score > 0.4) AND (o.score < 0.6) THEN ( 'Moyenne' ) "
            + " WHEN (o.domain = 'Education') AND (o.score > 0.6) AND (o.score < 1.0) THEN ( 'Bonne' ) "
            + " WHEN (o.domain = 'Agricole') AND (o.score > 1.0) AND (o.score < 1.4) THEN ( 'Elevée' ) "
            + " WHEN (o.domain = 'Agricole') AND (o.score > 1.4) AND (o.score < 1.6) THEN ( 'Moyenne' ) "
            + " WHEN (o.domain = 'Agricole') AND (o.score > 1.4) AND (o.score < 2.0) THEN ( 'Bonne' ) "
            + " WHEN (o.domain = 'Creation') AND (o.score > 2.0) AND (o.score < 2.4) THEN ( 'Elevée' ) "
            + " WHEN (o.domain = 'Creation') AND (o.score > 2.4) AND (o.score < 2.6) THEN ( 'Moyenne' ) "
            + " WHEN (o.domain = 'Creation') AND (o.score > 2.6) AND (o.score < 3.0) THEN ( 'Bonne' ) "
            + " WHEN (o.domain = 'Comercial') AND (o.score > 3.0) AND (o.score < 3.4) THEN ( 'Elevée' ) "
            + " WHEN (o.domain = 'Comercial') AND (o.score > 3.4) AND (o.score < 3.6) THEN ( 'Moyenne' ) "
            + " WHEN (o.domain = 'Comercial') AND (o.score > 3.6) AND (o.score < 4.0) THEN ( 'Bonne' ) "
            + " END")
			void updateRisque(Offer o);
	
	@Transactional	
	@Modifying
	@Query("UPDATE Offer o SET o.promotion = "
            + " CASE "
            + " WHEN (o.domain = 'Education') AND (o.risque = 'Elevée') THEN ( o.promotion + 0.04) "
            + " WHEN (o.domain = 'Education') AND (o.risque = 'Moyenne') THEN ( o.promotion + 0.03 ) "
            + " WHEN (o.domain = 'Agricole') AND (o.risque = 'Elevée') THEN ( o.promotion + 0.02) "
            + " WHEN (o.domain = 'Agricole') AND (o.risque = 'Moyenne') THEN ( o.promotion + 0.017) "
            + " WH"
            + "EN (o.domain = 'Creation') AND (o.risque = 'Elevée') THEN ( o.promotion + 0.015) "
            + " WHEN (o.domain = 'Creation') AND (o.risque = 'Moyenne') THEN ( o.promotion + 0.012) "
            + " WHEN (o.domain = 'Comercial') AND (o.risque = 'Elevée') THEN ( o.promotion + 0.011) "
            + " WHEN (o.domain = 'Comercial') AND (o.risque = 'Moyenne') THEN ( o.promotion + 0.01) "
            + " END")
			void updatePromotion(Offer o);

	@Transactional
	@Modifying
	@Query("Update Offer o set o.clusterNumber = :clusterNumber where o.id = :id  ")
	void updateClusterNumber(@Param ("clusterNumber") Integer clusterNumber,@Param ("id") Integer id );
	
	@Query("Select o from Offer o where o.clusterNumber = (Select o.clusterNumber from Offer o where o.name = :name)")
	public List<Offer> Recommand(@Param("name") String name);
	
}
