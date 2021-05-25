package com.aem.mywknd.core.configuration;

public interface OsgiConfig {

	public String getServiceName();

	public int getServiceCount();

	public boolean isLiveData();

	public String[] getCountries();

	public String getRunModes();

}
