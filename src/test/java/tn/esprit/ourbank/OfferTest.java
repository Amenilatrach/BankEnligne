package tn.esprit.ourbank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.ourbank.DAO.Entities.Domain;
import tn.esprit.ourbank.DAO.Entities.Offer;
import tn.esprit.ourbank.DAO.Repository.OfferRepository;


@SpringBootTest
public class OfferTest {

	@Autowired
	OfferRepository offerRepository;
	
	@Test
	public void testretrieveAllOffers(){
		List<Offer> Offers = (List<Offer>) offerRepository.findAll();
	    assertThat(Offers).size().isGreaterThan(0);
	}
	
	@Test
	public void testaddOffer() {
		Offer offer = Offer.builder()
				.name("Behya")
				.sum(5000)
				.material(5)
				.promotion(0.15)
				.domain(Domain.Consommation)
				.build();
		offerRepository.save(offer); 
	    assertThat(offer.getId()).isGreaterThan(0);
	}
	
	
	@Test
	public void testdeleteOffer() {
	    
        Offer offer = offerRepository.findById(22).get();
        offerRepository.delete(offer);
        Offer offer1 = null;
        Optional<Offer> optionalOffer = offerRepository.findById(22);
        if(optionalOffer.isPresent()){
        	offer1 = optionalOffer.get();
        }
        assertThat(offer1).isNull();
    }
	    
	
	
	@Test
	public void testupdateOffer() {
	    Offer offer = offerRepository.findById(18).get();
        offer.setSum(1000);
        offerRepository.save(offer);
        Offer offerUpdated = offerRepository.findById(18).get(); 
        assertThat(offerUpdated.getSum()).isEqualTo(1000);
	}
		
		 
	@Test
	public void testretirieveOffer () {
		Offer offer = offerRepository.findById(7).get();    
	    assertThat(offer.getId()).isEqualTo(7);
	}	
	
	
	
}
