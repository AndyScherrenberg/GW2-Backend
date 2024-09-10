package com.frysning.gw2.restclient.item;

import io.quarkus.cache.CacheResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/v2/items")
@RegisterRestClient(configKey="gw2-api")
public interface ItemService {

	@GET
	@CacheResult(cacheName = "item-cache")
	List<Item> getItems(@QueryParam("ids") String value);

	default String ids(String name) {
		return name;
	}
}