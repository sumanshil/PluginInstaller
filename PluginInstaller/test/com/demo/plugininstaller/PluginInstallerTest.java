package com.demo.plugininstaller;

import junit.framework.Assert;

import org.junit.Test;

import com.demo.plugininstaller.common.InstallationFailureCode;
import com.demo.plugininstaller.common.InstallationStatus;
import com.demo.plugininstaller.common.PluginInstallationManager;
import com.demo.plugininstaller.common.PluginInstallationResult;

public class PluginInstallerTest {

	@Test
	public void testSuccessfullInstallationTest1(){
		String[] input = new String[] {
				"A:B",
				"B:C",
				"C"
		};
		PluginInstallationResult result = PluginInstallationManager.getInstance().install(input);
	    Assert.assertTrue(result.getInstallationStatus() == InstallationStatus.SUCCESSFUL);
	    Assert.assertTrue(result.getInstallationFailureCode() == null);
	    Assert.assertTrue(result.getInstalledPlugins() != null);
	}
	
	@Test
	public void testSuccessfullInstallationTest2(){
		String[] input = new String[] {
				"A:B,C",
				"C:D,E",
				"B:",
				"D:",
				"E:"
		};
		PluginInstallationResult result = PluginInstallationManager.getInstance().install(input);
	    Assert.assertTrue(result.getInstallationStatus() == InstallationStatus.SUCCESSFUL);
	    Assert.assertTrue(result.getInstallationFailureCode() == null);
	}

	@Test
	public void testSuccessfullInstallationTest3(){
		String[] input = new String[] {
				"KittenService:",
				 "Leetmeme: Cyberportal",
				 "Cyberportal: Ice",
				 "CamelCaser: KittenService",
				 "Fraudstream: Leetmeme",
				 "Ice:"
		};
		PluginInstallationResult result = PluginInstallationManager.getInstance().install(input);
	    Assert.assertTrue(result.getInstallationStatus() == InstallationStatus.SUCCESSFUL);
	    Assert.assertTrue(result.getInstallationFailureCode() == null);
	    Assert.assertTrue(result.getInstalledPlugins() != null);
	    Assert.assertTrue(result.getInstalledPlugins().length == 6);
	    
	    int kitenServiceIndex = 0;
	    int camelCaserIndex = 0;
	    int leetMemeIndex = 0;
	    int cyberPortalIndex  = 0;
	    int fraudSteamIndex = 0;
	    int iceIndex = 0;
	    int index = 0;
	    for (String str : result.getInstalledPlugins()) {
	    	if ("KittenService".equals(str)){
	    		kitenServiceIndex = index;
	    	} else if ("Leetmeme".equals(str)){
	    		leetMemeIndex = index;
	    	} else if ("Cyberportal".equals(str)){
	    		cyberPortalIndex = index;
	    	} else if ("Ice".equals(str)){
	    		iceIndex = index;
	    	} else if ("CamelCaser".equals(str)){
	    		camelCaserIndex = index;
	    	} else if ("Fraudstream".equals(str)){
	    		fraudSteamIndex = index;
	    	}	    
	    	index++;
	    }
	    Assert.assertTrue(kitenServiceIndex < camelCaserIndex);
	    Assert.assertTrue(leetMemeIndex > cyberPortalIndex);
	    Assert.assertTrue(iceIndex < cyberPortalIndex);
	    Assert.assertTrue(leetMemeIndex < fraudSteamIndex);
	}

	@Test
	public void testCyclicDependencyInstallationTest(){
		String[] input = new String[] {
				"A:B",
				"B:A"
		};
		PluginInstallationResult result = PluginInstallationManager.getInstance().install(input);
	    Assert.assertTrue(result.getInstallationStatus() == InstallationStatus.FAILED);
	    Assert.assertTrue(result.getInstallationFailureCode() == InstallationFailureCode.CYCLIC_DEPENDEBCY);
	}

	@Test
	public void testMissingDependencyInstallationTest(){
		String[] input = new String[] {
				"A:B",
				"B:C"
		};
		PluginInstallationResult result = PluginInstallationManager.getInstance().install(input);
	    Assert.assertTrue(result.getInstallationStatus() == InstallationStatus.FAILED);
	    Assert.assertTrue(result.getInstallationFailureCode() == InstallationFailureCode.MISSING_DEPENDENCY);
	}

	@Test
	public void testInvalidInputInstallationTest(){
		String[] input = new String[] {
				"A:B:A",
				"B:A"
		};
		PluginInstallationResult result = PluginInstallationManager.getInstance().install(input);
	    Assert.assertTrue(result.getInstallationStatus() == InstallationStatus.FAILED);
	    Assert.assertTrue(result.getInstallationFailureCode() == InstallationFailureCode.INVALID_FORMAT);
	    Assert.assertTrue(result.getInstalledPlugins() == null);
	}
	
	@Test
	public void testInvalidDuplicateEntryTest(){
		String[] input = new String[] {
				"A:B",
				"B:C",
				"A:"
		};
		PluginInstallationResult result = PluginInstallationManager.getInstance().install(input);
	    Assert.assertTrue(result.getInstallationStatus() == InstallationStatus.FAILED);
	    Assert.assertTrue(result.getInstallationFailureCode() == InstallationFailureCode.DUPLICATE_ENTRY);
	    Assert.assertTrue(result.getInstalledPlugins() == null);
	}
	
	@Test
	public void testInvalidInputInstallationTest1(){
		String[] input = new String[] {
				"KittenService:","Leetmeme: Cyberportal","Cyberportal: Ice","CamelCaser: KittenService","Fraudstream:","Ice: Leetmeme"
		};
		PluginInstallationResult result = PluginInstallationManager.getInstance().install(input);
	    Assert.assertTrue(result.getInstallationStatus() == InstallationStatus.FAILED);
	    Assert.assertTrue(result.getInstallationFailureCode() == InstallationFailureCode.CYCLIC_DEPENDEBCY);
	    Assert.assertTrue(result.getInstalledPlugins() == null);
	}

}
