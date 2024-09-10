package com.frysning.gw2.restclient.currencies;

import io.quarkus.cache.CacheResult;
import io.quarkus.rest.client.reactive.ClientQueryParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/v2/currencies")
@RegisterRestClient(configKey="gw2-api")
//@ClientHeaderParam(name = "authorization", value = "Bearer ${gw2.main.apikey}")
public interface CurrencyService {

	@GET
	@ClientQueryParam(name = "ids", value = "all")
	@CacheResult(cacheName = "currency-cache")
	List<Currency> getItems();
}