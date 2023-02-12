package tn.esprit.ourbank.Service.Interface;

import java.util.List;

import tn.esprit.ourbank.DAO.Entities.Formation;

public interface FormationService {
	
	 	List<Formation> retrieveAllFormations();
		
	 	Formation addFormation(Formation f);

}
