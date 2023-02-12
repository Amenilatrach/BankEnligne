package tn.esprit.ourbank.DAO.Entities;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ProduitsDerives {
	
	 @Id
	 @GeneratedValue (strategy = GenerationType.IDENTITY)
	 private int id;
	 private int nombre;
	 private String entreprise;
	 private int maturite;
	 private double prixUnitaire;
	 private double coupon;
	 
	 @ManyToOne
		@JsonBackReference
		@JoinColumn(name="id_User_pd", referencedColumnName = "idUser")
		public Userr userr;
	 
	 @Enumerated(EnumType.STRING)
	 private typeProduitsDerivees type;

}
