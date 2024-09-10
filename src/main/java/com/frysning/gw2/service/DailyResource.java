package com.frysning.gw2.service;

import com.frysning.gw2.restclient.AccountService;
import com.frysning.gw2.restclient.achievements.AchievementGroup;
import com.frysning.gw2.restclient.achievements.AchievementService;
import com.frysning.gw2.restclient.achievements.Category;
import com.frysning.gw2.restclient.crafting.DailyCraftingService;
import io.quarkus.cache.CacheResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestHeader;

import java.util.ArrayList;
import java.util.List;

import static com.frysning.gw2.util.Utils.withToken;

@Path("daily")
public class DailyResource {

	@RestClient
	AchievementService dailyAchievementService;

	@RestClient
	AccountService accountService;

	@RestClient
	DailyCraftingService dailyCraftingService;


	@GET
	@Path("crafting")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getDailyCrafting(@RestHeader("api_key") String api_key) {
		var craftingDaily = dailyCraftingService.getDailyCrafting();
		var craftedDaily = accountService.dailyCrafting(withToken(api_key));
		craftingDaily.removeAll(craftedDaily);
		return craftingDaily;
	}

	@GET
	@Path("achievements")
	@CacheResult(cacheName = "daily-achievement-cache")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getDailyAchievements(@RestHeader("api_key")
											   String api_key) {
		AchievementGroup g = dailyAchievementService.getDailyAchievements(withToken(api_key));
		var categories = new ArrayList<Category>();

		for (int i : g.categories) {
			var category = dailyAchievementService.getCategories(withToken(api_key), String.valueOf(i));

			if (!category.getIds().isEmpty()) {
				var achievements = dailyAchievementService.getAchievementsById(withToken(api_key), category.getIds());
				category.updateAchievements(achievements);

			}
			categories.add(category);

		}
		return categories;
	}
}
