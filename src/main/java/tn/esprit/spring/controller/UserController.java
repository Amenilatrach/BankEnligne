package tn.esprit.spring.controller;

import tn.esprit.spring.dto.ResponseDto;
import tn.esprit.spring.entity.ForgotPasswordHttpRequest;
import tn.esprit.spring.entity.PasswordResetToken;
import tn.esprit.spring.entity.ResetPasswordHttpRequest;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.PasswordResetTokenRepository;
import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
   
    @Autowired
    private EmailSenderService mailSendService ;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository ;
    
    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }
  
    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
    @PutMapping("/updateUser")
	@ResponseBody
	public User updateUser(@RequestBody User u) {
	return userService.UpdateUser(u);
	}
    @DeleteMapping("/DeleteUser/{userName}")
    public void DeleteUser(@PathVariable("userName") String userName){
    	userService.deleteUser(userName); 
    }

    
//    @PutMapping("/changePassword/{token}/{Newpassword}")
//	public ResponseEntity<ResponseDto> changepass(@PathVariable("token") String token,@PathVariable("Newpassword") String Newpassword) {
//		PasswordResetToken t = passwordResetTokenRepository.findByToken(token);
//		User user = t.getUser();
//		userService.changeUserPassword(user,Newpassword);
//        ResponseDto response = new ResponseDto("Reset password", user);
//
//		return new ResponseEntity<>(response, HttpStatus.OK);
//		
//			
//	}
    @PostMapping("/forgot-password/{email}")
    public ResponseEntity<ResponseDto> forgotPassword(@PathVariable("email") String email){
        String user = userService.forgotPassword(email);
        ResponseDto response = new ResponseDto("Forgot password", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/reset-password")
    public User sendPasswordResetLink(@RequestBody ResetPasswordHttpRequest resetPasswordHttpRequest) {
        return userService.resetPassword(resetPasswordHttpRequest);
    }
    
}