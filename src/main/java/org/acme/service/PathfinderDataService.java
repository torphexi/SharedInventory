package org.acme.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.*;
import java.util.*;
import org.acme.model.PathfinderItem;
import java.net.URL;
import jakarta.inject.Inject;
import io.quarkus.runtime.annotations.RegisterForReflection;

@ApplicationScoped
@RegisterForReflection
public class PathfinderDataService {
    private static final String RESOURCES_DIR = "resources";
    private final ObjectMapper objectMapper;

    @Inject
    public PathfinderDataService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private String normalizeItemName(String name) {
        return name.toLowerCase()
                  .replace("'", "")
                  .replace(" ", "-");
    }

    public List<PathfinderItem> loadAllJsonFiles() {
        List<PathfinderItem> items = new ArrayList<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream resourceStream = classLoader.getResourceAsStream(RESOURCES_DIR);
            if (resourceStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream))) {
                    String resource;
                    while ((resource = reader.readLine()) != null) {
                        if (resource.endsWith(".json")) {
                            try (InputStream jsonStream = classLoader.getResourceAsStream(RESOURCES_DIR + "/" + resource)) {
                                if (jsonStream != null) {
                                    PathfinderItem item = objectMapper.readValue(jsonStream, PathfinderItem.class);
                                    items.add(item);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Optional<PathfinderItem> findItemByName(String searchName) {
        String normalizedSearchName = normalizeItemName(searchName);
        return loadAllJsonFiles().stream()
                .filter(item -> normalizeItemName(item.getName()).equals(normalizedSearchName))
                .findFirst();
    }
}
