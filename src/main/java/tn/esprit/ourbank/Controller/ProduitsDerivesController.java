package tn.esprit.ourbank.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import tn.esprit.ourbank.DAO.Entities.CompteCourant;
import tn.esprit.ourbank.DAO.Entities.ProduitsDerives;
import tn.esprit.ourbank.DAO.Entities.Userr;
import tn.esprit.ourbank.DAO.Repository.CompteCourantRepository;
import tn.esprit.ourbank.DAO.Repository.ProduitsDerivesRepository;
import tn.esprit.ourbank.DAO.Repository.UserrRepository;
import tn.esprit.ourbank.Service.Interface.ProduitsDerivesService;

@CrossOrigin
@RestController
public class ProduitsDerivesController {
	
	@Autowired
	ProduitsDerivesRepository produitsDerivesRepository;
	
	@Autowired
	ProduitsDerivesService produitsDerivesService;
	
	@Autowired
	UserrRepository userRepository;
	
	@Autowired
	CompteCourantRepository compteCourantRepository;
	
	
	
	@GetMapping("allProduitsDerives")
	public List<ProduitsDerives> GetAllProduitsDerives(){
		return produitsDerivesService.retrieveAllProduitsDerives();
	}
	
	
	@GetMapping("allProduitsDerivesFirst")
	public List<ProduitsDerives> GetAllProduitsDerivesFirst(){
		List<ProduitsDerives> pDerives = (List<ProduitsDerives>) (produitsDerivesRepository.findAll());
		List<ProduitsDerives> pDerivesFirst = new ArrayList<>();
		
		for (ProduitsDerives produitsDerives : pDerives) {
			if(produitsDerives.getUserr()==null) {
				pDerivesFirst.add(produitsDerives);
			}
		}
		
		return pDerivesFirst ;
	}
	
	
	@GetMapping("myProduitsDerives/{id}")
	public List<ProduitsDerives> GetmyProduitsDerives(@PathVariable("id")int id){
		Userr userr = userRepository.findById(id).get();
		//List<ProduitsDerives> mypDerives = (List<ProduitsDerives>) (produitsDerivesRepository.RetrievemyProduitsDerive(userr));
		List<ProduitsDerives> mypDerives = (List<ProduitsDerives>) userr.getProduitsDereives();
		
		return mypDerives ;
	}
	
	
	
	
	@PostMapping("addProduitsDerives")
	public ProduitsDerives ajouterProduitsDerives(@RequestBody ProduitsDerives pd)
		{produitsDerivesService.addProduitsDerives(pd);
		return pd;
		}
	
	/*
	@PutMapping("addAgencyClients")
	public Agency ajouterAgenceClient(@RequestBody Agency agence)
		{agencyService.ajoutavecClient(agence);
		return agence;
		}
	
	
    @GetMapping("retrieve_Agence/{id}")
    public Agency retrieveAgence(@PathVariable("id") int id) {
    return agencyService.retirieveAgency(id);
    } 
	*/
	
    @PutMapping("editProduitsDerives/{id}")
    public ProduitsDerives updateeProduitsDerives(@RequestBody ProduitsDerives pd, @PathVariable int id) {
   	return produitsDerivesService.updateProduitsDerives(id, pd); 
    }
    
    
    
    @DeleteMapping("/delete_ProduitsDerives/{id}")  
	public void deleteProduitsDerivesById(@PathVariable("id")int id) {
    	produitsDerivesService.deleteProduitsDerives(id);
	}
    
    @PutMapping("editproduitDeriveUser/{id}")
    public ProduitsDerives updateeUserProduitsderives(@RequestBody ProduitsDerives p, @PathVariable("id") int id) {
    	Userr user = userRepository.findById(id).get();
		//p.setUserr(user);
    	
    	CompteCourant cc = user.getLstCompteCourant().get(0);
    	double fee = p.getPrixUnitaire()*p.getNombre();
    	System.out.println("la valeur est " + fee);
    	
    	CompteCourant ca =  compteCourantRepository.RetrieveAgencyBynumCompte("0000");    	
    	
    		if(cc.getSolde()>fee) {
    			if(p.getUserr()==null) {
        		cc.setSolde(cc.getSolde()-fee);
        		ca.setSolde(ca.getSolde()+fee);
        		p.setUserr(user);
        		produitsDerivesRepository.save(p);
        		
        	}
    	}else {
    		System.out.println("Desolé vous n'avez pas de solde");
    	}
    	
    	
    	return p;
    	
    }

}
