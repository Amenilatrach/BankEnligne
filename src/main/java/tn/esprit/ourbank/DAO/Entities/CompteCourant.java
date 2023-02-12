package tn.esprit.ourbank.DAO.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class CompteCourant {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcptCC;
	private double solde;
	
	@Temporal(TemporalType.DATE)
	private Date datedecreation;
	
	private String numeroCompte;
    
    
    @ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_UserCC", referencedColumnName = "idUser")
	public Userr user;
	
}
