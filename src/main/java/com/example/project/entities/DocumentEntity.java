package com.example.project.entities;


import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "document")
@Entity
public class DocumentEntity extends CoreEntity {
    @Column(name = "url")
    @URL
    @NotNull
    public String url;
    @Column(name = "docType")
    @NotNull
    public String docType;

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    @Column(name = "folder_id")
    @NotNull
    public Long folderId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }
}
