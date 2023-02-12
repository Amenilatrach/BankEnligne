package tn.esprit.ourbank.Service.Interface;


import tn.esprit.ourbank.DAO.Entities.Offer;

public interface ClusterService {
	
	 void genereateRecord();
	 
	 void initiateClusterAndCentroid(int clusterNumber);
	 
	 void initializeCluster(int clusterNumber, Offer offer);
	 
	 void printRecordInformation();
	 
	 void printClusterInformation();
	 
	 void updateClusterNumber(Integer clusterNumber, Integer id ,Offer o);


}
