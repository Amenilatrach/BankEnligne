package esprit.tn.EnBank.Services;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import esprit.tn.EnBank.Dto.SaveOperationDto;
import esprit.tn.EnBank.Exceptions.InvalidAccountException;
import esprit.tn.EnBank.Model.Compte;
import esprit.tn.EnBank.Model.PortefeuilleOperation;
import esprit.tn.EnBank.Model.RemiseCheque;
import esprit.tn.EnBank.Model.Retrait;
import esprit.tn.EnBank.Model.Versement;
import esprit.tn.EnBank.Model.Virement;
import esprit.tn.EnBank.Repository.CompteRepository;
import esprit.tn.EnBank.Repository.PortefeuilleOperationRepository;





@Service
public class PortefeuilleOperationServiceImpl implements PortefeuilleOperationService{

	@Autowired
	private PortefeuilleOperationRepository portefeuilleOperationRepository;
	
	@Autowired
	private CompteService compteService;
	@Autowired
	private CompteRepository compteRepository;
	
	
	
	
	
	
	@Transactional
	@Override
	public void verser(SaveOperationDto saveOperationDto) {
		Compte cp= new Compte();
		try {
			cp=compteService.findCompte(saveOperationDto.getCodeCpte());
		}catch (InvalidAccountException e) {
			e.printStackTrace();
		}
		
		Date date =  new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		
		Versement v = new Versement( new Date(), date,"validate" ,saveOperationDto.getCin(),saveOperationDto.getNom_expediteur(),saveOperationDto.getCommentaire() , cp);
		v.setMontant(saveOperationDto.getMontant());
		cp.setSoldeCompte(cp.getSoldeCompte() + saveOperationDto.getMontant());
		portefeuilleOperationRepository.save(v);
		compteRepository.save(cp);
	}

	@Override
	public void retirer(SaveOperationDto saveOperationDto) {
		Compte cp = null;
     	try {
			cp = compteService.findCompte(saveOperationDto.getCodeCpte());
		} catch (InvalidAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//BigDecimal rouge = cp.get
		Long solde =cp.getSoldeCompte();
		Date date =  new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		//if compteCourant
		
		if( solde.compareTo(saveOperationDto.getMontant() ) >1)
			throw new RuntimeException("Solde insuffisant");
		Retrait r = new Retrait();
		r.setStatut("validate");
		r.setDate_operation(date);
		r.setDate_valeur(new Date());
		
		r.setCompte(cp);
		r.setCommentaire(saveOperationDto.getCommentaire());
		r.setDate_retrait(saveOperationDto.getDate_retrait());
		;
		
		r.setMontant(saveOperationDto.getMontant());
		cp.setSoldeCompte(cp.getSoldeCompte() - saveOperationDto.getMontant() );
		portefeuilleOperationRepository.save(r);
		compteRepository.save(cp);
		
	}

	@Override
	public void virement(SaveOperationDto saveOperationDto) {
		if(saveOperationDto.getCodeCpte().equals(saveOperationDto.getCodeCompte2())){
			throw new RuntimeException("Impossibile de faire un virement sur le même compte");
		}
		
		Compte cp1= new Compte();
		Compte cp2= new Compte();
		try {
			cp1 = compteService.findCompte(saveOperationDto.getCodeCpte());
			cp2 = compteService.findCompte(saveOperationDto.getCodeCompte2());
			
		} catch (InvalidAccountException e) {
			e.printStackTrace();
		}
		
		Date date =  new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		if( cp1.getSoldeCompte().compareTo(saveOperationDto.getMontant() ) >1)
			throw new RuntimeException("Solde insuffisant");
		Virement v = new Virement();
		
		v.setStatut("validate");
		v.setDate_operation(date);
		v.setDate_valeur(new Date());
		v.setNum_compte_recepteur(saveOperationDto.getNum_compte_recepteur());
		v.setCompte(cp1);
		v.setCommentaire(saveOperationDto.getCommentaire());
;
		v.setMontant(saveOperationDto.getMontant());
		cp1.setSoldeCompte(cp1.getSoldeCompte() - saveOperationDto.getMontant()) ;
		cp2.setSoldeCompte(cp2.getSoldeCompte() + saveOperationDto.getMontant());
		portefeuilleOperationRepository.save(v);
		compteRepository.save(cp1);
		compteRepository.save(cp2);		
	}

	@Override
	public void remiseCheque(SaveOperationDto saveOperationDto) {
		
		Compte cp = null;
		Compte cp2 = null;
     	try {
			cp = compteService.findCompte(saveOperationDto.getCodeCpte());
			cp2 = compteService.findCompte(saveOperationDto.getCodeCompte2());
		} catch (InvalidAccountException e) {
			e.printStackTrace();
		}
     	
     	if (cp.getSoldeCompte().compareTo(saveOperationDto.getMontant())==1 ||cp.getSoldeCompte().compareTo(saveOperationDto.getMontant())==0 ) {
     		
     		Date date =  new Date();
    		Calendar c = Calendar.getInstance(); 
    		c.setTime(date); 
    		c.add(Calendar.DATE, 1);
    		date = c.getTime();
    		if( cp.getSoldeCompte().compareTo(saveOperationDto.getMontant()) >1)
    			throw new RuntimeException("Solde insuffisant");
    		RemiseCheque r = new RemiseCheque( );
    			 

    		r.setStatut("validate");
    		r.setDate_operation(date);
    		r.setDate_valeur(new Date());
    		r.setNum_cheque(saveOperationDto.getNumCheque());
    		r.setCin(saveOperationDto.getCin());
    		r.setDate_remise(saveOperationDto.getDate_remise());
    		r.setCompte(cp);
    		r.setCommentaire(saveOperationDto.getCommentaire());
    		r.setMontant(saveOperationDto.getMontant());
    		cp.setSoldeCompte(cp.getSoldeCompte() - saveOperationDto.getMontant() );
    		cp2.setSoldeCompte(cp2.getSoldeCompte() + saveOperationDto.getMontant());
    		portefeuilleOperationRepository.save(r);
    		compteRepository.save(cp);
    		compteRepository.save(cp2);
     	}
	}
	
	public List<PortefeuilleOperation> getPortefeuilleByCompte(Long id){
		Compte c = compteRepository.findById(id).get();
		return portefeuilleOperationRepository.findByCompte(c);
	}

}
