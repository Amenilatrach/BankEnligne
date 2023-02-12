package tn.esprit.ourbank.Service.Interface;

import java.util.List;

import tn.esprit.ourbank.DAO.Entities.ProduitsDerives;

public interface ProduitsDerivesService {
	
	 	List<ProduitsDerives> retrieveAllProduitsDerives();
		
	 	ProduitsDerives addProduitsDerives(ProduitsDerives p);
		
		void deleteProduitsDerives(int id);
		
		ProduitsDerives updateProduitsDerives(int id ,ProduitsDerives p);

}
