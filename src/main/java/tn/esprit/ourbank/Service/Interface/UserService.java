package tn.esprit.ourbank.Service.Interface;


import java.util.List;

 
import tn.esprit.ourbank.DAO.Entities.Userr;
import tn.esprit.ourbank.Dto.UserDto;

public interface UserService {
	    
		void ajouterUser(Userr u);
		
	    List<Userr> retrieveAllUsers();
	    
	    List<Userr> retrieveusersfeedback();
	    
	    Userr AuthenticationUser (UserDto adminDto);
	    
	    int nbUsers();


}
