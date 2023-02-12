package tn.esprit.ourbank.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.ourbank.DAO.Entities.Formation;
import tn.esprit.ourbank.DAO.Repository.FormationRepository;
import tn.esprit.ourbank.Dto.Response;
import tn.esprit.ourbank.Service.Interface.FormationService;

@CrossOrigin
@RestController
public class FormationController {
	
	@Autowired
	FormationRepository formationRepository;
	
	@Autowired
	FormationService formationService;

	
	@GetMapping("allFormations")
	public List<Formation> GetAllFormations(){
		return formationService.retrieveAllFormations();
	}
	
	@Autowired  ServletContext context;
 	
 	@PostMapping("/Formation")
	 public ResponseEntity<Response> createFormation (@RequestParam("file") MultipartFile file,
			 @RequestParam("formation") String formation) throws JsonParseException , JsonMappingException , Exception
	 {
		 System.out.println("Ok .............");
       Formation f = new ObjectMapper().readValue(formation, Formation.class);
       boolean isExit = new File(context.getRealPath("/Images/")).exists();
       if (!isExit)
       {
       	new File (context.getRealPath("/Images/")).mkdir();
       	System.out.println("mk dir.............");
       }
       String filenamee = file.getOriginalFilename();
       String newFileName = FilenameUtils.getBaseName(filenamee)+"."+FilenameUtils.getExtension(filenamee);
       File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
       try
       {
       	System.out.println("Image");
       	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
       	 
       }catch(Exception e) {
       	e.printStackTrace();
       }

      
       f.setFilename(newFileName);
       Formation formationn = formationRepository.save(f);
       
       if (formationn != null)
       {
    	   //smsSenderService.sendSms();
    	   //EmailService.sendEmailWithAttachment("ahmed.hamadi@esprit.tn",
    		//		"Check out our latest and Special Offer",
    		//		"Brand New Offers",
   			//	"C:\\Users\\MSI\\Desktop\\3eme\\Projet_PI\\NewOffer.jpg");
       	return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
       }
       else
       {
       	return new ResponseEntity<Response>(new Response ("Article not saved"),HttpStatus.BAD_REQUEST);	
       }
	 }
 	
 	@GetMapping(path="/FormationArticle/{id}")
	 public byte[] getPhoto(@PathVariable("id") int id) throws Exception{
		 Formation formation   = formationRepository.findById(id).get();
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+formation.getFilename()));
	 }


}
