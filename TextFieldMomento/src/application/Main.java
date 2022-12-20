package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = new StackPane();
			TextField txt1 = new TextField();
			txt1.setMinWidth(300);
			txt1.setMaxWidth(300);
			txt1.setPromptText("Texto a mostrar");
			
			// Button bt1 = new Button();
			Button bt2 = new Button("Te saludo");
			root.getChildren().addAll(txt1, bt2);
			bt2.setOnAction(e->{
				txt1.setText("Ola");
			});
			// bt1.setAlignment(BOTTOM_RIGHT); 
			
			TableView<Alumno> tablaAlumnos = new TableView<Alumno>();
			TableColumn<Alumno,String> columnaNombre = new TableColumn<Alumno,String>("Nombre");
			TableColumn<Alumno,String> columnaApellidos = new TableColumn<Alumno,String>("Apellidos");
			TableColumn<Alumno,Integer> columnaEdad = new TableColumn<Alumno,Integer>("Edad");
			
			tablaAlumnos.getColumns().addAll(columnaNombre,columnaApellidos,columnaEdad);
			
			root.getChildren().add(tablaAlumnos);
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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