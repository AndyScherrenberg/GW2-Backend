package com.frysning.gw2.restclient.item;

import io.quarkus.cache.CacheResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/v2/account/inventory")
@RegisterRestClient(configKey="gw2-api")
public interface SharedInventoryService {

	@GET
	@CacheResult(cacheName = "shared-inventory-cache")
	List<BaseItem> sharedInventory(@HeaderParam("authorization") String token);
}
