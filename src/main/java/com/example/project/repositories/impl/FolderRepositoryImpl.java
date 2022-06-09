package com.example.project.repositories.impl;

import com.example.project.entities.FolderEntity;
import com.example.project.repositories.FolderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FolderRepositoryImpl extends CoreRepositoryImpl<FolderEntity> implements FolderRepository {
    public FolderRepositoryImpl() {
    }

    @Override
    protected Class<FolderEntity> getManagedClass() {
        return FolderEntity.class;
    }
}
