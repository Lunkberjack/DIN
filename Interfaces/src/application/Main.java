package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Main extends Application {
	private int cont;
	@Override
	public void start(Stage primaryStage) throws Exception{
		Button miBoton=new Button("AYUDA");
		miBoton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Se ha pulsado " + cont++ + " veces.");
			}
		});
		//Se implementa la estructura de la ventana
		StackPane raiz=new StackPane();
		raiz.getChildren().add(miBoton);
		Scene miEscena=new Scene(raiz,500,300);
		primaryStage.setScene(miEscena);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
