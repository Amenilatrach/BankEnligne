package tn.esprit.ourbank.Service.Interface;

import java.util.List;

import tn.esprit.ourbank.DAO.Entities.FeedBack;

public interface FeedBackService {
	
    List<FeedBack> retrieveAllFeedBacks();
	
	FeedBack addFeedBack(FeedBack f, int userId);
	
	 //Credit addCredit(Credit cr,int accountId);

}
