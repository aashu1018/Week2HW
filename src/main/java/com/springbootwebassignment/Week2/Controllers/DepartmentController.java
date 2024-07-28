package com.springbootwebassignment.Week2.Controllers;

import com.springbootwebassignment.Week2.DTOs.DepartmentDTO;
import com.springbootwebassignment.Week2.Exceptions.ResourceNotFoundException;
import com.springbootwebassignment.Week2.Services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "departmentId") Long id){
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(id);
        return departmentDTO
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not Found"));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody DepartmentDTO inputDept){
        DepartmentDTO departmentDTO = departmentService.createNewDepartment(inputDept);
        return new ResponseEntity<>(departmentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@RequestBody DepartmentDTO inputDept,
                                                              @PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.updateDepartmentById(inputDept, departmentId));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId){
        boolean deleted = departmentService.deleteDepartmentById(departmentId);
        if(deleted){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

}
