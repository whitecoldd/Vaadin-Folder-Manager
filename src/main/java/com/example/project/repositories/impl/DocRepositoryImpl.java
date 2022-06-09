package com.example.project.repositories.impl;

import com.example.project.entities.DocumentEntity;
import com.example.project.repositories.DocumentRepository;
import org.springframework.stereotype.Repository;


@Repository
public class DocRepositoryImpl extends CoreRepositoryImpl<DocumentEntity> implements DocumentRepository  {
    public DocRepositoryImpl(){

    }

    @Override
    protected Class<DocumentEntity> getManagedClass() {
        return DocumentEntity.class;
    }


}
