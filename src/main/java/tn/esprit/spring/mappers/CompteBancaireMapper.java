package tn.esprit.spring.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tn.esprit.spring.Dto.CompteBancaireDto;
import tn.esprit.spring.entity.CompteBancaire;

public class CompteBancaireMapper {
	public static CompteBancaireDto compteBancaireToCompteBancaireDto(CompteBancaire compteBancaire)
    {
		CompteBancaireDto compteBancaireDto = new CompteBancaireDto ();
		compteBancaireDto.setId (compteBancaire.getId ());
        return compteBancaireDto;
    }

    public static CompteBancaire compteBancaireDtoToCompteBancaire(CompteBancaireDto compteBancaireDto)
    {
    	CompteBancaire compteBancaire = new CompteBancaire ();
    	compteBancaire.setId (compteBancaireDto.getId ());
        return compteBancaire;
    }

    public static Collection<CompteBancaireDto> compteBancairesToCompteBancaireDtos(Collection<CompteBancaire> compteBancaires)
    {
        List<CompteBancaireDto> compteBancaireDtoList = new ArrayList<> ();
        compteBancaires.forEach(compteBancaire -> {
        	compteBancaireDtoList.add (compteBancaireToCompteBancaireDto (compteBancaire));
        });
        return compteBancaireDtoList;
    }
}
