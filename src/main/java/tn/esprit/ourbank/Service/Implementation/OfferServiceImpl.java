package tn.esprit.ourbank.Service.Implementation;


import java.util.Date;
import java.util.List;
import java.awt.Color;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.*;

import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.ourbank.DAO.Entities.Offer;
import tn.esprit.ourbank.DAO.Entities.Risque;
import tn.esprit.ourbank.DAO.Repository.OfferRepository;
import tn.esprit.ourbank.Service.Interface.OfferService;


@Slf4j
@Service
public class OfferServiceImpl implements OfferService {
	
	
	@Autowired
	OfferRepository offerRepository;

	@Override
	public List<Offer> retrieveAllOffers() {
		log.info("We trying to retrieve all Offers Existing");
		List<Offer> Offers = (List<Offer>) (offerRepository.findAll());
		return Offers ;
	}

	@Override
	public Offer addOffer(Offer o) {
		log.info("We trying to Add a new Offer");
		switch (o.getDomain()) {
		case Consommation:
			if((o.getSum() < 1000) && (o.getSum() > 0) ){
				if((o.getSum() > 200) && (o.getSum() < 500)) {
					o.setScore(o.getScore()+0.9);
					if((o.getMaterial() > 1) && (o.getMaterial() < 4) ) {
						o.setScore(o.getScore()-0.1);
					}
					if((o.getMaterial() > 3) && (o.getMaterial() < 7) ) {
						o.setScore(o.getScore()-0.2);
					}
					if((o.getMaterial() > 6) && (o.getMaterial() < 9) ) {
						o.setScore(o.getScore()-0.3);
					}	
				}
				if ((o.getSum() > 500) && (o.getSum() < 750)) {
						o.setScore(o.getScore()+0.6);
						if((o.getMaterial() > 1) && (o.getMaterial() < 4) ) {
							o.setScore(o.getScore()-0.05);
						}
						if((o.getMaterial() > 3) && (o.getMaterial() < 7) ) {
							o.setScore(o.getScore()-0.1);
						}
						if((o.getMaterial() > 6) && (o.getMaterial() < 9) ) {
							o.setScore(o.getScore()-0.2);
						}
					}
				if ((o.getSum() > 750) && (o.getSum() < 1000)) {
					o.setScore(o.getScore()+0.4);
					if((o.getMaterial() > 1) && (o.getMaterial() < 4) ) {
						o.setScore(o.getScore()-0.05);
					}
					if((o.getMaterial() > 3) && (o.getMaterial() < 7) ) {
						o.setScore(o.getScore()-0.1);
					}
					if((o.getMaterial() > 6) && (o.getMaterial() < 9) ) {
						o.setScore(o.getScore()-0.2);
					}
				}
				if(o.getScore()>0.6) {
					o.setRisque(Risque.Bonne);
				}
				if((o.getScore()>0.4) && (o.getScore()<0.7)) {
					o.setRisque(Risque.Moyenne);
				}
				if((o.getScore()>0.0) && (o.getScore()<0.4)) {
					o.setRisque(Risque.Elevée);
				}
				return offerRepository.save(o);
				}
			else {
				System.out.println("Pas dans la marge");
			}
			break;
		case Construction:
			if((o.getSum() < 40000) && (o.getSum() > 0) ){
				if((o.getSum() > 1000) && (o.getSum() < 10000)) {
					o.setScore(o.getScore()+0.9);
					if((o.getMaterial() > 1) && (o.getMaterial() < 4) ) {
						o.setScore(o.getScore()-0.1);
					}
					if((o.getMaterial() > 3) && (o.getMaterial() < 7) ) {
						o.setScore(o.getScore()-0.2);
					}
					if((o.getMaterial() > 6) && (o.getMaterial() < 9) ) {
						o.setScore(o.getScore()-0.3);
					}	
				}
				if ((o.getSum() > 10000) && (o.getSum() < 25000)) {
						o.setScore(o.getScore()+0.6);
						if((o.getMaterial() > 1) && (o.getMaterial() < 4) ) {
							o.setScore(o.getScore()-0.05);
						}
						if((o.getMaterial() > 3) && (o.getMaterial() < 7) ) {
							o.setScore(o.getScore()-0.1);
						}
						if((o.getMaterial() > 6) && (o.getMaterial() < 9) ) {
							o.setScore(o.getScore()-0.2);
						}
				}
				if ((o.getSum() > 25000) && (o.getSum() < 40000)) {
					o.setScore(o.getScore()+0.4);
					if((o.getMaterial() > 1) && (o.getMaterial() < 4) ) {
						o.setScore(o.getScore()-0.05);
					}
					if((o.getMaterial() > 3) && (o.getMaterial() < 7) ) {
						o.setScore(o.getScore()-0.1);
					}
					if((o.getMaterial() > 6) && (o.getMaterial() < 9) ) {
						o.setScore(o.getScore()-0.2);
					}
				}
				o.setScore(o.getScore()+1);
				if(o.getScore()>1.6) {
					o.setRisque(Risque.Bonne);
				}
				if((o.getScore()>1.4) && (o.getScore()<1.7)) {
					o.setRisque(Risque.Moyenne);
				}
				if((o.getScore()>1.0) && (o.getScore()<1.4)) {
					o.setRisque(Risque.Elevée);
				}
				return offerRepository.save(o);
				}
			else {
				System.out.println("Pas dans la marge");
			}
			break;
		case Immobilier:
			if((o.getSum() < 25000) && (o.getSum() > 0) ){
				if((o.getSum() > 1000) && (o.getSum() < 10000)) {
					o.setScore(o.getScore()+0.9);
					if((o.getMaterial() > 1) && (o.getMaterial() < 4) ) {
						o.setScore(o.getScore()-0.1);
					}
					if((o.getMaterial() > 3) && (o.getMaterial() < 7) ) {
						o.setScore(o.getScore()-0.2);
					}
					if((o.getMaterial() > 6) && (o.getMaterial() < 9) ) {
						o.setScore(o.getScore()-0.3);
					}	
				}
				if ((o.getSum() > 10000) && (o.getSum() < 25000)) {
						o.setScore(o.getScore()+0.6);
						if((o.getMaterial() > 1) && (o.getMaterial() < 4) ) {
							o.setScore(o.getScore()-0.05);
						}
						if((o.getMaterial() > 3) && (o.getMaterial() < 7) ) {
							o.setScore(o.getScore()-0.1);
						}
						if((o.getMaterial() > 6) && (o.getMaterial() < 9) ) {
							o.setScore(o.getScore()-0.15);
						}
				}
				if ((o.getSum() > 25000) && (o.getSum() < 40000)) {
					o.setScore(o.getScore()+0.4);
					if((o.getMaterial() > 1) && (o.getMaterial() < 4) ) {
						o.setScore(o.getScore()-0.05);
					}
					if((o.getMaterial() > 3) && (o.getMaterial() < 7) ) {
						o.setScore(o.getScore()-0.1);
					}
					if((o.getMaterial() > 6) && (o.getMaterial() < 9) ) {
						o.setScore(o.getScore()-0.2);
					}
				}
				o.setScore(o.getScore()+2);
				if(o.getScore()>2.6) {
					o.setRisque(Risque.Bonne);
				}
				if((o.getScore()>2.4) && (o.getScore()<2.7)) {
					o.setRisque(Risque.Moyenne);
				}
				if((o.getScore()>2.0) && (o.getScore()<2.4)) {
					o.setRisque(Risque.Elevée);
				}
				return offerRepository.save(o);
				}
			else {
				System.out.println("Pas dans la marge");
			}
			break;
		case auto:
			if((o.getSum() < 30000) && (o.getSum() > 0) ){
				if((o.getSum() > 2000) && (o.getSum() < 10000)) {
					o.setScore(o.getScore()+0.9);
					if((o.getMaterial() > 1) && (o.getMaterial() < 4) ) {
						o.setScore(o.getScore()-0.1);
					}
					if((o.getMaterial() > 3) && (o.getMaterial() < 7) ) {
						o.setScore(o.getScore()-0.2);
					}
					if((o.getMaterial() > 6) && (o.getMaterial() < 9) ) {
						o.setScore(o.getScore()-0.3);
					}	
				}
				if ((o.getSum() > 10000) && (o.getSum() < 20000)) {
						o.setScore(o.getScore()+0.6);
						if((o.getMaterial() > 1) && (o.getMaterial() < 4) ) {
							o.setScore(o.getScore()-0.05);
						}
						if((o.getMaterial() > 3) && (o.getMaterial() < 7) ) {
							o.setScore(o.getScore()-0.1);
						}
						if((o.getMaterial() > 6) && (o.getMaterial() < 9) ) {
							o.setScore(o.getScore()-0.15);
						}
				}
				if ((o.getSum() > 20000) && (o.getSum() < 30000)) {
					o.setScore(o.getScore()+0.4);
					if((o.getMaterial() > 1) && (o.getMaterial() < 4) ) {
						o.setScore(o.getScore()-0.05);
					}
					if((o.getMaterial() > 3) && (o.getMaterial() < 7) ) {
						o.setScore(o.getScore()-0.1);
					}
					if((o.getMaterial() > 6) && (o.getMaterial() < 9) ) {
						o.setScore(o.getScore()-0.2);
					}
				}
				o.setScore(o.getScore()+3);
				if(o.getScore()>3.6) {
					o.setRisque(Risque.Bonne);
				}
				if((o.getScore()>3.4) && (o.getScore()<3.7)) {
					o.setRisque(Risque.Moyenne);
				}
				if((o.getScore()>3.0) && (o.getScore()<3.4)) {
					o.setRisque(Risque.Elevée);
				}
				return offerRepository.save(o);
				}
			else {
				System.out.println("Pas dans la marge");
			}
			break;
		default:
			break;
		}
		return null;
	}

	@Override
	public void deleteOffer(int id) {
		log.info("We trying to delete an Existing Offer");
		offerRepository.deleteById(id);
		
	}

	@Override
	public Offer updateOffer(int id,Offer o) {
		log.info("We trying to update an offer");
		Offer o1 = offerRepository.findById(id).get();
		o1.setName(o.getName());
		o1.setSum(o.getSum());
		o1.setPromotion(o.getPromotion());
		o1.setDomain(o.getDomain());
		o1.setDatedeb(o.getDatedeb());
		o1.setDatefin(o.getDatefin());
		o1.setFilename(o.getFilename());
		return offerRepository.save(o1);
	}

	@Override
	public Offer retirieveOffer(int id) {
		log.info("We trying to retrieve an Existing offer by id");
		log.debug("Id : "+id);
		return offerRepository.findById(id).get();
	}

	@Override
	public Offer updateOfferByid(int id, double promotion) {
		log.info("We trying to retrieve all Offers Existing");
		log.debug("Id : "+id);
		log.debug("Promotion : "+promotion);
		Offer o = offerRepository.findById(id).get(); 
		o.setPromotion(promotion);
		return offerRepository.save(o);
	}

	@Override
	public void updatenbAjout(int id) {
		log.info("We trying to update le nombre d'Ajout de chaque offre");
		log.debug("Id "+id);
		Offer o = offerRepository.findById(id).get(); 
		o.setNbajout(o.getNbajout()+1);
		offerRepository.save(o);
	}

	@Override
	public List<Offer> RetrieveOffersSup(int nb) {
		log.info("We trying to retrieve Offers which their nbajout greater than a certain number");
		log.debug("Nombre" +nb);
		List<Offer> Offers = (List<Offer>) (offerRepository.RetrieveOffersSup(nb));
		return Offers ;
	}

	@Override
	public Offer addNewOffer(int nb) {
		log.info("We trying to create a new offer from the Existing");
		List<Offer> Offers = (List<Offer>) (offerRepository.RetrieveOffersSup(nb));
		int nbr = Offers.size();
		log.debug("Taille de la Liste est = " +nbr);
		Double Somme = 0.0 ;
		Double promotion = 100.0;
		for (Offer offer : Offers) {
			log.debug("Offre = " +offer.toString());
			Somme = Somme + offer.getSum();
			if(offer.getPromotion()<promotion) {
				promotion = offer.getPromotion();
			}	
		}
		Somme = Somme / nbr;
		Offer o = new Offer();
		o.setName("BestOne");
		o.setPromotion(promotion);
		o.setMaterial(2);
		o.setSum(Somme);
		o.setImage("3");
		return offerRepository.save(o);	
	}

	
	@Override
	public void updateScore(Offer o) {
		log.info("We trying to update the score automatically through scheduler and cron");
		List<Offer> offers = (List<Offer>) (offerRepository.findAll());
		for (Offer offer : offers) {
			offerRepository.updateScore(offer);
			o.setScore(offer.getScore());
			offerRepository.save(o);
		}	
	}

	@Override
	public void updateRisque(Offer o) {
		log.info("We trying to update the Risque automatically through scheduler and cron");
		List<Offer> offers = (List<Offer>) (offerRepository.findAll());
		for (Offer offer : offers) {
			offerRepository.updateRisque(o);
			o.setRisque(offer.getRisque());
			offerRepository.save(o);
		}	
	}

	@Override
	public void updatePromotion(Offer o) {
		log.info("We trying to update the promotion automatically through scheduler and cron");
		List<Offer> offers = (List<Offer>) (offerRepository.findAll());
		for (Offer offer : offers) {
			offerRepository.updatePromotion(o);
			o.setPromotion(offer.getPromotion());
			offerRepository.save(o);
		}	
	}

	@Override
	public List<Offer> RecommandOffers(String name) {
		log.info("We trying to recommand for you offers with the same attitude");
		List<Offer> Offers = (List<Offer>) (offerRepository.Recommand(name));
		return  Offers;
	}
	 
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase("Offer_Name", font));
	         
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Offer_Budget", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Offer_Promotion", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Offer_Domain", font));
	        table.addCell(cell);
	         
	       // cell.setPhrase(new Phrase("Risque", font));
	       // table.addCell(cell);       
	    }
	     
	    
	    private void writeTableData(PdfPTable table) {
	    	List<Offer> ListOffers = (List<Offer>) (offerRepository.findAll());
	        for (Offer offer : ListOffers) {
	            table.addCell(offer.getName());
	            table.addCell(String.valueOf(offer.getSum()));
	            table.addCell(String.valueOf(offer.getPromotion()));
	            table.addCell(offer.getDomain().toString());
	            //table.addCell(offer.getRisque().toString());
	        }
	    }
	     
	    @Override
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(Color.BLUE);
	         
	        Paragraph p = new Paragraph("List of Offers", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	        
	        Image img = Image.getInstance("src/main/resources/ebankLogo4.png");
	        
	        PdfPTable table = new PdfPTable(4);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {2.5f, 3.5f, 3.0f, 3.0f/*, 1.5f*/});
	        table.setSpacingBefore(20);
	        
	         
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	        p.setAlignment(img.ALIGN_LEFT);
	        img.setSpacingBefore(5);
	        document.add(img); 
	        
	        document.close();
	         
	    }

		@Override
		public int Activiteprevue(Offer o) {
			int actPrev = 0;
			//actPrev = o.getDatefin().
			
			return 0;
		}

		@Override
		public double nbJours(Date datedeb, Date datefin) {
			 long diff = datefin.getTime() - datedeb.getTime();
	         long res =  (diff / (1000*60*60*24));
	         double trim = res/91;
	         System.out.println(res);
			return trim;
		}

		@Override
		public Date AgeRetraite(Date Datedeb) {
		
	        int year=Datedeb.getYear();
	        System.out.println(Datedeb);
	        System.out.println("Year for date object is : "+year);  
	        System.out.println("***To get current year add 1900 to the value of year obtained from this date object***");  
	        int currentYear=year+1960;  
	        System.out.println(currentYear);  
	        
	        
	        Date DateFin  = Datedeb;
	        DateFin.setYear(year+60);
	          System.out.println(DateFin);
	          
	          
	          long diff = DateFin.getTime() - Datedeb.getTime();
		      long res =  (diff / (1000*60*60*24));
		      double trim = res/91;
		      System.out.println(trim);
	          
			return DateFin;
			
			
		}
int k = 0;
double moyenne;
double Somme = 0 ;
		@Override
		public void lesdates(Date Datedeb, Date DateDebAct , Date DateInterruption, Date DateReprise) {
			int year=Datedeb.getYear();
	        System.out.println(Datedeb);
	        System.out.println("Year for date object is : "+year);  
	        System.out.println("***To get current year add 1900 to the value of year obtained from this date object***");  
	        int currentYear=year+1960;  
	        System.out.println(currentYear);  
	        Date DateFin  = Datedeb;
	        DateFin.setYear(year+60);
	          System.out.println(DateFin);
	          long diffInter = DateReprise.getTime() - DateInterruption.getTime();
		      long resInter =  (diffInter / (1000*60*60*24));
		      double trimInter = resInter/91;
		      System.out.println(trimInter);
	          long diff = DateFin.getTime() - DateDebAct.getTime();
		      long res =  (diff / (1000*60*60*24));
		      double trim = res/91;
		      double dureeActivitee = trim - trimInter;
		      System.out.println(trim);
		      System.out.println(dureeActivitee);
		      double DixPremierAns = 40; 
		      double resteAnnnee = dureeActivitee - DixPremierAns;
		      System.out.println(resteAnnnee);
		      double tauxPension = DixPremierAns*0.01 + resteAnnnee*0.005;
		      System.out.println(tauxPension);
		      double tauxPensionFinal ; 
		      if(tauxPension > 0.8) {
		    	  tauxPensionFinal = 0.8;
		      }else {
		    	  tauxPensionFinal = tauxPension;
				}
			System.out.println(tauxPensionFinal);
			Date dateToday = new Date();
			System.out.println(dateToday);
			
			
			 
			  long diffAge = DateFin.getTime() - dateToday.getTime();
		      long resAge =  (diffAge / (1000*60*60*24));
		      System.out.println(resAge);
		      double Age = resAge/356;
		      int Nar = (int)Age;
		      System.out.println(Age);
		      int f = 1 ;
		      
		      
		      double Salaire = 24000;
		      double tauxCroissance  = 0.06;
		      int smig = 28361;
		      int smigk = 27907;
		      double tauxSmig = 0.038;
		      double [] coefact = { 1.64085, 1.54639, 1.46599, 1.39991, 1.34961, 1.28142, 1.19252, 1.11663, 1.05707, 1.00000 };
		      double [] coefactK = { 1.60609, 1.55116, 1.46946, 1.38487, 1.31287, 1.25369, 1.20864, 1.14758, 1.06796, 1.00000 };

		      double[][] a = new double[13][8];

		      for (int i = 1; i <13 /*Nar+1*/; i++) {
		          
		    	
		    	
		            a[i][1] = (int) i;
		            a[i][2] = 2020 + i - 1 ;
		            a[i][3] = (int) (Salaire*Math.pow((1+tauxCroissance),i - 1));
		            a[i][4] = (int) (smigk*Math.pow((1+tauxSmig),i - 1));
		            if( a[i][4] > a[i][3] ) {
		            	a[i][5] = a[i][3];
		            }else {
		            	a[i][5] = a[i][4];
		            }
		            if(i>2 && k<10) {
		            a[i][6] =  coefactK[k];
		            k=k+1;
		            }
		            
		            a[i][7] = (int) a[i][5] * a[i][6] ;
		            
		            
		            if( a[i][7] > 0) {
		            	Somme = Somme + a[i][7];
		            }  
		        }

		      
		      moyenne = Somme /10 ;
		      
		        for (int i = 1; i < a.length ; i++) {
		          for (int j = 1; j < a[0].length ; j++) {
		            System.out.print(a[i][j] + "\t");
		            
		          }
		          System.out.println();
		        }

		        System.out.println(moyenne);
		      
		      
		      
		}

}
