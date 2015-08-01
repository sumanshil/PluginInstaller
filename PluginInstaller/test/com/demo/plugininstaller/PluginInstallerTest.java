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
				"B:A"
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
	    Assert.assertTrue(result.getInstallationFailureCode() == InstallationFailureCode.INVALID_INPUT);
	    Assert.assertTrue(result.getInstalledPlugins() == null);
	}
}
