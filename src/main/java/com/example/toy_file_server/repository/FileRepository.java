package com.example.toy_file_server.repository;
import com.example.toy_file_server.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Long> {
    File findByName(String name);
}
