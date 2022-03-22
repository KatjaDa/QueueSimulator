package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simu.framework.Kello;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import simu.model.Asiakas;
import simu.model.AsiakasTyyppi;

/**
 * @author Dahlman, Laamo, Lappi
 *
 */
class AsiakasTest {
	

	private static Asiakas asiakasX;
	private static Asiakas asiakasY;
	
	private final double DELTA = 0.001;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Trace.setTraceLevel(Level.INFO);
		Kello.getInstance().getAika();

		asiakasX = new Asiakas(AsiakasTyyppi.X);
		Kello.getInstance().setAika(5);
		asiakasY = new Asiakas(AsiakasTyyppi.Y);
	}

	@BeforeEach
	void setUp() {
		Kello.getInstance().setAika(5);
		Asiakas.setLapimenneetX(0);
		Asiakas.setLapimenneetY(0);
		Asiakas.setSummaX(0);
		Asiakas.setSummaY(0);
		Asiakas.setSummaXY(0);
	}

	@Test
	void testAsiakasKonstruktori() {
		// Test asiakasX
		assertEquals(AsiakasTyyppi.X, asiakasX.getTyyppi(), "Asiakastyyppi X on väärin.");
		assertEquals(1, asiakasX.getId(), "Asiakkaan X id on väärin.");
		assertEquals(0, asiakasX.getSaapumisaika(), "Asiakkaan X saapumisaika on väärin.");
		// Test asiakasY
		assertEquals(AsiakasTyyppi.Y, asiakasY.getTyyppi(), "Asiakastyyppi Y on väärin.");
		assertEquals(2, asiakasY.getId(), "Asiakkaan Y id on väärin.");
		assertEquals(5, asiakasY.getSaapumisaika(), "Asiakkaan Y saapumisaika on väärin.");
	}

	@Test
	void testSetPoistumisaika() {
		Kello.getInstance().setAika(10);
		asiakasX.setPoistumisaika(Kello.getInstance().getAika());
		assertEquals(10, asiakasX.getPoistumisaika(), "Asiakkaan X poistumisaika on väärin.");
	}

	@Test
	void testGetLapimenneet() {
		asiakasX.raportti();
		assertEquals(1, Asiakas.getLapimenneetX(), "Läpimenneet X asiakkaat väärin.");
		assertEquals(0, Asiakas.getLapimenneetY(), "Läpimenneet Y asiakkaat väärin.");
		asiakasY.raportti();
		assertEquals(1, Asiakas.getLapimenneetX(), "Läpimenneet X asiakkaat väärin.");
		assertEquals(1, Asiakas.getLapimenneetY(), "Läpimenneet Y asiakkaat väärin.");
	}

	@Test
	void testGetSummaXY() {
		asiakasX.setPoistumisaika(Kello.getInstance().getAika());
		asiakasX.raportti();
		assertEquals(5, Asiakas.getSummaXY(), "Summa XY on väärin.");
		assertEquals(5, Asiakas.getSummaX(), "Summa X on väärin.");
		assertEquals(0, Asiakas.getSummaY(), "Summa Y on väärin.");
		
		Kello.getInstance().setAika(20);
		asiakasY.setPoistumisaika(Kello.getInstance().getAika());
		asiakasY.raportti();
		assertEquals(20, Asiakas.getSummaXY(), "Summa XY on väärin.");
		assertEquals(5, Asiakas.getSummaX(), "Summa X on väärin.");
		assertEquals(15, Asiakas.getSummaY(), "Summa Y on väärin.");
	}
	
	@Test
	void testGetSaapuneetAsiakkaatYht() {
		assertEquals(2, Asiakas.getSaapuneetAsiakkaatYht(), "Saapuneiden asiakkaiden määrä on väärin.");
	}
	
	@Test
	void testGetXAsiakkaidenLapimenoaika() {
		Asiakas.setSummaX(10);
		Asiakas.setLapimenneetX(3);	
		assertEquals(3.333, Asiakas.getXAsiakkaidenLapimenoaika(), DELTA, "X asiakkaiden läpimenoaika on väärin.");
	}
	
	@Test
	void testGetYAsiakkaidenLapimenoaika() {
		Asiakas.setSummaY(4);
		Asiakas.setLapimenneetY(3);	
		assertEquals(1.333, Asiakas.getYAsiakkaidenLapimenoaika(), DELTA, "Y asiakkaiden läpimenoaika on väärin.");
	}
	
	@Test
	void testGetAsiakkaidenLapimenoaikaYht() {
		Asiakas.setSummaXY(15);
		Asiakas.setLapimenneetX(2);
		Asiakas.setLapimenneetY(2);	
		assertEquals(3.75, Asiakas.getAsiakkaidenLapimenoaikaYht(), DELTA, "Kaikkien asiakkaiden läpimenoaika on väärin.");
	}

}
