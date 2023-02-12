package tn.esprit.ourbank.Service.Interface;

import java.util.List;

import tn.esprit.ourbank.Dto.AdminDto;
import tn.esprit.ourbank.DAO.Entities.Admin;


public interface InterfaceAdmin {

	void ajouterAdmin(Admin a);
	
    List<Admin> retrieveAllAdmins();

    //void SignIn (String matricule,String code);
    
    Admin Authentication (AdminDto adminDto);
	
}
