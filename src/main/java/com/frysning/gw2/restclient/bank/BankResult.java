package com.frysning.gw2.restclient.bank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frysning.gw2.restclient.item.BaseItem;
import com.frysning.gw2.restclient.item.Item;
import jakarta.annotation.Priority;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.frysning.gw2.util.Utils.removeLastChar;

public class BankResult {

	private final int BANK_TAB_SIZE = 30;

	@JsonIgnore
	public List<BankItemSlot> bankItemList;

	public BankResult() {
		this.bankItemList = new ArrayList<>();
	}

	public BankResult(List<BankItemSlot> bankItemList) {
		this.bankItemList = bankItemList;
	}

	public void addBankItem(int index, BaseItem baseItem) {
		this.bankItemList.add(new BankItemSlot(baseItem, index));
	}

	@JsonIgnore
	public String getIdCollection() {
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

	@Priority(1)
	public int getTotalSlots() {
		return bankItemList.size();
	}

	@Priority(2)
	public int getUsedSlots() {
		return bankItemList.stream().filter(b -> b.isFilled).toList().size();
	}

	@Priority(3)
	public int getUnusedSlots() {
		return bankItemList.stream().filter(b -> !b.isFilled).toList().size();
	}

	@Priority(4)
	public List<List<BankItemSlot>> getBankItems() {
		List<List<BankItemSlot>> bank = new ArrayList<>();

		int amount = bankItemList.size() / BANK_TAB_SIZE;

		for (int i = 0; i < amount; i++) {
			bank.add(bankItemList.subList(i * BANK_TAB_SIZE, (i + 1) * BANK_TAB_SIZE));
		}

		return bank;
	}
}
