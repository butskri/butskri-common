package be.butskri.commons.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class EnvironmentDependentProperties extends Properties {

	private static final long serialVersionUID = 1L;

	public static final String CONFIGLOCATION = "CONFIGLOCATION";

	public static final String DEFAULTLOCATION = "classpath:config.properties";

	public static final String ENVIRONMENTSUFFIX = "ENVIRONMENTSUFFIX";

	private String environmentString;

	public EnvironmentDependentProperties() {
		this(System.getProperty(CONFIGLOCATION, DEFAULTLOCATION));
	}

	public EnvironmentDependentProperties(String file) {

		environmentString = "{" + getSuffix() + "}";
		loadProperties(file);

		LogFactory.getLog(this.getClass()).info("Loaded " + file + " using environment suffix: " + environmentString);
	}

	private void loadProperties(String file) {
		InputStream in = null;
		try {
			ResourceLoader loader = new DefaultResourceLoader();
			Resource res = loader.getResource(file);
			in = res.getInputStream();
			this.load(in);
		} catch (Exception e) {
			throw new RuntimeException("Resource " + file + " not found");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ignore) {
				}
			}
		}
	}

	@Override
	public synchronized Object put(Object key, Object value) {
		String environmentKey = key.toString();

		if (environmentKey.endsWith(environmentString)) {
			environmentKey = environmentKey.substring(0, environmentKey.lastIndexOf(environmentString));

			return super.put(environmentKey, value);
		} else if (environmentKey.endsWith("}") && (environmentKey.indexOf("{") > 0)) {
			return null;
		} else if (super.get(environmentKey) == null) {
			return super.put(environmentKey, value);
		}

		return null;
	}

	static String getSuffix() {
		String suffix = System.getProperty(ENVIRONMENTSUFFIX);

		if (suffix == null) {
			try {
				InetAddress addr = InetAddress.getLocalHost();
				suffix = addr.getHostName();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		return suffix;
	}

}
