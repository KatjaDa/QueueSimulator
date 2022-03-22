package view;

import java.text.DecimalFormat;

import controller.IKontrolleriVtoM;
import controller.Kontrolleri;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.framework.Trace;
import simu.framework.Trace.Level;

/**
 * @author Dahlman, Laamo, Lappi
 *
 */
public class LoppuraporttiGUI extends Application implements ILoppuraporttiUI {
	
	private Label simuLoppuraporttiLabel;
	
	private Label simuKokonaisaikaLabel;
	private Label simuKokonaisaikaTulos;
	
	private Label xAsiakkaidenParamLabel;
	private Label xAsiakkaidenParamTulos;

	private Label yAsiakkaidenParamLabel;
	private Label yAsiakkaidenParamTulos;
	
	private Label simuSuoritustehoLabel;
	private Label simuSuoritustehoTulos;
	
	private Label xSaapuneetLabel;
	private Label xSaapuneetTulos;

	private Label ySaapuneetLabel;
	private Label ySaapuneetTulos;

	private Label yhtSaapuneetLabel;
	private Label yhtSaapuneetTulos;

	private Label xLapiSysteeminLabel;
	private Label xLapiSysteeminTulos;

	private Label yLapiSysteeminLabel;
	private Label yLapiSysteeminTulos;

	private Label yhtLapiSysteeminLabel;
	private Label yhtLapiSysteeminTulos;
	
	private Label xAsiakkaidenLapimenoaikaLabel;
	private Label xAsiakkaidenLapimenoaikaTulos;
	
	private Label yAsiakkaidenLapimenoaikaLabel;
	private Label yAsiakkaidenLapimenoaikaTulos;
	
	private Label asiakkaidenLapimenoaikaYhtLabel;
	private Label asiakkaidenLapimenoaikaYhtTulos;
	
	private Label kayttoastePP1Label;
	private Label kayttoastePP1Tulos;
	
	private Label kayttoastePP2Label;
	private Label kayttoastePP2Tulos;
	
	private Label kayttoastePP3Label;
	private Label kayttoastePP3Tulos;
	
	private Label kayttoastePP4Label;
	private Label kayttoastePP4Tulos;
	
	private Label oleskeluaikaPP1Label;
	private Label oleskeluaikaPP1Tulos;
	
	private Label oleskeluaikaPP2Label;
	private Label oleskeluaikaPP2Tulos;
	
	private Label oleskeluaikaPP3Label;
	private Label oleskeluaikaPP3Tulos;
	
	private Label oleskeluaikaPP4Label;
	private Label oleskeluaikaPP4Tulos;
	
	private Label jononPituusPP1Label;
	private Label jononPituusPP1Tulos;
	
	private Label jononPituusPP2Label;
	private Label jononPituusPP2Tulos;
	
	private Label jononPituusPP3Label;
	private Label jononPituusPP3Tulos;
	
	private Label jononPituusPP4Label;
	private Label jononPituusPP4Tulos;
	
	private Button tarkasteleRaporttejaButton;
	
	private TietokantaRaporttiGUI tietokantaraporttiGUI;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
	
		try {

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					Platform.exit();
					System.exit(0);
				}
			});

			primaryStage.setTitle("Simulaation loppuraportti");
			primaryStage.getIcons().add(new Image(LoppuraporttiGUI.class.getResourceAsStream("graphics/gui_ikoni.png")));

			BorderPane border = new BorderPane();
			border.setStyle("-fx-background-color: #363537;");

			Scene scene = new Scene(border, 850, 780);
			scene.getStylesheets().add("view/loppuraporttiStyle.css");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setVgap(10);
			grid.setHgap(5);

			border.setCenter(grid);
			
			simuLoppuraporttiLabel = new Label("Simulaation loppuraportti");
			simuLoppuraporttiLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
			
			simuKokonaisaikaLabel = new Label("Simulaation kokonaisaika:");
			simuKokonaisaikaLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			simuKokonaisaikaLabel.setTooltip(new Tooltip("Simulaation ajamiseen käytetty aika."));
			simuKokonaisaikaTulos = new Label();
			simuKokonaisaikaTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			simuKokonaisaikaTulos.setMaxWidth(100);
			
			xAsiakkaidenParamLabel = new Label("Ei WC:ssä käyvien asiakkaiden parametri:");
			xAsiakkaidenParamLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			xAsiakkaidenParamLabel.setTooltip(new Tooltip("Parametri sille, kuinka usein ei WC:n kautta kulkevia asiakkaita saapuu. \n Pieni luku: asiakkaita saapuu usein, eli suuri määrä. \n Suuri luku: asiakkaita saapuu harvoin, eli pieni määrä."));
			xAsiakkaidenParamTulos = new Label();
			xAsiakkaidenParamTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			xAsiakkaidenParamTulos.setMaxWidth(100);
			
			yAsiakkaidenParamLabel = new Label("WC:ssä käyvien asiakkaiden parametri:");
			yAsiakkaidenParamLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			yAsiakkaidenParamLabel.setTooltip(new Tooltip("Parametri sille, kuinka usein WC:ssä käyviä asiakkaita saapuu. \n Pieni luku: asiakkaita saapuu usein, eli suuri määrä. \n Suuri luku: asiakkaita saapuu harvoin, eli pieni määrä."));
			yAsiakkaidenParamTulos = new Label();
			yAsiakkaidenParamTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			yAsiakkaidenParamTulos.setMaxWidth(100);
			
			simuSuoritustehoLabel = new Label("Simulaation suoritusteho:");
			simuSuoritustehoLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			simuSuoritustehoLabel.setTooltip(new Tooltip("Kuinka monta asiakasta simulaation aikana palvellaan simulaation kokonaisaikaa kohti."));
			simuSuoritustehoTulos = new Label();
			simuSuoritustehoTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			simuSuoritustehoTulos.setMaxWidth(100);
			
			xSaapuneetLabel = new Label("Saapuneet ei WC:ssä käyvät asiakkaat:");
			xSaapuneetLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			xSaapuneetLabel.setTooltip(new Tooltip("Kuinka monta asiakasta, jotka eivät käy WC:ssä, on saapunut simulaatioon."));
			xSaapuneetTulos = new Label();
			xSaapuneetTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			xSaapuneetTulos.setMaxWidth(100);
			
			ySaapuneetLabel = new Label("Saapuneet WC:ssä käyvät asiakkaat:");
			ySaapuneetLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			ySaapuneetLabel.setTooltip(new Tooltip("Kuinka monta WC:ssä käyvää asiakasta on saapunut simulaatioon."));
			ySaapuneetTulos = new Label();
			ySaapuneetTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			ySaapuneetTulos.setMaxWidth(100);
			
			yhtSaapuneetLabel = new Label("Saapuneet asiakkaat yhteensä:");
			yhtSaapuneetLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			yhtSaapuneetLabel.setTooltip(new Tooltip("Kuinka monta asiakasta yhteensä on saapunut simulaatioon."));
			yhtSaapuneetTulos = new Label();
			yhtSaapuneetTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			yhtSaapuneetTulos.setMaxWidth(100);
			
			xLapiSysteeminLabel = new Label("Systeemin läpi ei WC:ssä käyneitä asiakkaita:");
			xLapiSysteeminLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			xLapiSysteeminLabel.setTooltip(new Tooltip("Kuinka monta asiakasta, jotka käyvät WC:ssä, on palveltu palvelupisteillä ja he ovat poistuneet keikalle."));
			xLapiSysteeminTulos = new Label();
			xLapiSysteeminTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			xLapiSysteeminTulos.setMaxWidth(100);

			yLapiSysteeminLabel = new Label("Systeemin läpi WC:ssä käyneitä asiakkaita:");
			yLapiSysteeminLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			yLapiSysteeminLabel.setTooltip(new Tooltip("Kuinka monta WC:ssä käyvää asiakasta on palveltu palvelupisteillä ja he ovat poistuneet keikalle."));
			yLapiSysteeminTulos = new Label();
			yLapiSysteeminTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			yLapiSysteeminTulos.setMaxWidth(100);

			yhtLapiSysteeminLabel = new Label("Systeemin läpi asiakkaita yhteensä:");
			yhtLapiSysteeminLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			yhtLapiSysteeminLabel.setTooltip(new Tooltip("Kuinka monta asiakasta yhteensä on palveltu palvelupisteillä ja he ovat poistuneet keikalle."));
			yhtLapiSysteeminTulos = new Label();
			yhtLapiSysteeminTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			yhtLapiSysteeminTulos.setMaxWidth(100);
			
			xAsiakkaidenLapimenoaikaLabel = new Label("Ei WC:ssä käyneiden asiakkaiden keskim. läpimenoaika:");
			xAsiakkaidenLapimenoaikaLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			xAsiakkaidenLapimenoaikaLabel.setTooltip(new Tooltip("Kokonaisoleskeluaika jaettuna ei WC:ssä käyneiden asiakkaiden määrällä."));
			xAsiakkaidenLapimenoaikaTulos = new Label();
			xAsiakkaidenLapimenoaikaTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			xAsiakkaidenLapimenoaikaTulos.setMaxWidth(100);

			yAsiakkaidenLapimenoaikaLabel = new Label("WC:ssä käyneiden asiakkaiden keskim. läpimenoaika:");
			yAsiakkaidenLapimenoaikaLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			yAsiakkaidenLapimenoaikaLabel.setTooltip(new Tooltip("Kokonaisoleskeluaika jaettuna WC:ssä käyneiden asiakkaiden määrällä."));
			yAsiakkaidenLapimenoaikaTulos = new Label();
			yAsiakkaidenLapimenoaikaTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			yAsiakkaidenLapimenoaikaTulos.setMaxWidth(100);

			asiakkaidenLapimenoaikaYhtLabel = new Label("Kaikkien asiakkaiden keskim. läpimenoaika:");
			asiakkaidenLapimenoaikaYhtLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			asiakkaidenLapimenoaikaYhtLabel.setTooltip(new Tooltip("Kokonaisoleskeluaika jaettuna kaikkien asiakkaiden määrällä."));
			asiakkaidenLapimenoaikaYhtTulos = new Label();
			asiakkaidenLapimenoaikaYhtTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			asiakkaidenLapimenoaikaYhtTulos.setMaxWidth(100);
			
			kayttoastePP1Label = new Label("Käyttöaste PP1 (Lipuntarkastus):");
			kayttoastePP1Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			kayttoastePP1Label.setTooltip(new Tooltip("Kuinka suurella prosenttiosuudella Palvelupiste 1 (Lipuntarkastus) on ollut käytössä simuloinnin kokonaisajasta."));
			kayttoastePP1Tulos = new Label();
			kayttoastePP1Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			kayttoastePP1Tulos.setMaxWidth(100);
			
			kayttoastePP2Label = new Label("Käyttöaste PP2 (Turvatarkastus):");
			kayttoastePP2Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			kayttoastePP2Label.setTooltip(new Tooltip("Kuinka suurella prosenttiosuudella Palvelupiste 2 (Turvatarkastus) on ollut käytössä simuloinnin kokonaisajasta."));
			kayttoastePP2Tulos = new Label();
			kayttoastePP2Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			kayttoastePP2Tulos.setMaxWidth(100);
			
			kayttoastePP3Label = new Label("Käyttöaste PP3 (Baaritiski):");
			kayttoastePP3Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			kayttoastePP3Label.setTooltip(new Tooltip("Kuinka suurella prosenttiosuudella Palvelupiste 3 (Baaritiski) on ollut käytössä simuloinnin kokonaisajasta."));
			kayttoastePP3Tulos = new Label();
			kayttoastePP3Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			kayttoastePP3Tulos.setMaxWidth(100);
			
			kayttoastePP4Label = new Label("Käyttöaste PP4 (WC):");
			kayttoastePP4Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			kayttoastePP4Label.setTooltip(new Tooltip("Kuinka suurella prosenttiosuudella Palvelupiste 4 (WC) on ollut käytössä simuloinnin kokonaisajasta."));
			kayttoastePP4Tulos = new Label();
			kayttoastePP4Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			kayttoastePP4Tulos.setMaxWidth(100);
			
			oleskeluaikaPP1Label = new Label("Kaikki asiakkaat kokonaisoleskeluaika PP1:");
			oleskeluaikaPP1Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			oleskeluaikaPP1Label.setTooltip(new Tooltip("Palvelupisteellä 1 (Lipuntarkastus) kaikkien asiakkaiden yhteenlasketut jonotusajat ja palveluajat."));
			oleskeluaikaPP1Tulos = new Label();
			oleskeluaikaPP1Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			oleskeluaikaPP1Tulos.setMaxWidth(100);
			
			oleskeluaikaPP2Label = new Label("Kaikki asiakkaat kokonaisoleskeluaika PP2:");
			oleskeluaikaPP2Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			oleskeluaikaPP2Label.setTooltip(new Tooltip("Palvelupisteellä 2 (Turvatarkastus) kaikkien asiakkaiden yhteenlasketut jonotusajat ja palveluajat."));
			oleskeluaikaPP2Tulos = new Label();
			oleskeluaikaPP2Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			oleskeluaikaPP2Tulos.setMaxWidth(100);
			
			oleskeluaikaPP3Label = new Label("Kaikki asiakkaat kokonaisoleskeluaika PP3:");
			oleskeluaikaPP3Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			oleskeluaikaPP3Label.setTooltip(new Tooltip("Palvelupisteellä 3 (Baaritiski) kaikkien asiakkaiden yhteenlasketut jonotusajat ja palveluajat."));
			oleskeluaikaPP3Tulos = new Label();
			oleskeluaikaPP3Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			oleskeluaikaPP3Tulos.setMaxWidth(100);
			
			oleskeluaikaPP4Label = new Label("Kaikki asiakkaat kokonaisoleskeluaika PP4:");
			oleskeluaikaPP4Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			oleskeluaikaPP4Label.setTooltip(new Tooltip("Palvelupisteellä 4 (WC) kaikkien asiakkaiden yhteenlasketut jonotusajat ja palveluajat."));
			oleskeluaikaPP4Tulos = new Label();
			oleskeluaikaPP4Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			oleskeluaikaPP4Tulos.setMaxWidth(100);
			
			jononPituusPP1Label = new Label("Keskimääräinen jonon pituus PP1:");
			jononPituusPP1Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			jononPituusPP1Label.setTooltip(new Tooltip("Kuinka monta asiakasta on keskimäärin jonossa Palvelupisteelle 1 (Lipuntarkastus)."));
			jononPituusPP1Tulos = new Label();
			jononPituusPP1Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			jononPituusPP1Tulos.setMaxWidth(100);
			
			jononPituusPP2Label = new Label("Keskimääräinen jonon pituus PP2:");
			jononPituusPP2Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			jononPituusPP2Label.setTooltip(new Tooltip("Kuinka monta asiakasta on keskimäärin jonossa Palvelupisteelle 2 (Turvatarkastus)."));
			jononPituusPP2Tulos = new Label();
			jononPituusPP2Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			jononPituusPP2Tulos.setMaxWidth(100);
			
			jononPituusPP3Label = new Label("Keskimääräinen jonon pituus PP3:");
			jononPituusPP3Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			jononPituusPP3Label.setTooltip(new Tooltip("Kuinka monta asiakasta on keskimäärin jonossa Palvelupisteelle 3 (Baaritiski)."));
			jononPituusPP3Tulos = new Label();
			jononPituusPP3Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			jononPituusPP3Tulos.setMaxWidth(100);
			
			jononPituusPP4Label = new Label("Keskimääräinen jonon pituus PP4:");
			jononPituusPP4Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			jononPituusPP4Label.setTooltip(new Tooltip("Kuinka monta asiakasta on keskimäärin jonossa Palvelupisteelle 4 (WC)."));
			jononPituusPP4Tulos = new Label();
			jononPituusPP4Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			jononPituusPP4Tulos.setMaxWidth(100);
			
			tarkasteleRaporttejaButton = new Button("Tarkastele simulointiraportteja");
			tarkasteleRaporttejaButton.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			tarkasteleRaporttejaButton.setPrefWidth(220);
			
			tarkasteleRaporttejaButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						tietokantaraporttiGUI = new TietokantaRaporttiGUI();
						Stage raporttiStage = new Stage();
						tietokantaraporttiGUI.start(raporttiStage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			grid.add(simuLoppuraporttiLabel, 0, 0);
			GridPane.setHalignment(simuLoppuraporttiLabel, HPos.CENTER);
			
			grid.add(simuKokonaisaikaLabel, 0, 1);
			grid.add(simuKokonaisaikaTulos, 1, 1);
			
			grid.add(xAsiakkaidenParamLabel, 0, 2);
			grid.add(xAsiakkaidenParamTulos, 1, 2);
			
			grid.add(yAsiakkaidenParamLabel, 0, 3);
			grid.add(yAsiakkaidenParamTulos, 1, 3);
			
			grid.add(simuSuoritustehoLabel, 0, 4);
			grid.add(simuSuoritustehoTulos, 1, 4);
			
			grid.add(xSaapuneetLabel, 0, 5);
			grid.add(xSaapuneetTulos, 1, 5);
			
			grid.add(ySaapuneetLabel, 0, 6);
			grid.add(ySaapuneetTulos, 1, 6);
			
			grid.add(yhtSaapuneetLabel, 0, 7);
			grid.add(yhtSaapuneetTulos, 1, 7);
			
			grid.add(xLapiSysteeminLabel, 0, 8);
			grid.add(xLapiSysteeminTulos, 1, 8);
			
			grid.add(yLapiSysteeminLabel, 0, 9);
			grid.add(yLapiSysteeminTulos, 1, 9);
			
			grid.add(yhtLapiSysteeminLabel, 0, 10);
			grid.add(yhtLapiSysteeminTulos, 1, 10);
			
			grid.add(xAsiakkaidenLapimenoaikaLabel, 0, 11);
			grid.add(xAsiakkaidenLapimenoaikaTulos, 1, 11);
			
			grid.add(yAsiakkaidenLapimenoaikaLabel, 0, 12);
			grid.add(yAsiakkaidenLapimenoaikaTulos, 1, 12);
			
			grid.add(asiakkaidenLapimenoaikaYhtLabel, 0, 13);
			grid.add(asiakkaidenLapimenoaikaYhtTulos, 1, 13);
			
			grid.add(kayttoastePP1Label, 0, 14);
			grid.add(kayttoastePP1Tulos, 1, 14);
			
			grid.add(kayttoastePP2Label, 0, 15);
			grid.add(kayttoastePP2Tulos, 1, 15);
			
			grid.add(kayttoastePP3Label, 0, 16);
			grid.add(kayttoastePP3Tulos, 1, 16);
			
			grid.add(kayttoastePP4Label, 0, 17);
			grid.add(kayttoastePP4Tulos, 1, 17);
			
			grid.add(oleskeluaikaPP1Label, 0, 18);
			grid.add(oleskeluaikaPP1Tulos, 1, 18);
			
			grid.add(oleskeluaikaPP2Label, 0, 19);
			grid.add(oleskeluaikaPP2Tulos, 1, 19);
			
			grid.add(oleskeluaikaPP3Label, 0, 20);
			grid.add(oleskeluaikaPP3Tulos, 1, 20);
			
			grid.add(oleskeluaikaPP4Label, 0, 21);
			grid.add(oleskeluaikaPP4Tulos, 1, 21);
			
			grid.add(jononPituusPP1Label, 0, 22);
			grid.add(jononPituusPP1Tulos, 1, 22);
			
			grid.add(jononPituusPP2Label, 0, 23);
			grid.add(jononPituusPP2Tulos, 1, 23);
			
			grid.add(jononPituusPP3Label, 0, 24);
			grid.add(jononPituusPP3Tulos, 1, 24);
			
			grid.add(jononPituusPP4Label, 0, 25);
			grid.add(jononPituusPP4Tulos, 1, 25);
			
			grid.add(tarkasteleRaporttejaButton, 0, 26);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void setSimulaationKokonaisaika(double simulaationKokonaisaika) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.simuKokonaisaikaTulos.setText(formatter.format(simulaationKokonaisaika));
	}

	@Override
	public void setxParam(double xParam) {
		this.xAsiakkaidenParamTulos.setText(xParam + "");
	}

	@Override
	public void setyParam(double yParam) {
		this.yAsiakkaidenParamTulos.setText(yParam + "");
	}

	@Override
	public void setSimulaationSuoritusteho(double simulaationSuoritusteho) {
		this.simuSuoritustehoTulos.setText(simulaationSuoritusteho + " %");
	}

	@Override
	public void setAsiakasMaaraX(double asiakasMaaraX) {
		this.xSaapuneetTulos.setText(asiakasMaaraX + " kappaletta");
	}

	@Override
	public void setAsiakasMaaraY(double asiakasMaaraY) {
		this.ySaapuneetTulos.setText(asiakasMaaraY + " kappaletta");
	}
	
	@Override
	public void setAsiakasMaaraYht(double asiakasMaaraYht) {
		this.yhtSaapuneetTulos.setText(asiakasMaaraYht + " kappaletta");	
	}

	@Override
	public void setxAsiakkaidenLapimenoaika(double xAsiakkaidenLapimenoaika) {
		this.xAsiakkaidenLapimenoaikaTulos.setText(xAsiakkaidenLapimenoaika + "");
	}

	@Override
	public void setyAsiakkaidenLapimenoaika(double yAsiakkaidenLapimenoaika) {
		this.yAsiakkaidenLapimenoaikaTulos.setText(yAsiakkaidenLapimenoaika + "");
	}

	@Override
	public void setAsiakkaidenLapimenoaikaYht(double asiakkaidenLapimenoaikaYht) {
		this.asiakkaidenLapimenoaikaYhtTulos.setText(asiakkaidenLapimenoaikaYht + "");
	}

	@Override
	public void setLapimenneetX(int lapimenneetX) {
		this.xLapiSysteeminTulos.setText(lapimenneetX + " kappaletta");
	}

	@Override
	public void setLapimenneetY(int lapimenneetY) {
		this.yLapiSysteeminTulos.setText(lapimenneetY + " kappaletta");
	}
	
	@Override
	public void setLapimenneetYht(int lapimenneetYht) {
		this.yhtLapiSysteeminTulos.setText(lapimenneetYht + " kappaletta");	
	}

	@Override
	public void setOleskeluaikaPP1(double oleskeluaikaPP1) {
		this.oleskeluaikaPP1Tulos.setText(oleskeluaikaPP1 + "");
	}

	@Override
	public void setOleskeluaikaPP2(double oleskeluaikaPP2) {
		this.oleskeluaikaPP2Tulos.setText(oleskeluaikaPP2 + "");
	}

	@Override
	public void setOleskeluaikaPP3(double oleskeluaikaPP3) {
		this.oleskeluaikaPP3Tulos.setText(oleskeluaikaPP3 + " ");
	}

	@Override
	public void setOleskeluaikaPP4(double oleskeluaikaPP4) {
		this.oleskeluaikaPP4Tulos.setText(oleskeluaikaPP4 + "");
	}

	@Override
	public void setJononPituusPP1(double jononPituusPP1) {
		this.jononPituusPP1Tulos.setText(jononPituusPP1 + " henkilöä");
	}

	@Override
	public void setJononPituusPP2(double jononPituusPP2) {
		this.jononPituusPP2Tulos.setText(jononPituusPP2 + " henkilöä");
	}

	@Override
	public void setJononPituusPP3(double jononPituusPP3) {
		this.jononPituusPP3Tulos.setText(jononPituusPP3 + " henkilöä");
	}

	@Override
	public void setJononPituusPP4(double jononPituusPP4) {
		this.jononPituusPP4Tulos.setText(jononPituusPP4 + " henkilöä");
	}

	@Override
	public void setKayttoastePP1(double kayttoastePP1) {
		this.kayttoastePP1Tulos.setText(kayttoastePP1 + " %");	
	}

	@Override
	public void setKayttoastePP2(double kayttoastePP2) {
		this.kayttoastePP2Tulos.setText(kayttoastePP2 + " %");	
	}

	@Override
	public void setKayttoastePP3(double kayttoastePP3) {
		this.kayttoastePP3Tulos.setText(kayttoastePP3 + " %");
	}

	@Override
	public void setKayttoastePP4(double kayttoastePP4) {
		this.kayttoastePP4Tulos.setText(kayttoastePP4 + " %");	
	}

}
