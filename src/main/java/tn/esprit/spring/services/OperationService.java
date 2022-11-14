package tn.esprit.spring.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.Dto.OperationDto;
import tn.esprit.spring.dao.OperationDao;
import tn.esprit.spring.mappers.OperationMapper;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
@Transactional
@Service
public class OperationService {
    private final OperationDao operationDao;

    @Autowired
    public OperationService(OperationDao operationDao) {
        this.operationDao = operationDao;
    }

    public OperationDto save(OperationDto operationDto) {
        this.operationDao.saveAndFlush (OperationMapper.operationDtoToOperation (operationDto));
        return operationDto;
    }

    public void deleteById(Long id) {
        this.operationDao.deleteById (id);
    }

    public OperationDto findOne(Long id) {
        return OperationMapper.operationToOperationDto (this.operationDao.getOne (id));
    }

    public Collection<OperationDto> findAll() {
        return OperationMapper.operationsToOperationDtos (this.operationDao.findAll ());
    }
}
