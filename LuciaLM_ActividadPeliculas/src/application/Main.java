package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Punto de partida de la aplicación.
 * 
 * @author LuciaLM
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Layout.fxml"));
	        AnchorPane root = loader.load();
	        Scene scene = new Scene(root);
	        
	        primaryStage.setTitle("Videoclub");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
