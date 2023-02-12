package tn.esprit.ourbank.DAO.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import tn.esprit.ourbank.DAO.Entities.Agency;
import tn.esprit.ourbank.DAO.Entities.Userr;


@Repository
public interface UserrRepository extends CrudRepository<Userr, Integer> {

	@Query("Select u from Userr u where u.matricule = :matricule and u.code = :code ")
	public Userr RetrieveUserbymatriculeandcode(@Param("matricule")String matricule,@Param("code")  String code);

	@Modifying
	@Transactional
	@Query("Update Userr u set u.agency.id = :id where u.idUser = :idusr")
	public void UpdateUserAgency(@Param("id") int id,@Param("idusr") int idusrr);
	
	
	@Modifying
	@Transactional
	@Query("Update Userr u set u.agency = :agency where u.idUser = :idusr")
	public void UpdateUserAgencyy(@Param("agency") Agency agency,@Param("idusr") int idusrr);
	
}
