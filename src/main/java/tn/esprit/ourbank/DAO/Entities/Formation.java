package tn.esprit.ourbank.DAO.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Formation {
	
	 	@Id
	    @GeneratedValue (strategy = GenerationType.IDENTITY)
	    private int id;
	    private String name;
	    private String image;
	    private int nbplace;
	    private String filename;
	    @Temporal(TemporalType.DATE) 
	    private Date datedeb;
	   	    
}
