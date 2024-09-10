package com.frysning.gw2.restclient.bank;

import com.frysning.gw2.restclient.item.BaseItem;
import io.quarkus.cache.CacheResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/v2/account/bank")
@RegisterRestClient(configKey="gw2-api")
//@ClientHeaderParam(name = "authorization", value = "Bearer ${gw2.main.apikey}")
public interface BankService {

	@GET
	@CacheResult(cacheName = "bank-cache")
	List<BaseItem> bank(@HeaderParam("authorization") String token);
}
