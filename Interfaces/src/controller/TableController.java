package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cliente;

public class TableController implements Initializable{
	
	int posicionTabla;
	
	private TableColumn<Cliente, String> columnaNombre;
	private TableColumn<Cliente, String> columnaApellido;
	private TableColumn<Cliente, String> columnaApellido2;
	
	@FXML
	private Button bt1;

	@FXML
	private Button bt2;
	
	@FXML
	private Button bt3;
	
	@FXML
	private TableView<Cliente> tablaClientes;
	private ObservableList <Cliente> listaClientes;
	
	@FXML
	private TextField campoNCliente;
	
	@FXML
	private TextField campoACliente;
	
	@FXML
	private TextField campoACliente2;
	
	@FXML
	private Label lbNombreCliente;
	
	@FXML
	private Label lbApellidoCliente;
	
	@FXML
	private Label lbApellidoCliente2;
	
	private final ListChangeListener<Cliente> selectorTablaCliente = new ListChangeListener<Cliente> () {
		public void onChanged(Change<? extends Cliente> arg0) {
			indicarFilaSeleccionada();
		}
	};
	
	private Cliente getTablaClienteSeleccionado() {
		if (tablaClientes != null) {
			List<Cliente> listaNueva = tablaClientes.getSelectionModel().getSelectedItems();
			
			if (listaNueva.size()== 1) {
				final Cliente seleccionado = listaNueva.get(0);
				return seleccionado;
			}
		}
		return null;
	}

	public void inicializar() {
		
		posicionTabla = 0;
		
		columnaNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
		columnaApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
		columnaApellido2.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido 2"));

		
	}
	
	private void indicarFilaSeleccionada() {
		final Cliente clienteSeleccionado = getTablaClienteSeleccionado();
		posicionTabla = listaClientes.indexOf(clienteSeleccionado);
		
		if (clienteSeleccionado != null) {
			campoNCliente.setText(clienteSeleccionado.getNombre());
			campoACliente.setText(clienteSeleccionado.getApellido());
			campoACliente2.setText(clienteSeleccionado.getApellido2());

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		inicializar();
		
		final ObservableList<Cliente> tablaClienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItems();
		
	}
	

}