package tn.esprit.ourbank.DAO.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "T_User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Userr {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idUser; // Clé primaire
	@Column(nullable = false)
	private String username;
	@Column(unique=true , nullable = false)
	private String matricule;
	@Column(nullable = false)
	private String code;
	
	@ManyToOne
	@JsonBackReference
	public Agency agency;
	
	
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@JsonManagedReference
	 //@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(cascade = CascadeType.ALL,mappedBy ="user",fetch = FetchType.EAGER)
	 //@JoinColumn(name = "user_id_user",referencedColumnName = "idUser")
	 List<FeedBack> feedback;
	
	
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@JsonManagedReference
	 //@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(cascade = CascadeType.ALL,mappedBy ="user",fetch = FetchType.LAZY)
	 //@JoinColumn(name = "user_id_user",referencedColumnName = "idUser")
	 List<CompteCourant> lstCompteCourant;
	
	
	
	 	@JsonIgnore
	    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
	    @JsonManagedReference
		//@LazyCollection(LazyCollectionOption.TRUE)
		@OneToMany(cascade = CascadeType.ALL,mappedBy = "userr",fetch = FetchType.LAZY)
		List<ProduitsDerives> produitsDereives;

	
	
	public static double nb = 1;
	public static double newnb ;
	public static double percentage  ;
	
}
