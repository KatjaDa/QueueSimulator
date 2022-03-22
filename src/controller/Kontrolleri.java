package controller;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.stage.Stage;
import simu.framework.IMoottori;
import simu.model.Asiakas;
import simu.model.OmaMoottori;
import simu.model.Palvelupiste;
import simu.model.TietokantaRaportti;
import view.ILoppuraporttiUI;
import view.ISimulaattorinUI;
import view.LoppuraporttiGUI;

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV { // UUSI

	private IMoottori moottori;
	private ISimulaattorinUI ui;
	private LoppuraporttiGUI loppuraporttiGUI;

	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
		this.loppuraporttiGUI = new LoppuraporttiGUI();
	}

	// Moottorin ohjausta:

	@Override
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		moottori.setSimulointiaika(ui.getAika());
		moottori.setViive(ui.getViive());
		moottori.setXParam(ui.getXParam());
		moottori.setYParam(ui.getYParam());
		ui.getPP1Visualisointi().tyhjennaNaytto();
		((Thread) moottori).start();
		// ((Thread)moottori).run(); // Ei missään tapauksessa näin. Miksi?
	}

	@Override
	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long) (moottori.getViive() * 1.10));
	}

	@Override
	public void nopeuta() { // nopeutetaan moottorisäiettä
		moottori.setViive((long) (moottori.getViive() * 0.9));
	}

	// Simulointitulosten välittämistä käyttöliittymään.
	// Koska FX-ui:n päivitykset tulevat moottorisäikeestä, ne pitää ohjata
	// JavaFX-säikeeseen:

	@Override
	public void naytaLoppuaika(double aika) {
		Platform.runLater(() -> ui.setLoppuaika(aika));
	}

	@Override
	public void visualisoiAsiakas(ArrayList<Palvelupiste> palvelupisteet) {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.setProgressBarAika();
				ui.getPP1Visualisointi().tyhjennaNaytto();
				ui.getPP2Visualisointi().tyhjennaNaytto();
				ui.getPP3Visualisointi().tyhjennaNaytto();
				ui.getPP4Visualisointi().tyhjennaNaytto();

				for (Asiakas asiakas : palvelupisteet.get(0).getJono()) {
					ui.getPP1Visualisointi().uusiAsiakas(asiakas.getTyyppi());
				}

				for (Asiakas asiakas : palvelupisteet.get(1).getJono()) {
					ui.getPP2Visualisointi().uusiAsiakas(asiakas.getTyyppi());
				}

				for (Asiakas asiakas : palvelupisteet.get(2).getJono()) {
					ui.getPP3Visualisointi().uusiAsiakas(asiakas.getTyyppi());
				}

				for (Asiakas asiakas : palvelupisteet.get(3).getJono()) {
					ui.getPP4Visualisointi().uusiAsiakas(asiakas.getTyyppi());
				}
			}
		});
	}

	@Override
	public void naytaAsiakasMaaraX(int asiakasMaaraX) {
		Platform.runLater(() -> ui.setAsiakasMaaraX(asiakasMaaraX));
	}

	@Override
	public void naytaAsiakasMaaraY(int asiakasMaaraY) {
		Platform.runLater(() -> ui.setAsiakasMaaraY(asiakasMaaraY));
	}

	@Override
	public void naytaAsiakasMaara(int asiakasMaara) {
		Platform.runLater(() -> ui.setAsiakasMaaraYht(asiakasMaara));
	}

	// Lisätäänkö metodin toteutus OmaMoottoriin
	@Override
	public void naytaPalvellutX(int asiakasMaaraX) {
		Platform.runLater(() -> ui.setPalvellutX(asiakasMaaraX));
	}

	// Lisätäänkö metodin toteutus OmaMoottoriin
	@Override
	public void naytaPalvellutY(int asiakasMaaraY) {
		Platform.runLater(() -> ui.setPalvellutY(asiakasMaaraY));
	}

	@Override
	public void naytaPalvellut(int asiakasMaara) {
		Platform.runLater(() -> ui.setPalvellut(asiakasMaara));
	}

	@Override
	public void naytaLoppuraportti(TietokantaRaportti raportti) {
		Platform.runLater(new Runnable() {
			public void run() {
				// Avataan loppuraportti ikkuna
				try {
					Thread.sleep(1000);
					Stage loppuraporttiStage = new Stage();
					loppuraporttiGUI.start(loppuraporttiStage);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Asetetaan loppuraportti näkymään simulaation tiedon TietokantaRaportti olion avulla
				Platform.runLater(() -> loppuraporttiGUI.setSimulaationKokonaisaika(raportti.getSimulaationKokonaisaika()));			
				Platform.runLater(() -> loppuraporttiGUI.setxParam(raportti.getXParam()));
				Platform.runLater(() -> loppuraporttiGUI.setyParam(raportti.getYParam()));
				Platform.runLater(() -> loppuraporttiGUI.setSimulaationSuoritusteho(raportti.getSimulaationSuoritusteho()));
				Platform.runLater(() -> loppuraporttiGUI.setAsiakasMaaraX(raportti.getAsiakasMaaraX()));
				Platform.runLater(() -> loppuraporttiGUI.setAsiakasMaaraY(raportti.getAsiakasMaaraY()));
				Platform.runLater(() -> loppuraporttiGUI.setAsiakasMaaraYht(raportti.getAsiakasMaaraX() + raportti.getAsiakasMaaraY()));
				Platform.runLater(() -> loppuraporttiGUI.setxAsiakkaidenLapimenoaika(raportti.getXAsiakkaidenLapimenoaika()));
				Platform.runLater(() -> loppuraporttiGUI.setyAsiakkaidenLapimenoaika(raportti.getYAsiakkaidenLapimenoaika()));
				Platform.runLater(() -> loppuraporttiGUI.setAsiakkaidenLapimenoaikaYht(raportti.getAsiakkaidenLapimenoaikaYht()));
				Platform.runLater(() -> loppuraporttiGUI.setLapimenneetX(raportti.getLapimenneetX()));
				Platform.runLater(() -> loppuraporttiGUI.setLapimenneetY(raportti.getLapimenneetY()));	
				Platform.runLater(() -> loppuraporttiGUI.setLapimenneetYht(raportti.getLapimenneetX() + raportti.getLapimenneetY()));
				Platform.runLater(() -> loppuraporttiGUI.setOleskeluaikaPP1(raportti.getOleskeluaikaPP1()));	
				Platform.runLater(() -> loppuraporttiGUI.setOleskeluaikaPP2(raportti.getOleskeluaikaPP2()));	
				Platform.runLater(() -> loppuraporttiGUI.setOleskeluaikaPP3(raportti.getOleskeluaikaPP3()));	
				Platform.runLater(() -> loppuraporttiGUI.setOleskeluaikaPP4(raportti.getOleskeluaikaPP4()));	
				Platform.runLater(() -> loppuraporttiGUI.setJononPituusPP1(raportti.getJononPituusPP1()));	
				Platform.runLater(() -> loppuraporttiGUI.setJononPituusPP2(raportti.getJononPituusPP2()));
				Platform.runLater(() -> loppuraporttiGUI.setJononPituusPP3(raportti.getJononPituusPP3()));
				Platform.runLater(() -> loppuraporttiGUI.setJononPituusPP4(raportti.getJononPituusPP4()));			
				Platform.runLater(() -> loppuraporttiGUI.setKayttoastePP1(raportti.getKayttoastePP1()));
				Platform.runLater(() -> loppuraporttiGUI.setKayttoastePP2(raportti.getKayttoastePP2()));
				Platform.runLater(() -> loppuraporttiGUI.setKayttoastePP3(raportti.getKayttoastePP3()));
				Platform.runLater(() -> loppuraporttiGUI.setKayttoastePP4(raportti.getKayttoastePP4()));
			}

		});
	}

}
