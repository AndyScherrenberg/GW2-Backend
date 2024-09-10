package com.frysning.gw2.restclient.bank;

import com.frysning.gw2.restclient.item.BaseItem;
import com.frysning.gw2.restclient.item.Item;

public class BankItemSlot {

	public int index;

	public boolean isFilled;

	public int amount = 0;

	public Item item;

	public int id = 0;

	public BankItemSlot(BaseItem baseItem, int index) {
		System.out.println(baseItem);
		System.out.println(index);
		this.index = index;
		this.isFilled = baseItem != null;
		if (baseItem != null) {
			this.amount = baseItem.count;
			this.id = baseItem.id;
		}
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
