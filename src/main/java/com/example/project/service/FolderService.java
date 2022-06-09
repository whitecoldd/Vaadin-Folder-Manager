package com.example.project.service;

import com.example.project.errors.NotFoundException;
import com.example.project.models.FolderDTO;

import java.util.List;

public interface FolderService {
    List<FolderDTO> findAll();
    FolderDTO save(FolderDTO dto) throws NotFoundException;
    FolderDTO update(FolderDTO dto) throws NotFoundException;

    void delete(Long id) throws NotFoundException;
}
