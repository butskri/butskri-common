package be.butskri.commons.infrastructure;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class PropertyConfiguration extends PropertyPlaceholderConfigurer implements InitializingBean {
	
	private List<String> baseNames;
	private String env;
	private String applicationDir;
	
	public PropertyConfiguration() {
		setSystemPropertiesMode(SYSTEM_PROPERTIES_MODE_OVERRIDE);
		setIgnoreUnresolvablePlaceholders(true);
	}
	
	public void setBaseNames(List<String> baseNames) {
		this.baseNames = baseNames;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		env = System.getProperty("ENVIRONMENT");
		applicationDir = System.getProperty("APPLICATION_DIR");
		
		List<Resource> resources = new ArrayList<Resource>();
		for (String basename: baseNames) {
			resources.add(new ClassPathResource("/config/" + getPropertiesFile(basename)));
			resources.add(new ClassPathResource("/config/" + getEnvPropertiesFile(basename)));
			resources.add(new FileSystemResource(new File(getConfigDir(), getPropertiesFile(basename))));
			resources.add(new FileSystemResource(new File(getConfigDir(), getEnvPropertiesFile(basename))));
		}
		resources.removeAll(notExistingResources(resources));
		setLocations(resources.toArray(new Resource[resources.size()]));
	}

	private Collection<Resource> notExistingResources(List<Resource> resources) {
		Collection<Resource> result = new ArrayList<Resource>();
		for (Resource resource : resources) {
			if (!resource.exists()) {
				result.add(resource);
			}
		}
		return result;
	}

	private File getConfigDir() {
		return new File(applicationDir, "config");
	}

	private String getEnvPropertiesFile(String basename) {
		return env + "." + basename + ".properties";
	}

	private String getPropertiesFile(String basename) {
		return basename + ".properties";
	}
	
}
