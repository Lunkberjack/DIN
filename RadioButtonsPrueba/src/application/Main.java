package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class Main extends Application {
	RadioButton rbM; // RadioButton aux
	
	@Override
	public void start(Stage primaryStage) {
		try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 200, 200);
            VBox vbox = new VBox();
            RadioButton rb1 = new RadioButton("Radio button 1");
            RadioButton rb2 = new RadioButton("Radio button 2");
            ToggleGroup tg = new ToggleGroup();
            
            vbox.setAlignment(Pos.CENTER);
            
            rb1.setToggleGroup(tg);
            rb2.setToggleGroup(tg);

            Label lb = new Label("");
            Button btn = new Button("Cambiar label");

            root.setLeft(vbox);
            vbox.getChildren().addAll(rb1, rb2, lb, btn);

          
            tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                    rbM = (RadioButton) tg.getSelectedToggle();
                    // lb.setText(rbM.getText()); // sin usar el botón (auto)
                }
            });
            
//          Pulsando el botón
//            btn.setOnAction(new EventHandler<ActionEvent>() {
//                @Override public void handle(ActionEvent e) {
//                    lb.setText(rbM.getText());
//                }
//            });
            
//          Expresión lambda
            btn.setOnAction(actionEvent -> 
            	lb.setText(rbM.getText())
            );
            
            primaryStage.setTitle("ExamenDIN");
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
