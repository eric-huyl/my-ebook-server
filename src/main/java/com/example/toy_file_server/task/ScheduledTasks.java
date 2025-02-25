package com.example.toy_file_server.task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.Arrays;
import com.example.toy_file_server.model.FileEntity;
import com.example.toy_file_server.service.FileStorageService;
@Component
public class ScheduledTasks {

    @Value("${file.storage.location}")
    private String storageLocation;

    @Autowired
    private FileStorageService fileStorageService;


    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);


    @Scheduled(fixedRate = 10000)
    public void updateDatabaseTask() {
        fileStorageService.removeAll();
        File folder = new File(storageLocation);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            Arrays.stream(listOfFiles)
                  .filter(File::isFile)
                  .forEach(file -> {
                      // Process each file as needed
                      log.info("Found File: " + file.getName());
                      fileStorageService.saveFile(FileEntity.convertToEntity(file));
                  });
        } else {
            System.out.println("The folder is empty or does not exist.");
        }
    }
}
