package com.demo.plugininstaller.impl;

import com.demo.plugininstaller.common.InstallationFailureCode;
import com.demo.plugininstaller.common.InstallationStatus;
import com.demo.plugininstaller.common.PluginInstallationManager;
import com.demo.plugininstaller.common.PluginInstallationResult;

public class PluginInstallationManagerImpl extends PluginInstallationManager {

	@Override
	public PluginInstallationResult install(String[] plugins) {		
		PluginInstallationResult result =  PluginInstallationResult.
				getInstance(InstallationFailureCode.UNKNOWN, InstallationStatus.FAILED);
		return result;
	}

}
