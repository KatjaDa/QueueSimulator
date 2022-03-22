package simu.model;

import simu.framework.Kello;

public class Palvelutapahtuma {
	
	private int asiakkaanId;
	private double saapumisaika;
	private double poistumisaika;
	
	/**
	 * @param asiakkaanId is id of customer.
	 */
	public Palvelutapahtuma(int asiakkaanId) {
		this.asiakkaanId = asiakkaanId;
		this.saapumisaika = Kello.getInstance().getAika();
		this.poistumisaika = Kello.getInstance().getAika();
		
	}

	/**
	 * @return id of customer.
	 */
	public int getAsiakkaanId() {
		return asiakkaanId;
		 
	}

	/**
	 * @param asiakkaanId is a id of customer.
	 */
	public void setAsiakkaanId(int asiakkaanId) {
		this.asiakkaanId = asiakkaanId;
		
	}

	/**
	 * @return arrival time of a customer for a service point.
	 */
	public double getSaapumisaika() {
		return saapumisaika;
		
	}

	/**
	 * @param saapumisaika for arrival time.
	 */
	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
		
	}

	/**
	 * @return departure of a customer from a service point.
	 */
	public double getPoistumisaika() {
		return poistumisaika;
		
	}

	/**
	 * @param poistumisaika is a departure time of a customer from a service point.
	 */
	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
		
	}
	
	/**
	 * @return the time one customer has spent in a line and in one service point
	 */
	public double asiakkaanKokonaisoleskeluaika() {
		 return this.poistumisaika-this.saapumisaika;
		
	}
	
	
	

}
