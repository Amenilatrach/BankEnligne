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
//import tn.esprit.ourbank.DAO.Entities.Staff;
import tn.esprit.ourbank.Service.Implementation.AgencyServiceImpl;
import tn.esprit.ourbank.Service.Interface.AgencyService;



@CrossOrigin
@RestController
public class AgencyRestController {
	@Autowired
	AgencyService agencyService;
	
	@GetMapping("allAgencies")
	public List<Agency> GetAllAgencies(){
		return agencyService.retrieveAllAgencies();
	}
	
	@PostMapping("addAgency")
	public Agency ajouterAgence(@RequestBody Agency agence)
		{agencyService.addAgency(agence);
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

    
    

}