package com.springbootwebassignment.Week2.Repositories;

import com.springbootwebassignment.Week2.Entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
