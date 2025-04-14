package org.acme.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.*;
import java.util.*;
import org.acme.model.PathfinderItem;

@ApplicationScoped
public class PathfinderDataService {
    private static final String RESOURCES_PATH = "resources";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String normalizeItemName(String name) {
        return name.toLowerCase()
                  .replace("'", "")
                  .replace(" ", "-");
    }

    public List<PathfinderItem> loadAllJsonFiles() {
        List<PathfinderItem> dataList = new ArrayList<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream dirStream = classLoader.getResourceAsStream(RESOURCES_PATH);
            if (dirStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(dirStream))) {
                    String filename;
                    while ((filename = reader.readLine()) != null) {
                        if (filename.endsWith(".json")) {
                            try (InputStream fileStream = classLoader.getResourceAsStream(RESOURCES_PATH + "/" + filename)) {
                                if (fileStream != null) {
                                    PathfinderItem item = objectMapper.readValue(fileStream, PathfinderItem.class);
                                    dataList.add(item);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public Optional<PathfinderItem> findItemByName(String searchName) {
        String normalizedSearchName = normalizeItemName(searchName);
        return loadAllJsonFiles().stream()
                .filter(item -> normalizeItemName(item.getName()).equals(normalizedSearchName))
                .findFirst();
    }
}
