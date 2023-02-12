package tn.esprit.ourbank.Service.Implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.ourbank.DAO.Entities.CompteCourant;
import tn.esprit.ourbank.DAO.Entities.FeedBack;
import tn.esprit.ourbank.DAO.Entities.Userr;
import tn.esprit.ourbank.DAO.Repository.CompteCourantRepository;
import tn.esprit.ourbank.DAO.Repository.UserrRepository;
import tn.esprit.ourbank.Service.Interface.CompteCourantService;

@Service
public class CompteCourantService_Impl implements CompteCourantService {
	
	@Autowired
	CompteCourantRepository compteCourantRepository;
	
	@Autowired
	UserrRepository userrRepository;

	@Override
	public List<CompteCourant> retrieveAllCC() {
		List<CompteCourant> CCS = (List<CompteCourant>) (compteCourantRepository.findAll());
		return CCS ;
	}

	@Override
	public CompteCourant addCC(CompteCourant cc, int userId) {
		Userr user = userrRepository.findById(userId).get();
		Date d = new Date();
		cc.setDatedecreation(d);
		cc.setUser(user);
		return compteCourantRepository.save(cc);
	}

	
	

}
