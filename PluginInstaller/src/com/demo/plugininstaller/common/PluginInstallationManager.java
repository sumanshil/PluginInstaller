package com.demo.plugininstaller.common;

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
	protected boolean validate(String[] plugins) {
		for (String plugin : plugins) {
			String[] str = plugin.split(":");
			if (str.length > 2) {
				return false;
			}
		}
		return true;
	}

}
