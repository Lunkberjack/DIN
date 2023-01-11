package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main {
	public static void start(Stage primaryStage) {
	BorderPane root = new BorderPane();

    Scene scene = new Scene(root, 600, 768);

    VBox vbox = new VBox();

    RadioButton rb1 = new RadioButton("Radio button 1");
    RadioButton rb2 = new RadioButton("Radio button 2");

    ToggleGroup tg = new ToggleGroup();

    rb1.setToggleGroup(tg);
    rb2.setToggleGroup(tg);

    Label lb = new Label("Label en blanco");
    Button btn = new Button("Cambiar label");

    root.setLeft(vbox);
    vbox.getChildren().addAll(rb1, rb2, lb, btn);

    tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
        @Override
        public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {

            RadioButton rbM = (RadioButton) tg.getSelectedToggle();
            lb.setText(rbM.getText());
        }
    });

    primaryStage.setTitle("ExamenDIN");
    primaryStage.setScene(scene);
    primaryStage.show();
	}

}