package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import model.Pelicula;
/**
 * Aplicación que consta de una interfaz gráfica para manejar una colección
 * de películas (por ejemplo, un videoclub).
 * 
 * Permite crear películas al introducir un título, un año y un género, limpiar
 * la interfaz y mostrar al usuario dicha colección de películas.
 * 
 * Se sigue el modelo MVC: la clase Película usada se importa desde el paquete model.
 * 
 * @author LuciaLM
 */
public class Main extends Application {
	ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
	// RadioButton auxiliar para poder recuperar el valor seleccionado
	RadioButton aux = new RadioButton();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			// Se usa un GridPane y se trabaja con los índices de fila y columna
			// add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan)
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(16);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 20, 20, 20));
			
			// Título ---------------------------------------------
			grid.add(new Label("Título:"), 0, 0);
			TextField tituloTF = new TextField();
			grid.add(tituloTF, 1, 0, 1, 1);

			// Año ------------------------------------------------
			grid.add(new Label("Año:"), 0, 1);
			TextField anioTF = new TextField();
			grid.add(anioTF, 1, 1, 1, 1);
			
			// Género ---------------------------------------------
			grid.add(new Label("Género:"), 0, 2);
			
            ToggleGroup toggleGenero = new ToggleGroup();
            RadioButton accion = new RadioButton("Acción");
            RadioButton comedia = new RadioButton("Comedia");
            RadioButton cienciaFiccion = new RadioButton("Ciencia ficción");
            RadioButton otros = new RadioButton("Otros");
            
            accion.setToggleGroup(toggleGenero);
            comedia.setToggleGroup(toggleGenero);
            cienciaFiccion.setToggleGroup(toggleGenero);
            otros.setToggleGroup(toggleGenero);
            
            // Se ejecuta cada vez que se pulse un RadioButton
            toggleGenero.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                    aux = (RadioButton) toggleGenero.getSelectedToggle();
                }
            });
            
            grid.add(accion, 1, 2, 2, 1);
            grid.add(comedia, 1, 3, 2, 1);
            grid.add(cienciaFiccion, 1, 4, 2, 1);
            grid.add(otros, 1, 5, 2, 1);
			
            // Resultado -----------------------------------------
			TextArea resultado = new TextArea();
			resultado.setWrapText(true); // Para que no aparezcan las molestas barras de scroll
			grid.add(resultado, 3, 0, 2, 8);
			
			// Botones -------------------------------------------
			HBox botones = new HBox();
			botones.setSpacing(60);
			
			// Botón limpiar
			Button btnLimpiar = new Button("Limpiar");
			
			EventHandler<ActionEvent> limpiar = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					tituloTF.setText("");
					anioTF.setText("");
					toggleGenero.getSelectedToggle().setSelected(false);
				}
			};
			
			btnLimpiar.setOnAction(limpiar);
            botones.getChildren().add(btnLimpiar);
            
			// Botón crear
			Button btnCrear = new Button("Crear película");
			EventHandler<ActionEvent> crear = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					// Se asegura de que se introduzcan todos los campos para crear
					// la película.
					if(!tituloTF.getText().equals("") && !anioTF.getText().equals("")
							&& !aux.getText().equals("")) {
						peliculas.add(new Pelicula(tituloTF.getText(), anioTF.getText(), aux.getText()));
						tituloTF.setText("");
						anioTF.setText("");
						toggleGenero.getSelectedToggle().setSelected(false);
					} else {
						System.out.println("Introduce correctamente todos los datos de la película");
					}
					// Almacenará todas las películas en el "videoclub".
					StringBuilder res = new StringBuilder();
					// Almacena las películas en res y las muestra al pulsar el botón.
					for(Pelicula x : peliculas) {
						res.append(x.toString());
					}
					resultado.setText(res.toString());
				}
			};
			btnCrear.setOnAction(crear);
			botones.getChildren().add(btnCrear);
			
			// Propiedades GridPane y Stage 
			grid.add(botones, 0, 7, 3, 1);
			grid.setAlignment(Pos.CENTER);

			primaryStage.setTitle("Examen Unidad 2 - Lucía León Milán");
			primaryStage.setScene(new Scene(grid, 500, 280));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}