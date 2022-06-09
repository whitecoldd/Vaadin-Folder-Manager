package com.example.project.entities;

import com.example.project.models.FolderDTO;
public enum FolderColorEnum {
    BLACK, WHITE, RED, BLUE, GREEN;

    private FolderColorEnum color;

    public String getColor() {
        return this.color.name();
    }

    public void setColor(String color) {
        this.color = FolderColorEnum.valueOf(color);
    }
}
