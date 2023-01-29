package application;
	
import controller.ConnectionController;
import controller.MetodosSQL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

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
		
		ConnectionController conexion = new ConnectionController();
		// Conectar a SQLite con el Driver y la cadena de conexión:
		if(conexion.conectar(
				"jdbc:sqlite:/home/lucia/videoclub.db")) {
			MetodosSQL metodos = new MetodosSQL(conexion.getConexion());
			
			System.out.println("me he conectao");
			// Ir descomentando para probar:
			// metodos.aniadirFabricante(7, "Subaru");
			// metodos.aniadirCoche(7, "Forester", 20000, 2020, 4000, 7);
			// Se pueden ir modificando con distintas combinaciones de parámetros.
			// metodos.modificarRegistro("coche", "modelo", "Outback", "modelo", "Forester");
			// metodos.modificarRegistro("coche", "km", 45, "km", 500);
			// Descomentar solo un eliminar a la vez, las foreign key no están configuradas ON DELETE CASCADE.
			// metodos.eliminarRegistro("coche", "modelo", "Outback");
			//metodos.eliminarRegistro("fabricante", "id", 7);
			//metodos.mostrarRegistrosSinFiltro("fabricante", "id", "asc");
			// metodos.mostrarRegistrosFiltrando("coche", "id", "Prius", "id", "asc");
		} else {
			conexion.desconectar();
			System.out.println("Problema al conectar");
		}
	}
}
