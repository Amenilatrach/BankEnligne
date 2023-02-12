package tn.esprit.ourbank.Service.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.ourbank.DAO.Entities.Agency;
import tn.esprit.ourbank.DAO.Entities.FeedBack;
import tn.esprit.ourbank.DAO.Entities.Offer;
import tn.esprit.ourbank.DAO.Entities.Userr;
import tn.esprit.ourbank.DAO.Repository.FeedBackRepository;
import tn.esprit.ourbank.DAO.Repository.UserrRepository;
import tn.esprit.ourbank.Service.Interface.FeedBackService;

@Service
public class FeedBackServiceImpl implements FeedBackService {
	
	@Autowired
	FeedBackRepository feedBackRepository;
	
	@Autowired
	UserrRepository userrRepository;

	@Override
	public FeedBack addFeedBack(FeedBack f, int userId) {
		Userr user = userrRepository.findById(userId).get();
		Date d = new Date();
		f.setDateAjout(d);
		f.setUser(user);
		feedBackRepository.save(f);
		
		return f;
		
	}

	@Override
	public List<FeedBack> retrieveAllFeedBacks() {
		List<FeedBack> FeedBacks = (List<FeedBack>) (feedBackRepository.findAll());
		return FeedBacks ;
	}

	
	

}
