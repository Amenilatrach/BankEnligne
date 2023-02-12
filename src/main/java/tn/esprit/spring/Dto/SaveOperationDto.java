package esprit.tn.EnBank.Dto;

import java.time.LocalDate;
public class SaveOperationDto {
	
	private String typeOperation;  
	private Long codeCpte;
	private Long montant;
	private Long codeCompte2;
	private String commentaire;
	private Long numCheque;
	private String cin;
	private LocalDate Date_remise;
	private LocalDate Date_retrait;
	private Statut statut;  
	private String nom_expediteur;
	private Long num_compte_recepteur;
	private LocalDate Date_virement;
	
	public enum Statut {
        VALIDER,WAITING,REJECTED;
    }
	
	public SaveOperationDto() {
		super();
	}

	public void setDate_remise(LocalDate date_remise) {
		Date_remise = date_remise;
	}

	public SaveOperationDto(String typeOperation, Long codeCpte, Long montant, Long codeCompte2, String commentaire,
			Long numCheque, String cin, LocalDate date_remise, LocalDate date_retrait, Statut statut,
			String nom_expediteur, Long num_compte_recepteur, LocalDate date_virement) {
		super();
		this.typeOperation = typeOperation;
		this.codeCpte = codeCpte;
		this.montant = montant;
		this.codeCompte2 = codeCompte2;
		this.commentaire = commentaire;
		this.numCheque = numCheque;
		this.cin = cin;
		Date_remise = date_remise;
		Date_retrait = date_retrait;
		this.statut = statut;
		this.nom_expediteur = nom_expediteur;
		this.num_compte_recepteur = num_compte_recepteur;
		Date_virement = date_virement;
	}

	public void setDate_retrait(LocalDate date_retrait) {
		Date_retrait = date_retrait;
	}

	public void setDate_virement(LocalDate date_virement) {
		Date_virement = date_virement;
	}

	public String getTypeOperation() {
		return typeOperation;
	}
	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}
	public Long getCodeCpte() {
		return codeCpte;
	}
	public void setCodeCpte(Long codeCpte) {
		this.codeCpte = codeCpte;
	}
	public Long getMontant() {
		return montant;
	}
	public void setMontant(Long montant) {
		this.montant = montant;
	}
	public Long getCodeCompte2() {
		return codeCompte2;
	}
	public void setCodeCompte2(Long codeCompte2) {
		this.codeCompte2 = codeCompte2;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Long getNumCheque() {
		return numCheque;
	}
	public void setNumCheque(Long numCheque) {
		this.numCheque = numCheque;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom_expediteur() {
		return nom_expediteur;
	}
	public void setNom_expediteur(String nom_expediteur) {
		this.nom_expediteur = nom_expediteur;
	}
	public Long getNum_compte_recepteur() {
		return num_compte_recepteur;
	}
	public void setNum_compte_recepteur(Long num_compte_recepteur) {
		this.num_compte_recepteur = num_compte_recepteur;
	}
	
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public LocalDate getDate_remise() {
		return Date_remise;
	}

	public LocalDate getDate_retrait() {
		return Date_retrait;
	}

	public LocalDate getDate_virement() {
		return Date_virement;
	}
	
	
	

}
