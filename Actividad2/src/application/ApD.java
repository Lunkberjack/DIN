package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ApD extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			stage.setTitle("Diálogo");
			Button button = new Button("Diálogo");
			TilePane tilepane = new TilePane();

			EventHandler<ActionEvent> event = 
					new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e)
				{
					// Se crea un nuevo escenario
					final Stage dialog = new Stage();

					// Define una ventana modal que bloquea el envío de
					// eventos a otra ventana de aplicación.
					dialog.initModality(Modality.APPLICATION_MODAL);

					// Se añade el propietario de la nueva ventana
					dialog.initOwner(stage);
					HBox hbox = new HBox();
					hbox.setAlignment(Pos.CENTER);

					// Muestra una etiqueta con texto en la nueva ventana
					hbox.getChildren().add(new Button("Cancelar"));
					hbox.getChildren().add(new Button("Aceptar"));
					Scene dialogScene = new Scene(hbox, 300, 200);
					dialog.setScene(dialogScene);
					dialog.show();
				}
			};
			button.setOnAction(event);
			tilepane.setAlignment(Pos.CENTER);
			tilepane.getChildren().add(button);

			stage.setScene(new Scene(tilepane, 500, 500));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}