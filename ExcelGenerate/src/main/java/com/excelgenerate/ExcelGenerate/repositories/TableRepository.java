package com.excelgenerate.ExcelGenerate.repositories;

import com.excelgenerate.ExcelGenerate.entities.Tables;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TableRepository extends JpaRepository<Tables, String> {

}
