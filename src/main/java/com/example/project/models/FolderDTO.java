package com.example.project.models;

import com.example.project.entities.FolderColorEnum;


public class FolderDTO {

    private Long id;
    private String name;
    private String description;
    private String color;
    private Long parentId;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getParentId() {return parentId;}

    public void setParentId(Long parentId) {this.parentId = parentId;}
}
