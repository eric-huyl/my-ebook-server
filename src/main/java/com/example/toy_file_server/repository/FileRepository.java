package com.example.toy_file_server.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.toy_file_server.model.File;

public interface FileRepository extends CrudRepository<File, Long> {
    File findByName(String name);
}
