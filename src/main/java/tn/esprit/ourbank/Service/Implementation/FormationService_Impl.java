package tn.esprit.ourbank.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.ourbank.DAO.Entities.Agency;
import tn.esprit.ourbank.DAO.Entities.Formation;
import tn.esprit.ourbank.DAO.Repository.AgencyRepository;
import tn.esprit.ourbank.DAO.Repository.FormationRepository;
import tn.esprit.ourbank.DAO.Repository.UserrRepository;
import tn.esprit.ourbank.Service.Interface.FormationService;

@Service
public class FormationService_Impl implements FormationService{
	
	@Autowired
	FormationRepository formationRepository;
	
	@Override
	public List<Formation> retrieveAllFormations() {
		List<Formation> formations = (List<Formation>) (formationRepository.findAll());
		return formations ;
	}

	@Override
	public Formation addFormation(Formation f) {
		return formationRepository.save(f);
	}


}
