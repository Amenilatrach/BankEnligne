package tn.esprit.spring.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString

@AllArgsConstructor
public class CompteBancaireDto {
	private long id;
	
	public CompteBancaireDto() {
		
	}
	
	public CompteBancaireDto(long id) {
		this.id = id;
	}
	
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    } 

}
