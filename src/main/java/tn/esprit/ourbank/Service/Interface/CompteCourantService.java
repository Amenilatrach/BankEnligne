package tn.esprit.ourbank.Service.Interface;

import java.util.List;

import tn.esprit.ourbank.DAO.Entities.CompteCourant;

public interface CompteCourantService {
	
	 	List<CompteCourant> retrieveAllCC();
		
		CompteCourant addCC(CompteCourant cc, int userId);

		

}
