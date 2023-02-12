package tn.esprit.ourbank.Controller;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import org.springframework.boot.json.JsonParseException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;

import tn.esprit.ourbank.DAO.Entities.Offer;
import tn.esprit.ourbank.DAO.Entities.SmsRequest;
import tn.esprit.ourbank.DAO.Entities.Userr;
import tn.esprit.ourbank.DAO.Repository.OfferRepository;
import tn.esprit.ourbank.DAO.Repository.UserrRepository;
import tn.esprit.ourbank.Dto.Response;
import tn.esprit.ourbank.Dto.UserDto;
import tn.esprit.ourbank.Service.Implementation.EmailSenderService;
import tn.esprit.ourbank.Service.Interface.OfferService;
import tn.esprit.ourbank.Service.Interface.SmsSenderService;

@CrossOrigin
@RestController
public class OfferRestController {
	
	@Autowired
	OfferService offerService;
	
	@Autowired 
	SmsSenderService smsSenderService;
	
	@Autowired
	EmailSenderService EmailService;
	
	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	UserrRepository userRepository;
	
	@GetMapping("allOffers")
	public List<Offer> GetAllOffers(){
		return offerService.retrieveAllOffers();
	}
	
	@GetMapping("nbjours/{datedeb}/{datefin}")
	public double nbjours(@PathVariable("datedeb") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datedeb , @PathVariable("datefin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datefin){
		return offerService.nbJours(datedeb, datefin);
	}
	
	
	@GetMapping("nbjours/{datedeb}")
	public Date annees(@PathVariable("datedeb") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datedeb){
		return offerService.AgeRetraite(datedeb);
	}
	
	
	@GetMapping("nbjours/{datedeb}/{datedebAct}/{dateInter}/{dateRepr}")
	public void lesdates(@PathVariable("datedeb") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datedeb,@PathVariable("datedebAct") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datedebAct, @PathVariable("dateInter") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateInter , @PathVariable("dateRepr") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateRepr){
		 offerService.lesdates(datedeb, datedebAct, dateInter, dateRepr);
	}
	
	
	
	
	@PostMapping("addOffer")
	public Offer ajouterOffer(@RequestBody Offer offer) throws MessagingException
		{
		/*EmailService.sendEmailWithAttachment("ahmed.hamadi@esprit.tn",
 				"Check out our latest and Special Offer",
 				"Brand New Offers",
				"C:\\Users\\MSI\\Desktop\\3eme\\Projet_PI\\NewOffer.jpg");*/
		smsSenderService.sendSms();
		offerService.addOffer(offer);
		
		return offer;
		}
	

    @GetMapping("retrieve_Offer/{id}")
    public Offer retrieveOffer(@PathVariable("id") int id) {
    	
    return offerService.retirieveOffer(id);
    } 
	
    
    @DeleteMapping("/delete_Offer/{id}")  
	public void deleteOfferById(@PathVariable("id")int id) {
    	offerService.deleteOffer(id);
	}
    
     @PutMapping("/modify_Offer_Promotion/{id}/{promotion}") 
    	public void mettreAjourOfferPromotion(@PathVariable("id") int id,@PathVariable("promotion") double promotion) {
    	 offerService.updateOfferByid(id, promotion);
    	}
	

     @PutMapping("/modifynbAjout/{id}") 
    	public void mettreAjourNbAjout(@PathVariable("id") int id) {
    	 offerService.updatenbAjout(id);
    	}
	
     @GetMapping("retrieve_OfferSup/{nb}")
     public List<Offer> retrieveOfferSup(@PathVariable("nb") int nb) {
    	 return offerService.RetrieveOffersSup(nb);
     }
     
     
     @PutMapping("editOffers/{id}")
     public Offer updateeOffer(@RequestBody Offer offer, @PathVariable int id) {
    	return offerService.updateOffer(id,offer); 
     }
     

 	@PostMapping("addNewOffer/{nb}")
 	public void ajouterNOffer(@PathVariable ("nb") int nb){
 		offerService.addNewOffer(nb);
 		}
 	//@Scheduled(cron="0 0 0 1 1/1 *")
 	//@Scheduled(fixedRateString = "PT1M")
 	@PutMapping("modify_Score") 
 	public void MofifierScore() throws NullPointerException {
 			Offer o = null;
 			offerService.updateScore(o);
 	}
 	
 	//@Scheduled(cron="0 0 0 1 1/1 *")
 	//@Scheduled(fixedRateString = "PT1M")
 	@PutMapping("modify_Risque") 
 	public void MofifierRisque() throws NullPointerException {
 			Offer o = null;
 			offerService.updateRisque(o);
 	}
 	//@Scheduled(cron="0 0 0 1 1/1 *")
 	//@Scheduled(fixedRateString = "PT1M")
 	@PutMapping("modify_Promotion") 
 	public void MofifierPromotion() throws NullPointerException {
 			Offer o = null;
 			offerService.updatePromotion(o);
 			
 	}
 	
 	@GetMapping("Recommand_Offer/{Offer_name}")
    public List<Offer> retrieveOffer(@PathVariable("Offer_name") String name) {	
 		return offerService.RecommandOffers(name);
    } 
 	
 	@GetMapping("SendMail")
 	public void triggerMail() throws MessagingException {

 		EmailService.sendEmailWithAttachment("ahmed.hamadi@esprit.tn",
 				"Check out our latest and Special Offer",
 				"Brand New Offers",
				"C:\\Users\\MSI\\Desktop\\3eme\\Projet_PI\\NewOffer.jpg");
	}
 	
 	
 	 @GetMapping("Offerpdf")
     public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
 		 response.setContentType("application/pdf");
         DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
         String currentDateTime = dateFormatter.format(new Date());
          
         String headerKey = "Content-Disposition";
         String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
         response.setHeader(headerKey, headerValue);

         offerService.export(response);
          
     }
 	 
 	@PostMapping
    public void sendSms() {
        smsSenderService.sendSms();
    }
 	
 	@Autowired  ServletContext context;
 	
 	@PostMapping("/Offer")
	 public ResponseEntity<Response> createArticle (@RequestParam("file") MultipartFile file,
			 @RequestParam("offer") String offer) throws JsonParseException , JsonMappingException , Exception
	 {
		 System.out.println("Ok .............");
       Offer offre = new ObjectMapper().readValue(offer, Offer.class);
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

      
       offre.setFilename(newFileName);
       Offer offr = offerRepository.save(offre);
       
       if (offr != null)
       {
    	   smsSenderService.sendSms();
    	   EmailService.sendEmailWithAttachment("ahmed.hamadi@esprit.tn",
    				"Check out our latest and Special Offer",
    				"Brand New Offers",
   				"C:\\Users\\MSI\\Desktop\\3eme\\Projet_PI\\NewOffer.jpg");
       	return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
       }
       else
       {
       	return new ResponseEntity<Response>(new Response ("Article not saved"),HttpStatus.BAD_REQUEST);	
       }
	 }
 	
 	@GetMapping(path="/Imgarticles/{id}")
	 public byte[] getPhoto(@PathVariable("id") int id) throws Exception{
		 Offer offre   = offerRepository.findById(id).get();
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+offre.getFilename()));
	 }
 	
	@GetMapping("getIdUser/{matricule}/{code}")
	public int GetUserId(@PathVariable("matricule") String matricule ,@PathVariable("code") String code) {
	
		Userr user = userRepository.RetrieveUserbymatriculeandcode(matricule, code);
		int id = user.getIdUser();
		return id;
		
	}
 	
 	
}
