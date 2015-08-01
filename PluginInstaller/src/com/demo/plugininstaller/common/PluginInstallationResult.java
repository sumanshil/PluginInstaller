package com.demo.plugininstaller.common;

import java.util.List;

/**
 * This class will contain the installation result.
 * @author SumanChandra
 *
 */
public class PluginInstallationResult {
	
    private InstallationFailureCode installationFailureCode;
    private InstallationStatus installationStatus;
    /**
     * This will contain the installed plugin names in the order the plugins were installed
     */
    private List<String> installedPlugins;
    
    private PluginInstallationResult(InstallationFailureCode installationFailureCode,
    		                         InstallationStatus installationStatus,
    		                         List<String> installedPlugins) {
    	this.installationFailureCode = installationFailureCode;
    	this.installationStatus = installationStatus;
    	this.installedPlugins = installedPlugins;
    	
    }
    
    public static PluginInstallationResult getInstance(InstallationFailureCode installationFailureCode,
    		                                           InstallationStatus installationStatus,
    		                                           List<String> installedPlugins){
    	return new PluginInstallationResult(installationFailureCode, installationStatus, installedPlugins);
    }
    
	public List<String> getInstalledPlugins() {
		return installedPlugins;
	}

	public void setInstalledPlugins(List<String> installedPlugins) {
		this.installedPlugins = installedPlugins;
	}

	public InstallationFailureCode getInstallationFailureCode() {
		return installationFailureCode;
	}
	public void setInstallationFailureCode(InstallationFailureCode installationFailureCode) {
		this.installationFailureCode = installationFailureCode;
	}
	public InstallationStatus getInstallationStatus() {
		return installationStatus;
	}
	public void setInstallationStatus(InstallationStatus installationStatus) {
		this.installationStatus = installationStatus;
	}
    
}
