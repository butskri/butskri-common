package be.butskri.commons.util;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import be.butskri.commons.test.BaseAssert;
import be.butskri.commons.types.Bedrag;

@RunWith(JUnit4ClassRunner.class)
public class RandomizerUtilTest {

	private static final int AANTAL = 100;

	@Test
	public void randomBedragGeeftGeldigBedragTerug() {
		assertNotNull(RandomizerUtil.randomBedrag());
	}

	@Test
	public void randomBedragGeeftRandomBedragTerug() {
		Set<Bedrag> bedragen = new HashSet<Bedrag>();
		for (int i = 0; i < AANTAL; i++) {
			bedragen.add(RandomizerUtil.randomBedrag());
		}

		assertGeldigAantalRandomWaarden(bedragen);
	}

	@Test
	public void randomLongGeeftRandomLongTerug() {
		Set<Long> waarden = new HashSet<Long>();
		for (int i = 0; i < AANTAL; i++) {
			waarden.add(RandomizerUtil.randomLong());
		}

		assertGeldigAantalRandomWaarden(waarden);
	}

	private <T extends Object> void assertGeldigAantalRandomWaarden(Set<T> waarden) {
		BaseAssert.assertBetween(waarden.size(), AANTAL / 2, AANTAL + 1);
	}

}
