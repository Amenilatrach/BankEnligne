package tn.esprit.ourbank.Service.Implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.ourbank.DAO.Entities.Cluster;
import tn.esprit.ourbank.DAO.Entities.Offer;
import tn.esprit.ourbank.DAO.Repository.OfferRepository;
import tn.esprit.ourbank.Service.Interface.ClusterService;

@Service
public class ClusterServiceImpl implements ClusterService {
	
	@Autowired
	OfferRepository offerRepository;
	
	List<Offer> data = new ArrayList<Offer>();
	List<Cluster> clusters = new ArrayList<Cluster>();
	Map<Cluster, List<Offer>> clusterOffers = new HashMap<Cluster, List<Offer>>();
	
	@Override
	public void genereateRecord() {
		List<Offer> Offers = (List<Offer>) (offerRepository.findAll());
		for (Offer offer : Offers) {
			Offer o = new Offer(offer.getId(), offer.getSum(), offer.getPromotion()/*, offer.getScore()*/);
			data.add(o);
		}
	}

	@Override
	public void initiateClusterAndCentroid(int clusterNumber) {
		int counter = 1;
		Iterator<Offer> iterator = data.iterator();
		Offer offer = null;
		
		while(iterator.hasNext()) {
			offer = iterator.next();
			if(counter <= clusterNumber) {
				offer.setClusterNumber(counter);
				initializeCluster(counter, offer);
				counter++;
			}else {
				System.out.println(offer);
				System.out.println("** Cluster Information **");
				for(Cluster cluster : clusters) {
					System.out.println(cluster);
				}
				System.out.println("*********************");
                double minDistance = Integer.MAX_VALUE;
                Cluster whichCluster = null;
                
                for(Cluster cluster : clusters) {
                	double distance = cluster.calculateDistance(offer);
                	System.out.println(distance);
                	if(minDistance > distance) {
                		minDistance = distance;
                		whichCluster = cluster;
                	}
                }
                
                offer.setClusterNumber(whichCluster.getClusterNumber());
				whichCluster.updateCentroid(offer);
				clusterOffers.get(whichCluster).add(offer);

			}
			
			System.out.println("** Cluster Information **");
			for(Cluster cluster : clusters) {
				System.out.println(cluster);
			}
			System.out.println("*********************");

			
		}

	}

	@Override
	public void initializeCluster(int clusterNumber, Offer offer) {
		
		Cluster cluster = new Cluster(clusterNumber,offer.getPromotion()/*,offer.getScore()*/,offer.getSum());
		clusters.add(cluster);
		List<Offer> clusterRecord = new ArrayList<Offer>();
		clusterRecord.add(offer);
		clusterOffers.put(cluster, clusterRecord);

	}

	@Override
	public void printRecordInformation() {
		   System.out.println("****** Each Offer INFORMATIN *********");
		   for(Offer offer : data) {
			   System.out.println(offer.toString());
		   }
	   }

	@Override
	public void printClusterInformation() {
	   System.out.println("****** FINAL CLUSTER INFORMATIN *********");
	   for (Map.Entry<Cluster, List<Offer>> entry : clusterOffers.entrySet())  {
        System.out.println("Key = " + entry.getKey() + 
                         ", Value = " + entry.getValue().toString()); 
    }
  }
	

	@Override
	public void updateClusterNumber(Integer clusterNumber, Integer id, Offer o) {
		List<Offer> Offers = (List<Offer>) (offerRepository.findAll());
		for(Offer offre : data) {  
			for (Offer offer : Offers) {
					id = offer.getId();
				   int i= 0;
				   if(offre.getId()==id) {
					   clusterNumber = offre.getClusterNumber();
					   o.setClusterNumber(clusterNumber);
					   offerRepository.updateClusterNumber(clusterNumber, id);
				   }
				}
			}
		}		
		
	
	
	
}
