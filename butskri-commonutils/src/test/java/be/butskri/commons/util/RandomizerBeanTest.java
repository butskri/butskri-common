package be.butskri.commons.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class RandomizerBeanTest {

	private RandomizerBean randomizerBean;
	private static final int AANTAL_UNIEKE_WAARDEN = 100;
	static final String CHARSET = "ABCD123";

	@Test
	public void getRandomDoubleGeeftUniekeDoublesTerug() {
		Set<Double> uniekeDoubles = new HashSet<Double>();

		for (int i = 0; i < AANTAL_UNIEKE_WAARDEN; i++) {
			uniekeDoubles.add(randomizerBean.getRandomDouble());
		}
		assertEquals(AANTAL_UNIEKE_WAARDEN, uniekeDoubles.size());
	}

	@Test
	public void getRandomIntegerGeeftUniekeIntegersTerug() {
		Set<Integer> uniekeInts = new HashSet<Integer>();

		for (int i = 0; i < AANTAL_UNIEKE_WAARDEN; i++) {
			uniekeInts.add(randomizerBean.getRandomInt());
		}
		assertEquals(AANTAL_UNIEKE_WAARDEN, uniekeInts.size());
	}

	@Test
	public void getRandomLongGeeftUniekeLongsTerug() {
		Set<Long> uniekeLongs = new HashSet<Long>();

		for (int i = 0; i < AANTAL_UNIEKE_WAARDEN; i++) {
			uniekeLongs.add(randomizerBean.getRandomLong());
		}
		assertEquals(AANTAL_UNIEKE_WAARDEN, uniekeLongs.size());
	}

	@Test
	public void getRandomStringBevatKaraktersUitDeCharSet() {
		String randomString = randomizerBean.getRandomString(10, CHARSET);

		assertEquals(10, randomString.length());

		for (int i = 0; i < randomString.length(); i++) {
			assertTrue(randomString.charAt(i) + " was unexpected", (CHARSET
					.indexOf(randomString.charAt(i)) >= 0));
		}
	}

	@Test
	public void getRandomStringIsRandom() {
		Set<String> uniekeStrings = new HashSet<String>();
		for (int i = 0; i < AANTAL_UNIEKE_WAARDEN; i++) {
			String newlyGeneratedRandomString = randomizerBean.getRandomString(
					10, CHARSET);
			uniekeStrings.add(newlyGeneratedRandomString);
		}
		assertEquals(AANTAL_UNIEKE_WAARDEN, uniekeStrings.size());
	}

	@Test
	public void randomizerConstructorThrowsIllegalArgumentExceptionOnInvalidAlgorithm() {
		try {
			new RandomizerBean("mijn algoritme");
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
		}
	}

	@Before
	public void setUp() throws Exception {
		randomizerBean = new RandomizerBean();
	}
}
