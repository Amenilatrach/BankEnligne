package esprit.tn.EnBank.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PortefeuilleOperation")
public  class PortefeuilleOperation {

	
	public enum statut {
        VALIDER,WAITING,REJECTED;
    }
	
	@Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id_Portefeuilleoperation;
	   
	   @Column(name = "date_operation")
		private Date date_operation;
	   
	   @Column(name = "date_valeur")
	   @Temporal(TemporalType.DATE)
		private Date date_valeur;
	   
	   @Column(name = "statut")
		private String statut;
	   
	   @Column(name = "commentaire")
		private String commentaire;
	   
	   
	   
	   @ManyToOne
	   @JsonIgnore 
	   @JoinColumn(name = "numeroCompte")
	   private Compte compte;

	   public PortefeuilleOperation() {
			super();
			// TODO Auto-generated constructor stub
		}
	   
	   
	   public PortefeuilleOperation(Date date_operation, Date date_valeur, String statut, String commentaire,Compte compte) {
			super();
			this.date_operation = date_operation;
			this.date_valeur = date_valeur;
			this.statut = statut;
			this.commentaire = commentaire;
			this.compte=compte;
		}

	public Long getId_Portefeuilleoperation() {
		return id_Portefeuilleoperation;
	}



	public void setId_Portefeuilleoperation(Long id_Portefeuilleoperation) {
		this.id_Portefeuilleoperation = id_Portefeuilleoperation;
	}



	public Date getDate_operation() {
		return date_operation;
	}



	public void setDate_operation(Date date_operation) {
		this.date_operation = date_operation;
	}



	public Date getDate_valeur() {
		return date_valeur;
	}



	public void setDate_valeur(Date date_valeur) {
		this.date_valeur = date_valeur;
	}



	public String getStatut() {
		return statut;
	}



	public void setStatut(String statut) {
		this.statut = statut;
	}



	public String getCommentaire() {
		return commentaire;
	}



	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}



	public Compte getCompte() {
		return compte;
	}



	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	   
	   
}
