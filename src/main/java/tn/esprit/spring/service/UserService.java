package tn.esprit.spring.service;

import tn.esprit.spring.configuration.JwtRequestFilter;
import tn.esprit.spring.constents.UserConstants;
import tn.esprit.spring.entity.ForgotPasswordHttpRequest;
import tn.esprit.spring.entity.Mail;
import tn.esprit.spring.entity.PasswordResetToken;
import tn.esprit.spring.entity.ResetPasswordHttpRequest;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.PasswordResetTokenRepository;
import tn.esprit.spring.repository.RoleDao;
import tn.esprit.spring.repository.UserDao;
import tn.esprit.spring.util.JwtUtil;
import tn.esprit.spring.util.UserUtils;
import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;
    
    
    @Autowired
    private EmailSenderService mailSendService ;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	 @Autowired
	    private JavaMailSender mailSender;
	
	 @Autowired
	 JwtRequestFilter jwtRequestFilter;
	
	 @Autowired
	 private PasswordResetTokenRepository passwordResetTokenRepository ;
	 
	  @ConfigProperty
	    public Long duration;
	 @ConfigProperty
	    public String issuer;

    
    public static final String PATH_OF_API = "http://localhost:4200";
    

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

       User adminUser = new User();

        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setConfirmUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setEmail("admin@gmail.com");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);
        
        adminUser.setUserName("ahmed");
        adminUser.setUserPassword(getEncodedPassword("ahmed@pass"));
        adminUser.setConfirmUserPassword(getEncodedPassword("ahmed@pass"));
        adminUser.setUserFirstName("ahmed");
        adminUser.setUserLastName("cherif");
        adminUser.setEmail("ahmed@gmail.com");
        Set<Role> adminRoless = new HashSet<>();
        adminRoless.add(adminRole);
        adminUser.setRole(adminRoless);
        userDao.save(adminUser);
        

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setConfirmUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        user.setEmail("raj@gmail.com");
//        Set<Role> userRoles = new HashSet<>();
//       userRoles.add(userRole);
//       user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        user.setConfirmUserPassword(getEncodedPassword(user.getConfirmUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
	public User findById(String userName) {
		return this.userDao.findById(userName).get();
	}

    public User UpdateUser(User user) {
    	user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        user.setConfirmUserPassword(getEncodedPassword(user.getConfirmUserPassword()));

		return userDao.save(user);

	}
    public void deleteUser(String userName) {

    	userDao.deleteById(userName);
	}
    public User findByEmail(String email) {
		return userDao.findByEmail(email);
    	
    }

	public void changeUserPassword(User user, String UserPassword) {
	    user.setUserPassword(new BCryptPasswordEncoder().encode(UserPassword));
	    userDao.save(user);
	}
   
    public User resetPassword(ResetPasswordHttpRequest resetPasswordHttpRequest) {
        User user = userDao.findByEmail(resetPasswordHttpRequest.getEmail());
        if(user != null){
            user.setUserPassword(passwordEncoder.encode(resetPasswordHttpRequest.getPassword()));
            return userDao.save(user);
        }

        return null;
    }
    
    
    public String forgotPassword(String email) {
    	 User user = userDao.findByEmail(email);
            String token = UUID.randomUUID().toString();
          
                  PasswordResetToken passwordResetToken = new PasswordResetToken();
                  passwordResetToken.setToken(token);
                   passwordResetToken.setUser(user);
                  passwordResetTokenRepository.save(passwordResetToken);


            user = userDao.save(user);
            String url =  String.format(PATH_OF_API + "/resetPassword?email=%s&token=%s", user.getEmail(), token);
            String emailContent = String.format("Please, click on this link [%s] in order to reset your password !", url);

            mailSendService.sendSimpleEmail(user.getEmail(), "Reset Password", "Hi " + user.getUserName() + "\n"
                    + "You're receiving this email because you requested a password reset \n"
                    + "Click the following link to change the password : " + emailContent);
 

        return "Check Your Email To Change The Password";
    }
 
}
