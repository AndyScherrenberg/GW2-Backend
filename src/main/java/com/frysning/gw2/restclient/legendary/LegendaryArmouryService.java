package com.frysning.gw2.restclient.legendary;

import com.frysning.gw2.restclient.item.BaseItem;
import io.quarkus.cache.CacheResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/v2/account/legendaryarmory")
@RegisterRestClient(configKey="gw2-api")
public interface LegendaryArmouryService {

	@GET
	@CacheResult(cacheName = "legendary-cache")
	List<BaseItem> bank(@HeaderParam("authorization") String token);
}
