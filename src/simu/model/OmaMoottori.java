package simu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controller.IKontrolleriMtoV;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

/**
 * @author Dahlman, Laamo, Lappi
 *
 */
public class OmaMoottori extends Moottori {
	static ITietokantaRaporttiDAO tietokantaraporttiDAO = new TietokantaRaporttiAO();

	private Saapumisprosessi saapumisprosessiX;
	private Saapumisprosessi saapumisprosessiY;
	private ArrayList<Palvelupiste> kaikkiPalvelupisteet;
	private double xParam = 5;
	private double yParam = 5;

	/**
	 * @param kontrolleri is controller object
	 */
	public OmaMoottori(IKontrolleriMtoV kontrolleri) {
		super(kontrolleri);

		palvelupisteet = new Palvelupiste[4];

		palvelupisteet[0] = new Palvelupiste(new Negexp(4, 5), tapahtumalista, TapahtumanTyyppi.PP1DEP,
				"Lipuntarkastus");
		palvelupisteet[1] = new Palvelupiste(new Negexp(5, 5), tapahtumalista, TapahtumanTyyppi.PP2DEP,
				"Turvatarkastus");
		palvelupisteet[2] = new Palvelupiste(new Negexp(5, 5), tapahtumalista, TapahtumanTyyppi.PP3DEP, "Kaljatiski");
		palvelupisteet[3] = new Palvelupiste(new Negexp(13, 5), tapahtumalista, TapahtumanTyyppi.PP4DEP, "Vessa");

		kaikkiPalvelupisteet = new ArrayList<>();
		for (Palvelupiste palvelupiste : palvelupisteet) {
			kaikkiPalvelupisteet.add(palvelupiste);
		}
	}

	/**
	 * Method alustukset() generates first customers to queue.
	 */
	@Override
	protected void alustukset() {
		saapumisprosessiX = new Saapumisprosessi(new Negexp(xParam, 5), tapahtumalista, TapahtumanTyyppi.XARR);
		saapumisprosessiY = new Saapumisprosessi(new Negexp(yParam, 5), tapahtumalista, TapahtumanTyyppi.YARR);

		saapumisprosessiX.generoiSeuraava();
		saapumisprosessiY.generoiSeuraava();
	}

	/**
	 * Method, that executes events (B-type) and moves customers from service point
	 * to another. Shows and visualizes customers and amount of customer types
	 * (x,y).
	 */
	@Override
	protected void suoritaTapahtuma(Tapahtuma t) {
		Asiakas a;
		switch (t.getTyyppi()) {

		case XARR:
			palvelupisteet[0].lisaaJonoon(new Asiakas(AsiakasTyyppi.X));
			saapumisprosessiX.generoiSeuraava();
			kontrolleri.naytaAsiakasMaaraX(Asiakas.getSaapuneetX());
			kontrolleri.naytaAsiakasMaara((Asiakas.getSaapuneetAsiakkaatYht()));
			kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
			break;
		case YARR:
			palvelupisteet[0].lisaaJonoon(new Asiakas(AsiakasTyyppi.Y));
			saapumisprosessiY.generoiSeuraava();
			kontrolleri.naytaAsiakasMaaraY(Asiakas.getSaapuneetY());
			kontrolleri.naytaAsiakasMaara((Asiakas.getSaapuneetAsiakkaatYht()));
			kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
			break;
		case PP1DEP:
			a = palvelupisteet[0].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);

			break;
		case PP2DEP:
			a = palvelupisteet[1].otaJonosta();
			if (a.getTyyppi().equals(AsiakasTyyppi.X)) {
				palvelupisteet[2].lisaaJonoon(a);
				kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
				break;
			}
			palvelupisteet[3].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
			break;
		case PP3DEP:
			a = palvelupisteet[2].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			a.raportti();
			kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
			kontrolleri.naytaPalvellutX(Asiakas.getLapimenneetX());
			kontrolleri.naytaPalvellutY(Asiakas.getLapimenneetY());
			kontrolleri.naytaPalvellut(palvelupisteet[2].getPalvellutAsiakkaat());
			break;
		case PP4DEP:
			a = palvelupisteet[3].otaJonosta();
			palvelupisteet[2].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
			break;
		}
	}

	@Override
	protected void tulokset() {

		double simulaationSuoritusteho = (Asiakas.getLapimenneetX()+Asiakas.getLapimenneetY())/Kello.getInstance().getAika()*100;

		double jononPituusPP1 = palvelupisteet[0].getJononPituus();
		double jononPituusPP2 = palvelupisteet[1].getJononPituus();
		double jononPituusPP3 = palvelupisteet[2].getJononPituus();
		double jononPituusPP4 = palvelupisteet[3].getJononPituus();

		System.out.println("Tulokset OmaMoottori: ");
		System.out.println("OmaMoottori: Saapuneiden X-asiakkaiden määrä: " + Asiakas.getSaapuneetX());
		System.out.println("OmaMoottori: Saapuneiden Y-asiakkaiden määrä: " + Asiakas.getSaapuneetY());
		System.out.println("OmaMoottori: Saapuneiden asiakkaiden määrä yhteensä: " + Asiakas.getSaapuneetAsiakkaatYht());
		System.out.println("OmaMoottori: Systeemin läpi asiakkaita yhteensä: " + palvelupisteet[2].getPalvellutAsiakkaat());
		System.out.println("OmaMoottori: Koko simulaation suoritusteho: " + simulaationSuoritusteho);

		for (Palvelupiste palvelupiste : palvelupisteet) {
			System.out.println("OmaMoottori: Palvelupisteen " + palvelupiste.getPalvelupisteenNimi()
					+ " kokonaisoleskeluaika: " + palvelupiste.getKokonaisOleskeluaika());
			
			System.out.println("OmaMoottori: Palvelupisteen " + palvelupiste.getPalvelupisteenNimi() + " jonon pituus: "
					+ palvelupiste.getJononPituus());
			
			System.out.println("OmaMoottori: Palvelupisteen " + palvelupiste.getPalvelupisteenNimi()
					+ " käyttöaste: " + palvelupiste.getPalvelupisteenKayttoaste());
			System.out.println(palvelupiste);
		}

		System.out.println(
				"OmaMoottori: X-asiakkaiden keskimääräinen läpimenoaika " + Asiakas.getXAsiakkaidenLapimenoaika());
		System.out.println(
				"OmaMoottori: Y-asiakkaiden keskimääräinen läpimenoaika " + Asiakas.getYAsiakkaidenLapimenoaika());
		System.out.println("OmaMoottori: Kaikkien asiakkaiden keskimääräinen läpimenoaika " + Asiakas.getAsiakkaidenLapimenoaikaYht());

		kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());

		System.out.println("OmaMoottori: XParametri: " + xParam + " YParametri: " + yParam);

		TietokantaRaportti raportti = new TietokantaRaportti(Kello.getInstance().getAika(), xParam, yParam,
				simulaationSuoritusteho, Asiakas.getSaapuneetX(), Asiakas.getLapimenneetY(),
				Asiakas.getXAsiakkaidenLapimenoaika(), Asiakas.getYAsiakkaidenLapimenoaika(),
				Asiakas.getAsiakkaidenLapimenoaikaYht(), palvelupisteet[0].getKokonaisOleskeluaika(),
				palvelupisteet[1].getKokonaisOleskeluaika(), palvelupisteet[2].getKokonaisOleskeluaika(),
				palvelupisteet[3].getKokonaisOleskeluaika(), jononPituusPP1, jononPituusPP2, jononPituusPP3,
				jononPituusPP4, Asiakas.getLapimenneetX(), Asiakas.getLapimenneetY(),
				palvelupisteet[0].getPalvelupisteenKayttoaste(), palvelupisteet[1].getPalvelupisteenKayttoaste(),
				palvelupisteet[2].getPalvelupisteenKayttoaste(), palvelupisteet[3].getPalvelupisteenKayttoaste());

		System.out.println("OmaMoottori: " + raportti.toString());
		kontrolleri.naytaLoppuraportti(raportti);
		boolean res = tietokantaraporttiDAO.createRaportti(raportti);
		// System.out.println("OmaMoottori: toteutuiko: " + res);

		TietokantaRaportti[] raportit = tietokantaraporttiDAO.readRaportit();
		for (TietokantaRaportti r : raportit) {
			System.out.println("OmaMoottori: tämä tulee tietokannasta " + r);
		}
	}

	@Override
	public void setXParam(double xParam) {
		this.xParam = xParam;
	}

	@Override
	public void setYParam(double yParam) {
		this.yParam = yParam;
	}

}
