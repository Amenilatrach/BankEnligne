package esprit.tn.EnBank.Services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esprit.tn.EnBank.Exceptions.InvalidAccountException;
import esprit.tn.EnBank.Model.Compte;
import esprit.tn.EnBank.Repository.CompteRepository;



@Service
public class ComteServiceImpl implements CompteService{

	@Autowired
	private CompteRepository compteRepository;

	@Override
	public Compte updateAccount(Compte compte, Long aId) {
		compte.setNumeroCompte(aId);
		return compteRepository.save(compte);
	}

	@Override
	public List<Compte> allAccounts() {
		// TODO Auto-generated method stub
		return compteRepository.findAll();
	}


	@Override
	public Compte findCompte(Long id) throws InvalidAccountException {
		Compte compte = compteRepository.findById(id).orElseThrow(() -> new InvalidAccountException("Compte indisponible"));

		if (!compte.isEtatCompte()) {
			throw new InvalidAccountException("Compte n'est plus disponible veuillez contacter votre agence");
		}

		return compte;
	}
	
	
}
