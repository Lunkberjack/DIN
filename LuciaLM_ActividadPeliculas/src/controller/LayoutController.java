package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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

/**
 * Clase con la lógica que controla tanto la adición como la modificación 
 * y eliminación de los datos del Videoclub.
 * @author lucia
 *
 */
public class LayoutController implements Initializable {
	private ConnectionController conexion = new ConnectionController();

	private MetodosSQL metodos;

	private FileInputStream pasarBlob;

	private File foto;

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

	/**
	 * Añade una película tanto a la base de datos como a la lista observable.
	 * 
	 * Usa el método crearPelicula() para dividir la funcionalidad y hacer el código más
	 * manejable.
	 * 
	 * @throws SQLException
	 */
	void aniadir() throws SQLException {		
		Pelicula peli = crearPelicula();
		listaPeliculas.add(peli);

		metodos.aniadirPelicula(Pelicula.getContadorPeliculas(), textTitulo.getText(), textGenero.getText(),
				textDuracion.getText(), textSinopsis.getText(), textPais.getText(), textIdioma.getText(),
				textActores.getText(), readFile(foto));
	}

	/**
	 * Permite modificar los datos de la película sobre la que tengamos la selección.
	 * 
	 * Se vale del método modificar() de la clase MetodosSQL, que ejecuta un UPDATE de
	 * todos los campos para que así se pueda editar fácilmente cualquier dato.
	 * 
	 * @param peliculaSeleccionada
	 * @throws SQLException
	 */
	void editar(Pelicula peliculaSeleccionada) throws SQLException {		
		metodos.modificar(peliculaSeleccionada.getId(), textTitulo.getText(), textGenero.getText(), textDuracion.getText(), textSinopsis.getText(),
				textPais.getText(), textIdioma.getText(), textActores.getText(), readFile(foto));
		// Actualiza los cambios que se hayan realizado en alguna de las películas.
		actualizar();
	}

	/**
	 * Elimina la película sobre la que esté el foco o selección en ese momento.
	 * La elimina tanto de la lista como de la base de datos.
	 * 
	 * @throws SQLException
	 */
	private void borrar() throws SQLException {
		Pelicula aBorrar = getTablaPeliculaSeleccionada();
		listaPeliculas.remove(aBorrar);
		metodos.eliminar(aBorrar.getId());
	}

	/**
	 * Crea una película añadiendo todos los datos que se hayan proporcionado
	 * en los TextField de la interfaz.
	 */
	private Pelicula crearPelicula() {
		return new Pelicula(textTitulo.getText(), textGenero.getText(), textDuracion.getText(),
				textSinopsis.getText(), textPais.getText(), textIdioma.getText(),
				textActores.getText(), readFile(foto));
	}
	
	/**
	 * Borra la lista y vuelve a traerla de la base de datos, esta vez con las
	 * modificaciones que se hayan realizado.
	 * 
	 * Permite que la aplicación sea dinámica, y se ejecuta al editar o modificar
	 * una película seleccionada en la interfaz.
	 * 
	 * @throws SQLException
	 */
	public void actualizar() throws SQLException {
		listaPeliculas.clear(); // Elimina todos los elementos de la lista.
		
		ResultSet rs = metodos.recibirTabla();
		while (rs.next()) {
			Pelicula peli = new Pelicula(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBytes(9));
		    listaPeliculas.add(peli);
		}
	}

	// Se ejecuta cada vez que la película seleccionada en la tabla cambia.
	private final ListChangeListener<Pelicula> selectorTabla = new ListChangeListener<Pelicula>(){
		@Override
		public void onChanged(Change<? extends Pelicula> arg0) {
			indicarFilaSeleccionada();
		}
	};

	/**
	 * Crea una nueva lista con solo un elemento, que devuelve la Película que está actualmente
	 * seleccionada en la vista de tabla (ObservableList).
	 * 
	 * @return la película seleccionada
	 */
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

	/**
	 * Haciendo uso del método getTablaPeliculaSeleccionada() para saber la película
	 * en la que está el foco, establece todos los TextField de la interfaz para que
	 * coincidan con los campos de esta película (y sea mucho más fácil editarla).
	 */
	private void indicarFilaSeleccionada() {
		final Pelicula peliculaSeleccionada = getTablaPeliculaSeleccionada();
		if (peliculaSeleccionada != null) {
			textTitulo.setText(peliculaSeleccionada.getTitulo());
			textGenero.setText(peliculaSeleccionada.getGenero());
			textDuracion.setText(peliculaSeleccionada.getDuracion());
			textSinopsis.setText(peliculaSeleccionada.getSinopsis());
			textPais.setText(peliculaSeleccionada.getPais());
			textIdioma.setText(peliculaSeleccionada.getIdioma());
			textActores.setText(peliculaSeleccionada.getActores());

			if(peliculaSeleccionada.getImagen() != null) {
				Image img = new Image(new ByteArrayInputStream(peliculaSeleccionada.getImagen()));
				imagenMostrar.setImage(img);
			}
		}
	}

	@FXML
	void click(ActionEvent event) {
		// Se ha pulsado el botón añadir (+)
		if(event.getSource().equals(btnAniadir)) {
			try {
				aniadir();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		// Se ha pulsado editar (bolígrafo)
		} else if(event.getSource().equals(btnEditar)) {
			if(getTablaPeliculaSeleccionada() != null) {
				try {
					editar(getTablaPeliculaSeleccionada());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("ninguna seleccionada");
			}
		// Se ha pulsado eliminar (X)
		} else if(event.getSource().equals(btnBorrar)) {
			try {
				borrar();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		// Se abre el explorador de archivos y se elige foto (se ha pulsado en Subir).
		} else if(event.getSource().equals(fotoChooser)) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Elegir imagen");
			// Los filtros que permitirá aplicar para buscar imágenes.
			fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png")
			);
			// El escenario donde aparecerá el diálogo para seleccionar
			// un archivo de imagen:
			Stage stage = (Stage) ap.getScene().getWindow();

			foto = fileChooser.showOpenDialog(stage);

			// Si se ha subido algún archivo:
			if (foto != null) {
				try {
					pasarBlob = new FileInputStream(foto);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				// Se muestra la imagen recién subida.
				imagenMostrar.setImage(new Image(pasarBlob));
			}
		}
	}

	/**
	 * Convierte un archivo de imagen, en este caso, en un array de bytes.
	 * @param file
	 * @return array de bytes
	 */
	private byte[] readFile(File file) {
		ByteArrayOutputStream bos = null;
		if(file != null) {
			try {
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				bos = new ByteArrayOutputStream();
				for (int len; (len = fis.read(buffer)) != -1;) {
					bos.write(buffer, 0, len);
				}
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			} catch (IOException e2) {
				System.err.println(e2.getMessage());
			}
		}
		// Si el ByteArrayInputStream no es null, lo devuelve.
		// En otro caso, devuelve null.
		return bos != null ? bos.toByteArray() : null;
	}
	
	/**
	 * Todas las funciones que se ejecutan cada vez que se inicia la aplicación.
	 * 
	 * Se encarga de crear una tabla de cero, muestra una selección de columnas y
	 * crea una lista Observable, para que podamos visualizar lo que le ocurre al añadir
	 * y modificar elementos de la misma.
	 * 
	 * @throws SQLException
	 */
	private void inicializar() throws SQLException {
		conexion.conectar("jdbc:sqlite:/home/lucia/videoclub.db");
		metodos = new MetodosSQL(conexion.getConexion());
		listaPeliculas = FXCollections.observableArrayList();
		metodos.tablaDeCero(); // Cada vez que iniciamos la aplicación tenemos un nuevo videoclub
		
		colTitulo.setCellValueFactory(new PropertyValueFactory<Pelicula, String>("titulo"));
		colGenero.setCellValueFactory(new PropertyValueFactory<Pelicula, String>("genero"));
		colPais.setCellValueFactory(new PropertyValueFactory<Pelicula, String>("pais"));
		
		tabla.setItems(listaPeliculas);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			inicializar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		final ObservableList<Pelicula> tablaClienteSeleccionado = tabla.getSelectionModel().getSelectedItems();
		tablaClienteSeleccionado.addListener(selectorTabla);
	}
}