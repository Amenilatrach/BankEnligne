package esprit.tn.EnBank.Model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Retrait extends PortefeuilleOperation{

	
	@Column(name = "montant")
	private long montant;
	
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat(iso=ISO.DATE)
	//@JsonFormat(pattern="yyyy-MM-dd")

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "Date_retrait")
	private LocalDate Date_retrait;
	
	
	
	

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getMontant() {
		return montant;
	}

	public void setMontant(long montant) {
		this.montant = montant;
	}

	public LocalDate getDate_retrait() {
		return Date_retrait;
	}

	public void setDate_retrait(LocalDate date_retrait) {
		Date_retrait = date_retrait;
	}

	public Retrait(Date date_operation, Date date_valeur, String statut, String commentaire,
			esprit.tn.EnBank.Model.Compte compte) {
		super(date_operation, date_valeur, statut, commentaire, compte);
		// TODO Auto-generated constructor stub
	}

	public Retrait(long montant, LocalDate date_retrait) {
		super();
		this.montant = montant;
		Date_retrait = date_retrait;
	}

	
	
	
}
