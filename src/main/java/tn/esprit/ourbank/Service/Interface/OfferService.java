package tn.esprit.ourbank.Service.Interface;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.repository.query.Param;

import com.lowagie.text.DocumentException;

import tn.esprit.ourbank.DAO.Entities.Offer;



public interface OfferService {
	
	    List<Offer> retrieveAllOffers();
		
	    Offer addOffer(Offer o);
		
		void deleteOffer(int id);
		
		Offer updateOffer(int id ,Offer o);
		
		Offer retirieveOffer (int id);
		
		Offer updateOfferByid(int id, double promotion);

		void updatenbAjout(@Param("id") int id);
		
		public List<Offer> RetrieveOffersSup(@Param("nb") int nb);
		
		Offer addNewOffer(int nb);
		
		void updateScore(Offer o);
		
		void updateRisque(Offer o);
		
		void updatePromotion(Offer o);

		List<Offer> RecommandOffers (String name);
		
		int Activiteprevue(Offer o);
		
		void export(HttpServletResponse response) throws DocumentException, IOException ;
		
		double nbJours(Date Datedeb, Date datefin );
		
		Date AgeRetraite (Date Datedeb );
		
		void lesdates (Date Datedeb ,Date DateDebAct , Date DateInterruption , Date DateReprise );
}
