package tn.esprit.ourbank.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.ourbank.Dto.AdminDto;
import tn.esprit.ourbank.Service.Interface.InterfaceAdmin;
import tn.esprit.ourbank.DAO.Entities.Admin;

@CrossOrigin
@RestController
public class AdminController {
	
	@Autowired
	InterfaceAdmin interfaceadmin;
	
	@PostMapping("SignUp")
	public Admin ajouterAdmin(@RequestBody Admin admin) {
		interfaceadmin.ajouterAdmin(admin);
		return admin;
	}
	
	@GetMapping("allAdmins")
	public List<Admin> GetAllAdmins(){
		return interfaceadmin.retrieveAllAdmins();
	}
	
	//@PostMapping("SignIn/{matricule}/{code}")
	//public void Signin(@PathVariable("matricule") String matricule, @PathVariable("code") String code ) {
	//	interfaceadmin.SignIn(matricule, code);
	//}

	
	@PostMapping("SignIn")
		public ResponseEntity<Admin> authenticateAdmin(@RequestBody AdminDto adminDto) {
		if (adminDto !=null) {
			Admin admin =  interfaceadmin.Authentication(adminDto);
			
			if(admin != null) {
				return new ResponseEntity<>(admin, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		}
		
		return null;
		}
	
	
	
	
}
