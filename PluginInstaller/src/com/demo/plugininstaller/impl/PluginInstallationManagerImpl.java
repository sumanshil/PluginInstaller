package com.demo.plugininstaller.impl;

import java.util.ArrayList;

import com.demo.plugininstaller.common.InstallationFailureCode;
import com.demo.plugininstaller.common.InstallationStatus;
import com.demo.plugininstaller.common.PluginInstallationManager;
import com.demo.plugininstaller.common.PluginInstallationResult;

public class PluginInstallationManagerImpl extends PluginInstallationManager {

	@Override
	public PluginInstallationResult install(String[] plugins) {		
		if (!validate(plugins)) {
		   return PluginInstallationResult.getInstance(InstallationFailureCode.INVALID_INPUT,
				                                       InstallationStatus.FAILED,
				                                       null);	
		}
		PluginInstallationResult result =  PluginInstallationResult.
				getInstance(InstallationFailureCode.UNKNOWN, InstallationStatus.FAILED, new ArrayList<>());
		return result;
	}


}
