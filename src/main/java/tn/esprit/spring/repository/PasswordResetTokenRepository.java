package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken,Long> {
	PasswordResetToken findByToken(String token);
}
