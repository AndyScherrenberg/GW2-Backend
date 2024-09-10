package com.frysning.gw2.restclient.achievements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frysning.gw2.util.Utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.frysning.gw2.util.Utils.removeLastChar;

public class AchievementGroup {
	public String id;
	public String name;
	public String description;
	public int order;
	public ArrayList<Integer> categories;

	@JsonIgnore
	public String getIds() {
		return removeLastChar(categories.stream().map(Object::toString).collect(Collectors.joining(",")));
	}
}
