package tn.esprit.ourbank.Service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.ourbank.DAO.Entities.Userr;
import tn.esprit.ourbank.DAO.Repository.UserrRepository;
import tn.esprit.ourbank.Dto.UserDto;
import tn.esprit.ourbank.Service.Interface.UserService;

@Service
public class UserService_Impl implements UserService {

	@Autowired
	UserrRepository userRepository;
	
	@Override
	public void ajouterUser(Userr u) {
		userRepository.save(u);
		
	}

	@Override
	public List<Userr> retrieveAllUsers() {
		List<Userr> Users = (List<Userr>) (userRepository.findAll());
		return Users ;
	}
	
	



	@Override
	public Userr AuthenticationUser(UserDto userDto) {
		return userRepository.RetrieveUserbymatriculeandcode(userDto.getMatricule(), userDto.getCode());

	}

	@Override
	public int nbUsers() {
		int nb ;
		List<Userr> Users = (List<Userr>) (userRepository.findAll());
		nb = Users.size();
		return nb;
	}

	@Override
	public List<Userr> retrieveusersfeedback() {
		List<Userr> Users = (List<Userr>) (userRepository.findAll());
		List<Userr> userrs = new ArrayList<>(); 
		for (Userr userr : Users) {
			if(userr.getFeedback().size()>0) {
				userrs.add(userr);
			}
		}
		return userrs ;
	}

	

	
}
