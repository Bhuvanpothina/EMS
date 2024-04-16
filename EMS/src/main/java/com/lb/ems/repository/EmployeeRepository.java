package com.lb.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lb.ems.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//	boolean existsByRoleId(Integer roleId);
	@Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.role.roleId = :roleId")
	boolean existsByRoleId(@Param("roleId") Integer roleId);

    List<Employee> findByDepartmentDepartmentId(Integer departmentId);
//    List<Employee> findByDepartmentId(Integer departmentId);

}
