package tn.esprit.ourbank.DAO.Entities;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter

public class Offer implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double sum;
    private int material;
    private String image;
    private double promotion;
    private int nbajout;
    @Enumerated(EnumType.STRING)
    private Domain domain;
    @Enumerated(EnumType.STRING)
    private Risque risque;
    private String filename;
    
    private int clusterNumber;
    @Temporal(TemporalType.DATE)
    
    private Date datedeb;
    @Temporal(TemporalType.DATE)
    private Date datefin;
    
    
    
 
    public Offer(int id, double sum, double promotion, double score) {
		super();
		this.id = id;
		this.sum = sum;
		this.promotion = promotion;
		this.score = score;
	}
    
    public Offer(int id, String name, double sum, double promotion, int nbajout, int clusterNumber, Date datedeb,
			Date datefin, double score) {
		super();
		this.id = id;
		this.name = name;
		this.sum = sum;
		this.promotion = promotion;
		this.nbajout = nbajout;
		this.clusterNumber = clusterNumber;
		this.datedeb = datedeb;
		this.datefin = datefin;
		this.score = score;
	}

	private double score = 0.0;
    

  




	@Override
	public String toString() {
		return "Offer [id=" + id + ", sum=" + sum + ", promotion=" + promotion + ", clusterNumber=" + clusterNumber
				+ ", score=" + score + "]";
	}




	

    
   
}
