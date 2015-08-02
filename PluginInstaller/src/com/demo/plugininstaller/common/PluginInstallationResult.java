package com.demo.plugininstaller.common;

import java.util.List;
import java.util.Set;

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
    private String[] installedPlugins;
    
    private PluginInstallationResult(InstallationFailureCode installationFailureCode,
    		                         InstallationStatus installationStatus,
    		                         String[] installedPlugins) {
    	this.installationFailureCode = installationFailureCode;
    	this.installationStatus = installationStatus;
    	this.installedPlugins = installedPlugins;
    	
    }
    
    public static PluginInstallationResult getInstance(InstallationFailureCode installationFailureCode,
    		                                           InstallationStatus installationStatus,
    		                                           String[] installedPlugins){
    	return new PluginInstallationResult(installationFailureCode, installationStatus, installedPlugins);
    }
    
	public String[] getInstalledPlugins() {
		return installedPlugins;
	}

	public void setInstalledPlugins(String[] installedPlugins) {
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
