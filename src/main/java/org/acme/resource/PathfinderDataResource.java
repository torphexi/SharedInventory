package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

import org.acme.service.PathfinderDataService;

@Path("/pathfinder-data")
public class PathfinderDataResource {

    @Inject
    PathfinderDataService pathfinderDataService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getAllData() {
        return pathfinderDataService.loadAllJsonFiles();
    }
}
