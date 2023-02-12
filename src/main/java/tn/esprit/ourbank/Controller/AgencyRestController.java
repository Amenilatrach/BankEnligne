package tn.esprit.ourbank.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.ourbank.DAO.Entities.Agency;
import tn.esprit.ourbank.DAO.Entities.Offer;
import tn.esprit.ourbank.DAO.Repository.AgencyRepository;
import tn.esprit.ourbank.DAO.Repository.OfferRepository;
import tn.esprit.ourbank.DAO.Repository.UserrRepository;
//import tn.esprit.ourbank.DAO.Entities.Staff;
import tn.esprit.ourbank.Service.Implementation.AgencyServiceImpl;
import tn.esprit.ourbank.Service.Interface.AgencyService;
import tn.esprit.ourbank.Service.Interface.UserService;



@CrossOrigin
@RestController
public class AgencyRestController {
	@Autowired
	AgencyService agencyService;
	
	
	@Autowired
	UserrRepository userRepository;
	
	@GetMapping("allAgencies")
	public List<Agency> GetAllAgencies(){
		return agencyService.retrieveAllAgencies();
	}
	
	@PostMapping("addAgency")
	public Agency ajouterAgence(@RequestBody Agency agence)
		{agencyService.addAgency(agence);
		return agence;
		}
	
	@PutMapping("addAgencyClients")
	public Agency ajouterAgenceClient(@RequestBody Agency agence)
		{agencyService.ajoutavecClient(agence);
		return agence;
		}
	
	
    @GetMapping("retrieve_Agence/{id}")
    public Agency retrieveAgence(@PathVariable("id") int id) {
    return agencyService.retirieveAgency(id);
    } 
	
    @PutMapping("editAgencies/{id}")
    public Agency updateeAgency(@RequestBody Agency agency, @PathVariable int id) {
   	return agencyService.updateAgency(id, agency); 
    }
    
    
    
    @DeleteMapping("/delete_Agence/{id}")  
	public void deleteAgenceById(@PathVariable("id")int id) {
    	agencyService.deleteAgency(id);
	}
    
     @PutMapping("/modify_Agence_Location/{id}/{location}") 
    	public void mettreAjourAgenceLocation(@PathVariable("id") int id, @PathVariable("location") String location) {
    	 agencyService.updateAgencyByid(id, location);
    	}
	
     
     /*
    @GetMapping("AgenciesbyLocation/{location}")
 	public List<Agency> GetAgenciesBylocation(@PathVariable("location") String location){
 		return agencyService.RetrieveAgencyByLocation(location);
 	}

    @GetMapping("AgencyStaff")
	public List<String> GetAgencySatff(){
		return agencyService.RetrieveAgencyStafs();
	}
    
    @PutMapping("/StaffAgency") 
	public void mettreAjourStaffAgency() {
    	agencyService.UpdateStaffAgency();
	}
    
    @PutMapping("/AgencyStaffNumber") 
   	public void mettreAjourAgencyStaffNumber() {
       	agencyService.nbreStaff();
   	}
    
    
   */ 
     
     
     //@PutMapping("editUserAgency/{id}/{idusr}")
     //public void updateeUserAgency(@PathVariable int id, @PathVariable int idusr) {
     //	 agencyService.UpdateUserAgency(id, idusr); 
     //}
     
     
     @PutMapping("editUserAgencyy/{idusr}")
     public void updateeUserAgency(@RequestBody Agency agency, @PathVariable("idusr") int idusr) {
    	 userRepository.UpdateUserAgencyy(agency, idusr);
     }

    
    

}