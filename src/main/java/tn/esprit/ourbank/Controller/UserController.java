package tn.esprit.ourbank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.rest.chat.v1.service.User;

import tn.esprit.ourbank.Dto.AdminDto;
import tn.esprit.ourbank.Service.Interface.InterfaceAdmin;
import tn.esprit.ourbank.Service.Interface.UserService;
import tn.esprit.ourbank.DAO.Entities.Admin;
import tn.esprit.ourbank.DAO.Entities.Offer;
import tn.esprit.ourbank.DAO.Entities.Userr;
import tn.esprit.ourbank.DAO.Repository.UserrRepository;
import tn.esprit.ourbank.Dto.UserDto;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	UserService interfaceUser;
	
	@Autowired
	UserrRepository userRepository;
	
	@PostMapping("SignUpUser")
	public Userr ajouterUser(@RequestBody Userr user) {
		interfaceUser.ajouterUser(user);
		return user;
	}
	
	@GetMapping("allUsers")
	public List<Userr> GetAllUsers(){
		return interfaceUser.retrieveAllUsers();
	}
	
	@GetMapping("allUsersfeedback")
	public List<Userr> GetAllUsersfeedback(){
		return interfaceUser.retrieveusersfeedback();
	}
	
	@GetMapping("nbUsers")
	public int nbAllUsers(){
		return interfaceUser.nbUsers();
	}
	
		//@Scheduled(cron="0 0 0 0 1/1 *")
	 	//@Scheduled(fixedRateString = "PT5M")
	 	@GetMapping("Varie") 
	 	public void AugmentationUsers() throws NullPointerException {
	 			int nbr;
	 			List<Userr> Users = (List<Userr>) (userRepository.findAll());
	 			nbr = Users.size();
	 			Userr.newnb = nbr - Userr.nb;
	 			Userr.percentage =  Userr.newnb/nbr; 
	 			
	 			System.out.println("nbUser Actuel = "+nbr);
	 			System.out.println("nbUser Ancien = "+Userr.nb);
	 			System.out.println("Variation = "+Userr.newnb);
	 			System.out.println("percentage = "+ Userr.percentage);
	 			
	 			Userr.nb= nbr;
	 	}
	
		@GetMapping("Variation")
		public double varieetion(){
			return Userr.percentage*100;
		}

	
	@PostMapping("SignInUser")
		public ResponseEntity<Userr> authenticateUser(@RequestBody UserDto userDto) {
		if (userDto !=null) {
			Userr user =  interfaceUser.AuthenticationUser(userDto);
			
			if(user != null) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		}
		
		return null;
		}
	
	
	
}
