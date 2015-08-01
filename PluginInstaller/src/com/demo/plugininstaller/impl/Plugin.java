package com.demo.plugininstaller.impl;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

public class Plugin implements Comparable<Plugin> {
	
    private String name;
    private List<String> dependencies;
    private boolean installed;
    public boolean isInstalled() {
		return installed;
	}

	public void setInstalled(boolean installed) {
		this.installed = installed;
	}

	private Plugin() {
    	dependencies = new ArrayList<String>();
    }
    
    public static Plugin getInstance() {
    	return new Plugin();
    }
    
    public Plugin populate(String str) throws InvalidAlgorithmParameterException {
    	String[] arr = str.split(":");
    	if (arr.length > 2){
    		throw new InvalidAlgorithmParameterException();
    	}
    	this.name = arr[0];
    	if (arr.length > 1){
    		String dependencies = arr[1];
    		String[] dependencyArr = dependencies.split(",");
    		for ( String dependencyString : dependencyArr) {
    			this.dependencies.add(dependencyString);
    		}
    	}
    	return this;
    }
    
    @Override
    public int hashCode() {
    	return this.name.intern().hashCode();
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}

	@Override
    public boolean equals(Object obj) {
    	if (!(obj instanceof Plugin)) {
    		return false;
    	}
    	return this.name.intern().equals(((Plugin)obj).name);
    }

	@Override
	public int compareTo(Plugin otherPlugin) {		
		return this.name.intern().compareTo(otherPlugin.name.intern());
	}
	
	public List<String> getDependencies() {
		return dependencies;
	}
}
