package be.butskri.commons.infrastructure;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertyConfigurationTest {

	private static final String APPLICATION_DIR = "APPLICATION_DIR";
	private static final String ENVIRONMENT = "ENVIRONMENT";
	private File applicationDir;
	private File configDir;
	private String vorigeEnvironment;
	private String vorigeApplicationDir;
	private ApplicationContext applicationContext;

	@Before
	public void setUp() {
		applicationDir = createTempDirectory();
		configDir = new File(applicationDir, "config");
		configDir.mkdir();
		createPropertyFiles();
		
		vorigeEnvironment = System.getProperty(ENVIRONMENT);
		vorigeApplicationDir = System.getProperty(APPLICATION_DIR);
		System.setProperty(ENVIRONMENT, "dev");
		System.setProperty(APPLICATION_DIR, applicationDir.getAbsolutePath());
		System.setProperty("systemProperty", "waardeSystemProperty");
		
		applicationContext = new ClassPathXmlApplicationContext("/property-configuration-test-config.xml");
	}

	@After
	public void tearDown() throws Exception {
		setSystemProperty(ENVIRONMENT, vorigeEnvironment);
		setSystemProperty(APPLICATION_DIR, vorigeApplicationDir);
		
		FileUtils.deleteDirectory(applicationDir);
	}

	private void setSystemProperty(String key, String value) {
		if (value == null) {
			System.clearProperty(key);
		} else {
			System.setProperty(key, value);
		}
	}

	@Test
	public void waardeVanSystemPropertyKrijgtVoorrangOpAlDeRest() {
		MyBean myBean = (MyBean) applicationContext
				.getBean("beanMetWaardeVanSystemProperty");
		Assert.assertEquals("waardeSystemProperty", myBean.getPropertyValue());
	}

	@Test
	public void environmentSpecifiekeWaardeUitApplicationDirKrijgtVoorrangOpNietEnvironmentSpecifiekeWaarde() {
		MyBean myBean = (MyBean) applicationContext
				.getBean("beanMetEnvironmentSpecifiekeWaardeUitApplicationDir");
		Assert.assertEquals(
				"waardeEnvironmentSpecifiekePropertyUitApplicationDir", myBean
						.getPropertyValue());
	}

	@Test
	public void waardeUitApplicationDirKrijgtVoorrangOpEnvironmentSpecifiekeWaardeOpClasspath() {
		MyBean myBean = (MyBean) applicationContext
				.getBean("beanMetApplicationDirWaarde");
		Assert.assertEquals("waardeApplicationDirProperty", myBean
				.getPropertyValue());
	}

	@Test
	public void environmentSpecifiekeWaardeUitClasspathKrijgtVoorrangOpNietEnvironmentSpecifiekeWaardeUitClasspath() {
		MyBean myBean = (MyBean) applicationContext
				.getBean("beanMetEnvironmentSpecifiekePropertyOpClasspath");
		Assert.assertEquals("waardeEnvironmentSpecifiekePropertyOpClasspath",
				myBean.getPropertyValue());
	}

	@Test
	public void defaultWordtDeNietEnvironmentSpecifiekeWaardeUitHetClasspathTerugGegeven() {
		MyBean myBean = (MyBean) applicationContext
				.getBean("beanMetPropertyOpClasspath");
		Assert.assertEquals("waardePropertyOpClasspath", myBean
				.getPropertyValue());
	}

	@Test
	public void placeHolderWordtTeruggegevenIndienPropertyNietBestaat() {
		MyBean myBean = (MyBean) applicationContext
				.getBean("beanMetOnbestaandeProperty");
		Assert.assertEquals("${onbestaandeProperty}", myBean.getPropertyValue());
	}

	public File createTempDirectory() {
		try {
			File temp = File.createTempFile("temp", Long.toString(System
					.nanoTime()));

			if (!(temp.delete())) {
				throw new RuntimeException("Could not delete temp file: "
						+ temp.getAbsolutePath());
			}

			if (!(temp.mkdir())) {
				throw new RuntimeException("Could not create temp directory: "
						+ temp.getAbsolutePath());
			}

			return (temp);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void createPropertyFiles() {
		saveProperties("dev.property-configuration-test.properties", //
				"environmentSpecifiekePropertyUitApplicationDir=waardeEnvironmentSpecifiekePropertyUitApplicationDir");
		saveProperties("property-configuration-test.properties", //
				"environmentSpecifiekePropertyUitApplicationDir=ongeldig", //
				"applicationDirProperty=waardeApplicationDirProperty");
	}

	private void saveProperties(String filename, String... lines) {
		File file = new File(configDir, filename);
		try {
			FileUtils.writeLines(file, Arrays.asList(lines));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
