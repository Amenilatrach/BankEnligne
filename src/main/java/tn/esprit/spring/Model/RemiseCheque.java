package esprit.tn.EnBank.Model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



@Entity
public class RemiseCheque extends PortefeuilleOperation{

	@Column(name = "num_cheque")
	private long num_cheque;
	@Column(name = "cin")
	private String cin;
	@Column(name = "date_remise")
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate Date_remise;
	@Column(name = "montant")
	private long montant;
	
	
	public RemiseCheque() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public long getNum_cheque() {
		return num_cheque;
	}

	public void setNum_cheque(long num_cheque) {
		this.num_cheque = num_cheque;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	

	public LocalDate getDate_remise() {
		return Date_remise;
	}

	public void setDate_remise(LocalDate date_remise) {
		Date_remise = date_remise;
	}

	public RemiseCheque(long num_cheque, String cin, LocalDate date_remise, long montant) {
		super();
		this.num_cheque = num_cheque;
		this.cin = cin;
		Date_remise = date_remise;
		this.montant = montant;
	}

	public RemiseCheque(Date date_operation, Date date_valeur, String statut, String commentaire,
			esprit.tn.EnBank.Model.Compte compte) {
		super(date_operation, date_valeur, statut, commentaire, compte);
		// TODO Auto-generated constructor stub
	}

	public long getMontant() {
		return montant;
	}

	public void setMontant(long montant) {
		this.montant = montant;
	}
	
	
	
	
}
