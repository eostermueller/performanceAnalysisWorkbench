package com.github.eostermueller.snail4j;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.eostermueller.snail4j.launcher.Messages;

/**
 * This non-static version of OsUtils exists so state can be prepared for JUnit tests, as in TestEnvironment.java
 * @author eoste
 *
 */
public class NonStaticOsUtils {

	public static final String JAVA_HOME = "JAVA_HOME";
	private OsEnvWrapper env;

	public NonStaticOsUtils(OsEnvWrapper val) {
		this.env = val;
	}
	public NonStaticOsUtils() {
		this.env = new RealEnvWrapper();
	}
	
	public OsEnvWrapper getEnv() {
		return this.env;
	}
	
	public Path get_JAVA_HOME() throws Snail4jException {
		String javaHomeEnvVar = getEnv().get(JAVA_HOME);
		Messages m = DefaultFactory.getFactory().getMessages();
		Path p = null;
		if (javaHomeEnvVar!=null && javaHomeEnvVar.trim().length()>0) {
			p = Paths.get(javaHomeEnvVar);
			
			if (!p.toFile().exists() || !p.toFile().isDirectory() ) {
				throw new Snail4jException( m.javaHomeFolderDoesNotExistOrLackingPermissions(p.toFile()) );
			}
		} else {
			throw new Snail4jException(m.javaHomeEnvVarNotSet());
		}
		return p;
	}
	
	
}

