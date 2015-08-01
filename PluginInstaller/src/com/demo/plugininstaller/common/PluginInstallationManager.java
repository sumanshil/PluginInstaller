package com.demo.plugininstaller.common;

import com.demo.plugininstaller.impl.PluginInstallationManagerImpl;

public abstract class PluginInstallationManager {
    public static PluginInstallationManager getInstance() {
    	return new PluginInstallationManagerImpl();
    }

    public abstract PluginInstallationResult install(String[] plugins);
}
