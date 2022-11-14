package tn.esprit.spring.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@ToString
@AllArgsConstructor
public class OperationDto {
    private long id;

    private long compteBancaireId;
    public OperationDto(Long id){
        this.id=id;
    }

    public OperationDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCompteBancaireId() {
        return compteBancaireId;
    }

    public void setCompteBancaireId(long compteBancaireId) {
        this.compteBancaireId = compteBancaireId;
    }
}
