package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Pelicula;

public class LayoutController implements Initializable {
	private FileInputStream pasarBlob;
	
	@FXML 
	private AnchorPane ap;

	@FXML
	private Button fotoChooser;

	@FXML
	private Button btnAniadir;

	@FXML
	private Button btnBorrar;

	@FXML
	private Button btnEditar;

	@FXML
	private TextField textActores;

	@FXML
	private TextField textDuracion;

	@FXML
	private TextField textFoto;

	@FXML
	private TextField textGenero;

	@FXML
	private TextField textIdioma;

	@FXML
	private TextField textPais;

	@FXML
	private TextField textSinopsis;

	@FXML
	private TextField textTitulo;

	@FXML
	private TableView<Pelicula> tabla;

	@FXML
	private TableColumn<Pelicula, String> colTitulo;

	@FXML
	private TableColumn<Pelicula, String> colGenero;

	@FXML
	private TableColumn<Pelicula, String> colPais;

	@FXML
	private ImageView imagenMostrar;
	
	private ObservableList<Pelicula> listaPeliculas;
	private int posicionTabla;

	@FXML
	void aniadir() {
		Pelicula peli = crearPelicula();
		//limpiar();
		listaPeliculas.add(peli);
	}

	@FXML
	void editar(ActionEvent event) {
		Pelicula peli = crearPelicula();
		//limpiar();
		listaPeliculas.set(posicionTabla, peli);
	}

	@FXML
	private void borrar() {
		listaPeliculas.remove(posicionTabla);
	}

	private Pelicula crearPelicula() {
		if(pasarBlob != null) {
			return new Pelicula(textTitulo.getText(), textGenero.getText(), textDuracion.getText(),
				textSinopsis.getText(), textPais.getText(), textIdioma.getText(),
				textActores.getText(), pasarBlob);
		} else {
			// Cambiar por un alert cuando se implemente la validación
			return null;
		}

	}

	//    /*
	//    * Metodo para inicializar la posición de la tabla y también
	//    para inicializar la tabla.
	//    * No es necesario en este metodo crear las columnas ya que han
	//    sido creadas en SceneBuilder
	//    */
	private void Inicializar()
	{
		posicionTabla = 0;
		colTitulo.setCellValueFactory(new PropertyValueFactory<Pelicula, String>("titulo"));
		colGenero.setCellValueFactory(new PropertyValueFactory<Pelicula, String>("genero"));
		colPais.setCellValueFactory(new PropertyValueFactory<Pelicula, String>("pais"));
		listaPeliculas = FXCollections.observableArrayList();
		tabla.setItems(listaPeliculas);

	}
	//    IMPLEMENTAR OTRO BOTÓN
	//    void limpiar()
	//    {
	//    txtNombre.setText("");
	//    txtPrimerApellido.setText("");
	//    txtSegundoApellido.setText("");
	//    }
	//    
	/*
	 * Cuando se selecciona una fila de la tabla se ejecuta este me-
    todo
	 */
	private final ListChangeListener<Pelicula> selectorTabla = new ListChangeListener<Pelicula>(){
		@Override
		public void onChanged(Change<? extends Pelicula> arg0) {
			indicarFilaSeleccionada();
		}
	};


	private Pelicula getTablaPeliculaSeleccionada() {
		if(tabla != null) {
			List<Pelicula> listaNueva = tabla.getSelectionModel().getSelectedItems();
			if(listaNueva.size() == 1) {
				// Escoge la película seleccionada de una lista de un solo elemento
				final Pelicula seleccionado = listaNueva.get(0);
				return seleccionado;
			}
		}
		return null;
	}

	/*
	 * Es un método que permite escribir en los campos de texto
	 * el nombre, primer apellido y segundo apellido del cliente
	 * que se ha seleccionado en la tabla
	 */
	private void indicarFilaSeleccionada() {
		final Pelicula peliculaSeleccionada = getTablaPeliculaSeleccionada();
		posicionTabla = listaPeliculas.indexOf(peliculaSeleccionada);
		if (peliculaSeleccionada != null) {
			textTitulo.setText(peliculaSeleccionada.getTitulo());
			textGenero.setText(peliculaSeleccionada.getGenero());
			textDuracion.setText(peliculaSeleccionada.getDuracion());
			textSinopsis.setText(peliculaSeleccionada.getSinopsis());
			textPais.setText(peliculaSeleccionada.getPais());
			textIdioma.setText(peliculaSeleccionada.getIdioma());
			textActores.setText(peliculaSeleccionada.getActores());
			InputStream ke = (InputStream)peliculaSeleccionada.getImagen();
            imagenMostrar.setImage(new Image(ke));
			// 
		}
	}


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Inicializar();
		final ObservableList<Pelicula> tablaClienteSeleccionado = tabla.getSelectionModel().getSelectedItems();
		tablaClienteSeleccionado.addListener(selectorTabla);
	}

	@FXML
	void click(ActionEvent event) throws IOException {
		if(event.getSource().equals(btnAniadir)) {
			System.out.println("pulsao");
			aniadir();
		} else if(event.getSource().equals(btnEditar)) {

		} else if(event.getSource().equals(btnBorrar)) {

		} else if(event.getSource().equals(fotoChooser)) {
			System.out.println("fotoChooser");

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Elegir imagen");
			// Los filtros que permitirá aplicar para buscar imágenes
			fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png")
			);

			Stage stage = (Stage) ap.getScene().getWindow();
			File foto = fileChooser.showOpenDialog(stage);
			
            if (foto != null) {
                pasarBlob = new FileInputStream(foto);
                imagenMostrar.setImage(new Image(pasarBlob));
            }
            pasarBlob.close();
		}
		
	}
}
