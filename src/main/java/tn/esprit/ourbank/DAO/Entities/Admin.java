package tn.esprit.ourbank.DAO.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table( name = "T_Admin")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idAdmin; // Clé primaire
	@Column(nullable = false)
	private String username;
	@Column(unique=true , nullable = false)
	private String matricule;
	@Column(nullable = false)
	private String code;
	
	
	
	


}
