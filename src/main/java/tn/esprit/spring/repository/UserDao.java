package tn.esprit.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {

	User findByEmail(String email);






	

	 
}