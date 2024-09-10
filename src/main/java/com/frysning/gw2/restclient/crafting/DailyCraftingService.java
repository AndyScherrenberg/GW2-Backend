package com.frysning.gw2.restclient.crafting;

import io.quarkus.cache.CacheResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.Cache;

import java.util.List;

@Path("/v2/dailycrafting")
@RegisterRestClient(configKey = "gw2-api")
public interface DailyCraftingService {

	@CacheResult(cacheName = "daily-crafting-cache")
	@GET
	List<String> getDailyCrafting();
}

