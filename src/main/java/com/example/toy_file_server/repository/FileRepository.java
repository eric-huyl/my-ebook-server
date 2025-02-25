package com.example.toy_file_server.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.toy_file_server.model.FileEntity;

public interface FileRepository extends CrudRepository<FileEntity, Long> {
    FileEntity findByName(String name);
}
