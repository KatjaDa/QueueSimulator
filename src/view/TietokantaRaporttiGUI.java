package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import simu.model.ITietokantaRaporttiDAO;
import simu.model.TietokantaRaportti;
import simu.model.TietokantaRaporttiAO;


/**
 * @author Dahlman, Laamo, Lappi
 *
 */
public class TietokantaRaporttiGUI extends Application {
	static ITietokantaRaporttiDAO tietokantaraporttiDAO = new TietokantaRaporttiAO();


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Created a table view
		TableView tbv = new TableView();
        final Label label = new Label("Loppuraportti tietokannasta");
        label.setId("tietokantaraporttiHeader");
		
		// Added all columns to table view
//		TableColumn<Integer, TietokantaRaportti> cl1 = new TableColumn<>("Id");
//		cl1.setCellValueFactory(new PropertyValueFactory<>("Id"));
		TableColumn<LocalDate, TietokantaRaportti> cl2 = new TableColumn<>("Päivämäärä");
		cl2.setCellValueFactory(new PropertyValueFactory<>("paivamaara"));
		TableColumn<Double, TietokantaRaportti> cl3 = new TableColumn<>("Kokonaisaika");
		cl3.setCellValueFactory(new PropertyValueFactory<>("simulaationKokonaisaika"));
		TableColumn<Double, TietokantaRaportti> cl4 = new TableColumn<>("Ei WC as. \nparametri");
		cl4.setCellValueFactory(new PropertyValueFactory<>("xParam"));
		TableColumn<Double, TietokantaRaportti> cl5 = new TableColumn<>("WC as. \nparametri");
		cl5.setCellValueFactory(new PropertyValueFactory<>("yParam"));
		TableColumn<Double, TietokantaRaportti> cl6 = new TableColumn<>("Simulaation \nsuoritusteho \n(%)");
		cl6.setCellValueFactory(new PropertyValueFactory<>("simulaationSuoritusteho"));
		TableColumn<Double, TietokantaRaportti> cl7 = new TableColumn<>("Saapuneet \nei WC as.\n(henkilö)");
		cl7.setCellValueFactory(new PropertyValueFactory<>("asiakasMaaraX"));
		cl7.setMinWidth(80);
		TableColumn<Double, TietokantaRaportti> cl8 = new TableColumn<>("Saapuneet \nWC as.\n(henkilö)");
		cl8.setCellValueFactory(new PropertyValueFactory<>("asiakasMaaraY"));
		cl8.setMinWidth(80);
		TableColumn<Double, TietokantaRaportti> cl9 = new TableColumn<>("Ei WC as. \nläpimenoaika\n(aika)");
		cl9.setCellValueFactory(new PropertyValueFactory<>("xAsiakkaidenLapimenoaika"));
		cl9.setMinWidth(80);
		TableColumn<Double, TietokantaRaportti> cl10 = new TableColumn<>("WC as. \nläpimenoaika\n(aika)");
		cl10.setCellValueFactory(new PropertyValueFactory<>("yAsiakkaidenLapimenoaika"));
		cl10.setMinWidth(80);
		TableColumn<Double, TietokantaRaportti> cl11 = new TableColumn<>("Läpimenoaika \nyhteensä\n(aika)");
		cl11.setCellValueFactory(new PropertyValueFactory<>("asiakkaidenLapimenoaikaYht"));
		cl11.setMinWidth(80);
		TableColumn<Double, TietokantaRaportti> cl13 = new TableColumn<>("PP1 \nkäyttöaste\n(%)");
		cl13.setCellValueFactory(new PropertyValueFactory<>("kayttoastePP1"));
		TableColumn<Double, TietokantaRaportti> cl14 = new TableColumn<>("PP2 \nkäyttöaste\n(%)");
		cl14.setCellValueFactory(new PropertyValueFactory<>("kayttoastePP2"));
		TableColumn<Double, TietokantaRaportti> cl15 = new TableColumn<>("PP3 \nkäyttöaste\n(%)");
		cl15.setCellValueFactory(new PropertyValueFactory<>("kayttoastePP3"));
		TableColumn<Double, TietokantaRaportti> cl16 = new TableColumn<>("PP4 \nkäyttöaste\n(%)");
		cl16.setCellValueFactory(new PropertyValueFactory<>("kayttoastePP4"));
		TableColumn<Double, TietokantaRaportti> cl17 = new TableColumn<>("PP1 jonon \npituus\n(henkilö)");
		cl17.setCellValueFactory(new PropertyValueFactory<>("jononPituusPP1"));
		TableColumn<Double, TietokantaRaportti> cl18 = new TableColumn<>("PP2 jonon \npituus\n(henkilö)");
		cl18.setCellValueFactory(new PropertyValueFactory<>("jononPituusPP2"));
		TableColumn<Double, TietokantaRaportti> cl19 = new TableColumn<>("PP3 jonon \npituus\n(henkilö)");
		cl19.setCellValueFactory(new PropertyValueFactory<>("jononPituusPP3"));
		TableColumn<Double, TietokantaRaportti> cl20 = new TableColumn<>("PP4 jonon \npituus\n(henkilö)");
		cl20.setCellValueFactory(new PropertyValueFactory<>("jononPituusPP4"));
		
		//tbv.getColumns().add(cl1);
		tbv.getColumns().add(cl2);
		tbv.getColumns().add(cl3);
		tbv.getColumns().add(cl4);
		tbv.getColumns().add(cl5);
		tbv.getColumns().add(cl6);
		tbv.getColumns().add(cl7);
		tbv.getColumns().add(cl8);
		tbv.getColumns().add(cl9);
		tbv.getColumns().add(cl10);
		tbv.getColumns().add(cl11);
		tbv.getColumns().add(cl13);
		tbv.getColumns().add(cl14);
		tbv.getColumns().add(cl15);
		tbv.getColumns().add(cl16);
		tbv.getColumns().add(cl17);
		tbv.getColumns().add(cl18);
		tbv.getColumns().add(cl19);
		tbv.getColumns().add(cl20);
		
		TietokantaRaportti[] raportit = tietokantaraporttiDAO.readRaportit();
		for (TietokantaRaportti r : raportit) {
			System.out.println("Omamoottori: tämä tulee tietokannasta " + r);
			tbv.getItems().add(r);
		}

		primaryStage.setTitle("Loppuraportti tietokannasta");
		primaryStage.getIcons().add(new Image(TietokantaRaporttiGUI.class.getResourceAsStream("graphics/gui_ikoni.png")));
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(label,tbv);
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPrefHeight(600);
		Scene scene = new Scene(vbox, 1540,780);
		scene.getStylesheets().add("view/style.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
}
