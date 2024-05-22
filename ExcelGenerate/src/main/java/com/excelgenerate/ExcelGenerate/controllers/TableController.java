package com.excelgenerate.ExcelGenerate.controllers;

import com.excelgenerate.ExcelGenerate.entities.Tables;
import com.excelgenerate.ExcelGenerate.services.TableService;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("Table")
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping
    public List<Tables> getAllTables() {
        return tableService.getAllTables();
    }

    @GetMapping("/{id}")
    public Tables getAllTables(@PathVariable String id) {
        return tableService.getByTable(id);
    }

    @PostMapping(value = "upload-excel",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadExcel(@RequestPart(value ="file" ) MultipartFile multipartFile,
                                         @RequestParam("filename") String filename) {
//        DataFormatter dataFormatter = new DataFormatter();
        try {
            tableService.processExcelFile(multipartFile);
            return ResponseEntity.ok("Excel file uploaded and table data updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error uploading Excel file: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createTable(@RequestBody Tables tables){
        return new ResponseEntity<>(tableService.createTable(tables), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTable(@PathVariable String id, @RequestBody Tables updatedTable) {
        try {
            tableService.updateTable(id, updatedTable);
            return ResponseEntity.ok("Table data updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error updating table data: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTable(@PathVariable String id) {
        try {
            tableService.deleteTable(id);
            return ResponseEntity.ok("Table deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error deleting table: " + e.getMessage());
        }
    }

}
