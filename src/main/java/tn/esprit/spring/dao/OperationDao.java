package tn.esprit.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Operation;

@Repository
public interface OperationDao extends JpaRepository<Operation,Long> {
}
