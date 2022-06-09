package com.example.project.service.impl;

import com.example.project.entities.DocumentEntity;
import com.example.project.errors.NotFoundException;
import com.example.project.repositories.DocumentRepository;
import com.example.project.service.DocService;
import com.example.project.models.DocDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocServiceImpl implements DocService {

    @Autowired
    private DocumentRepository docRep;

    @Override
    public List<DocDTO> findAll() {
        List<DocDTO> list = new ArrayList<>();
        List<DocumentEntity> entityList = docRep.findAll();
        entityList.forEach(entity -> {
            DocDTO dto = new DocDTO();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setName(entity.getName());
            dto.setUrl(entity.getUrl());
            dto.setDocType(entity.getDocType());
            if (entity.getFolderId() != null) {
                dto.setFolderId(entity.getFolderId());
            }
            list.add(dto);
        });
        return list;
    }

    @Override
    public DocDTO save(DocDTO dto) throws NotFoundException {
        DocumentEntity entity = new DocumentEntity();
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setUrl(dto.getUrl());
        entity.setDocType(dto.getDocType());
        entity.setFolderId(dto.getFolderId());
        docRep.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public DocDTO update(DocDTO dto) throws NotFoundException {
        DocumentEntity entity = docRep.findById(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setUrl(dto.getUrl());
        entity.setDocType(dto.getDocType());
        entity.setFolderId(dto.getFolderId());
        docRep.update(entity);
        return dto;
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        docRep.delete(id);
    }
}
