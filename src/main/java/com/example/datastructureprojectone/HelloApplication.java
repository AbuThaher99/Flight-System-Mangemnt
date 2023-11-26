package com.example.datastructureprojectone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HelloApplication extends Application {

	LinkedList<Passengers> passngerdata = new LinkedList<Passengers>();
	LinkedList<Flights> flightdata = new LinkedList<Flights>();
	File file1 = null;
	File file2 = null;

	public void readpass(Stage pri) {
		if (flightdata.length() > 1) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose Passengers.txt");

			file1 = fileChooser.showOpenDialog(pri);

			Scanner sc;
			String[] output = new String[6];

			try {
				sc = new Scanner(file1);
				while (sc.hasNextLine()) {
					output = sc.nextLine().split(",");

					passngerdata.insertf(
							new Passengers(Integer.parseInt(output[0].trim()), Long.parseLong(output[1].trim()),
									output[2].trim(), output[3].trim(), output[4].trim(), output[5].trim()));

					Node<Flights> S = flightdata
							.searchm(new Flights(Integer.parseInt(output[0].trim()), null, null, null, 0));

					if (S != null) {
						if (S.getData().getCapacity() > count(Integer.parseInt(output[0].trim()))) {
							S.getData().getPassdata()
									.insertf(new Passengers(Integer.parseInt(output[0].trim()),
											Long.parseLong(output[1].trim()), output[2].trim(), output[3].trim(),
											output[4].trim(), output[5].trim()));

						}

					}
				}
				 if(passngerdata.length() > 1) {
					 Alert alert = new Alert(AlertType.NONE, "The Passengers has been Read", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {}
				} else if(passngerdata.length() == 0) {
					 Alert alert = new Alert(AlertType.NONE, "Empty File", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {}
				}


				
			} catch (NumberFormatException e) {
				e.getMessage();
				Alert alert = new Alert(AlertType.NONE, "Can Not read this file", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
			} catch (FileNotFoundException e) {
				e.getMessage();
				Alert alert = new Alert(AlertType.NONE, "Can Not read this file", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
			} catch (NullPointerException e) {
				e.getMessage();
				Alert alert = new Alert(AlertType.NONE, "Can Not read this file", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
			}
		} else {
			Alert alert = new Alert(AlertType.NONE, "Can Not Read The Passengers" + "\nNo Fligths Exiti",
					ButtonType.OK);
			if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
			}

		}

	}

	public void readflight(Stage pri) {
		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Choose Fligths.txt");
		file2 = fileChooser.showOpenDialog(pri);

		Scanner sc;
		String[] output = new String[5];
		try {
			sc = new Scanner(file2);
			while (sc.hasNextLine()) {
				output = sc.nextLine().split(",");

				flightdata.insertf(new Flights(Integer.parseInt(output[0].trim()), output[1].trim(), output[2].trim(),
						output[3].trim(), Integer.parseInt(output[4].trim())));

			}
			if(flightdata.length() > 1) {
				 Alert alert = new Alert(AlertType.NONE, "The Fligths has been Read", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {}
			} else if(flightdata.length() == 0) {
				 Alert alert = new Alert(AlertType.NONE, "Empty File", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {}
			}

		} catch (NumberFormatException e) {
			e.getMessage();
			Alert alert = new Alert(AlertType.NONE, "Can Not read this file", ButtonType.OK);
			if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
			Alert alert = new Alert(AlertType.NONE, "Can Not read this file", ButtonType.OK);
			if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
			}
		} catch (NullPointerException e) {
			e.getMessage();
			Alert alert = new Alert(AlertType.NONE, "Can Not read this file", ButtonType.OK);
			if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
			}
		}
	}

	public long max(int num) {
		long max = 0;
		Node<Flights> sear = flightdata.searchm(new Flights(num, null, null, null, 0));
		Node<Passengers> sear1 = sear.getData().getPassdata().getHead();
		if (sear.getData().getPassdata().length() == 0) {
			return 1;
		}

		while (sear1 != null) {
			if (max < sear1.getData().getNumticket()) {
				max = sear1.getData().getNumticket() + 1;
			}
			sear1 = sear1.getNext();
		}

		return max;

	}

	public int count(int data) {
		int coubt = 0;

		Node<Flights> node = flightdata.searchm(new Flights(data, null, null, null, 0));
		Node<Passengers> nono = node.getData().getPassdata().getHead();
		for (; nono != null; nono = nono.getNext()) {
			if (nono.getData().getNumflight() == data) {
				coubt++;
			}
		}

		return coubt;

	}

	public void savepass() {

		try {
			PrintWriter out = new PrintWriter(file1);
			Node<Flights> S = flightdata.getHead();
			String s = "";
			while (S != null) {
				s += S.getData().getPassdata().toString();
				S = S.getNext();
			}
			out.write(s);
			out.close();
		} catch (FileNotFoundException mo) {
			System.out.println(mo);
		}
	}

	public void savefligths() {
		try {
			PrintWriter out = new PrintWriter(file2);
			Node<Flights> fi = flightdata.getHead();
			String s = "";
			while (fi != null) {
				s += fi.getData().getFligthnumber() + "," + fi.getData().getAirlinename() + "," + fi.getData().getFrom()
						+ "," + fi.getData().getTo() + "," + fi.getData().getCapacity() + "\n";
				fi = fi.getNext();
			}

			out.write(s.trim());

			out.close();
		} catch (FileNotFoundException mo) {
			System.out.println(mo);
		}
	}

	private void shutdown(Stage mainWindow) {

		Alert alert = new Alert(AlertType.NONE, "Really close the stage?", ButtonType.YES, ButtonType.NO);
		if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {

			mainWindow.close();
		}
	}

	Scene main, displyFlight, displsyPassengers, AddEdit, ReseveTicket, CancelRsev, CheckTicket, SearchPassengers,
			MainPage, ReadData;

	Stage read;
	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

	@Override
	public void start(Stage primaryStage) {
		try {

			primaryStage.setOnCloseRequest(evt -> {

				evt.consume();

				shutdown(primaryStage);
			});

			StackPane st17 = new StackPane();
			Image mh17 = new Image("moha.jpg");
			ImageView mah17 = new ImageView(mh17);
			mah17.setFitHeight(1050);
			mah17.setFitWidth(1920);

			GridPane root = new GridPane();
			Button Read = new Button("Read Data", new ImageView("read.png"));
			Read.setPrefHeight(70);
			Read.setPrefWidth(200);
			Read.setTextFill(Color.BLACK);
			Read.setFont(Font.font("Oranienbaum", 18));
			Read.setContentDisplay(ContentDisplay.TOP);
			Read.setId("button");
			root.setMargin(Read, new Insets(30, 20, 30, 0));

			Read.setOnAction(e -> {
				primaryStage.setScene(ReadData);
			});

			Button dis = new Button("Display flight�s information", new ImageView("dis.png"));
			dis.setPrefHeight(70);
			dis.setPrefWidth(200);
			dis.setTextFill(Color.BLACK);
			dis.setFont(Font.font("Oranienbaum", 18));
			dis.setContentDisplay(ContentDisplay.TOP);
			root.setMargin(dis, new Insets(30, 20, 30, 0));
			dis.setId("button");
			dis.setOnAction(e -> {
				primaryStage.setScene(displyFlight);

			});

			Button diss = new Button("Display Passengers information for a specific fligh",
					new ImageView("dis.png"));
			diss.setPrefHeight(70);
			diss.setPrefWidth(200);
			diss.setTextFill(Color.BLACK);
			diss.setFont(Font.font("Oranienbaum", 18));
			diss.setContentDisplay(ContentDisplay.TOP);
			root.setMargin(diss, new Insets(30, 20, 30, 0));
			diss.setId("button");
			diss.setOnAction(e -> {
				primaryStage.setScene(displsyPassengers);
			});

			Button Add = new Button("Add/Edit flight", new ImageView("edit.png"));
			Add.setPrefHeight(70);
			Add.setPrefWidth(200);
			Add.setTextFill(Color.BLACK);
			Add.setFont(Font.font("Oranienbaum", 18));
			Add.setContentDisplay(ContentDisplay.TOP);
			root.setMargin(Add, new Insets(30, 20, 30, 0));
			Add.setId("button");
			Add.setOnAction(e -> {
				primaryStage.setScene(AddEdit);
			});

			Button res = new Button("Reserve a ticket for a specific flight", new ImageView("tik.png"));
			res.setPrefHeight(70);
			res.setPrefWidth(200);
			res.setTextFill(Color.BLACK);
			res.setFont(Font.font("Oranienbaum", 18));
			res.setContentDisplay(ContentDisplay.TOP);
			root.setMargin(res, new Insets(30, 20, 30, 0));
			res.setId("button");
			res.setOnAction(e -> {
				primaryStage.setScene(ReseveTicket);
			});

			Button canc = new Button("Cancel a reservation", new ImageView("can.png"));
			canc.setPrefHeight(70);
			canc.setPrefWidth(200);
			canc.setTextFill(Color.BLACK);
			canc.setFont(Font.font("Oranienbaum", 18));
			canc.setContentDisplay(ContentDisplay.TOP);
			root.setMargin(canc, new Insets(30, 20, 30, 0));
			canc.setId("button");
			canc.setOnAction(e -> {
				primaryStage.setScene(CancelRsev);

			});

			Button check = new Button("Check whether a ticket is reserved for a particular person",
					new ImageView("che.png"));
			check.setPrefHeight(70);
			check.setPrefWidth(200);
			check.setTextFill(Color.BLACK);
			check.setFont(Font.font("Oranienbaum", 18));
			check.setContentDisplay(ContentDisplay.TOP);
			root.setMargin(check, new Insets(30, 20, 30, 0));
			check.setId("button");
			check.setOnAction(e -> {
				primaryStage.setScene(CheckTicket);

			});

			Button search = new Button("Search for a specific passenger and print all his information",
					new ImageView("print.png"));
			search.setPrefHeight(70);
			search.setPrefWidth(200);
			search.setTextFill(Color.BLACK);
			search.setFont(Font.font("Oranienbaum", 18));
			search.setContentDisplay(ContentDisplay.TOP);
			root.setMargin(search, new Insets(30, 20, 30, 0));
			search.setId("button");
			search.setOnAction(e -> {
				primaryStage.setScene(SearchPassengers);
			});

			Button Exit = new Button("Exit", new ImageView("exit.png"));
			Exit.setPrefHeight(70);
			Exit.setPrefWidth(200);
			Exit.setTextFill(Color.BLACK);
			Exit.setFont(Font.font("Oranienbaum", 18));
			Exit.setContentDisplay(ContentDisplay.TOP);
			root.setMargin(Exit, new Insets(30, 20, 30, 0));
			Exit.setId("button");
			Exit.setOnAction(e -> {
				Alert al = new Alert(AlertType.CONFIRMATION);
				al.setTitle("Exit");
				al.setHeaderText("You're about to Exit");

				if (al.showAndWait().get() == ButtonType.OK) {
					read = (Stage) root.getScene().getWindow();
					read.close();
				}
			});

			root.add(Read, 0, 0);
			root.add(dis, 1, 0);
			root.add(diss, 2, 0);

			root.add(Add, 0, 1);
			root.add(res, 1, 1);
			root.add(canc, 2, 1);

			root.add(check, 0, 2);
			root.add(search, 1, 2);
			root.add(Exit, 2, 2);

			root.setAlignment(Pos.CENTER);
			st17.getChildren().addAll(mah17, root);
			main = new Scene(st17, screenSize.getWidth(), screenSize.getHeight());
			main.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//===================================================display flight inforamtion ================	
			StackPane mo = new StackPane();
			Image mh = new Image("moha.jpg");
			ImageView mah = new ImageView(mh);
			mah.setFitHeight(1050);
			mah.setFitWidth(1920);

			GridPane displayflight = new GridPane();

			TextArea Area = new TextArea();
			Area.setPrefWidth(800);
			Area.setPrefHeight(400);
			Area.setEditable(false);
			Area.setId("text-area");

			Button printf = new Button("Print", new ImageView("print.png"));
			printf.setPrefHeight(60);
			printf.setPrefWidth(100);
			printf.setTextFill(Color.BLACK);
			printf.setFont(Font.font("Oranienbaum", 18));
			printf.setContentDisplay(ContentDisplay.TOP);
			displayflight.setMargin(printf, new Insets(30, 0, 0, 120));
			printf.setId("button");

			printf.setOnAction(e -> {
				try {

					Area.setText(flightdata.toString());

				} catch (NullPointerException m) {
					m.getMessage();
				}

			});

			Button back = new Button("Back", new ImageView("Back.png"));
			back.setPrefHeight(60);
			back.setPrefWidth(100);
			back.setTextFill(Color.BLACK);
			back.setFont(Font.font("Oranienbaum", 18));
			back.setContentDisplay(ContentDisplay.TOP);
			back.setId("button");
			displayflight.setMargin(back, new Insets(30, 120, 0, 0));
			back.setOnAction(e -> {
				primaryStage.setScene(main);
			});

			displayflight.add(Area, 1, 1);
			displayflight.add(printf, 0, 2);
			displayflight.add(back, 2, 2);

			displayflight.setAlignment(Pos.CENTER);

			mo.getChildren().addAll(mah, displayflight);
			displyFlight = new Scene(mo, screenSize.getWidth(), screenSize.getHeight());
			displyFlight.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//=========================================================================================
			StackPane mo1 = new StackPane();
			Image mh1 = new Image("moha.jpg");
			ImageView mah1 = new ImageView(mh1);
			mah1.setFitHeight(1050);
			mah1.setFitWidth(1920);

			GridPane displayPassengers = new GridPane();

			Label leb = new Label("Enter the Flight Number :");
			leb.setPrefHeight(113);
			leb.setPrefWidth(227);
			leb.setFont(Font.font("Oranienbaum", 18));
			leb.setTextFill(Color.WHITE);

			TextField tex = new TextField();
			tex.setPrefHeight(25);
			tex.setPrefWidth(195);

			tex.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

			TextArea Area1 = new TextArea();
			Area1.setPrefWidth(800);
			Area1.setPrefHeight(400);
			Area1.setEditable(false);
			Area1.setId("text-area");

			Button printf1 = new Button("Search & Display", new ImageView("sar.png"));
			printf1.setPrefHeight(70);
			printf1.setPrefWidth(200);
			printf1.setTextFill(Color.BLACK);
			printf1.setFont(Font.font("Oranienbaum", 18));
			printf1.setContentDisplay(ContentDisplay.TOP);
			printf1.setId("button");
			displayPassengers.setMargin(printf1, new Insets(30, 0, 0, 150));

			printf1.setOnAction(e -> {
				try {
					Node<Flights> ss = flightdata
							.searchm(new Flights(Integer.parseInt(tex.getText()), null, null, null, 0));
					if (ss != null) {
						Area1.setText(ss.getData().getPassdata().toString());
					} else {
						Alert alert = new Alert(AlertType.NONE, "The Fligths Dose Not Exit", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}

					}

				} catch (NumberFormatException m) {
					m.getMessage();
				}

			});

			Button back1 = new Button("Back", new ImageView("Back.png"));
			back1.setPrefHeight(70);
			back1.setPrefWidth(200);
			back1.setTextFill(Color.BLACK);
			back1.setFont(Font.font("Oranienbaum", 18));
			back1.setContentDisplay(ContentDisplay.TOP);
			back1.setId("button");
			displayPassengers.setMargin(back1, new Insets(30, 150, 0, 0));
			back1.setOnAction(e -> {
				primaryStage.setScene(main);
			});
			displayPassengers.add(leb, 0, 0);
			displayPassengers.add(tex, 1, 0);
			displayPassengers.add(Area1, 1, 1);
			displayPassengers.add(printf1, 0, 2);
			displayPassengers.add(back1, 2, 2);

			displayPassengers.setAlignment(Pos.CENTER);

			mo1.getChildren().addAll(mah1, displayPassengers);
			displsyPassengers = new Scene(mo1, screenSize.getWidth(), screenSize.getHeight());
			displsyPassengers.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//================================================AddEdit=========================================
			StackPane mo2 = new StackPane();
			Image mh2 = new Image("moha.jpg");
			ImageView mah2 = new ImageView(mh2);
			mah2.setFitHeight(1050);
			mah2.setFitWidth(1920);

			GridPane AddEditPane = new GridPane();

			Label Fliglab = new Label();
			Fliglab.setText("flight number :");
			Fliglab.setPrefHeight(113);
			Fliglab.setPrefWidth(227);
			Fliglab.setFont(Font.font("Oranienbaum", 18));
			Fliglab.setTextFill(Color.WHITE);
			AddEditPane.setMargin(Fliglab, new Insets(30, 0, 0, 130));

			Label Fliglab2 = new Label();
			Fliglab2.setText("Airline name :");
			Fliglab2.setPrefHeight(113);
			Fliglab2.setPrefWidth(227);
			Fliglab2.setFont(Font.font("Oranienbaum", 18));
			Fliglab2.setTextFill(Color.WHITE);
			AddEditPane.setMargin(Fliglab2, new Insets(30, 0, 0, 130));

			Label Fliglab3 = new Label();
			Fliglab3.setText("From :");
			Fliglab3.setPrefHeight(113);
			Fliglab3.setPrefWidth(227);
			Fliglab3.setFont(Font.font("Oranienbaum", 18));
			Fliglab3.setTextFill(Color.WHITE);
			AddEditPane.setMargin(Fliglab3, new Insets(30, 0, 0, 130));

			Label Fliglab4 = new Label();
			Fliglab4.setText("To :");
			Fliglab4.setPrefHeight(113);
			Fliglab4.setPrefWidth(227);
			Fliglab4.setFont(Font.font("Oranienbaum", 18));
			Fliglab4.setTextFill(Color.WHITE);
			AddEditPane.setMargin(Fliglab4, new Insets(30, 0, 0, 130));

			Label Fliglab5 = new Label();
			Fliglab5.setText("Capacity :");
			Fliglab5.setPrefHeight(113);
			Fliglab5.setPrefWidth(227);
			Fliglab5.setFont(Font.font("Oranienbaum", 18));
			Fliglab5.setTextFill(Color.WHITE);
			AddEditPane.setMargin(Fliglab5, new Insets(30, 0, 0, 130));

			TextField fligtext = new TextField();
			fligtext.setPrefHeight(25);
			fligtext.setPrefWidth(195);
			fligtext.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
			AddEditPane.setMargin(fligtext, new Insets(30, 0, 0, 0));

			TextField fligtext1 = new TextField();
			fligtext1.setPrefHeight(25);
			fligtext1.setPrefWidth(195);
			AddEditPane.setMargin(fligtext1, new Insets(30, 0, 0, 0));

			TextField fligtext2 = new TextField();
			fligtext2.setPrefHeight(25);
			fligtext2.setPrefWidth(195);

			AddEditPane.setMargin(fligtext2, new Insets(30, 0, 0, 0));

			TextField fligtext3 = new TextField();
			fligtext3.setPrefHeight(25);
			fligtext3.setPrefWidth(195);
			AddEditPane.setMargin(fligtext3, new Insets(30, 0, 0, 0));

			TextField fligtext4 = new TextField();
			fligtext4.setPrefHeight(25);
			fligtext4.setPrefWidth(195);
			// fligtext4.setTextFormatter(new TextFormatter<>(new
			// IntegerStringConverter()));
			AddEditPane.setMargin(fligtext4, new Insets(30, 0, 0, 0));

			fligtext1.setDisable(true);
			fligtext2.setDisable(true);
			fligtext3.setDisable(true);
			fligtext4.setDisable(true);

			Button AddFligts = new Button("Add", new ImageView("add.png"));
			AddFligts.setPrefHeight(61);
			AddFligts.setPrefWidth(106);
			AddFligts.setTextFill(Color.BLACK);
			AddFligts.setFont(Font.font("Oranienbaum", 18));
			AddFligts.setContentDisplay(ContentDisplay.TOP);
			AddFligts.setId("button");
			AddFligts.setDisable(true);
			AddEditPane.setMargin(AddFligts, new Insets(30, 0, 0, 80));
			AddFligts.setOnAction(e -> {
				try {
					Node<Flights> ram = flightdata
							.searchm(new Flights(Integer.parseInt(fligtext.getText()), null, null, null, 0));

					if (ram == null) {
						if (fligtext.getText().isEmpty() || fligtext1.getText().isEmpty()
								|| fligtext3.getText().isEmpty() || fligtext4.getText().isEmpty()
								|| fligtext2.getText().isEmpty()) {
							Alert alert = new Alert(AlertType.NONE, "The Text Field is Empty", ButtonType.OK);
							if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
							}
							return;
						} else
							flightdata.insertf(new Flights(Integer.parseInt(fligtext.getText()), fligtext1.getText(),
									fligtext2.getText(), fligtext3.getText(), Integer.parseInt(fligtext4.getText())));

						fligtext.setText("");
						fligtext1.setText("");
						fligtext2.setText("");
						fligtext3.setText("");
						fligtext4.setText("");
						fligtext1.setDisable(true);
						fligtext2.setDisable(true);
						fligtext3.setDisable(true);
						fligtext4.setDisable(true);
						AddFligts.setDisable(true);

						Alert alert = new Alert(AlertType.NONE, "The Flight hass been Added", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}

					} else {

						Alert alert = new Alert(AlertType.NONE, "The Flight Is Not Exiting", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
					}
				} catch (NumberFormatException m) {
					Alert alert = new Alert(AlertType.NONE, "Can Not Add" + "\nCheck The Capacity",
							ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
					m.getMessage();
				}
			});

			Button backAdd = new Button("Back", new ImageView("Back.png"));
			backAdd.setPrefHeight(61);
			backAdd.setPrefWidth(106);
			backAdd.setTextFill(Color.BLACK);
			backAdd.setFont(Font.font("Oranienbaum", 18));
			backAdd.setContentDisplay(ContentDisplay.TOP);
			backAdd.setId("button");
			AddEditPane.setMargin(backAdd, new Insets(30, 0, 0, 150));
			backAdd.setOnAction(e -> {

				primaryStage.setScene(main);
			});

			Button Edit = new Button("Update", new ImageView("update.png"));
			Edit.setPrefHeight(61);
			Edit.setPrefWidth(106);
			Edit.setTextFill(Color.BLACK);
			Edit.setFont(Font.font("Oranienbaum", 18));
			Edit.setContentDisplay(ContentDisplay.TOP);
			Edit.setId("button");
			Edit.setDisable(true);
			AddEditPane.setMargin(Edit, new Insets(30, 0, 0, 55));

			Edit.setOnAction(e -> {
				try {

					Node<Flights> zozo = flightdata
							.searchm(new Flights(Integer.parseInt(fligtext.getText()), null, null, null, 0));
					if (zozo != null) {
						if (fligtext.getText().isEmpty() || fligtext1.getText().isEmpty()
								|| fligtext3.getText().isEmpty() || fligtext4.getText().isEmpty()
								|| fligtext2.getText().isEmpty()) {
							Alert alert = new Alert(AlertType.NONE, "The Text Field is Empty", ButtonType.OK);
							if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
							}
							return;
						}

						zozo.getData().setAirlinename(fligtext1.getText());
						zozo.getData().setFrom(fligtext2.getText());
						zozo.getData().setTo(fligtext3.getText());
						zozo.getData().setCapacity(Integer.parseInt(fligtext4.getText()));
					}

					fligtext1.setText("");
					fligtext2.setText("");
					fligtext3.setText("");
					fligtext4.setText("");
					fligtext.setText("");
					fligtext1.setDisable(true);
					fligtext2.setDisable(true);
					fligtext3.setDisable(true);
					fligtext4.setDisable(true);
					Edit.setDisable(true);

					Alert alert = new Alert(AlertType.NONE, "Update successfully", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}

				} catch (NumberFormatException m) {
					Alert alert = new Alert(AlertType.NONE, "Can Not Update" + "\nCheck The Capacity",
							ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
					m.getMessage();
				}
			});

			Button SearchEdit = new Button("Find", new ImageView("sar.png"));
			SearchEdit.setPrefHeight(61);
			SearchEdit.setPrefWidth(106);
			SearchEdit.setTextFill(Color.BLACK);
			SearchEdit.setFont(Font.font("Oranienbaum", 18));
			SearchEdit.setContentDisplay(ContentDisplay.TOP);
			SearchEdit.setId("button");
			AddEditPane.setMargin(SearchEdit, new Insets(30, 0, 0, 100));

			SearchEdit.setOnAction(e -> {
				try {
					Node<Flights> Nod = flightdata
							.searchm(new Flights(Integer.parseInt(fligtext.getText()), null, null, null, 0));
					if (Nod != null) {
						fligtext1.setText(Nod.getData().getAirlinename());
						fligtext2.setText(Nod.getData().getFrom());
						fligtext3.setText(Nod.getData().getTo());
						fligtext4.setText(String.valueOf(Nod.getData().getCapacity()));
						Edit.setDisable(false);
						AddFligts.setDisable(true);
						fligtext1.setDisable(false);
						fligtext2.setDisable(false);
						fligtext3.setDisable(false);
						fligtext4.setDisable(false);

						Alert alert = new Alert(AlertType.NONE, "Found", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
					} else {
						Alert alert = new Alert(AlertType.NONE, "Not Exixt " + "\nYou can Add", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}

						AddFligts.setDisable(false);
						Edit.setDisable(true);
						fligtext1.setDisable(false);
						fligtext2.setDisable(false);
						fligtext3.setDisable(false);
						fligtext4.setDisable(false);

						fligtext1.setText("");
						fligtext2.setText("");
						fligtext3.setText("");
						fligtext4.setText("");

					}
				} catch (NumberFormatException m) {
					Alert alert = new Alert(AlertType.NONE, "You must import The Fligth Number", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
					m.getMessage();
				}

			});

			AddEditPane.add(Fliglab, 0, 1);
			AddEditPane.add(Fliglab2, 0, 2);
			AddEditPane.add(Fliglab3, 0, 3);
			AddEditPane.add(Fliglab4, 0, 4);
			AddEditPane.add(Fliglab5, 0, 5);

			AddEditPane.add(fligtext, 1, 1);
			AddEditPane.add(fligtext1, 1, 2);
			AddEditPane.add(fligtext2, 1, 3);
			AddEditPane.add(fligtext3, 1, 4);
			AddEditPane.add(fligtext4, 1, 5);

			AddEditPane.add(AddFligts, 1, 6);
			AddEditPane.add(backAdd, 0, 6);
			AddEditPane.add(SearchEdit, 2, 6);

			AddEditPane.add(Edit, 3, 6);

			AddEditPane.setAlignment(Pos.CENTER);

			mo2.getChildren().addAll(mah2, AddEditPane);
			AddEdit = new Scene(mo2, screenSize.getWidth(), screenSize.getHeight());
			AddEdit.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//==========================================resevTicket===========================================================
			StackPane st4 = new StackPane();
			Image mh4 = new Image("moha.jpg");
			ImageView mah4 = new ImageView(mh4);
			mah4.setFitHeight(1050);
			mah4.setFitWidth(1920);

			GridPane AddGame = new GridPane();

			Label lab = new Label();
			lab.setText("Flight number :");
			lab.setPrefHeight(113);
			lab.setPrefWidth(227);
			lab.setFont(Font.font("Oranienbaum", 24));
			lab.setTextFill(Color.WHITE);
			AddGame.setMargin(lab, new Insets(0, 0, 0, 130));

			Label lab2 = new Label();
			lab2.setText("Ticket number :");
			lab2.setPrefHeight(113);
			lab2.setPrefWidth(227);
			lab2.setFont(Font.font("Oranienbaum", 24));
			lab2.setTextFill(Color.WHITE);
			AddGame.setMargin(lab2, new Insets(0, 0, 0, 130));

			Label lab3 = new Label();
			lab3.setText("Full Name :");
			lab3.setPrefHeight(113);
			lab3.setPrefWidth(227);
			lab3.setFont(Font.font("Oranienbaum", 24));
			lab3.setTextFill(Color.WHITE);
			AddGame.setMargin(lab3, new Insets(0, 0, 0, 130));

			Label lab4 = new Label();
			lab4.setText("Passport number :");
			lab4.setPrefHeight(113);
			lab4.setPrefWidth(227);
			lab4.setFont(Font.font("Oranienbaum", 24));
			lab4.setTextFill(Color.WHITE);
			AddGame.setMargin(lab4, new Insets(0, 0, 0, 130));

			Label com = new Label("Nationality :");
			com.setPrefHeight(113);
			com.setPrefWidth(227);
			com.setFont(Font.font("Oranienbaum", 24));
			com.setTextFill(Color.WHITE);
			AddGame.setMargin(com, new Insets(0, 0, 0, 130));

			Label com1 = new Label("Birthdate :");
			com1.setPrefHeight(113);
			com1.setPrefWidth(227);
			com1.setFont(Font.font("Oranienbaum", 24));
			com1.setTextFill(Color.WHITE);
			AddGame.setMargin(com1, new Insets(0, 0, 0, 130));

			Label lab5 = new Label();
			lab5.setText("The Messege is :");
			lab5.setPrefHeight(113);
			lab5.setPrefWidth(227);
			lab5.setFont(Font.font("Oranienbaum", 24));
			lab5.setTextFill(Color.WHITE);
			AddGame.setMargin(lab5, new Insets(0, 0, 0, 130));

			TextField text = new TextField();
			text.setPrefHeight(25);
			text.setPrefWidth(195);
			text.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

			TextField text1 = new TextField();
			text1.setPrefHeight(25);
			text1.setPrefWidth(195);
			text1.setTextFormatter(new TextFormatter<>(new LongStringConverter()));

			TextField text2 = new TextField();
			text2.setPrefHeight(25);
			text2.setPrefWidth(195);

			TextField text3 = new TextField();
			text3.setPrefHeight(25);
			text3.setPrefWidth(195);

			TextField tt = new TextField();
			tt.setPrefHeight(25);
			tt.setPrefWidth(195);

			TextField tt1 = new TextField();
			tt1.setPrefHeight(25);
			tt1.setPrefWidth(195);

			DatePicker Date1 = new DatePicker();
			Date1.setPrefHeight(30);
			Date1.setPrefWidth(150);
			Date1.setEditable(false);
			AddGame.setMargin(Date1, new Insets(0, 0, 0, 40));

			text1.setDisable(true);
			text2.setDisable(true);
			text3.setDisable(true);
			tt.setDisable(true);
			tt1.setDisable(true);
			Date1.setDisable(true);

			EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					LocalDate i = Date1.getValue();

					tt1.setText("" + i);
				}
			};

			Date1.setShowWeekNumbers(true);
			Date1.setOnAction(event1);

			Button AddResrve = new Button("Add", new ImageView("add.png"));
			AddResrve.setPrefHeight(61);
			AddResrve.setPrefWidth(106);
			AddResrve.setTextFill(Color.BLACK);
			AddResrve.setFont(Font.font("Oranienbaum", 18));
			AddResrve.setContentDisplay(ContentDisplay.TOP);
			AddResrve.setId("button");
			AddGame.setMargin(AddResrve, new Insets(30, 0, 0, 55));

			AddResrve.setOnAction(e -> {
				try {
					Node<Flights> S = flightdata
							.searchm(new Flights(Integer.parseInt(text.getText()), null, null, null, 0));

					if (text.getText().isEmpty() || text1.getText().isEmpty() || text2.getText().isEmpty()
							|| text3.getText().isEmpty() || tt.getText().isEmpty() || tt1.getText().isEmpty()) {
						Alert alert = new Alert(AlertType.NONE, "The Text Field is Empty", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
						return;

					} else if (S != null) {

						S.getData().getPassdata()
								.insertf(new Passengers(Integer.parseInt(text.getText()),
										Long.parseLong(text1.getText()), text2.getText(), text3.getText(), tt.getText(),
										tt1.getText()));
						Alert alert = new Alert(AlertType.NONE, "The ticket has been Reseve", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}

						text.setText("");
						text1.setText("");
						text2.setText("");
						text3.setText("");
						tt.setText("");
						tt1.setText("");
						AddResrve.setDisable(true);
						tt1.setDisable(true);
						tt.setDisable(true);
						text3.setDisable(true);
						text2.setDisable(true);
					}

				} catch (NumberFormatException m) {
					m.getMessage();
				}

			});

			Button backResve = new Button("Back", new ImageView("Back.png"));
			backResve.setPrefHeight(61);
			backResve.setPrefWidth(106);
			backResve.setTextFill(Color.BLACK);
			backResve.setFont(Font.font("Oranienbaum", 18));
			backResve.setContentDisplay(ContentDisplay.TOP);
			backResve.setId("button");
			AddGame.setMargin(backResve, new Insets(30, 0, 0, 130));
			backResve.setOnAction(e -> {

				primaryStage.setScene(main);
			});

			Button FindReseve = new Button("Find", new ImageView("sar.png"));
			FindReseve.setPrefHeight(61);
			FindReseve.setPrefWidth(106);
			FindReseve.setTextFill(Color.BLACK);
			FindReseve.setFont(Font.font("Oranienbaum", 18));
			FindReseve.setContentDisplay(ContentDisplay.TOP);
			FindReseve.setId("button");
			AddGame.setMargin(FindReseve, new Insets(30, 0, 0, 130));
			AddResrve.setDisable(true);
			FindReseve.setOnAction(e -> {
				try {
					Node<Flights> newNode = flightdata
							.searchm(new Flights(Integer.parseInt(text.getText()), null, null, null, 0));

					if (newNode == null) {

						Alert alert = new Alert(AlertType.NONE, "The Fligths Does Not Found", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
						AddResrve.setDisable(true);
					} else {

						if (count(Integer.parseInt(text.getText())) < newNode.getData().getCapacity()) {

							Alert alert = new Alert(AlertType.NONE, "The Flights is Exiting", ButtonType.OK);
							if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
							}
							text1.setDisable(true);

							long mm = max(Integer.parseInt(text.getText()));

							text1.setText(String.valueOf(mm));

							if (newNode != null) {
								// text1.setDisable(false);
								text2.setDisable(false);
								text3.setDisable(false);
								tt.setDisable(false);
								Date1.setDisable(false);
								Date1.setDisable(false);
								AddResrve.setDisable(false);

								Alert alerte = new Alert(AlertType.NONE, "You can Reseve A Ticket",
										ButtonType.OK);
								if (alerte.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
								}
							}

						} else {
							Alert alert = new Alert(AlertType.NONE, "The Fligths Is Full", ButtonType.OK);
							if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
							}

							text1.setDisable(true);
							text2.setDisable(true);
							text3.setDisable(true);
							tt.setDisable(true);
							tt1.setDisable(true);
							Date1.setDisable(true);
							Date1.setDisable(true);
							AddResrve.setDisable(true);
						}

					}
				} catch (NumberFormatException m) {
					Alert alert = new Alert(AlertType.NONE, "You Must Import A Flight Number", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
					m.getMessage();
				}

			});

			AddGame.add(lab, 0, 0);
			AddGame.add(lab2, 0, 1);
			AddGame.add(lab3, 0, 2);
			AddGame.add(lab4, 0, 3);
			AddGame.add(com, 0, 4);
			AddGame.add(com1, 0, 5);

			AddGame.add(text, 1, 0);
			AddGame.add(text1, 1, 1);
			AddGame.add(text2, 1, 2);
			AddGame.add(text3, 1, 3);
			AddGame.add(tt, 1, 4);
			AddGame.add(tt1, 1, 5);

			AddGame.add(backResve, 2, 6);
			AddGame.add(AddResrve, 1, 6);
			AddGame.add(FindReseve, 0, 6);
			AddGame.add(Date1, 2, 5);
			AddGame.setAlignment(Pos.CENTER);

			st4.getChildren().addAll(mah4, AddGame);
			ReseveTicket = new Scene(st4, screenSize.getWidth(), screenSize.getHeight());
			ReseveTicket.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//=================================Cancel resev================================================
			StackPane st5 = new StackPane();
			Image mh5 = new Image("moha.jpg");
			ImageView mah5 = new ImageView(mh5);
			mah5.setFitHeight(1050);
			mah5.setFitWidth(1920);

			GridPane Cancel = new GridPane();

			Label cancleb = new Label();
			cancleb.setText("Enter Flights Number :");
			cancleb.setPrefHeight(113);
			cancleb.setPrefWidth(227);
			cancleb.setFont(Font.font("Oranienbaum", 20));
			cancleb.setTextFill(Color.WHITE);

			Label cancleb2 = new Label();
			cancleb2.setText("Enter Full Name:");
			cancleb2.setPrefHeight(113);
			cancleb2.setPrefWidth(227);
			cancleb2.setFont(Font.font("Oranienbaum", 20));
			cancleb2.setTextFill(Color.WHITE);

			TextField tex4 = new TextField();
			tex4.setPrefHeight(25);
			tex4.setPrefWidth(195);
			tex4.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

			TextField tex4e = new TextField();
			tex4e.setPrefHeight(25);
			tex4e.setPrefWidth(195);

			Button backCancel = new Button("Back", new ImageView("Back.png"));
			backCancel.setPrefHeight(61);
			backCancel.setPrefWidth(106);
			backCancel.setTextFill(Color.BLACK);
			backCancel.setFont(Font.font("Oranienbaum", 18));
			backCancel.setContentDisplay(ContentDisplay.TOP);
			backCancel.setId("button");
			Cancel.setMargin(backCancel, new Insets(30, 0, 0, 130));
			backCancel.setOnAction(e -> {

				primaryStage.setScene(main);
			});

			Button cancelBut = new Button("Cancel", new ImageView("cancel.png"));
			cancelBut.setPrefHeight(61);
			cancelBut.setPrefWidth(106);
			cancelBut.setTextFill(Color.BLACK);
			cancelBut.setFont(Font.font("Oranienbaum", 18));
			cancelBut.setContentDisplay(ContentDisplay.TOP);
			cancelBut.setId("button");
			Cancel.setMargin(cancelBut, new Insets(30, 0, 0, 100));

			cancelBut.setOnAction(e -> {

				Node<Flights> m1 = flightdata
						.searchm(new Flights(Integer.parseInt(tex4.getText()), null, null, null, 0));
				Node<Passengers> m2 = m1.getData().getPassdata()
						.searchm(new Passengers(0, 0, tex4e.getText(), null, null, null));
				if (tex4.getText().isEmpty() || tex4e.getText().isEmpty()) {
					Alert alert = new Alert(AlertType.NONE, "The Text is Empty", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				} else if (m1 != null) {
					if (m2 != null) {
						m1.getData().getPassdata().delete(m2.getData());
						Alert alert = new Alert(AlertType.NONE, "The Ticket has been Cancel", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
						tex4.setText("");
						tex4e.setText("");
						cancelBut.setDisable(true);
						tex4e.setDisable(true);

					}
				}

			});
			cancelBut.setDisable(true);
			tex4e.setDisable(true);

			Button SearchCancel = new Button("Find", new ImageView("sar.png"));
			SearchCancel.setPrefHeight(61);
			SearchCancel.setPrefWidth(106);
			SearchCancel.setTextFill(Color.BLACK);
			SearchCancel.setFont(Font.font("Oranienbaum", 18));
			SearchCancel.setContentDisplay(ContentDisplay.TOP);
			SearchCancel.setId("button");
			Cancel.setMargin(SearchCancel, new Insets(30, 0, 0, 130));
			SearchCancel.setOnAction(e -> {

				try {

					Node<Flights> m4 = flightdata
							.searchm(new Flights(Integer.parseInt(tex4.getText()), null, null, null, 0));
					Node<Passengers> m5 = m4.getData().getPassdata()
							.searchm(new Passengers(0, 0, tex4e.getText(), null, null, null));

//				 if(tex4.getText().isEmpty()) {
//						Alert alert = new Alert(Alert.AlertType.NONE, "The Text is Empty", ButtonType.YES, ButtonType.NO);
//					    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {}
//					}
//				 	else

					if (m4 != null) {
						tex4e.setDisable(false);

						if (m5 != null) {
							cancelBut.setDisable(false);
							Alert alerte = new Alert(AlertType.NONE,
									"The Fligths Found" + "\nThe Passengers Found", ButtonType.OK);
							if (alerte.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
							}

						} else {

							Alert alerte = new Alert(AlertType.NONE, "The Names Dose Not Exixt", ButtonType.OK);
							if (alerte.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
								return;
							}

						}
					}

				} catch (NumberFormatException m) {
					Alert alert = new Alert(AlertType.NONE, "You Have to Put an Interger Value", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
					m.getMessage();
				} catch (NullPointerException m) {
					Alert alert = new Alert(AlertType.NONE, "The Fligths Number Dose Not Exixt", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
					m.getMessage();
				}

			});

			Cancel.add(cancleb, 0, 0);
			Cancel.add(cancleb2, 0, 1);

			Cancel.add(tex4, 1, 0);
			Cancel.add(tex4e, 1, 1);

			Cancel.add(backCancel, 0, 2);
			Cancel.add(cancelBut, 1, 2);
			Cancel.add(SearchCancel, 2, 2);

			Cancel.setAlignment(Pos.CENTER);

			st5.getChildren().addAll(mah5, Cancel);
			CancelRsev = new Scene(st5, screenSize.getWidth(), screenSize.getHeight());
			CancelRsev.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//============================================Check Ticket =====================================
			StackPane st6 = new StackPane();
			Image mh6 = new Image("moha.jpg");
			ImageView mah6 = new ImageView(mh6);
			mah6.setFitHeight(1050);
			mah6.setFitWidth(1920);

			GridPane Check = new GridPane();

			Label Chleb = new Label();
			Chleb.setText("Enter Full Name :");
			Chleb.setPrefHeight(113);
			Chleb.setPrefWidth(227);
			Chleb.setFont(Font.font("Oranienbaum", 18));
			Chleb.setTextFill(Color.WHITE);

			Label Chleb1 = new Label();
			Chleb1.setText("Enter Fligths Number :");
			Chleb1.setPrefHeight(113);
			Chleb1.setPrefWidth(227);
			Chleb1.setFont(Font.font("Oranienbaum", 18));
			Chleb1.setTextFill(Color.WHITE);

			TextField tex5 = new TextField();
			tex5.setPrefHeight(25);
			tex5.setPrefWidth(195);

			TextField tex5n = new TextField();
			tex5n.setPrefHeight(25);
			tex5n.setPrefWidth(195);

			TextArea Area4 = new TextArea();
			Area4.setPrefHeight(50);
			Area4.setPrefWidth(204);
			Area4.setEditable(false);
			Area4.setPromptText("Erorr");
			Area.setId("text-area");

			Button backCheck = new Button("Back", new ImageView("Back.png"));
			backCheck.setPrefHeight(61);
			backCheck.setPrefWidth(106);
			backCheck.setTextFill(Color.BLACK);
			backCheck.setFont(Font.font("Oranienbaum", 18));
			backCheck.setContentDisplay(ContentDisplay.TOP);
			backCheck.setId("button");
			Check.setMargin(backCheck, new Insets(30, 0, 0, 130));
			backCheck.setOnAction(e -> {

				primaryStage.setScene(main);
			});

			Button SearchCheck = new Button("Check", new ImageView("sar.png"));
			SearchCheck.setPrefHeight(61);
			SearchCheck.setPrefWidth(106);
			SearchCheck.setTextFill(Color.BLACK);
			SearchCheck.setFont(Font.font("Oranienbaum", 18));
			SearchCheck.setContentDisplay(ContentDisplay.TOP);
			SearchCheck.setId("button");
			Check.setMargin(SearchCheck, new Insets(30, 0, 0, 130));

			SearchCheck.setOnAction(e -> {

				try {

					Node<Flights> ra = flightdata
							.searchm(new Flights(Integer.parseInt(tex5n.getText()), null, null, null, 0));
					Node<Passengers> ra2 = ra.getData().getPassdata()
							.searchm(new Passengers(0, 0, tex5.getText(), null, null, null));

					if (ra2 != null) {
						if (ra != null) {

							Alert alert = new Alert(AlertType.NONE,
									"The Ticket is reserved for a particular person", ButtonType.OK);
							if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
							}
							tex5n.setText("");
							tex5.setText("");
						}

					} else {

						Alert alert = new Alert(AlertType.NONE, "Not Reseve", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
					}
				} catch (NumberFormatException m) {
					Alert alert = new Alert(AlertType.NONE, "The Number of Fligths not Exixt", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
					// Area4.setText("The Number of Fligths not Exixt");
					m.getMessage();
				} catch (NullPointerException m) {
					Alert alert = new Alert(AlertType.NONE, "You Have to Put an Rigths Value", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
					m.getMessage();
				}

			});

			Check.add(Chleb, 0, 0);
			Check.add(Chleb1, 0, 1);

			Check.add(tex5, 1, 0);
			Check.add(tex5n, 1, 1);

			Check.add(backCheck, 2, 2);
			Check.add(SearchCheck, 0, 2);

			Check.setAlignment(Pos.CENTER);

			st6.getChildren().addAll(mah6, Check);
			CheckTicket = new Scene(st6, screenSize.getWidth(), screenSize.getHeight());
			CheckTicket.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//========================================= Search Passengers================================
			StackPane st7 = new StackPane();
			Image mh7 = new Image("moha.jpg");
			ImageView mah7 = new ImageView(mh7);
			mah7.setFitHeight(1050);
			mah7.setFitWidth(1920);

			GridPane SearchPass = new GridPane();

			Label labe = new Label();
			labe.setText("Flight number :");
			labe.setPrefHeight(113);
			labe.setPrefWidth(227);
			labe.setFont(Font.font("Oranienbaum", 24));
			labe.setTextFill(Color.WHITE);
			SearchPass.setMargin(labe, new Insets(0, 0, 0, 130));

			Label lab2e = new Label();
			lab2e.setText("Ticket number :");
			lab2e.setPrefHeight(113);
			lab2e.setPrefWidth(227);
			lab2e.setFont(Font.font("Oranienbaum", 24));
			lab2e.setTextFill(Color.WHITE);
			SearchPass.setMargin(lab2e, new Insets(0, 0, 0, 130));

			Label lab3e = new Label();
			lab3e.setText("Full Name :");
			lab3e.setPrefHeight(113);
			lab3e.setPrefWidth(227);
			lab3e.setFont(Font.font("Oranienbaum", 24));
			lab3e.setTextFill(Color.WHITE);
			SearchPass.setMargin(lab3e, new Insets(0, 0, 0, 130));

			Label lab4e = new Label();
			lab4e.setText("Passport number :");
			lab4e.setPrefHeight(113);
			lab4e.setPrefWidth(227);
			lab4e.setFont(Font.font("Oranienbaum", 24));
			lab4e.setTextFill(Color.WHITE);
			SearchPass.setMargin(lab4e, new Insets(0, 0, 0, 130));

			Label come = new Label("Nationality :");
			come.setPrefHeight(113);
			come.setPrefWidth(227);
			come.setFont(Font.font("Oranienbaum", 24));
			come.setTextFill(Color.WHITE);
			SearchPass.setMargin(come, new Insets(0, 0, 0, 130));

			Label com1e = new Label("Birthdate :");
			com1e.setPrefHeight(113);
			com1e.setPrefWidth(227);
			com1e.setFont(Font.font("Oranienbaum", 24));
			com1e.setTextFill(Color.WHITE);
			SearchPass.setMargin(com1e, new Insets(0, 0, 0, 130));

			TextField texte = new TextField();
			texte.setPrefHeight(25);
			texte.setPrefWidth(195);
			texte.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

			TextField text1e = new TextField();
			text1e.setPrefHeight(25);
			text1e.setPrefWidth(195);
			text1e.setTextFormatter(new TextFormatter<>(new LongStringConverter()));

			TextField text2e = new TextField();
			text2e.setPrefHeight(25);
			text2e.setPrefWidth(195);

			TextField text3e = new TextField();
			text3e.setPrefHeight(25);
			text3e.setPrefWidth(195);

			TextField tte = new TextField();
			tte.setPrefHeight(25);
			tte.setPrefWidth(195);

			TextField tt1e = new TextField();
			tt1e.setPrefHeight(25);
			tt1e.setPrefWidth(195);

			texte.setEditable(false);
			text1e.setEditable(false);
			text3e.setEditable(false);
			tte.setEditable(false);
			tt1e.setEditable(false);

			Button SearchBack = new Button("Back", new ImageView("Back.png"));
			SearchBack.setPrefHeight(61);
			SearchBack.setPrefWidth(106);
			SearchBack.setTextFill(Color.BLACK);
			SearchBack.setFont(Font.font("Oranienbaum", 18));
			SearchBack.setContentDisplay(ContentDisplay.TOP);
			SearchBack.setId("button");
			SearchPass.setMargin(SearchBack, new Insets(30, 0, 0, 130));
			SearchBack.setOnAction(e -> {

				primaryStage.setScene(main);
			});

			Button SearchFind = new Button("Find", new ImageView("sar.png"));
			SearchFind.setPrefHeight(61);
			SearchFind.setPrefWidth(106);
			SearchFind.setTextFill(Color.BLACK);
			SearchFind.setFont(Font.font("Oranienbaum", 18));
			SearchFind.setContentDisplay(ContentDisplay.TOP);
			SearchFind.setId("button");
			SearchPass.setMargin(SearchFind, new Insets(30, 0, 0, 130));

			SearchFind.setOnAction(e -> {
				try {

					Node<Passengers> pass = passngerdata
							.searchm(new Passengers(0, 0, text2e.getText(), null, null, null));
					if (pass != null) {
						text1e.setText(String.valueOf(pass.getData().getNumticket()));
						text2e.setText(pass.getData().getFullname());
						texte.setText(String.valueOf(pass.getData().getNumflight()));
						text3e.setText(String.valueOf(pass.getData().getPassport()));
						tte.setText(pass.getData().getNationality());
						tt1e.setText(pass.getData().getData());

						Alert alert = new Alert(AlertType.NONE, "The Passengers has been Found", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
					} else {
						texte.setText("");
						text1e.setText("");
						text3e.setText("");
						tte.setText("");
						tt1e.setText("");
						Alert alert = new Alert(AlertType.NONE, "The Passengers does not Exiting", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}

					}

				} catch (NullPointerException m) {
					m.getMessage();
				}

			});

			SearchPass.add(lab3e, 0, 0);
			SearchPass.add(labe, 0, 1);
			SearchPass.add(lab2e, 0, 2);
			SearchPass.add(lab4e, 0, 3);
			SearchPass.add(come, 0, 4);
			SearchPass.add(com1e, 0, 5);

			SearchPass.add(text2e, 1, 0);
			SearchPass.add(texte, 1, 1);
			SearchPass.add(text1e, 1, 2);
			SearchPass.add(text3e, 1, 3);
			SearchPass.add(tte, 1, 4);
			SearchPass.add(tt1e, 1, 5);

			SearchPass.add(SearchBack, 2, 6);

			SearchPass.add(SearchFind, 0, 6);

			SearchPass.setAlignment(Pos.CENTER);

			st7.getChildren().addAll(mah7, SearchPass);
			SearchPassengers = new Scene(st7, screenSize.getWidth(), screenSize.getHeight());
			SearchPassengers.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//======================================================================================
			StackPane st8 = new StackPane();
			Image mh8 = new Image("moha.jpg");
			ImageView mah8 = new ImageView(mh8);
			mah8.setFitHeight(1050);
			mah8.setFitWidth(1920);

			GridPane Pane = new GridPane();

			Label lable1 = new Label("User Name :");
			lable1.setPrefHeight(113);
			lable1.setPrefWidth(227);
			lable1.setFont(Font.font("Oranienbaum", 20));
			lable1.setTextFill(Color.BLACK);

			Label lable2 = new Label("Password :");

			lable2.setPrefHeight(113);
			lable2.setPrefWidth(227);
			lable2.setFont(Font.font("Oranienbaum", 20));
			lable2.setTextFill(Color.BLACK);

			Label lable3 = new Label("Welcome to Palestine Airport ");
			lable3.setPrefHeight(113);
			lable3.setPrefWidth(350);
			lable3.setFont(Font.font("Oranienbaum", 24));
			lable3.setTextFill(Color.BLACK);
			Pane.setMargin(lable3, new Insets(0, 0, 0, 160));

			TextField Text1 = new TextField();
			Text1.setPrefHeight(25);
			Text1.setPrefWidth(195);
			Text1.setPromptText("admin");

			PasswordField pass = new PasswordField();
			pass.setPrefHeight(25);
			pass.setPrefWidth(195);
			pass.setPromptText("admin");

			Button But = new Button("Login", new ImageView("nmo.png"));
			But.setPrefHeight(61);
			But.setPrefWidth(106);
			But.setTextFill(Color.BLACK);

			But.setId("button");

			But.setFont(Font.font("Oranienbaum", 18));
			But.setContentDisplay(ContentDisplay.TOP);
			Pane.setMargin(But, new Insets(0, 0, 0, 300));
			But.setOnAction(e -> {

				if (Text1.getText().equals("admin") && pass.getText().equals("admin")) {
					primaryStage.setScene(main);
				}

			});

			Pane.add(lable3, 0, 0);
			Pane.add(lable1, 0, 2);
			Pane.add(lable2, 0, 3);

			Pane.add(Text1, 1, 2);
			Pane.add(pass, 1, 3);
			Pane.add(But, 0, 4);


			Pane.setAlignment(Pos.CENTER);
			st8.getChildren().addAll(mah8, Pane);
			MainPage = new Scene(st8, screenSize.getWidth(), screenSize.getHeight());
			MainPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//==================================   Read Data =========================
			StackPane st9 = new StackPane();
			Image mh9 = new Image("moha.jpg");
			ImageView mah9 = new ImageView(mh9);
			mah9.setFitHeight(1050);
			mah9.setFitWidth(1920);

			GridPane readdata = new GridPane();

			Button ReadPaas = new Button("Read Passengers", new ImageView("pass.png"));
			ReadPaas.setPrefHeight(100);
			ReadPaas.setPrefWidth(300);
			ReadPaas.setTextFill(Color.BLACK);
			ReadPaas.setOnAction(e -> {
				readpass(primaryStage);
			});

			ReadPaas.setDisable(true);
			Button ReadFligths = new Button("Read Flights", new ImageView("flights.png"));
			ReadFligths.setOnAction(e -> {

				readflight(primaryStage);
				ReadPaas.setDisable(false);
				if (flightdata.length() == 0) {
					res.setDisable(true);
				} else {
					res.setDisable(false);
				}

			});

			ReadFligths.setPrefHeight(100);
			ReadFligths.setPrefWidth(300);
			ReadFligths.setTextFill(Color.BLACK);
			readdata.setMargin(ReadFligths, new Insets(0, 0, 0, 150));

			Button BackRead = new Button("Back", new ImageView("Back.png"));
			BackRead.setPrefHeight(61);
			BackRead.setPrefWidth(106);
			BackRead.setTextFill(Color.BLACK);
			BackRead.setFont(Font.font("Oranienbaum", 18));
			BackRead.setContentDisplay(ContentDisplay.TOP);
			BackRead.setId("button");
			readdata.setMargin(BackRead, new Insets(30, 0, 0, 400));
			BackRead.setOnAction(e -> {
				primaryStage.setScene(main);
			});

			readdata.add(ReadPaas, 0, 1);
			readdata.add(ReadFligths, 1, 1);
			readdata.add(BackRead, 0, 2);

			readdata.setAlignment(Pos.CENTER);

			st9.getChildren().addAll(mah9, readdata);
			ReadData = new Scene(st9, screenSize.getWidth(), screenSize.getHeight());
			ReadData.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(MainPage);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Exit(Stage stage, GridPane root) {
		Alert al = new Alert(AlertType.CONFIRMATION);
		al.setTitle("Exit");
		al.setHeaderText("You're about to Exit");

		if (al.showAndWait().get() == ButtonType.OK) {
			stage = (Stage) root.getScene().getWindow();

			stage.close();

		}
	}

	@Override
	public void stop() throws Exception {
		Node<Flights> fli = flightdata.getHead();
		if (flightdata.length() == 0 || fli.getData().getPassdata().length() == 0) {
			Alert alert = new Alert(AlertType.NONE, "The data has not been stored", ButtonType.OK);
			if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
			}
			return;
		} else {
			savefligths();
			savepass();
			Alert alert = new Alert(AlertType.NONE, "The data has been stored", ButtonType.OK);
			if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
			}

		}
		super.stop();
	}

	
}