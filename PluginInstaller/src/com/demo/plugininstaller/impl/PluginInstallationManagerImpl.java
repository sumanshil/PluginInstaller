package com.demo.plugininstaller.impl;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.demo.plugininstaller.common.InstallationFailureCode;
import com.demo.plugininstaller.common.InstallationStatus;
import com.demo.plugininstaller.common.PluginInstallationManager;
import com.demo.plugininstaller.common.PluginInstallationResult;

public class PluginInstallationManagerImpl extends PluginInstallationManager {

	@Override
	public PluginInstallationResult install(String[] plugins) {	
		
		List<Plugin> pluginSet = new ArrayList<>();
		Set<String> pluginNames = new HashSet<>();
		for (String plugin : plugins){
			
			Plugin pluginInstance= null;
			try {
				pluginInstance = Plugin.getInstance().populate(plugin);
				pluginNames.add(pluginInstance.getName());
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
		// check for missing dependency 
		for (Plugin plugin : pluginSet) {
			for ( String dependencyName : plugin.getDependencies()) {
				if (!pluginNames.contains(dependencyName)){
					   return PluginInstallationResult.getInstance(InstallationFailureCode.MISSING_DEPENDENCY,
	                           InstallationStatus.FAILED,
	                           null);	 									
				}
			}
		}

		Set<Plugin> installed = new LinkedHashSet<Plugin>();
		while (true) {
			if (installationComplete(pluginSet)){
				break;
			}
			boolean installedFlag = false;
			Set<Plugin> installedCurrentCycle = new LinkedHashSet<Plugin>();
			for (Plugin plugin : pluginSet) {
				if ( isReadyToBeInstalled(plugin, installed)
					 && ! installed.contains(plugin)) {
					installed.add(plugin);
					installedCurrentCycle.add(plugin);
					installedFlag = true;
					plugin.setInstalled(true);
				}
			}
			if (!installedFlag) {
				return PluginInstallationResult.
						getInstance(InstallationFailureCode.CYCLIC_DEPENDEBCY,
								    InstallationStatus.FAILED, null);
			}
			// remove installed plugins from dependency list
			for (Plugin plugin : installedCurrentCycle ) {
				for ( Plugin plugin2 : pluginSet ){
					plugin2.removeDependency(plugin.getName());
				}
			}
				
		}
		
		// set up the final result
		String[] retVal = new String[installed.size()];
		int index = 0;
		for (Plugin plugin : installed) {
			retVal[index++]= plugin.getName();
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
		if ( plugin.getDependencies().size() == 0 ) { 
			return true;
		} 
		return false;
	}


}
