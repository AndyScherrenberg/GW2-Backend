package com.frysning.gw2.restclient.achievements;

import io.quarkus.rest.client.reactive.ClientQueryParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/v2/achievements")
@RegisterRestClient(configKey = "gw2-api")
public interface AchievementService {

	@GET
	@Path("/groups")
	@ClientQueryParam(name = "id", value = "18DB115A-8637-4290-A636-821362A3C4A8")
	AchievementGroup getDailyAchievements(@HeaderParam("authorization") String token);


	@GET
	@Path("categories/{id}")
	@ClientQueryParam(name = "v", value = "2022-03-23T19:00:00.000Z")
	Category getCategories(@HeaderParam("authorization") String token, String id);

	@GET
	List<Achievement> getAchievementsById(@HeaderParam("authorization") String token, @QueryParam("ids") String value);
}