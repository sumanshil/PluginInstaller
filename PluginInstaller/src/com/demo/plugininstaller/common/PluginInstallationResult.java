package com.demo.plugininstaller.common;

/**
 * This class will contain the installation result.
 * @author SumanChandra
 *
 */
public class PluginInstallationResult {
	
    private InstallationFailureCode installationFailureCode;
    private InstallationStatus installationStatus;
    
    private PluginInstallationResult(InstallationFailureCode installationFailureCode,
    		                         InstallationStatus installationStatus) {
    	this.installationFailureCode = installationFailureCode;
    	this.installationStatus = installationStatus;
    	
    }
    
    public static PluginInstallationResult getInstance(InstallationFailureCode installationFailureCode,
    		                                           InstallationStatus installationStatus){
    	return new PluginInstallationResult(installationFailureCode, installationStatus);
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
