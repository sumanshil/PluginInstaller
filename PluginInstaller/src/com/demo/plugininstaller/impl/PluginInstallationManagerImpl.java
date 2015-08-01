package com.demo.plugininstaller.impl;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.demo.plugininstaller.common.InstallationFailureCode;
import com.demo.plugininstaller.common.InstallationStatus;
import com.demo.plugininstaller.common.PluginInstallationManager;
import com.demo.plugininstaller.common.PluginInstallationResult;

public class PluginInstallationManagerImpl extends PluginInstallationManager {

	@Override
	public PluginInstallationResult install(String[] plugins) {	
		
		List<Plugin> pluginSet = new ArrayList<>();
		for (String plugin : plugins){
			
			Plugin pluginInstance= null;
			try {
				pluginInstance = Plugin.getInstance().populate(plugin);
			} catch (InvalidAlgorithmParameterException e) {
				   return PluginInstallationResult.getInstance(InstallationFailureCode.INVALID_FORMAT,
                           InstallationStatus.FAILED,
                           null);	 
			}
			if (pluginSet.contains(pluginInstance)){
				   return PluginInstallationResult.getInstance(InstallationFailureCode.DUPLICATE_ENTRY,
                           InstallationStatus.FAILED,
                           null);	 				
			}
			pluginSet.add(pluginInstance);
		}
		
		InstallationFailureCode failureCode = validate(pluginSet);
		if ( failureCode != null ) {
		   return PluginInstallationResult.getInstance(failureCode,
				                                       InstallationStatus.FAILED,
				                                       null);	
		}

		Set<Plugin> installed = new HashSet<Plugin>();
		while (true) {
			if (installationComplete(pluginSet)){
				break;
			}
			boolean installedFlag = false;
			for (Plugin plugin : pluginSet) {
				if ( isReadyToBeInstalled(plugin, installed)
					 && ! installed.contains(plugin)	) {
					installed.add(plugin);
					installedFlag = true;
					plugin.setInstalled(true);
				}
			}
			if (!installedFlag) {
				return PluginInstallationResult.
						getInstance(InstallationFailureCode.MISSING_DEPENDENCY,
								    InstallationStatus.FAILED, null);
			}
				
		}
		Set<String> retVal = new HashSet<>();
		for (Plugin plugin : installed) {
			retVal.add(plugin.getName());
		}
		
		PluginInstallationResult result =  PluginInstallationResult.
				getInstance(null, InstallationStatus.SUCCESSFUL, retVal);
		return result;
	}

	private boolean installationComplete(List<Plugin> pluginSet) {
       for (Plugin plugin : pluginSet){
    	   if (!plugin.isInstalled()){
    		   return false;
    	   }
       }
		return true;
	}

	private boolean isReadyToBeInstalled(Plugin plugin, Set<Plugin> installed) {
		return plugin.getDependencies().size() == 0 || installed.contains(plugin) ;
	}


}
