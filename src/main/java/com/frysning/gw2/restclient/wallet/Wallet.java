package com.frysning.gw2.restclient.wallet;

import com.frysning.gw2.restclient.currencies.Currency;

import java.util.ArrayList;
import java.util.List;

public class Wallet {

	public List<WalletEntry> walletEntries = new ArrayList<>();

	public void addWalletEntries(List<WalletEntry> walletEntries) {
		this.walletEntries = walletEntries;
	}

	public void updateWalletEntries(List<Currency> currencies) {
		for (Currency currency : currencies) {
			this.walletEntries.stream().filter(walletEntry -> walletEntry.id == currency.id).forEach(walletEntry ->
					walletEntry.setCurrency(currency));
		}
	}

}
