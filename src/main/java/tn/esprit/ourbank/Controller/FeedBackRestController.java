package tn.esprit.ourbank.Controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.ourbank.DAO.Entities.FeedBack;
import tn.esprit.ourbank.DAO.Entities.Offer;
import tn.esprit.ourbank.Service.Interface.FeedBackService;

@CrossOrigin
@RestController
public class FeedBackRestController {
	
	@Autowired
	FeedBackService feedBackService;
	
	@GetMapping("allFeedBacks")
	public List<FeedBack> GetAllFeedBacks(){
		return feedBackService.retrieveAllFeedBacks();
	}
	
	
	@PostMapping("addFeedBack/{idUser}")
	public FeedBack ajouterFeedBack(@RequestBody FeedBack f, @PathVariable("idUser") int id) {
		feedBackService.addFeedBack(f, id);
		return f;
		}
	
	
	
	

}
