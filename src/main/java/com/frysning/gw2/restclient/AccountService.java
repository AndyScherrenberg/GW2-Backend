package com.frysning.gw2.restclient;

import jakarta.ws.rs.HeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/v2/account")
@RegisterRestClient(configKey="gw2-api")
public interface AccountService {

	@GET
	String get(@HeaderParam("authorization") String token);

	@GET
	@Path("dailycrafting")
	List<String> dailyCrafting(@HeaderParam("authorization") String token);
}