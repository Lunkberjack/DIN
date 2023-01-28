package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Se llama al fxml del paquete view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainLayout.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Judeau");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
