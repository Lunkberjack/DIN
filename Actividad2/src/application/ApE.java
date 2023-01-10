package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ApE extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			
			// add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan)
			Label nombreJugador = new Label("Nombre del jugador:");
			grid.add(nombreJugador, 0, 1);

			TextField nombreJugadorT = new TextField();
			grid.add(nombreJugadorT, 1, 1, 4, 1);

			Label equipo = new Label("Equipo en el que juega:");
			grid.add(equipo, 0, 2);
			
			TextField equipoT = new TextField();
			grid.add(equipoT, 1, 2, 4, 1);
			
			Label promedio = new Label("Promedio de puntos:");
			grid.add(promedio, 0, 3);
			
			TextField promedioT = new TextField();
			promedioT.setMaxWidth(75);
			grid.add(promedioT, 1, 3);
			
			primaryStage.setTitle("Aplicación TextField");
			Button button = new Button("Mostrar");
			button.setAlignment(Pos.BOTTOM_RIGHT); // El botón no se alinea
			grid.add(button, 3, 4);
			EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					// Se crea un nuevo escenario
					final Stage dialog = new Stage();

					// Se añade la modalidad y más propiedades
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);
					VBox vbox = new VBox();
					vbox.setAlignment(Pos.CENTER);

					// Muestra etiquetas con la información introducida
					vbox.getChildren().add(new Label("Nombre del jugador: " + nombreJugadorT.getText()));
					vbox.getChildren().add(new Label("Equipo: " + equipoT.getText()));
					vbox.getChildren().add(new Label("Promedio: " + promedioT.getText()));
					
					Scene dialogScene = new Scene(vbox, 300, 200);
					dialog.setScene(dialogScene);
					dialog.show();
				}
			};
			button.setOnAction(event);
			grid.setAlignment(Pos.CENTER);

			primaryStage.setScene(new Scene(grid, 500, 250));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}