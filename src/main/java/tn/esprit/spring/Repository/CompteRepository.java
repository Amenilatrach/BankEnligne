package esprit.tn.EnBank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import esprit.tn.EnBank.Model.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long>{

}
