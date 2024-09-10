package com.frysning.gw2.restclient.achievements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Category {
	public int id;
	public String name;
	public String description;
	public int order;
	public String icon;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public ArrayList<StrippedAchievement> achievements = new ArrayList<>();

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public ArrayList<StrippedAchievement> tomorrow = new ArrayList<>();

	public List<Achievement> achievementsWithInfo;
	public List<Achievement> tomorrowWithInfo;

	@JsonIgnore
	public String getIds() {
		return Stream.concat(achievements.stream(), tomorrow.stream())
				.map(achievement -> achievement.id)
				.distinct()
				.map(Object::toString)
				.collect(Collectors.joining(","));
	}

	public void updateAchievements(List<Achievement> updatedAchievements) {
		achievementsWithInfo =
				updatedAchievements.stream()
						.filter(info -> achievements.stream()
								.anyMatch(noInfo -> info.id == noInfo.id)
						).collect(Collectors.toList());

		tomorrowWithInfo = updatedAchievements.stream()
				.filter(info -> tomorrow.stream()
						.anyMatch(noInfo -> info.id == noInfo.id)
				).collect(Collectors.toList());
	}
}
