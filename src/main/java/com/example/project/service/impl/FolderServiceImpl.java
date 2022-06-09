package com.example.project.service.impl;

import com.example.project.entities.FolderEntity;
import com.example.project.errors.NotFoundException;
import com.example.project.models.FolderDTO;
import com.example.project.repositories.FolderRepository;
import com.example.project.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderRepository rep;

    @Override
    public List<FolderDTO> findAll() {
        List<FolderDTO> list = new ArrayList<>();
        List<FolderEntity> entityList = rep.findAll();
        entityList.forEach(folderEntity -> {
            FolderDTO dto = new FolderDTO();
            dto.setId(folderEntity.getId());
            dto.setDescription(folderEntity.getDescription());
            dto.setName(folderEntity.getName());
            dto.setColor(folderEntity.getColor());
            dto.setParentId(folderEntity.getParentId());

            list.add(dto);
        });
        return list;
    }
    @Override
    public FolderDTO save(FolderDTO dto) throws NotFoundException {
        FolderEntity folderEntity = new FolderEntity();
        folderEntity.setDescription(dto.getDescription());
        folderEntity.setName(dto.getName());
        folderEntity.setColor(dto.getColor());
        folderEntity.setParentId(dto.getParentId());
        rep.save(folderEntity);
        dto.setId(folderEntity.getId());
        return dto;
    }

    @Override
    public FolderDTO update(FolderDTO dto) throws NotFoundException {
        FolderEntity folderEntity = rep.findById(dto.getId());
        folderEntity.setDescription(dto.getDescription());
        folderEntity.setName(dto.getName());
        folderEntity.setColor(dto.getColor());
        folderEntity.setParentId(dto.getParentId());
        rep.update(folderEntity);
        return dto;
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        rep.delete(id);
    }
}
