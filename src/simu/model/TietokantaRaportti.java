package simu.model;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "Loppuraportti")
public class TietokantaRaportti {

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;

	@Column(name = "Paivamaara")
	LocalDateTime paivamaara;

	@Column(name = "Kokonaisaika")
	double simulaationKokonaisaika;

	@Column(name = "XasiakkaidenParametri")
	double xParam;

	@Column(name = "YasiakkaidenParametri")
	double yParam;

	@Column(name = "SimulaationSuoritusteho")
	double simulaationSuoritusteho;

	@Column(name = "XSaapuneet")
	double asiakasMaaraX;

	@Column(name = "YSaapuneet")
	double asiakasMaaraY;

	@Column(name = "XAsiakkaidenLapimenoaika")
	double xAsiakkaidenLapimenoaika;

	@Column(name = "YAsiakkaidenLapimenoaika")
	double yAsiakkaidenLapimenoaika;

	@Column(name = "AsiakkaidenLapimenoaikaYht")
	double asiakkaidenLapimenoaikaYht;

	@Column(name = "OleskeluaikaPP1")
	double oleskeluaikaPP1;

	@Column(name = "OleskeluaikaPP2")
	double oleskeluaikaPP2;

	@Column(name = "OleskeluaikaPP3")
	double oleskeluaikaPP3;

	@Column(name = "OleskeluaikaPP4")
	double oleskeluaikaPP4;

	@Column(name = "JononPituusPP1")
	double jononPituusPP1;

	@Column(name = "JononPituusPP2")
	double jononPituusPP2;

	@Column(name = "JononPituusPP3")
	double jononPituusPP3;

	@Column(name = "JononPituusPP4")
	double jononPituusPP4;

	@Column(name = "LapimenneetX")
	int lapimenneetX;

	@Column(name = "LapimenneetY")
	int lapimenneetY;

	@Column(name = "KayttoastePP1")
	double kayttoastePP1;

	@Column(name = "KayttoastePP2")
	double kayttoastePP2;

	@Column(name = "KayttoastePP3")
	double kayttoastePP3;

	@Column(name = "KayttoastePP4")
	double kayttoastePP4;

	@Transient
	DecimalFormat formatter = new DecimalFormat("#0.00");

	public TietokantaRaportti() {
	}

	/**
	 * @param simulaationKokonaisaika 
	 * @param xParam parameter for X-type customers
	 * @param yParam parameter for Y-type customers
	 * @param simulaationSuoritusteho is throughput
	 * @param asiakasMaaraX amount of X-type customer into simulation
	 * @param asiakasMaaraY amount of Y-type customer into simulation
	 * @param xAsiakkaidenLapimenoaika lead time for customer type X
	 * @param yAsiakkaidenLapimenoaika lead time for customer type Y
	 * @param asiakkaidenLapimenoaikaYht lead time for all customer type
	 * @param oleskeluaikaPP1 service point 1 time that customers has spent
	 * @param oleskeluaikaPP2 service point 2 time that customers has spent
	 * @param oleskeluaikaPP3 service point 3 time that customers has spent
	 * @param oleskeluaikaPP4 service point 4 time that customers has spent
	 * @param jononPituusPP1 size of line on service point 1
	 * @param jononPituusPP2 size of line on service point 2
	 * @param jononPituusPP3 size of line on service point 3
	 * @param jononPituusPP4 size of line on service point 4
	 * @param lapimenneetX amount of X-type customers that have gone through system
	 * @param lapimenneetY amount of Y-type customers that have gone through system
	 * @param kayttoastePP1 utilization % for service point 1
	 * @param kayttoastePP2 utilization % for service point 1
	 * @param kayttoastePP3 utilization % for service point 1
	 * @param kayttoastePP4 utilization % for service point 1
	 */
	public TietokantaRaportti(double simulaationKokonaisaika, double xParam, double yParam,
			double simulaationSuoritusteho, double asiakasMaaraX, double asiakasMaaraY, double xAsiakkaidenLapimenoaika,
			double yAsiakkaidenLapimenoaika, double asiakkaidenLapimenoaikaYht, double oleskeluaikaPP1,
			double oleskeluaikaPP2, double oleskeluaikaPP3, double oleskeluaikaPP4, double jononPituusPP1,
			double jononPituusPP2, double jononPituusPP3, double jononPituusPP4, int lapimenneetX, int lapimenneetY,
			double kayttoastePP1, double kayttoastePP2, double kayttoastePP3, double kayttoastePP4) {
		super();
		this.paivamaara = java.time.LocalDateTime.now();
		this.simulaationKokonaisaika = simulaationKokonaisaika;
		this.xParam = xParam;
		this.yParam = yParam;
		this.simulaationSuoritusteho = simulaationSuoritusteho;
		this.asiakasMaaraX = asiakasMaaraX;
		this.asiakasMaaraY = asiakasMaaraY;
		this.xAsiakkaidenLapimenoaika = xAsiakkaidenLapimenoaika;
		this.yAsiakkaidenLapimenoaika = yAsiakkaidenLapimenoaika;
		this.asiakkaidenLapimenoaikaYht = asiakkaidenLapimenoaikaYht;
		this.oleskeluaikaPP1 = oleskeluaikaPP1;
		this.oleskeluaikaPP2 = oleskeluaikaPP2;
		this.oleskeluaikaPP3 = oleskeluaikaPP3;
		this.oleskeluaikaPP4 = oleskeluaikaPP4;
		this.jononPituusPP1 = jononPituusPP1;
		this.jononPituusPP2 = jononPituusPP2;
		this.jononPituusPP3 = jononPituusPP3;
		this.jononPituusPP4 = jononPituusPP4;
		this.lapimenneetX = lapimenneetX;
		this.lapimenneetY = lapimenneetY;
		this.kayttoastePP1 = kayttoastePP1;
		this.kayttoastePP2 = kayttoastePP2;
		this.kayttoastePP3 = kayttoastePP3;
		this.kayttoastePP4 = kayttoastePP4;
		
	}

	public LocalDateTime getPaivamaara() {
		return paivamaara;
	}

	public void setPaivamaara(LocalDateTime paivamaara) {
		this.paivamaara = paivamaara;
	}

	public double getSimulaationKokonaisaika() {
		return pyoristaLuku(this.simulaationKokonaisaika);
	}

	public void setSimulaationKokonaisaika(double simulaationKokonaisaika) {
		this.simulaationKokonaisaika = simulaationKokonaisaika;
	}

	public double getXParam() {
		return xParam;
	}

	public void setXParam(double xParam) {
		this.xParam = xParam;
	}

	public double getYParam() {
		return yParam;
	}

	public void setYParam(double yParam) {
		this.yParam = yParam;
	}

	public double getSimulaationSuoritusteho() {
		return pyoristaLuku(this.simulaationSuoritusteho);
	}

	public void setSimulaationSuoritusteho(double simulaationSuoritusteho) {
		this.simulaationSuoritusteho = simulaationSuoritusteho;
	}

	public double getAsiakasMaaraX() {
		return asiakasMaaraX;
	}

	public void setAsiakasMaaraX(double asiakasMaaraX) {
		this.asiakasMaaraX = asiakasMaaraX;
	}

	public double getAsiakasMaaraY() {
		return asiakasMaaraY;
	}

	public void setAsiakasMaaraY(double asiakasMaaraY) {
		this.asiakasMaaraY = asiakasMaaraY;
	}

	public double getXAsiakkaidenLapimenoaika() {
		return pyoristaLuku(this.xAsiakkaidenLapimenoaika);
	}

	public void setXAsiakkaidenLapimenoaika(double xAsiakkaidenLapimenoaika) {
		this.xAsiakkaidenLapimenoaika = xAsiakkaidenLapimenoaika;
	}

	public double getYAsiakkaidenLapimenoaika() {
		return pyoristaLuku(this.yAsiakkaidenLapimenoaika);
	}

	public void setYAsiakkaidenLapimenoaika(double yAsiakkaidenLapimenoaika) {
		this.yAsiakkaidenLapimenoaika = yAsiakkaidenLapimenoaika;
	}

	public double getAsiakkaidenLapimenoaikaYht() {
		return pyoristaLuku(this.asiakkaidenLapimenoaikaYht);
	}

	public void setAsiakkaidenLapimenoaikaYht(double asiakkaidenLapimenoaikaYht) {
		this.asiakkaidenLapimenoaikaYht = asiakkaidenLapimenoaikaYht;
	}

	public double getOleskeluaikaPP1() {
		return pyoristaLuku(this.oleskeluaikaPP1);
	}

	public void setOleskeluaikaPP1(double oleskeluaikaPP1) {
		this.oleskeluaikaPP1 = oleskeluaikaPP1;
	}

	public double getOleskeluaikaPP2() {
		return pyoristaLuku(this.oleskeluaikaPP2);
	}

	public void setOleskeluaikaPP2(double oleskeluaikaPP2) {
		this.oleskeluaikaPP2 = oleskeluaikaPP2;
	}

	public double getOleskeluaikaPP3() {
		return pyoristaLuku(this.oleskeluaikaPP3);
	}

	public void setOleskeluaikaPP3(double oleskeluaikaPP3) {
		this.oleskeluaikaPP3 = oleskeluaikaPP3;
	}

	public double getOleskeluaikaPP4() {
		return pyoristaLuku(this.oleskeluaikaPP4);
	}

	public void setOleskeluaikaPP4(double oleskeluaikaPP4) {
		this.oleskeluaikaPP4 = oleskeluaikaPP4;
	}

	public double getJononPituusPP1() {
		return pyoristaLuku(this.jononPituusPP1);
	}

	public void setJononPituusPP1(double jononPituusPP1) {
		this.jononPituusPP1 = jononPituusPP1;
	}

	public double getJononPituusPP2() {
		return pyoristaLuku(this.jononPituusPP2);
	}

	public void setJononPituusPP2(double jononPituusPP2) {
		this.jononPituusPP2 = jononPituusPP2;
	}

	public double getJononPituusPP3() {
		return pyoristaLuku(this.jononPituusPP3);
	}

	public void setJononPituusPP3(double jononPituusPP3) {
		this.jononPituusPP3 = jononPituusPP3;
	}

	public double getJononPituusPP4() {
		return pyoristaLuku(this.jononPituusPP4);
	}

	public void setJononPituusPP4(double jononPituusPP4) {
		this.jononPituusPP4 = jononPituusPP4;
	}

	public int getLapimenneetX() {
		return lapimenneetX;
	}

	public void setLapimenneetX(int lapimenneetX) {
		this.lapimenneetX = lapimenneetX;
	}

	public int getLapimenneetY() {
		return lapimenneetY;
	}

	public void setLapimenneetY(int lapimenneetY) {
		this.lapimenneetY = lapimenneetY;
	}

	public double getKayttoastePP1() {
		return pyoristaLuku(this.kayttoastePP1);
	}

	public void setKayttoastePP1(double kayttoastePP1) {
		this.kayttoastePP1 = kayttoastePP1;
	}

	public double getKayttoastePP2() {
		return pyoristaLuku(this.kayttoastePP2);
	}

	public void setKayttoastePP2(double kayttoastePP2) {
		this.kayttoastePP2 = kayttoastePP2;
	}

	public double getKayttoastePP3() {
		return pyoristaLuku(this.kayttoastePP3);
	}

	public void setKayttoastePP3(double kayttoastePP3) {
		this.kayttoastePP3 = kayttoastePP3;
	}

	public double getKayttoastePP4() {
		return pyoristaLuku(this.kayttoastePP4);
	}

	public void setKayttoastePP4(double kayttoastePP4) {
		this.kayttoastePP4 = kayttoastePP4;
	}

	private double pyoristaLuku(double luku) {
		double arvo = luku * 100;
		arvo = Math.round(arvo);
		arvo = arvo / 100;
		return arvo;
	}

	@Override
	public String toString() {
		return "TietokantaRaportti [paivamaara=" + paivamaara + ", simulaationKokonaisaika=" + simulaationKokonaisaika
				+ ", xParam=" + xParam + ", yParam=" + yParam + ", simulaationSuoritusteho=" + simulaationSuoritusteho
				+ ", asiakasMaaraX=" + asiakasMaaraX + ", asiakasMaaraY=" + asiakasMaaraY
				+ ", xAsiakkaidenLapimenoaika=" + xAsiakkaidenLapimenoaika + ", yAsiakkaidenLapimenoaika="
				+ yAsiakkaidenLapimenoaika + ", asiakkaidenLapimenoaikaYht=" + asiakkaidenLapimenoaikaYht
				+ ", oleskeluaikaPP1=" + oleskeluaikaPP1 + ", oleskeluaikaPP2=" + oleskeluaikaPP2 + ", oleskeluaikaPP3="
				+ oleskeluaikaPP3 + ", oleskeluaikaPP4=" + oleskeluaikaPP4 + ", jononPituusPP1=" + jononPituusPP1
				+ ", jononPituusPP2=" + jononPituusPP2 + ", jononPituusPP3=" + jononPituusPP3 + ", jononPituusPP4="
				+ jononPituusPP4 + ", lapimenneetX=" + lapimenneetX + ", lapimenneetY=" + lapimenneetY
				+ ", kayttoastePP1=" + kayttoastePP1 + ", kayttoastePP2=" + kayttoastePP2 + ", kayttoastePP3="
				+ kayttoastePP3 + ", kayttoastePP4=" + kayttoastePP4 + "]";
	}
}
