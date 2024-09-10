package com.frysning.gw2.restclient.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frysning.gw2.restclient.bank.BankItemSlot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.frysning.gw2.util.Utils.removeLastChar;

public class SharedInventoryCollection {

	public List<BankItemSlot> bankItemList;

	public SharedInventoryCollection() {
		this.bankItemList = new ArrayList<>();
	}


	public void addItem(int index, BaseItem baseItem) {
		this.bankItemList.add(new BankItemSlot(baseItem, index));
	}

	@JsonIgnore
	public String getIds() {
		StringBuilder idCollection = new StringBuilder();
		bankItemList
				.stream()
				.filter(bankItem -> bankItem.isFilled)
				.collect(Collectors.groupingBy(bankItem -> bankItem.id))
				.values()
				.stream()
				.flatMap(group -> group.stream().limit(1))
				.map(p -> p.id)
				.forEach(id -> idCollection.append(id).append(","));
		return removeLastChar(idCollection.toString());
	}

	public void updateItems(List<Item> items) {
		for (Item item : items) {
			bankItemList.stream()
					.filter(bankItem -> bankItem.isFilled && Objects.equals(item.id, ((Integer) bankItem.id).toString()))
					.forEach(bankItem -> bankItem.setItem(item));
		}
	}

}
