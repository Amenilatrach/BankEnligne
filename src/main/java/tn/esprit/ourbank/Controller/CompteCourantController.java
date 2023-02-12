package tn.esprit.ourbank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.ourbank.DAO.Entities.CompteCourant;
import tn.esprit.ourbank.Service.Interface.CompteCourantService;

@CrossOrigin
@RestController
public class CompteCourantController {
	
	@Autowired
	CompteCourantService compteCourantService;
	
	@GetMapping("allCCs")
	public List<CompteCourant> GetAllCCS(){
		return compteCourantService.retrieveAllCC();
	}
	
	
	@PostMapping("addCC/{idUser}")
	public CompteCourant ajouterCC(@RequestBody CompteCourant cc, @PathVariable("idUser") int id) {
		compteCourantService.addCC(cc, id);
		return cc;
		}

}
