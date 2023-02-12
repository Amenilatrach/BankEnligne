package esprit.tn.EnBank.Services;

import java.util.List;

import esprit.tn.EnBank.Exceptions.InvalidAccountException;
import esprit.tn.EnBank.Model.Compte;




public interface CompteService {
	Compte updateAccount(Compte compte, Long aId);
	
	List<Compte> allAccounts();

	Compte findCompte(Long id) throws InvalidAccountException;
}
