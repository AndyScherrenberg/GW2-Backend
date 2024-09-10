package com.frysning.gw2.service;

import com.frysning.gw2.restclient.bank.BankResult;
import com.frysning.gw2.restclient.item.SharedInventoryCollection;
import com.frysning.gw2.restclient.legendary.LegendaryCollection;
import com.frysning.gw2.restclient.wallet.Wallet;

public class AccountInventory {

	public Wallet wallet;
	public LegendaryCollection legendaryItems;
	public BankResult bank;

	public SharedInventoryCollection sharedInventory;

	public AccountInventory(Wallet wallet, LegendaryCollection legendaryCollection, BankResult bankResult, SharedInventoryCollection sharedInventoryCollection){
		this.wallet = wallet;
		this.legendaryItems = legendaryCollection;
		this.bank = bankResult;
		this.sharedInventory = sharedInventoryCollection;
	}
}
