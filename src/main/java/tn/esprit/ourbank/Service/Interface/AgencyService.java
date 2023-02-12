package tn.esprit.ourbank.Service.Interface;

import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.ourbank.DAO.Entities.Agency;

public interface AgencyService {
	
    List<Agency> retrieveAllAgencies();
	
    Agency addAgency(Agency a);
    
    Agency ajoutavecClient(Agency ag);
	
	void deleteAgency(int id);
	
	Agency updateAgency(int id, Agency a);
	
	Agency retirieveAgency (int id);
	
	Agency updateAgencyByid(int id, String location);
	
	//public List<Agency> RetrieveAgencyByLocation(@Param("location") String location);
	
	//public List<String> RetrieveAgencyStafs();

	public void UpdateUserAgency(int idAgency, int idUser);

	//public int nbreStaff();
}
