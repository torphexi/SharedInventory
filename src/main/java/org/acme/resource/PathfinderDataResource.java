package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import org.acme.model.PathfinderItem;
import org.acme.service.PathfinderDataService;

@Path("/pathfinder-data")
public class PathfinderDataResource {

    @Inject
    PathfinderDataService pathfinderDataService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllData() {
        List<PathfinderItem> items = pathfinderDataService.loadAllJsonFiles();
        return Response.ok(items).build();
    }

    @GET
    @Path("/names")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItemNames() {
        List<String> names = pathfinderDataService.loadAllJsonFiles().stream()
                .map(PathfinderItem::getName)
                .collect(Collectors.toList());
        return Response.ok(names).build();
    }

    @GET
    @Path("/item/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemByName(@PathParam("name") String name) {
        return pathfinderDataService.findItemByName(name)
                .map(item -> Response.ok(item).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
