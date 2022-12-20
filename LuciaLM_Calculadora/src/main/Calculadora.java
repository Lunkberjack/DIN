package main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Calculadora básica implementada en Java, usando el software
 * JavaFX para el diseño de la interfaz de usuario.
 * 
 * Permite sumar, restar, multiplicar y dividir números enteros,
 * además de limpiar la pantalla (vuelve al valor por defecto, 0).
 * 
 * (!!!) Solo permite un tipo de operación a la vez.
 * 
 * @author LuciaLM
 */
public class Calculadora extends Application {
	private final int ANCHURA = 480;
	private final int ALTURA = 400;

	// Declarados fuera del método start() por si debemos
	// acceder a ellos desde otro método.
	private Label texto;
	private VBox root;
	private Scene scene;

	@Override
	public void start(Stage primaryStage) {
		try {
			root = new VBox();
			scene = new Scene(root,ANCHURA,ALTURA);
			primaryStage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
			
			// Menú con el nombre de la autora
	        MenuBar menu = new MenuBar();
	        Menu menuAutor = new Menu("Autor");
	        MenuItem nombre = new MenuItem("Lucía León Milán");
	        
	        menuAutor.getItems().add(nombre);
	        menu.getMenus().add(menuAutor);
	        root.getChildren().add(menu);
	        
			// Label donde se mostrarán las operaciones y resultados
			texto = new Label();
			texto.setText("0"); // Valor 0 por defecto
			texto.setAlignment(Pos.CENTER_RIGHT);
			texto.setPadding(new Insets(ANCHURA/20));
			texto.setPrefSize(ANCHURA, ALTURA/6);
			texto.setStyle("-fx-font-size: 20px");
			root.getChildren().add(texto);

			// "Tabla" de botones
			this.crearFila(root, new String[] {"1", "2", "3"});
			this.crearFila(root, new String[] {"4", "5", "6"});
			this.crearFila(root, new String[] {"7", "8", "9"});
			this.crearFila(root, new String[] {"C", "0", "Borrar"});
			this.crearFila(root, new String[] {"+", "-", "x", "%", "="});

			primaryStage.setTitle("Mi primera calculadora");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Crea cada fila de manera dinámica (flexibilidad).
	 * 
	 * @param root
	 * @param btns
	 */
	private void crearFila(VBox root, String[] btns) {
		HBox fila = new HBox();
		Button[] btnsAniadir = new Button[btns.length];

		// Permite usar el mismo método para crear filas 
		// con un número de botones variable
		for(int i = 0; i < btns.length; i++) {
			Button btn = new Button(btns[i]);
			this.formatearBoton(btn, btns, this.texto);
			btnsAniadir[i] = btn;
		}
		fila.getChildren().addAll(btnsAniadir);
		root.getChildren().add(fila);
	}

	/**
	 * Cualquier cambio que queramos hacer a todos los botones se añadirá
	 * aquí (se escribe solo una vez, ayuda al mantenimiento del código)
	 * 
	 * @param btn
	 * @param btns
	 */
	private void formatearBoton(Button btn, String[] btns, Label text) {
		btn.setPrefWidth(Math.ceil(ANCHURA/btns.length));
		btn.setPrefHeight(ALTURA/5);

		if(btn.getText().equals("=")) {
			btn.setStyle("-fx-font-size: 30px;");

			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					String operacion = text.getText();
					// Si el primer dígito es efectivamente un dígito y no un operador:
					if(Character.isDigit(operacion.charAt(0))) {
						// Si se encuentra que la operación a realizar es una suma:
						if(operacion.indexOf('+') != -1) {
							// Muy importantes las \\, '+' no se reconoce como carácter 
							// si no se añaden.
							String[] cadena = operacion.split("\\+");
							int[] operandos = new int[cadena.length];
							int suma = Integer.valueOf(cadena[0]); // Todo se aplica al primer operando

							// Y se aplica a partir del siguiente.
							for(int i = 1; i < operandos.length; i++) {
								operandos[i] = Integer.valueOf(cadena[i]);
								suma += operandos[i];
							}

							text.setText("" + suma);
							suma = 0;
							// Si se encuentra que es una resta:
						} else if(operacion.indexOf("-") != -1) {
							String[] cadena = operacion.split("-");
							int[] operandos = new int[cadena.length];
							int resta = Integer.valueOf(cadena[0]);

							for(int i = 1; i < operandos.length; i++) {
								operandos[i] = Integer.valueOf(cadena[i]);
								resta -= operandos[i];
								System.out.println(resta);
							}
							text.setText("" + resta);
							// Si se encuentra que es una multiplicación:
						} else if(operacion.indexOf("x") != -1) {
							String[] cadena = operacion.split("x");
							int[] operandos = new int[cadena.length];
							int mult = Integer.valueOf(cadena[0]);

							for(int i = 1; i < cadena.length; i++) {
								operandos[i] = Integer.valueOf(cadena[i]);
								mult *= operandos[i];
							}
							text.setText("" + mult);
							// Si se encuentra que es una división
						} else if(operacion.indexOf("%") != -1) {
							boolean cero = false;
							String[] cadena = operacion.split("%");
							int[] operandos = new int[cadena.length];
							
							operandos[0] = Integer.valueOf(cadena[0]); // Para que lo compruebe el for que busca ceros.
							int div = operandos[0];
							System.out.println(div);
							
							for(int i = 1; i < operandos.length; i++) {
								// Por si alguien intenta introducir un double
								if(cadena[i].equals("0") || cadena[i].equals("0.0")) {
									cero = true;
								} else {
										operandos[i] = Integer.valueOf(cadena[i]);
										div /= operandos[i];
								}
								text.setText(cero ? "No se puede dividir por cero" : ("" + div));
							}
						} else {
							text.setText("Sintaxis inválida");
						}
					}
				}
			});
			// Elimina todo el contenido del Label.
		} else if(btn.getText().equals("C")) {
			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					text.setText("0");
				}
			});
			// Elimina el último carácter introducido.
		} else if(btn.getText().equals("Borrar")) {
			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					// No puede borrar si no hay ningún operando.
					if(text.getText().length() > 1) {
						String borrado = text.getText().substring(0, text.getText().length() - 1);
						text.setText(borrado);
						// Pone el valor por defecto si, aun así, se intenta.
					} else {
						text.setText("0");
					}
				}
			});
			// Caso de todos los demás botones: imprimen texto en la pantalla.
		} else {
			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					if(text.getText().equals("0")) {
						text.setText(btn.getText());
					} else {
						text.setText(text.getText() + btn.getText());
					}
				}
			});
		}
	}
}