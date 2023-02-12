package esprit.tn.EnBank.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
public class Virement extends PortefeuilleOperation{

	
	@Column(name = "num_compte_recepteur")
	private long num_compte_recepteur;
	@Column(name = "montant")
	private long montant;
	@Column(name = "date_virement")
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso=ISO.DATE)
	private Date Date_virement;
	
	public Virement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Virement( Date date_operation, Date date_valeur, String statut,long num_compte_recepteur,Date Date_virement, String commentaire,
			Compte compte) {
		super( date_operation, date_valeur, statut, commentaire, compte);
		this.num_compte_recepteur=num_compte_recepteur;
		this.Date_virement=Date_virement;
	}


	public long getNum_compte_recepteur() {
		return num_compte_recepteur;
	}


	public void setNum_compte_recepteur(long num_compte_recepteur) {
		this.num_compte_recepteur = num_compte_recepteur;
	}


	public long getMontant() {
		return montant;
	}


	public void setMontant(long montant) {
		this.montant = montant;
	}


	public Date getDate_virement() {
		return Date_virement;
	}


	public void setDate_virement(Date date_virement) {
		Date_virement = date_virement;
	}

	
	
	
}
