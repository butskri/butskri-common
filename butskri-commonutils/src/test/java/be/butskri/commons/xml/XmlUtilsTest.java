package be.butskri.commons.xml;

import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import be.butskri.commons.test.BaseAssert;

@RunWith(BlockJUnit4ClassRunner.class)
public class XmlUtilsTest {

	private static final String WAARDE1 = "Dit is de waarde die ik wil vinden";
	private static final String WAARDE2 = "Nog een waarde die ik wil vinden";
	private static final String WAARDE3 = "Hopelijk wordt deze waarde ook gevonden";
	private XmlUtils xmlUtils;

	@Before
	public void setUp() {
		xmlUtils = new XmlUtils();
	}

	@Test
	public void getTagValuesGeeftDeWaardenVanDeTagMetDeGegevenNaamTerugVoorDeOpgegevenInputStream() {
		InputStream inputStream = this.getClass().getResourceAsStream("/tagwaarden.xml");

		List<String> tagwaarden = xmlUtils.getTagWaarden(inputStream, "tagToBeFound");
		BaseAssert.assertContainsOnly(tagwaarden, WAARDE1, WAARDE2, WAARDE3);
	}
}
