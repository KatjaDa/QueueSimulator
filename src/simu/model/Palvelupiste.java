package simu.model;

import java.util.ArrayList;
import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;

/**
 * @author Dahlman, Laamo, Lappi
 *
 */
public class Palvelupiste {

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>();
	private ArrayList<Palvelutapahtuma> palvelutapahtumat = new ArrayList<Palvelutapahtuma>();
	
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
	
	private boolean varattu = false;
	private int palvellutAsiakkaat;
	private double kokonaisPalveluaika;
	private String palvelupisteenNimi;


	/**
	 * @param generator provides a double value according to the distribution it relies on, this is for generating service times.
	 * @param tapahtumalista is a list that keeps track on all events that should executed and time that they will be executed.
	 * @param tyyppi enum for type of event.
	 * @param palvelupisteenNimi is a name for service point.
	 */
	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, String palvelupisteenNimi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.palvelupisteenNimi = palvelupisteenNimi;
	}


	/**
	 * @param a is customer that will be added to list. First from list will be given a service next.
	 */
	public void lisaaJonoon(Asiakas a){
		jono.add(a);
		palvelutapahtumat.add(new Palvelutapahtuma(a.getId()));
	}

	/**
	 * @return will return and remove first customer from queue (jono) list. Will also set departure time for a customer.
	 */
	public Asiakas otaJonosta(){
		palvellutAsiakkaat++;
		varattu = false;
		Asiakas asiakas = jono.poll();
		for(Palvelutapahtuma palvelutapahtuma : palvelutapahtumat) {
			if(asiakas.getId() == palvelutapahtuma.getAsiakkaanId()) {
				palvelutapahtuma.setPoistumisaika(Kello.getInstance().getAika());
			}
		}
		return asiakas;
		
	}

	/**
	 * @return returns amount of customers that have been already served.
	 */
	public int getPalvellutAsiakkaat() {
		return palvellutAsiakkaat;
	}


	/**
	 * method aloitaPalvelu() starts a new service. Customer stays in queue while been served.
	 */
	public void aloitaPalvelu(){
			Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
			varattu = true;
			double palveluaika = generator.sample(); // B busy time
			tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
			kokonaisPalveluaika+=palveluaika; // B busy time

	}

	
	/**
	 * @return the time how long entire service has lasted.
	 */
	public double getKokonaispalveluAika() {
		return kokonaisPalveluaika;
		
	}


	/**
	 * @return the status of service point - busy or available for next customer.
	 */
	public boolean onVarattu(){
		return varattu;
	}
	
	/**
	 * @return time  which all customers have spent in service point.
	 */
	public double getKokonaisOleskeluaika() {
		double kokonaisOleskeluaika = 0;
		for(Palvelutapahtuma palvelutapahtuma : palvelutapahtumat) {
			kokonaisOleskeluaika+=palvelutapahtuma.asiakkaanKokonaisoleskeluaika();
		}
		return kokonaisOleskeluaika;
	}

	public String getPalvelupisteenNimi() {
		return palvelupisteenNimi;
	}

	/**
	 * @return the status of queue, if there is still customers in line or not.
	 */
	public boolean onJonossa(){
		return jono.size() != 0;
	}

	/**
	 * @return the queue list of customers. 
	 */
	public LinkedList<Asiakas> getJono() {
		return jono;
	}
	
	/**
	 * @return utilization % for service point.
	 */
	public double getPalvelupisteenKayttoaste() {
		double kayttoaste = 0;
		try {
			kayttoaste = kokonaisPalveluaika/Kello.getInstance().getAika() *100;
			
		} catch (Exception e) {
			System.out.println("Käyttöasteen laskenta epäonnistui, syy "+e);
		}
		return kayttoaste;
	}
	
	/**
	 * @return time customers use up to queue.
	 */
	public double getJononPituus() {
		
		double jononPituus = 0;
		try {
			jononPituus = getKokonaisOleskeluaika()/Kello.getInstance().getAika();
			
		} catch (Exception e) {
			System.out.println("Jononpituuden laskenta epäonnistui, syy "+e);
		}
		return jononPituus;
		
	}

	@Override
	public String toString() {
		return "Palvelupisteen nimi: " + palvelupisteenNimi+", palvelupisteen palvellut asiakkaat: " + palvellutAsiakkaat + ", kokonaispalveluaika: " + kokonaisPalveluaika+".";
	}

}
