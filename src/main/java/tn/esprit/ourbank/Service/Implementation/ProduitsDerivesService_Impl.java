package tn.esprit.ourbank.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.ourbank.DAO.Entities.ProduitsDerives;
import tn.esprit.ourbank.DAO.Repository.ProduitsDerivesRepository;
import tn.esprit.ourbank.Service.Interface.ProduitsDerivesService;

@Service
public class ProduitsDerivesService_Impl implements ProduitsDerivesService {

	@Autowired
	ProduitsDerivesRepository produitsDerivesRepository; 
	
	@Override
	public List<ProduitsDerives> retrieveAllProduitsDerives() {
		List<ProduitsDerives> pDerives = (List<ProduitsDerives>) (produitsDerivesRepository.findAll());
		return pDerives ;
	}

	@Override
	public ProduitsDerives addProduitsDerives(ProduitsDerives p) {
		return produitsDerivesRepository.save(p);
	}

	@Override
	public void deleteProduitsDerives(int id) {
		produitsDerivesRepository.deleteById(id);

	}

	@Override
	public ProduitsDerives updateProduitsDerives(int id, ProduitsDerives p) {
		ProduitsDerives pd1 = produitsDerivesRepository.findById(id).get();
		pd1.setNombre(p.getNombre());
		pd1.setEntreprise(p.getEntreprise());
		pd1.setCoupon(p.getCoupon());
		pd1.setMaturite(p.getMaturite());
		pd1.setPrixUnitaire(p.getPrixUnitaire());

		return produitsDerivesRepository.save(pd1);
	}

}
