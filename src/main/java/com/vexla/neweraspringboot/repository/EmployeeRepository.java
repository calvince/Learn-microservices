package com.vexla.neweraspringboot.repository;

import com.vexla.neweraspringboot.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,String> {
}
