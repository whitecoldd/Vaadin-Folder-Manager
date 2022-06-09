package com.example.project.controllers;

import com.example.project.errors.NotFoundException;
import com.example.project.models.DocDTO;
import com.example.project.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentCRUDController {

    @Autowired
    private DocService ser;

    public DocumentCRUDController(){
    }

    @GetMapping("/api/doc")
    public ResponseEntity<List<DocDTO>> findAll(){
        return ResponseEntity.ok( ser.findAll());
    }
    @PostMapping("/api/doc")
    public ResponseEntity save(@RequestBody DocDTO entity) throws NotFoundException{
        return ResponseEntity.ok(ser.save(entity));
    }
    @PutMapping("/api/doc")
    public ResponseEntity update(@RequestBody DocDTO entity) {
        try {
            return ResponseEntity.ok(ser.update(entity));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/api/doc/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            ser.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
