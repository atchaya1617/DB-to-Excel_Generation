package com.excelgenerate.ExcelGenerate.services;

import com.excelgenerate.ExcelGenerate.entities.Tables;
import com.excelgenerate.ExcelGenerate.repositories.TableRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TableService {


    @Autowired
    private TableRepository tableRepository;


    public List<Tables> getAllTables() {
        return tableRepository.findAll();
    }

    public Tables getByTable(String id) {
        return tableRepository.findById(id).orElseGet(null);
    }

    public void processExcelFile(MultipartFile file) throws IOException, InvalidFormatException {
        XSSFWorkbook workbook= (XSSFWorkbook) WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
//        DataFormatter dataFormatter = new DataFormatter();

        for (Row row : sheet) {
            Tables table = new Tables();
            table.setId(row.getCell(0).getStringCellValue());
            table.setName(row.getCell(1).getStringCellValue());
            tableRepository.save(table);

        }
//        workbook.close();
    }


    public Tables createTable(Tables tables){
        return tableRepository.save(tables);
    }

    public void updateTable(String id, Tables updatedTable) {
        Optional<Tables> optionalTable = tableRepository.findById(id);
        if (optionalTable.isPresent()) {
            Tables table = optionalTable.get();
            table.setName(updatedTable.getName());
            tableRepository.save(table);
        }
    }

    public void deleteTable(String id) {
        tableRepository.deleteById(id);
    }


}
