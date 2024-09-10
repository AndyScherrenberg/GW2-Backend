package com.frysning.gw2.restclient.wizardvault;

public class WizardVaultData {

	public WizardVaultListening daily;
	public WizardVaultListening weekly;
	public WizardVaultListening special;

	public WizardVaultData(WizardVaultListening daily, WizardVaultListening weekly, WizardVaultListening special){
		this.daily = daily;
		this.weekly = weekly;
		this.special = special;
	}
}
