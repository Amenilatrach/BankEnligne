package tn.esprit.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import tn.esprit.spring.entity.User;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return User.class.equals(clazz);
	}


	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if(user.getUserPassword().length() <=6) {
			errors.rejectValue("password","Length","Password must be at least 6 characters");
		}
		if(!user.getUserPassword().equals(user.getConfirmUserPassword())) {
			errors.rejectValue("confirmPassword","Match","Password must match");
		}		
	}

}
