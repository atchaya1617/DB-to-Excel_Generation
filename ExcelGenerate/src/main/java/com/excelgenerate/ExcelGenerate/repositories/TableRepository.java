package com.excelgenerate.ExcelGenerate.repositories;

import com.excelgenerate.ExcelGenerate.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Tables, String> {

}
