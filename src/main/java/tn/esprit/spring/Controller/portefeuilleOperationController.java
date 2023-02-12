package esprit.tn.EnBank.Controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esprit.tn.EnBank.Dto.SaveOperationDto;
import esprit.tn.EnBank.Model.PortefeuilleOperation;
import esprit.tn.EnBank.Repository.PortefeuilleOperationRepository;
import esprit.tn.EnBank.Services.PortefeuilleOperationServiceImpl;




@Controller
@RestController
@RequestMapping("/PortefeuilleOperation")
@CrossOrigin(origins = "http://localhost:4200")
public class portefeuilleOperationController {

	@Autowired
	private PortefeuilleOperationRepository portefeuilleOperationRepository;
	@Autowired
	private PortefeuilleOperationServiceImpl portefeuilleOperationServiceImpl;

	 
	
	//@RequestMapping(value="/saveOperation", method=RequestMethod.POST)
	@PostMapping( value = "/saveOperation"  ,produces = "application/json")
	public ResponseEntity<String> saveOperation( @Valid @RequestBody SaveOperationDto saveOperationDto ){
		
		try{
			if(saveOperationDto.getTypeOperation().equals("VERSEMENT")){
			  
				portefeuilleOperationServiceImpl
				.verser(saveOperationDto);
			}
			else if(saveOperationDto.getTypeOperation().equals("RETIRER")){
				portefeuilleOperationServiceImpl.retirer(saveOperationDto);
			} 
			if (saveOperationDto.getTypeOperation().equals("VIREMENT")){
				portefeuilleOperationServiceImpl.virement(saveOperationDto);
			}
			if (saveOperationDto.getTypeOperation().equals("REMISECHEQUE")){
				portefeuilleOperationServiceImpl.remiseCheque(saveOperationDto);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body("Operation succes");
	}
	
	
/*@GetMapping(value = "operation/findAll")
blic ResponseEntity findAll() {
		List<PortefeuilleOperation> listOperation = new ArrayList<>();
		try {
			listOperation = portefeuilleOperationRepository.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(listOperation);
	}*/	
	@GetMapping(value = "/findOperationByCompte"  ,produces = "application/json")
	public List<PortefeuilleOperation>getPorteFeuille(@RequestParam Long id){
		return this.portefeuilleOperationServiceImpl.getPortefeuilleByCompte(id);
		
	}
	@GetMapping(value = "/findAllOpeartion"  ,produces = "application/json")
	public List<PortefeuilleOperation>getAllOperation(){
		return this.portefeuilleOperationRepository.findAll();
		
	}
	@DeleteMapping("/delete/{id}")
	public void deleteOperation(@PathVariable("id") Long id) {
		portefeuilleOperationRepository.deleteById(id);
		}
	
	
	/*@GetMapping("/export/releve")
    public void exportToPDF(HttpServletResponse response ,long compte) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=relevé" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        ArrayList<PortefeuilleOperation> pos = (ArrayList<PortefeuilleOperation>) portefeuilleOperationRepository.findAll();
         
        PDFExporter exporter = new PDFExporter(pos, compte);
        exporter.export(response);
         
    }
	
	@PostMapping("/addPortefeuilleOperation")
public ResponseEntity addPortefeuilleOperation(@RequestBody PortefeuilleOperation po) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(po);
	}*/
}
