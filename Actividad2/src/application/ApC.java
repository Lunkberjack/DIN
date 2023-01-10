package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class ApC extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Prueba ventana emergente");
			Button button = new Button("Emerge");
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
	                        dialog.initOwner(primaryStage);
	                        VBox vbox = new VBox();
	                        vbox.setAlignment(Pos.CENTER);
	                        
	                        // Muestra una etiqueta con texto en la nueva ventana
	                        vbox.getChildren().add(new Label("Soy una ventana emergente"));
	                        Scene dialogScene = new Scene(vbox, 300, 200);
	                        dialog.setScene(dialogScene);
	                        dialog.show();
	                    }
	                };

	        button.setOnAction(event);
	        tilepane.setAlignment(Pos.CENTER);
	        tilepane.getChildren().add(button);
	        
			primaryStage.setScene(new Scene(tilepane, 500, 500));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
