package tn.esprit.spring.services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Dto.CompteBancaireDto;
import tn.esprit.spring.dao.CompteBancaireDao;
import tn.esprit.spring.mappers.CompteBancaireMapper;

@Transactional
@Service
public class CompteBancaireService {
	private final CompteBancaireDao compteBancaireDao;
	@Autowired
	public CompteBancaireService(CompteBancaireDao compteBancaireDao) {
	this.compteBancaireDao = compteBancaireDao;
	}
	public CompteBancaireDto save(CompteBancaireDto compteBancaireDto) {
	this.compteBancaireDao.saveAndFlush (CompteBancaireMapper.compteBancaireDtoToCompteBancaire (compteBancaireDto));
	return compteBancaireDto;
	}
	public void deleteById(Long id) {
	this.compteBancaireDao.deleteById (id);
	}
	public CompteBancaireDto findOne(Long id) {
	return CompteBancaireMapper.compteBancaireToCompteBancaireDto (this.compteBancaireDao.getOne (id));
	}
	public Collection<CompteBancaireDto> findAll() {
	return CompteBancaireMapper.compteBancairesToCompteBancaireDtos (this.compteBancaireDao.findAll ());
	}
}
