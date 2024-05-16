package com.excelgenerate.ExcelGenerate.controllers;

import com.excelgenerate.ExcelGenerate.entities.Tables;
import com.excelgenerate.ExcelGenerate.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @PostMapping("upload-excel")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") File file) {
        try {
            tableService.processExcelFile((MultipartFile) file);
            return ResponseEntity.ok("Excel file uploaded and table data updated successfully!");
        } catch (IOException e) {
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
