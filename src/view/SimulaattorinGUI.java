package view;

import java.text.DecimalFormat;
import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.framework.Kello;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;

/**
 * @author Dahlman, Laamo, Lappi
 *
 */
public class SimulaattorinGUI extends Application implements ISimulaattorinUI {

	private IKontrolleriVtoM kontrolleri;

	private Button tarkasteleRaporttejaButton;
	private Button infoButton;

	private Label aikaLabel;
	private TextField aika;

	private Label viiveLabel;
	private TextField viive;

	private Label xAsiakkaidenParamLabel;
	private TextField xAsiakkaidenParam;

	private Label yAsiakkaidenParamLabel;
	private TextField yAsiakkaidenParam;

	private Button kaynnistaButton;
	private Button nopeutaButton;
	private Button hidastaButton;
	
	private Button turkoosiButton;
	private Button punainenButton;
	
	private Label turkoosiSeliteLabel;
	private Label punainenSeliteLabel;

	private Label pp1Label;
	private Label pp2Label;
	private Label pp3Label;
	private Label pp4Label;

	private IVisualisointi pp1Naytto;
	private IVisualisointi pp2Naytto;
	private IVisualisointi pp3Naytto;
	private IVisualisointi pp4Naytto;

	private ProgressBar progressBar;

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

	private Label simuKokonaisaikaLabel;
	private Label simuKokonaisaikaTulos;

	private TietokantaRaporttiGUI tietokantaraporttiGUI;

	@Override
	public void init() {
		Trace.setTraceLevel(Level.INFO);
		kontrolleri = new Kontrolleri(this);
		tietokantaraporttiGUI = new TietokantaRaporttiGUI();
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

			primaryStage.setTitle("Simulaattori");
			primaryStage.getIcons().add(new Image(SimulaattorinGUI.class.getResourceAsStream("graphics/gui_ikoni.png")));

			BorderPane border = new BorderPane();

			Scene scene = new Scene(border, 1100, 780);
			scene.getStylesheets().add("view/style.css");
			primaryStage.setScene(scene);
			primaryStage.show();

			HBox hBox = new HBox();
			hBox.setPadding(new Insets(20, 10, 0, 10));
			hBox.setSpacing(10);
			hBox.setAlignment(Pos.CENTER);
			border.setTop(hBox);

			tarkasteleRaporttejaButton = new Button("Tarkastele simulointiraportteja");
			tarkasteleRaporttejaButton.setPrefWidth(260);
			tarkasteleRaporttejaButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						Stage raporttiStage = new Stage();
						tietokantaraporttiGUI.start(raporttiStage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			infoButton = new Button("i");
			infoButton.setId("infoButton");
			
			infoButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.getDialogPane().setMinHeight(780);
					alert.setTitle("Simulaattorin käyttöohjeet");
					alert.setHeaderText(null);
					alert.setContentText("1. Syötä Simulointiaika-kenttään arvo, joka on vähintään 100. \n \n"
							+ "2. Syötä Viive-kenttään arvo, joka on vähintään 0. \n \n" 
							+ "3. Syötä Ei WC:ssä käyvät asiakkaat -kenttään arvo, joka määrittää, kuinka usein ei WC:n kautta kulkevia asiakkaita saapuu. \n \n Pieni luku: asiakkaita saapuu usein, eli suuri määrä. \n Suuri luku: asiakkaita saapuu harvoin, eli pieni määrä. \n \n"
							+ "4. Syötä WC:ssä käyvät asiakkaat -kenttään arvo, joka määrittää, kuinka usein WC:ssä käyviä asiakkaita saapuu. \n \n Pieni luku: asiakkaita saapuu usein, eli suuri määrä. \n Suuri luku: asiakkaita saapuu harvoin, eli pieni määrä. \n \n"
							+ "5. Klikkaa Käynnistä simulointi -painiketta. \n \n"
							+ "6. Voit simuloinnin ollessa käynnissä nopeuttaa tai hidastaa simuloinnin viivettä Nopeuta- ja Hidasta-painikkeista. \n \n"
							+ "7. Kun simulointiajo on valmis, simulaattorinäkymän päälle avautuu uusi näkymä, jossa on simulaation loppuraportti. \n \n"
							+ "8. Simulaation loppuraportin sisältämät tiedot tallennetaan automaattisesti simulointiraportteihin. \n \n"
							+ "9. Voit tarkastella tallennettuja simulointiraportteja Tarkastele simulointiraportteja -painikkeesta.");

					alert.getDialogPane().getStylesheets().add("view/style.css");
					alert.showAndWait();
				}
			});

			StackPane stack = new StackPane();
			stack.getChildren().add(infoButton);
			stack.setAlignment(Pos.CENTER_RIGHT);
			StackPane.setMargin(infoButton, new Insets(0, 20, 0, 0));

			Region tyhjaTila1 = new Region();
			HBox.setHgrow(tyhjaTila1, Priority.ALWAYS);

			Region tyhjaTila2 = new Region();
			HBox.setHgrow(tyhjaTila2, Priority.ALWAYS);

			hBox.getChildren().addAll(tyhjaTila1, tarkasteleRaporttejaButton, tyhjaTila2, stack);

			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setVgap(10);
			grid.setHgap(5);

			border.setCenter(grid);

			aikaLabel = new Label("Simulointiaika: ");
			aika = new TextField("");
			aika.setPromptText("Syötä arvo");
			aika.setMaxWidth(100);
			aika.setTooltip(new Tooltip("Simulointiajaksi tulee syöttää arvo, joka on vähintään 100."));

			viiveLabel = new Label("Viive: ");
			viive = new TextField("");
			viive.setPromptText("Syötä arvo");
			viive.setMaxWidth(100);
			viive.setTooltip(new Tooltip("Viiveeksi tulee syöttää arvo, joka on vähintään 0."));

			xAsiakkaidenParamLabel = new Label("Ei WC:ssä käyvät asiakkaat: ");
			xAsiakkaidenParam = new TextField("");
			xAsiakkaidenParam.setPromptText("Syötä arvo");
			xAsiakkaidenParam.setMaxWidth(100);
			xAsiakkaidenParam.setTooltip(new Tooltip("Parametri sille, kuinka usein ei WC:n kautta kulkevia asiakkaita saapuu. \n Pieni luku: asiakkaita saapuu usein, eli suuri määrä. \n Suuri luku: asiakkaita saapuu harvoin, eli pieni määrä."));

			yAsiakkaidenParamLabel = new Label("WC:ssä käyvät asiakkaat: ");
			yAsiakkaidenParam = new TextField("");
			yAsiakkaidenParam.setPromptText("Syötä arvo");
			yAsiakkaidenParam.setMaxWidth(100);
			yAsiakkaidenParam.setTooltip(new Tooltip("Parametri sille, kuinka usein WC:ssä käyviä asiakkaita saapuu. \n Pieni luku: asiakkaita saapuu usein, eli suuri määrä. \n Suuri luku: asiakkaita saapuu harvoin, eli pieni määrä."));
			
			kaynnistaButton = new Button();
			kaynnistaButton.setText("Käynnistä simulointi");
			kaynnistaButton.setMaxWidth(205.0);
			GridPane.setFillWidth(kaynnistaButton, true);

			kaynnistaButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					kontrolleri.kaynnistaSimulointi();
					kaynnistaButton.setDisable(true);
					
					turkoosiSeliteLabel = new Label("Ei WC:ssä käyvät asiakkaat  ");
					turkoosiSeliteLabel.setTooltip(new Tooltip("Ei WC:n kautta kulkevat asiakkaat etenevät seuraavasti: Lipuntarkastus - Turvatarkastus - Baaritiski."));
					grid.add(turkoosiSeliteLabel, 3, 3);
					GridPane.setHalignment(turkoosiSeliteLabel, HPos.LEFT);
					
					turkoosiButton = new Button("");
					turkoosiButton.setId("turkoosiButton");					
					grid.add(turkoosiButton, 3, 3);
					GridPane.setHalignment(turkoosiButton, HPos.RIGHT);
					
					punainenSeliteLabel = new Label("WC:ssä käyvät asiakkaat");
					punainenSeliteLabel.setTooltip(new Tooltip("WC:ssä käyvät asiakkaat etenevät seuraavasti: Lipuntarkastus - Turvatarkastus - WC - Baaritiski."));
					grid.add(punainenSeliteLabel, 3, 4);
					GridPane.setHalignment(punainenSeliteLabel, HPos.LEFT);
					
					punainenButton = new Button("");
					punainenButton.setId("punainenButton");
					
					grid.add(punainenButton, 3, 4);
					GridPane.setHalignment(punainenButton, HPos.RIGHT);
				}
			});

			nopeutaButton = new Button();
			nopeutaButton.setText("Nopeuta");
			nopeutaButton.setPrefWidth(100);
			nopeutaButton.setOnAction(e -> kontrolleri.nopeuta());

			hidastaButton = new Button();
			hidastaButton.setText("Hidasta");
			hidastaButton.setPrefWidth(100);
			hidastaButton.setOnAction(e -> kontrolleri.hidasta());

			pp1Label = new Label("PP1 Lipuntarkastus");

			pp2Label = new Label("PP2 Turvatarkastus");

			pp3Label = new Label("PP3 Baaritiski");

			pp4Label = new Label("PP4 WC");

			pp1Naytto = new Visualisointi(200, 260, "graphics/PP1_L.png");
			pp2Naytto = new Visualisointi(200, 260, "graphics/PP2_T.png");
			pp3Naytto = new Visualisointi(200, 260, "graphics/PP3_B.png");
			pp4Naytto = new Visualisointi(200, 260, "graphics/PP4_WC.png");

			progressBar = new ProgressBar(0);

			xSaapuneetLabel = new Label("Saapuneet asiakkaat, ei WC-käyntiä:");

			xSaapuneetTulos = new Label();
			xSaapuneetTulos.setMaxWidth(100);

			ySaapuneetLabel = new Label("Saapuneet asiakkaat, WC-käynti:");

			ySaapuneetTulos = new Label();
			ySaapuneetTulos.setMaxWidth(100);

			yhtSaapuneetLabel = new Label("Saapuneet asiakkaat yhteensä:");

			yhtSaapuneetTulos = new Label();
			yhtSaapuneetTulos.setMaxWidth(100);

			xLapiSysteeminLabel = new Label("Asiakkaita systeemin läpi, ei WC-käyntiä:");

			xLapiSysteeminTulos = new Label();
			xLapiSysteeminTulos.setMaxWidth(100);

			yLapiSysteeminLabel = new Label("Asiakkaita systeemin läpi, WC-käynti:");

			yLapiSysteeminTulos = new Label();
			yLapiSysteeminTulos.setMaxWidth(100);

			yhtLapiSysteeminLabel = new Label("Asiakkaita systeemin läpi yhteensä:");

			yhtLapiSysteeminTulos = new Label();
			yhtLapiSysteeminTulos.setMaxWidth(100);

			simuKokonaisaikaLabel = new Label("Simulaation kokonaisaika:");
			simuKokonaisaikaTulos = new Label();
			simuKokonaisaikaTulos.setMaxWidth(100);

			// Grid alkaa tyhjällä rivillä 0
			
			grid.add(aikaLabel, 1, 1);
			GridPane.setHalignment(aikaLabel, HPos.RIGHT);
			grid.add(aika, 2, 1);

			grid.add(viiveLabel, 1, 2);
			GridPane.setHalignment(viiveLabel, HPos.RIGHT);
			grid.add(viive, 2, 2);

			grid.add(xAsiakkaidenParamLabel, 0, 3, 2, 1);
			GridPane.setHalignment(xAsiakkaidenParamLabel, HPos.RIGHT);
			grid.add(xAsiakkaidenParam, 2, 3);

			grid.add(yAsiakkaidenParamLabel, 0, 4, 2, 1);
			GridPane.setHalignment(yAsiakkaidenParamLabel, HPos.RIGHT);
			grid.add(yAsiakkaidenParam, 2, 4);

			// Välissä tyhjä rivi 5

			grid.add(kaynnistaButton, 1, 6, 2, 1);
			GridPane.setHalignment(kaynnistaButton, HPos.CENTER);

			grid.add(nopeutaButton, 1, 7);
			GridPane.setHalignment(nopeutaButton, HPos.RIGHT);

			grid.add(hidastaButton, 2, 7);
			GridPane.setHalignment(hidastaButton, HPos.LEFT);

			// Välissä tyhjä rivi 8

			// Labelien järjestys: PP1, PP2, PP4, PP3
			grid.add(pp1Label, 0, 9);
			GridPane.setHalignment(pp1Label, HPos.CENTER);

			grid.add(pp2Label, 1, 9);
			GridPane.setHalignment(pp2Label, HPos.CENTER);

			grid.add(pp4Label, 2, 9);
			GridPane.setHalignment(pp4Label, HPos.CENTER);

			grid.add(pp3Label, 3, 9);
			GridPane.setHalignment(pp3Label, HPos.CENTER);

			// Näyttöjen järjestys: PP1, PP2, PP4, PP3
			grid.add((Canvas) pp1Naytto, 0, 10);
			grid.add((Canvas) pp2Naytto, 1, 10);
			grid.add((Canvas) pp4Naytto, 2, 10);
			grid.add((Canvas) pp3Naytto, 3, 10);

			progressBar.setMaxWidth(Double.MAX_VALUE);
			grid.add(progressBar, 0, 11, 4, 1);

			grid.add(xSaapuneetLabel, 0, 12, 2, 1);
			GridPane.setHalignment(xSaapuneetLabel, HPos.LEFT);
			grid.add(xSaapuneetTulos, 1, 12);
			GridPane.setHalignment(xSaapuneetTulos, HPos.CENTER);

			grid.add(ySaapuneetLabel, 0, 13, 2, 1);
			GridPane.setHalignment(ySaapuneetLabel, HPos.LEFT);
			grid.add(ySaapuneetTulos, 1, 13);
			GridPane.setHalignment(ySaapuneetTulos, HPos.CENTER);

			grid.add(yhtSaapuneetLabel, 0, 14, 2, 1);
			GridPane.setHalignment(yhtSaapuneetLabel, HPos.LEFT);
			grid.add(yhtSaapuneetTulos, 1, 14);
			GridPane.setHalignment(yhtSaapuneetTulos, HPos.CENTER);

			grid.add(xLapiSysteeminLabel, 2, 12, 2, 1);
			GridPane.setHalignment(xLapiSysteeminLabel, HPos.LEFT);
			grid.add(xLapiSysteeminTulos, 3, 12);
			GridPane.setHalignment(xLapiSysteeminTulos, HPos.CENTER);

			grid.add(yLapiSysteeminLabel, 2, 13, 2, 1);
			GridPane.setHalignment(yLapiSysteeminLabel, HPos.LEFT);
			grid.add(yLapiSysteeminTulos, 3, 13);
			GridPane.setHalignment(yLapiSysteeminTulos, HPos.CENTER);

			grid.add(yhtLapiSysteeminLabel, 2, 14, 2, 1);
			GridPane.setHalignment(yhtLapiSysteeminLabel, HPos.LEFT);
			grid.add(yhtLapiSysteeminTulos, 3, 14);
			GridPane.setHalignment(yhtLapiSysteeminTulos, HPos.CENTER);

			// Välissä tyhjä rivi 15

			grid.add(simuKokonaisaikaLabel, 0, 16);
			GridPane.setHalignment(simuKokonaisaikaLabel, HPos.LEFT);
			grid.add(simuKokonaisaikaTulos, 1, 16);
			GridPane.setHalignment(simuKokonaisaikaTulos, HPos.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public double getAika() {
		return Double.parseDouble(aika.getText());
	}

	@Override
	public long getViive() {
		return Long.parseLong(viive.getText());
	}

	@Override
	public double getXParam() {
		return Double.parseDouble(xAsiakkaidenParam.getText());
	}

	@Override
	public double getYParam() {
		return Double.parseDouble(yAsiakkaidenParam.getText());
	}

	@Override
	public void setLoppuaika(double aika) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.simuKokonaisaikaTulos.setText(formatter.format(aika));
	}

	@Override
	public void setAsiakasMaaraX(int asiakasMaaraX) {
		this.xSaapuneetTulos.setText(asiakasMaaraX + "");
	}


	@Override
	public void setAsiakasMaaraY(int asiakasMaaraY) {
		this.ySaapuneetTulos.setText(asiakasMaaraY + "");
		
	}

	@Override
	public void setAsiakasMaaraYht(int asiakasMaaraYht) {
		this.yhtSaapuneetTulos.setText(asiakasMaaraYht + "");
	}

	@Override
	public void setPalvellutX(int asiakasMaaraX) {
		this.xLapiSysteeminTulos.setText(asiakasMaaraX + "");
	}

	@Override
	public void setPalvellutY(int asiakasMaaraY) {
		this.yLapiSysteeminTulos.setText(asiakasMaaraY + "");
	}

	@Override
	public void setPalvellut(int asiakasMaara) {
		this.yhtLapiSysteeminTulos.setText(asiakasMaara + "");
	}

	public void setProgressBarAika() {
		progressBar.setProgress(Kello.getInstance().getAika() / getAika());
	}

	@Override
	public IVisualisointi getPP1Visualisointi() {
		return pp1Naytto;
	}

	@Override
	public IVisualisointi getPP2Visualisointi() {
		return pp2Naytto;
		
	}

	@Override
	public IVisualisointi getPP3Visualisointi() {
		return pp3Naytto;
	}

	@Override
	public IVisualisointi getPP4Visualisointi() {
		return pp4Naytto;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
