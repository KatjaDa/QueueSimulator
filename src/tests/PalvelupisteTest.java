package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eduni.distributions.Normal;
import simu.framework.*;
import simu.framework.Trace.Level;
import simu.model.*;

/**
 * @author Dahlman, Laamo, Lappi
 *
 */
class PalvelupisteTest {
	
	
	Tapahtumalista tapahtumalista;

	LinkedList<Asiakas> jono = new LinkedList<Asiakas>();
	Palvelupiste servicepoint = new Palvelupiste(new Normal(10, 6), tapahtumalista, TapahtumanTyyppi.PP1DEP, "CHECKPOINT");
	
	private static Asiakas asiakas;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Trace.setTraceLevel(Level.INFO);
		Kello.getInstance().getAika();
		asiakas = new Asiakas(AsiakasTyyppi.X);
		Kello.getInstance().setAika(5);
	}


	@BeforeEach
	void setUp() throws Exception {
		jono.add(asiakas);
	}
	
    @AfterEach
    public void tearDown() {
    	jono.clear();
    }

	@Test
	public void testPalvelupiste() {
		assertFalse(servicepoint.equals(null), "Olion luominen ei onnistunut, olio palauttaa NULL eli on tyhjä.");
	}


	@Test
	public void testLisaaJonoon() {
		System.out.println(jono.getFirst());
		assertEquals(asiakas, jono.getFirst(),"Listalle lisätty olio ei vastaa listalla olevaa oliota");
	}

	@Test
	final void testOtaJonosta() {
		assertEquals(asiakas, jono.peek(),"Listalle lisätty olio ei vastaa listalla olevaa oliota");
	}
	
	@Test
	public void testOnJonossa() {
		assertEquals(1, jono.size(), "Listan koko on eri kuin listalle lisättyjen olioiden määrä");
	}
	
	@Test
	public void testGetPalvelupisteenKayttoaste() {
		assertEquals(0, servicepoint.getPalvelupisteenKayttoaste()," Käyttöaste ei ole oikein ");
	}

}
