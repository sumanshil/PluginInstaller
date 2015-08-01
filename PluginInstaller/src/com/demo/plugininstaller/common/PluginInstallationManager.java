package com.demo.plugininstaller.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.demo.plugininstaller.impl.Plugin;
import com.demo.plugininstaller.impl.PluginInstallationManagerImpl;

public abstract class PluginInstallationManager {
    public static PluginInstallationManager getInstance() {
    	return new PluginInstallationManagerImpl();
    }

    public abstract PluginInstallationResult install(String[] plugins);
    
    /**
     * plugin entries should be of the form "<plugin-name>:<dependency-plugin>,..."
     * @param plugins
     * @return true if input is valid, false otherwise
     */
	protected InstallationFailureCode validate(List<Plugin> plugins) {
		for (Plugin plugin : plugins) {
			for(String dependency : plugin.getDependencies()) {
				for ( Plugin plugin2 : plugins) {
					if (plugin2.getName().intern().equals(dependency.intern())) {
						if (plugin2.getDependencies().contains(plugin.getName().intern())) {
							return InstallationFailureCode.CYCLIC_DEPENDEBCY;
						}
					}
				}
			}
		}
		return null;
	}

}
