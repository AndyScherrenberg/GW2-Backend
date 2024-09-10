package com.frysning.gw2.restclient.wallet;

import io.quarkus.cache.CacheResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/v2/account/wallet")
@RegisterRestClient(configKey="gw2-api")
public interface WalletService {

	@GET
	@CacheResult(cacheName = "wallet-cache")
	List<WalletEntry> get(@HeaderParam("authorization") String token);
}