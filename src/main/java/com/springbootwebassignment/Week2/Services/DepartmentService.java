package com.springbootwebassignment.Week2.Services;

import com.springbootwebassignment.Week2.DTOs.DepartmentDTO;
import com.springbootwebassignment.Week2.Entities.DepartmentEntity;
import com.springbootwebassignment.Week2.Repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(id);
        return departmentEntity.map(departmentEntity1 -> modelMapper.map(departmentEntity1, DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map((entities) -> modelMapper.map(entities, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDept) {
        DepartmentEntity toSaveEntity = modelMapper.map(inputDept, DepartmentEntity.class);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(toSaveEntity);
        return modelMapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById(DepartmentDTO inputDept, Long departmentId) {
        DepartmentEntity departmentEntity = modelMapper.map(inputDept, DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public boolean isDepartmentExists(Long departmentId){
        return departmentRepository.existsById(departmentId);
    }

    public boolean deleteDepartmentById(Long departmentId) {
        if(!isDepartmentExists(departmentId)){
            return false;
        }
        departmentRepository.deleteById(departmentId);
        return true;
    }
}
