package tn.esprit.ourbank.DAO.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.ourbank.DAO.Entities.Formation;

@Repository
public interface FormationRepository extends CrudRepository<Formation, Integer> {

}
