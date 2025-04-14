package org.acme.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@ApplicationScoped
public class PathfinderDataService {

    private static final String RESOURCES_PATH = "resources";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Map<String, Object>> loadAllJsonFiles() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            Path resourcesDir = Paths.get(RESOURCES_PATH);
            DirectoryStream<Path> stream = Files.newDirectoryStream(resourcesDir, "*.json");
            for (Path entry : stream) {
                Map<String, Object> data = objectMapper.readValue(entry.toFile(), Map.class);
                dataList.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
