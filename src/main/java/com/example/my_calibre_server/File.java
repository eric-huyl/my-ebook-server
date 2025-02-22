package com.example.my_calibre_server;

public class File {
    private String name;
    private String path;
    private Long id;

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
