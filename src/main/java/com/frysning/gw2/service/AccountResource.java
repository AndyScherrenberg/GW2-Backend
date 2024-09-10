package com.frysning.gw2.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import com.frysning.gw2.restclient.AccountService;
import org.jboss.resteasy.reactive.RestHeader;

import static com.frysning.gw2.util.Utils.withToken;

@Path("/account")
public class AccountResource {

	@RestClient
	AccountService accountService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAccount(@RestHeader("api_key") String api_key) {
		return accountService.get(withToken(api_key));
	}
}