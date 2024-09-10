package com.frysning.gw2.restclient.legendary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frysning.gw2.restclient.item.BaseItem;
import com.frysning.gw2.restclient.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.frysning.gw2.util.Utils.removeLastChar;

public class LegendaryCollection {

	List<BaseItem> baseItemList = new ArrayList<>();
	public List<Item> itemList = new ArrayList<>();

	public void addItems(List<BaseItem> baseItemList){
		this.baseItemList.addAll(baseItemList);
	}

	@JsonIgnore
	public String getIds(){
		StringBuilder idCollection = new StringBuilder();
		baseItemList
				.stream()
				.collect(Collectors.groupingBy(bankItem -> bankItem.id))
				.values()
				.stream()
				.flatMap(group -> group.stream().limit(1))
				.map(p -> p.id)
				.forEach(id -> idCollection.append(id).append(","));
		return removeLastChar(idCollection.toString());
	}

	public void addLegendaryItems(List<Item> items) {
		this.itemList.addAll(items);
	}
}
