module ProyectoSceneBuilder {
	requires javafx.controls;
	requires javafx.graphics;
	
	opens model to javafx.graphics, javafx.fxml;
}
