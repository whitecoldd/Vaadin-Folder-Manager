package com.example.project.models;


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class DocDTO {

    private Long id;
    private String name;
    private String description;
    private String url;
    private String docType;
    private Long folderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return isValidURL(url);
    }

    public void setUrl(String url) {this.url = url;}

    public String getDocType() {return docType;}

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String isValidURL(String url) {

        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return "";
        }

        return url;
    }
}
