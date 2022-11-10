package tn.esprit.ourbank.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.ourbank.DAO.Entities.Agency;
import tn.esprit.ourbank.DAO.Repository.AgencyRepository;
import tn.esprit.ourbank.Service.Interface.AgencyService;
//import tn.esprit.ourbank.DAO.Entities.Offer;
//import tn.esprit.ourbank.DAO.Entities.Staff;
//import tn.esprit.ourbank.DAO.Repository.StaffRepository;
@Slf4j
@Service
public class AgencyServiceImpl implements AgencyService {
	
	@Autowired
	AgencyRepository agencyRepository;
	
	//@Autowired
	//StaffRepository staffRepository;

	@Override
	public List<Agency> retrieveAllAgencies() {
		log.info("We are tryning to Retrieve All Agencies ");
		List<Agency> Agencies = (List<Agency>) (agencyRepository.findAll());
		return Agencies ;
	}

	@Override
	public Agency addAgency(Agency a) {
		log.info("We are tryning to add a new Agencie");
		return agencyRepository.save(a);
	}

	@Override
	public void deleteAgency(int id) {
		log.info("We are tryning to delete an agency");
		log.debug("idAgency : "  +id);
		
		agencyRepository.deleteById(id);;
		
	}

	@Override
	public Agency updateAgency(int id, Agency a) {
		log.info("We are tryning to Update an Agency ");
		Agency a1 = agencyRepository.findById(id).get();
		a1.setLocation(a.getLocation());
		a1.setBudget(a.getBudget());
		a1.setStaffNum(a.getStaffNum());
		return agencyRepository.save(a1);
	}

	@Override
	public Agency retirieveAgency(int id) {
		log.info("We are tryning to Retrieve an Agenciy by id");
		log.debug("Id : "  +id);
		
		return agencyRepository.findById(id).get();
	}

	@Override
	public Agency updateAgencyByid(int id, String location) {
		log.info("We are tryning to update an Agency by id");
		log.debug("Id : "  +id);
		log.debug("Location : "  +location);
		Agency a = agencyRepository.findById(id).get(); 
		 a.setLocation(location);
		 
		return agencyRepository.save(a);
	}
/*
	@Override
	public List<Agency> RetrieveAgencyByLocation(String location) {
		log.info("We are tryning to Retrieve Agencies by Location");
		log.debug("Location : "  +location);
		List<Agency> LstAgencie = (List<Agency>) (agencyRepository.RetrieveAgencyByLocation(location));
		return LstAgencie ;
	}

	@Override
	public List<String> RetrieveAgencyStafs() {
		List<String> Agencies = (List<String>) (agencyRepository.RetrieveAgencyStaff());
		return Agencies ;
	}

	@Override
	public void UpdateStaffAgency() {
		List<Agency> Agencie = (List<Agency>) (agencyRepository.findAll());
		List<Staff> Satffs = (List<Staff>) (staffRepository.findAll());
		for (Agency agency : Agencie) {
			for (Staff staff : Satffs) {
				if(agency.getLocation().equals(staff.getLocated())) {
					agency.getStaffs().add(staff);
					agencyRepository.save(agency);
				}
			}	
		}
		
		
	}

	@Override
	public int nbreStaff() {
		List<Agency> Agencies = (List<Agency>) (agencyRepository.findAll());
		for (Agency agency : Agencies) {
			int nb = agency.getStaffs().size();
			agency.setStaffNum(nb);
			agencyRepository.save(agency);
		}
		return 0;
	}
*/
}
