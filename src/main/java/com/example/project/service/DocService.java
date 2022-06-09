package com.example.project.service;

import com.example.project.errors.NotFoundException;
import com.example.project.models.DocDTO;

import java.util.List;

public interface DocService {
    List<DocDTO> findAll();
    DocDTO save(DocDTO dto) throws NotFoundException;
    DocDTO update(DocDTO dto) throws NotFoundException;

    void delete(Long id) throws NotFoundException;
}
