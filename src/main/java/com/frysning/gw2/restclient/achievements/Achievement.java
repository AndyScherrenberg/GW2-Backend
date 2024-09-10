package com.frysning.gw2.restclient.achievements;

import java.util.ArrayList;

public class Achievement {
	public int id;
	public String name;
	public String description;
	public String requirement;
	public String locked_text;
	public String type;
	public ArrayList<String> flags;
	public ArrayList<Bit> bits;
	public ArrayList<Tier> tiers;
	public ArrayList<Reward> rewards;
}
