module LuciaLM_ActividadPeliculas {
	requires javafx.controls;
	// Cuando se trabaja con módulo se tiene que añadir este requires
	requires java.sql;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens model to javafx.graphics, javafx.fxml, javafx.base;
	opens view to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;
}
