package tn.esprit.ourbank.DAO.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.ourbank.DAO.Entities.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer> {

	
	//@Query("Select a from Admin a where a.matricule = :matricule and a.code = :code ")
	//public Admin RetrieveAdmin(@Param("matricule") String matricule, @Param("code") String code);

	@Query("Select a from Admin a where a.matricule = :matricule and a.code = :code ")
	public Admin RetrieveAdminbymatriculeandcode(@Param("matricule")String matricule,@Param("code")  String code);


}
