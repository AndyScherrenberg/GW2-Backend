package com.frysning.gw2.service;

import com.frysning.gw2.restclient.bank.BankResult;
import com.frysning.gw2.restclient.bank.BankService;
import com.frysning.gw2.restclient.currencies.CurrencyService;
import com.frysning.gw2.restclient.item.BaseItem;
import com.frysning.gw2.restclient.item.ItemService;
import com.frysning.gw2.restclient.item.SharedInventoryCollection;
import com.frysning.gw2.restclient.item.SharedInventoryService;
import com.frysning.gw2.restclient.legendary.LegendaryArmouryService;
import com.frysning.gw2.restclient.legendary.LegendaryCollection;
import com.frysning.gw2.restclient.wallet.Wallet;
import com.frysning.gw2.restclient.wallet.WalletService;
import io.quarkus.cache.CacheResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestHeader;

import static com.frysning.gw2.util.Utils.withToken;

@Path("/account/inventory")
public class AccountInventoryResource {

	@RestClient
	SharedInventoryService sharedInventoryService;

	@RestClient
	ItemService itemService;

	@RestClient
	LegendaryArmouryService legendaryArmouryService;

	@RestClient
	WalletService walletService;

	@RestClient
	CurrencyService currencyService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public AccountInventory getAccountWideInventory(@RestHeader("api_key")
													String api_key) {
		LegendaryCollection legendaryCollection = getLegendaryCollection(api_key);
		SharedInventoryCollection sharedInventoryCollection = getSharedInventoryCollection(api_key);
		Wallet wallet = getWallet1(api_key);
		BankResult bank = getBankResult(api_key);

		return new AccountInventory(wallet, legendaryCollection, bank, sharedInventoryCollection);
	}

	@GET
	@Path("legendary")
	@Produces(MediaType.APPLICATION_JSON)
	public LegendaryCollection getLegendaryArmoury(@RestHeader("api_key")
												   String api_key) {
		return getLegendaryCollection(api_key);
	}

	@RestClient
	BankService bankService;

	@GET
	@Path("bank")
	@Produces(MediaType.APPLICATION_JSON)
	public BankResult getBank(@RestHeader("api_key") String api_key) {
		return getBankResult(api_key);
	}

	private BankResult getBankResult(String api_key) {
		var bankItems = bankService.bank(withToken(api_key));
		var bankResult = new BankResult();
		int bankTabSlotIndex = 0;

		for (BaseItem baseItem : bankItems) {
			bankResult.addBankItem(bankTabSlotIndex, baseItem);
			bankTabSlotIndex++;
		}

		var idCollection = bankResult.getIdCollection();
		if (idCollection != null) {
			var items = itemService.getItems(idCollection);
			bankResult.updateItems(items);
		}

		return bankResult;
	}

	private LegendaryCollection getLegendaryCollection(String api_key) {
		var legendaryItems = legendaryArmouryService.bank(withToken(api_key));
		var legendaryCollection = new LegendaryCollection();
		legendaryCollection.addItems(legendaryItems);
		var ids = legendaryCollection.getIds();
		var items = itemService.getItems(ids);
		legendaryCollection.addLegendaryItems(items);
		return legendaryCollection;
	}

	@GET
	@Path("shared")
	@Produces(MediaType.APPLICATION_JSON)
	public SharedInventoryCollection getSharedInventory(@RestHeader("api_key")
														String api_key) {
		return getSharedInventoryCollection(api_key);
	}

	private SharedInventoryCollection getSharedInventoryCollection(String api_key) {
		var sharedInventory = sharedInventoryService.sharedInventory(withToken(api_key));

		int bankTabSlotIndex = 0;
		var sharedInventoryCollection = new SharedInventoryCollection();

		for (BaseItem baseItem : sharedInventory) {
			sharedInventoryCollection.addItem(bankTabSlotIndex, baseItem);
			bankTabSlotIndex++;
		}

		var ids = sharedInventoryCollection.getIds();
		var items = itemService.getItems(ids);
		sharedInventoryCollection.updateItems(items);
		return sharedInventoryCollection;
	}

	@GET
	@Path("wallet")
	@Produces(MediaType.APPLICATION_JSON)
	public Wallet getWallet(@RestHeader("api_key")
							String api_key) {
		return getWallet1(api_key);
	}

	private Wallet getWallet1(String api_key) {
		var walletEntries = walletService.get(withToken(api_key));

		var wallet = new Wallet();
		wallet.addWalletEntries(walletEntries);

		var items = currencyService.getItems();
		wallet.updateWalletEntries(items);
		return wallet;
	}
}



