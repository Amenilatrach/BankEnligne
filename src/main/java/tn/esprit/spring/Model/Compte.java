package esprit.tn.EnBank.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
public class Compte {

	public enum TypeCompte {
        EPARGNE,COURANT;
    }
	public enum CategorieCompte {
        SILVER,GOLD,PLATINUM;
    }
	
	 	@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	 	private Long numeroCompte;

	    @JsonProperty(access =JsonProperty.Access.READ_ONLY)
	    private boolean etatCompte;

	    @JsonProperty(access =JsonProperty.Access.READ_ONLY)
	    @Temporal(TemporalType.DATE)
	    private Date dateCreation;

	    private Long soldeCompte;

	    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	    private String motDePasse;

	    @Enumerated(EnumType.STRING)
	    private TypeCompte typeCompte;

	    @Enumerated(EnumType.STRING)
	    private CategorieCompte categorieCompte ;
	    
	    
	    
	    @OneToMany(mappedBy = "id_Portefeuilleoperation")
	 
	    private List<PortefeuilleOperation> PortefeuilleoperationList = new ArrayList<>();



		public Long getNumeroCompte() {
			return numeroCompte;
		}



		public void setNumeroCompte(Long numeroCompte) {
			this.numeroCompte = numeroCompte;
		}



		public boolean isEtatCompte() {
			return etatCompte;
		}



		public void setEtatCompte(boolean etatCompte) {
			this.etatCompte = etatCompte;
		}



		public Date getDateCreation() {
			return dateCreation;
		}



		public void setDateCreation(Date dateCreation) {
			this.dateCreation = dateCreation;
		}



		public Long getSoldeCompte() {
			return soldeCompte;
		}



		public void setSoldeCompte(Long soldeCompte) {
			this.soldeCompte = soldeCompte;
		}



		public String getMotDePasse() {
			return motDePasse;
		}



		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}



		public TypeCompte getTypeCompte() {
			return typeCompte;
		}



		public void setTypeCompte(TypeCompte typeCompte) {
			this.typeCompte = typeCompte;
		}



		public CategorieCompte getCategorieCompte() {
			return categorieCompte;
		}



		public void setCategorieCompte(CategorieCompte categorieCompte) {
			this.categorieCompte = categorieCompte;
		}



		public List<PortefeuilleOperation> getPortefeuilleoperationList() {
			return PortefeuilleoperationList;
		}



		public void setPortefeuilleoperationList(List<PortefeuilleOperation> portefeuilleoperationList) {
			PortefeuilleoperationList = portefeuilleoperationList;
		}



		public Compte(Long numeroCompte, boolean etatCompte, Date dateCreation, Long soldeCompte, String motDePasse,
				TypeCompte typeCompte, CategorieCompte categorieCompte,
				List<PortefeuilleOperation> portefeuilleoperationList) {
			super();
			this.numeroCompte = numeroCompte;
			this.etatCompte = etatCompte;
			this.dateCreation = dateCreation;
			this.soldeCompte = soldeCompte;
			this.motDePasse = motDePasse;
			this.typeCompte = typeCompte;
			this.categorieCompte = categorieCompte;
			PortefeuilleoperationList = portefeuilleoperationList;
		}



		public Compte() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    

}
