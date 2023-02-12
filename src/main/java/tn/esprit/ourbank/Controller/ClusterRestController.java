package tn.esprit.ourbank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.ourbank.DAO.Entities.Offer;
import tn.esprit.ourbank.Service.Interface.ClusterService;
@CrossOrigin
@RestController
public class ClusterRestController {
	
	@Autowired
	ClusterService clusterService;
	
	@GetMapping("ShowClusters")
	public void GetAllOffers(){
		int clusterNumber = 3;
		clusterService.genereateRecord();
		clusterService.initiateClusterAndCentroid(clusterNumber);
		clusterService.printRecordInformation();
		clusterService.printClusterInformation();
	}
	
	@PutMapping("UpdateClustersOffers")
	public void UpdateOfferClusters(Integer clusternumber ,Integer id ,Offer o){
		int clusterNumber = 3;
		clusterService.genereateRecord();
		clusterService.initiateClusterAndCentroid(clusterNumber);
		clusterService.printRecordInformation();
		clusterService.printClusterInformation();
		clusterService.updateClusterNumber(clusternumber, id, o);
	}

}
