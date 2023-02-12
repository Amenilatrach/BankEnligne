package tn.esprit.ourbank.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.ourbank.Dto.AdminDto;
import tn.esprit.ourbank.DAO.Entities.Admin;
import tn.esprit.ourbank.Service.Interface.InterfaceAdmin;
import tn.esprit.ourbank.DAO.Repository.AdminRepository;


@Service
public class AdminServic_Impl implements InterfaceAdmin {

	@Autowired
	AdminRepository adminRepository;

	@Override
	public void ajouterAdmin(Admin a) {
		adminRepository.save(a);
	}

	@Override
	public List<Admin> retrieveAllAdmins() {
		List<Admin> Admins = (List<Admin>) (adminRepository.findAll());
		return Admins ;
	}

	

	

	@Override
	public Admin Authentication(AdminDto adminDto) {
		return adminRepository.RetrieveAdminbymatriculeandcode(adminDto.getMatricule(), adminDto.getCode());
	}

	

	
}
