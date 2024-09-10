package com.frysning.gw2.restclient.wizardvault;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v2/account/wizardsvault")
@RegisterRestClient(configKey="gw2-api")
public interface WizardsVaultService {

	@GET()
	@Path("/daily")
	WizardVaultListening getDailyVault(@HeaderParam("authorization") String token);


	@GET()
	@Path("/weekly")
	WizardVaultListening getWeeklyVault(@HeaderParam("authorization") String token);

	@GET()
	@Path("/special")
	WizardVaultListening getSpecialVault(@HeaderParam("authorization") String token);
}