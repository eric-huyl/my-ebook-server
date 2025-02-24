package com.example.toy_file_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class File {
    private String name;
    private String path;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public File() {}

    public File(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("File id=%d, name=%s\n", id, name);
    }

    // Getters and Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getPath() {
        return this.path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
