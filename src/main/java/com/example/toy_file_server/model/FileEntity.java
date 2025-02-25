package com.example.toy_file_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class FileEntity {
    @Id
    private String name;
    private String path;

    public FileEntity() {}

    public FileEntity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("File name=%s, path=%s\n", name, path);
    }

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
