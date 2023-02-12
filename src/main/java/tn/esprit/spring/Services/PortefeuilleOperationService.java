package esprit.tn.EnBank.Services;

import java.math.BigDecimal;
import java.util.Date;

import esprit.tn.EnBank.Dto.SaveOperationDto;




public interface PortefeuilleOperationService {

	public void verser(SaveOperationDto saveOperationDto);
	public void retirer(SaveOperationDto saveOperationDto);
	public void virement(SaveOperationDto saveOperationDto);
	public void remiseCheque(SaveOperationDto saveOperationDto);
	//PortefeuilleOperation addPortefeuilleOperation(PortefeuilleOperation po);
}
