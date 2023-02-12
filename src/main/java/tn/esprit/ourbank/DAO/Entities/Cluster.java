package tn.esprit.ourbank.DAO.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cluster {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clusterNumber;
	private double PromotionCentroid;
	//private double SocreCentroid;
	private double SommeCentroid;

	public Cluster(int clusterNumber, double promotionCentroid/*, /*double socreCentroid*/, double sommeCentroid) {
		super();
		PromotionCentroid = promotionCentroid;
		//SocreCentroid = socreCentroid;
		SommeCentroid = sommeCentroid;
		this.clusterNumber = clusterNumber;
	}

	

	public double getPromotionCentroid() {
		return PromotionCentroid;
	}

	public void setPromotionCentroid(double promotionCentroid) {
		PromotionCentroid = promotionCentroid;
	}

	/*
	public double getSocreCentroid() {
		return SocreCentroid;
	}

	public void setSocreCentroid(double socreCentroid) {
		SocreCentroid = socreCentroid;
	}
*/
	public double getSommeCentroid() {
		return SommeCentroid;
	}

	public void setSommeCentroid(double sommeCentroid) {
		SommeCentroid = sommeCentroid;
	}

	public int getClusterNumber() {
		return clusterNumber;
	}

	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}
	
	

	@Override
	public String toString() {
		return "Cluster [PromotionCentroid=" + PromotionCentroid + /*", SocreCentroid=" + SocreCentroid +*/ ", SommeCentroid="
				+ SommeCentroid + ", clusterNumber=" + clusterNumber + "]";
	}

	
	// Calcul Distance
	public double calculateDistance(Offer offre) {
		return Math.sqrt(Math.pow((getPromotionCentroid() - offre.getPromotion()), 2) /*+ Math.pow((getSocreCentroid() - offre.getScore()),2)*/ + Math.pow((getSommeCentroid() - offre.getSum()), 2));
    }

	public void updateCentroid(Offer offer) {
		setPromotionCentroid((getPromotionCentroid()+offer.getPromotion())/2);
		//setSocreCentroid((getSocreCentroid()+offer.getScore())/2);
		setSommeCentroid((getSommeCentroid()+offer.getSum())/2); 
	}

}
