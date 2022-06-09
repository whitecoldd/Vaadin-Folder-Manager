package com.example.project.entities;

import javax.persistence.*;

@Table(name = "folder")
@Entity
public class FolderEntity extends CoreEntity{
    @Column(name = "color")

    public String color;

    /*@ManyToOne
    @JoinColumn(name="parent_id")
    public FolderEntity parent;*/

    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long parentId;

    /*public FolderEntity getParent() {
        return parent;
    }

    public void setParent(FolderEntity parent) {
        this.parent = parent;
    }*/

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
