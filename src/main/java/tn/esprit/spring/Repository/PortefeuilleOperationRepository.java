package esprit.tn.EnBank.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import esprit.tn.EnBank.Model.Compte;
import esprit.tn.EnBank.Model.PortefeuilleOperation;

@Repository
public interface PortefeuilleOperationRepository extends JpaRepository<PortefeuilleOperation, Long>{
List<PortefeuilleOperation> findByCompte(Compte compte);

}
