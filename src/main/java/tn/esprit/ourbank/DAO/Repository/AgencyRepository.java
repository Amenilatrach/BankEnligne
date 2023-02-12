package tn.esprit.ourbank.DAO.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.ourbank.DAO.Entities.Agency;

//import tn.esprit.ourbank.DAO.Entities.Staff;

@Repository
public interface AgencyRepository extends CrudRepository<Agency, Integer> {

	//@Query("Select a from Agency a where a.location = :location")
	//	public List<Agency> RetrieveAgencyByLocation(@Param("location") String location);
	
	//@Query("SELECT DISTINCT a.location,s.pwd FROM Agency a JOIN a.staffs s ")
	//public List<String> RetrieveAgencyStaff();
	
	//@Query("SELECT s FROM Agency a RIGHT JOIN a.staffs s")
	//	public List<Staff> nbStaff();
	
	
	

	
	
	//*******************
	
	//SELECT A FROM A a LEFT JOIN a.B b WHERE b.id = null;
	
	//SELECT a, s  FROM Agency a RIGHT JOIN a.staff s
	//SELECT DISTINCT e FROM Employee e INNER JOIN e.tasks t
	//SELECT e, d  FROM Professor e LEFT JOIN e.department d
	//Select a from Agency a INNER JOIN s Staff ON a.id = s.agency_id
	//SELECT *
	//FROM A
	//INNER JOIN B ON A.key = B.key
}
