package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simu.model.Palvelutapahtuma;



/**
 * @author Dahlman, Laamo, Lappi
 *
 */

class PalvelutapahtumaTest {
	
	 private static final int ASIAKKAANID = 1;
	 private static final double SAAPUMISAIKA = 2.0;
	 private static final double POISTUMISAIKA= 5.0;
	 Palvelutapahtuma tapahtuma = new Palvelutapahtuma(ASIAKKAANID);
	 


	@BeforeEach
	void setUp() throws Exception {
		 tapahtuma.setSaapumisaika(SAAPUMISAIKA);
		 tapahtuma.setPoistumisaika(POISTUMISAIKA);
		 tapahtuma.setAsiakkaanId(ASIAKKAANID);
		 
	}

	@AfterEach
	void tearDown() throws Exception {
		 tapahtuma.setSaapumisaika(0);
		 tapahtuma.setPoistumisaika(0);
		 tapahtuma.setAsiakkaanId(ASIAKKAANID);
	}

	@Test
	final void testPalvelutapahtuma() {
		 Palvelutapahtuma tapahtuma2 = new Palvelutapahtuma(2);
		 assertEquals(2, tapahtuma2.getAsiakkaanId(), "Asiakkaan ID ei täsmää");
		 assertFalse(tapahtuma2.equals(null), "Olion luominen ei onnistunut, olio palauttaa NULL eli on tyhjä.");
		 
	}

	@Test
	final void testGetAsiakkaanId() {
		assertEquals(ASIAKKAANID, tapahtuma.getAsiakkaanId(), "Asiakkaan ID ei täsmää");
	}

	@Test
	final void testSetAsiakkaanId() {
		tapahtuma.setAsiakkaanId(5);
		assertEquals(5, tapahtuma.getAsiakkaanId(), "Asiakkaan ID ei täsmää");
		
	}

	@Test
	final void testGetSaapumisaika() {
		assertEquals(SAAPUMISAIKA, tapahtuma.getSaapumisaika(), "Asiakkaan saapumisaika ei täsmää saapumisaikaan");
	}

	@Test
	final void testSetSaapumisaika() {
		tapahtuma.setSaapumisaika(3.5);
		assertEquals(3.5, tapahtuma.getSaapumisaika(), "Asiakkaan poistumisaika ei täsmää saapumisaikaan");
	}

	@Test
	final void testGetPoistumisaika() {
		assertEquals(POISTUMISAIKA, tapahtuma.getPoistumisaika(), "Asiakkaan haettu poistumisaika ei täsmää poistumisaikaan");
	}

	@Test
	final void testSetPoistumisaika() {
		tapahtuma.setPoistumisaika(4.0);
		assertEquals(4.0, tapahtuma.getPoistumisaika(), "Asiakkaan poistumisaika ei täsmää saapumisaikaan");
	}

	@Test
	final void testAsiakkaanKokonaisoleskeluaika() {
		double oleskelu = tapahtuma.asiakkaanKokonaisoleskeluaika();
		assertEquals(3.0, oleskelu, "Asiakkaan kokonaisoleskeluaika ei täsmää oikeaan oleskeluaikaan");
	}

}
