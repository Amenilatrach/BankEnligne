package tn.esprit.spring.mappers;

import tn.esprit.spring.Dto.OperationDto;
import tn.esprit.spring.entity.Operation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OperationMapper {
    public static OperationDto operationToOperationDto(Operation operation)
    {
        OperationDto operationDto = new OperationDto ();
        operationDto.setId (operation.getId ());
        operationDto.setCompteBancaireId (operation.getCompteBancaire ().getId ());
        return operationDto;
    }

    public static Operation operationDtoToOperation(OperationDto operationDto)
    {
        Operation operation = new Operation ();
        operation.setId (operationDto.getId ());
        operation.setCompteBancaire (PatientMapper.patientDtoToPatient (new PatientDto (operationDto.getCompteBancaireId ())));
        return operation;
    }

    public static Collection<OperationDto> operationsToOperationDtos(Collection<Operation> operations)
    {
        List<OperationDto> operationDtoList = new ArrayList<> ();
        operations.forEach(operation -> {
            operationDtoList.add (operationToOperationDto (operation));
        });
        return operationDtoList;
    }
}
