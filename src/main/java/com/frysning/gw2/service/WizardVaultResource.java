package com.frysning.gw2.service;

import com.frysning.gw2.restclient.wizardvault.WizardVaultData;
import com.frysning.gw2.restclient.wizardvault.WizardVaultListening;
import com.frysning.gw2.restclient.wizardvault.WizardsVaultService;
import io.quarkus.cache.CacheResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestHeader;

import static com.frysning.gw2.util.Utils.withToken;

@Path("/wizardvault")
public class WizardVaultResource {

	@RestClient
	WizardsVaultService wizardsVaultService;

	@GET
	@CacheResult(cacheName = "vault-cache")
	@Produces(MediaType.APPLICATION_JSON)
	public WizardVaultData full(@RestHeader("api_key")
								String api_key) {
		var specialVault = wizardsVaultService.getSpecialVault(withToken(api_key));
		var dailyVault = wizardsVaultService.getDailyVault(withToken(api_key));
		var weeklyVault = wizardsVaultService.getWeeklyVault(withToken(api_key));
		return new WizardVaultData(dailyVault, weeklyVault, specialVault);
	}

	@GET
	@Path("daily")
	@Produces(MediaType.APPLICATION_JSON)
	public WizardVaultListening daily(@RestHeader("api_key")
									  String api_key) {
		return wizardsVaultService.getDailyVault(withToken(api_key));
	}

	@GET
	@Path("weekly")
	@Produces(MediaType.APPLICATION_JSON)
	public WizardVaultListening weekly(@RestHeader("api_key")
									   String api_key) {
		return wizardsVaultService.getWeeklyVault(withToken(api_key));
	}

	@GET
	@Path("special")
	@Produces(MediaType.APPLICATION_JSON)
	public WizardVaultListening special(@RestHeader("api_key")
										String api_key) {
		return wizardsVaultService.getSpecialVault(withToken(api_key));
	}
}