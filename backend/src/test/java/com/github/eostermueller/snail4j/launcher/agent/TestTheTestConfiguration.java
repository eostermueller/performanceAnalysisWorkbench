package com.github.eostermueller.snail4j.launcher.agent;

import static org.junit.Assert.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class TestTheTestConfiguration {

	
	public static final String unix_ABS_PATH_TO_TJP = "/Users/erikostermueller/Documents/src/jdist/tjpUnzipped/tjp";
	public static final String unix_JAVA_HOME = "/Library/Java/JavaVirtualMachines/openjdk-11.0.2.jdk/Contents/Home";
	private static final String LITTLE_MOCK_MASTER = "littleMock-master";
	
	private static final String JAVA_PERF_TROUBLESHOOTING_MASTER = "javaPerformanceTroubleshooting-master";
	private static final String MAVEN_HOME_SUFFIX = "maven/apache-maven-3.5.4";
	
	private static final String win_ABS_PATH_TO_PG = "C:/UseXrs/erikXostermueller/Documents/src/jdist/tjpUnzipped/tjp";
	private static final String win_JAVA_HOME = "C:/Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home";
	
	@Test
	public void canCreatePathsBasedOnInstallationHome_unixStyle() {
		Path tjpHome = Paths.get(unix_ABS_PATH_TO_TJP);
		Path javaHome = Paths.get(this.unix_JAVA_HOME);
		char myPlatformSeparater = '/';
				
		TestConfiguration testConfiguration = new TestConfiguration(tjpHome, javaHome);
		
		Path jptHome = testConfiguration.getSutAppHome();
		Assert.assertEquals(unix_ABS_PATH_TO_TJP + myPlatformSeparater + JAVA_PERF_TROUBLESHOOTING_MASTER, jptHome.toAbsolutePath().toString() );
		

	}
	/**
	 * 
	 */
	@Test
	public void canCreatePathsBasedOnInstallationHome_winStyle() {
		Path pgHome = Paths.get(win_ABS_PATH_TO_PG);
		Path javaHome = Paths.get(win_JAVA_HOME);
		char myPlatformSeparater = '/';
				
		TestConfiguration testConfiguration = new TestConfiguration(pgHome, javaHome);
		

		Path sutHome = testConfiguration.getSutAppHome();
		Assert.assertEquals(win_ABS_PATH_TO_PG + myPlatformSeparater + JAVA_PERF_TROUBLESHOOTING_MASTER, sutHome.toString() );
		
		

	}
	@Test
	public void canFindMvnExecutableOnWindows() {
		Path tjpHome = Paths.get(unix_ABS_PATH_TO_TJP);
		Path javaHome = Paths.get(this.unix_JAVA_HOME);
		char myPlatformSeparater = '/';
				
		TestConfiguration testConfiguration = new TestConfiguration(tjpHome, javaHome);
		String mavenHome = "C:/Users/erikostermueller/.snail4j/apache-maven-3.6.2";
		
		testConfiguration.setMavenHome( Paths.get(mavenHome ));
		
		testConfiguration.setOsWin(true);
		
		Assert.assertEquals( "mvn.cmd", testConfiguration.getMavenExeName() );
		Assert.assertEquals( mavenHome + "/bin/mvn.cmd", testConfiguration.createMavenExePath() );
		
		
	}
	@Test 
	public void canAddMavenOfflineFlagToLoadGen() {
		Path tjpHome = Paths.get(unix_ABS_PATH_TO_TJP);
		Path javaHome = Paths.get(this.unix_JAVA_HOME);
		char myPlatformSeparater = '/';
				
		TestConfiguration testCfg = new TestConfiguration(tjpHome, javaHome);
		String launch = testCfg.MAVEN_EXE_PATH + " verify";
		String offlineLaunch = testCfg.MAVEN_EXE_PATH + " --offline verify";
		
		testCfg.setLoadGeneratorLaunchCmd(launch);
		testCfg.setMavenOnline(true);
		
		Assert.assertEquals(launch, testCfg.getLoadGeneratorLaunchCmd());
		
		testCfg.setMavenOnline(false);

		Assert.assertEquals(offlineLaunch, testCfg.getLoadGeneratorLaunchCmd());
		
		
	}
	@Test 
	public void canAddMavenOfflineFlagToProcessManager() {
		Path tjpHome = Paths.get(unix_ABS_PATH_TO_TJP);
		Path javaHome = Paths.get(this.unix_JAVA_HOME);
		char myPlatformSeparater = '/';
				
		TestConfiguration testCfg = new TestConfiguration(tjpHome, javaHome);
		String launch = testCfg.MAVEN_EXE_PATH + " verify";
		String offlineLaunch = testCfg.MAVEN_EXE_PATH + " --offline verify";
		
		testCfg.setProcessManagerLaunchCmd(launch);
		testCfg.setMavenOnline(true);
		
		Assert.assertEquals(launch, testCfg.getProcessManagerLaunchCmd());
		
		testCfg.setMavenOnline(false);

		Assert.assertEquals(offlineLaunch, testCfg.getProcessManagerLaunchCmd());
		
		
	}
	@Test
	public void canFindMvnExecutableOnNonWindows() {
		Path tjpHome = Paths.get(unix_ABS_PATH_TO_TJP);
		Path javaHome = Paths.get(this.unix_JAVA_HOME);
		char myPlatformSeparater = '/';
				
		TestConfiguration testConfiguration = new TestConfiguration(tjpHome, javaHome);
		String mavenHome = "/Users/erikostermueller/.snail4j/apache-maven-3.6.2";
		
		testConfiguration.setMavenHome( Paths.get(mavenHome ));
		
		testConfiguration.setOsWin(false);
		
		Assert.assertEquals( "mvn", testConfiguration.getMavenExeName() );
		Assert.assertEquals( mavenHome + "/bin/mvn", testConfiguration.createMavenExePath() );
		
		
	}

}