package com.example.project.controllers;

import com.example.project.errors.NotFoundException;
import com.example.project.models.FolderDTO;
import com.example.project.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FolderCRUDController {

    @Autowired
    private FolderService ser;

    public FolderCRUDController(){
    }

    @GetMapping("/api/folder")
    public ResponseEntity<List<FolderDTO>> findAll(){
        return ResponseEntity.ok( ser.findAll());
    }
    @PostMapping("/api/folder")
    public ResponseEntity save(@RequestBody FolderDTO entity) throws NotFoundException {
        return ResponseEntity.ok(ser.save(entity));
    }
    @PutMapping("/api/folder")
    public ResponseEntity update(@RequestBody FolderDTO entity) {
        try {
            return ResponseEntity.ok(ser.update(entity));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/api/folder/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            ser.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
