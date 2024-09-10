package com.frysning.gw2.restclient.wallet;

import com.frysning.gw2.restclient.currencies.Currency;

public class WalletEntry {

	public int id;
	public int value;

	public Currency currency = new Currency();

	public void setCurrency(Currency currency){
		this.currency = currency;
	}
}
