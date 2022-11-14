package tn.esprit.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CompteBancaire;

@Repository
public interface CompteBancaireDao extends JpaRepository<CompteBancaire,Long> {

}
