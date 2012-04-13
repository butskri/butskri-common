package be.butskri.commons.infrastructure;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

@RunWith(JUnit4ClassRunner.class)
public class EnvironmentDependentPropertiesTest {
    
    private String testFile;
    private String testFileAsURL;
    
    @Before
    public void setUp() throws Exception {
        testFile = System.getProperty("java.io.tmpdir") + "environment" + System.currentTimeMillis() + ".properties";
        testFileAsURL = "file:///" + testFile;
        
        PrintWriter pw = new PrintWriter(testFile);
        pw.println("property1=value1");
        pw.println("property2=value2");
        pw.println("property3=value3");
        pw.println("property1{" + EnvironmentDependentProperties.getSuffix() + "}=localvalue1");
        pw.println("property3{localEnv}=localvalue3");
        
        pw.flush();
        pw.close();
    }
    
    @After
    public void tearDown() {
        new File(testFile).deleteOnExit();
    }
    
    @Test
    public void testEnvironmentProperties() throws Exception {
        Properties properties = new EnvironmentDependentProperties(testFileAsURL);
        assertEquals("localvalue1", properties.get("property1"));
        assertEquals("value2", properties.get("property2"));
    }
    
    @Test
    public void testEnvironmentPropertiesDependingOnSystemProperty() throws Exception {
        assertEquals("value3", new EnvironmentDependentProperties(testFileAsURL).get("property3"));
        System.setProperty(EnvironmentDependentProperties.ENVIRONMENTSUFFIX, "localEnv");
        assertEquals("localvalue3", new EnvironmentDependentProperties(testFileAsURL).get("property3"));
        System.clearProperty(EnvironmentDependentProperties.ENVIRONMENTSUFFIX);
    }
    
    @Test
    public void testLocationFromSystemProperty() throws Exception {
        System.setProperty(EnvironmentDependentProperties.CONFIGLOCATION, testFileAsURL);
        assertEquals("value3", new EnvironmentDependentProperties().get("property3"));
        System.clearProperty(EnvironmentDependentProperties.CONFIGLOCATION);
    }
}
