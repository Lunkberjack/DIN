module ProyectoSceneBuilder {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	
	opens model to javafx.graphics, javafx.fxml;
}
