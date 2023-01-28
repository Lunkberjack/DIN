package controller;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cliente;

public class ClienteController implements Initializable{

	 	@FXML
	    private Button btnAnyadirUsuario;

	    @FXML
	    private Button btnEditarUsuario;

	    @FXML
	    private Button btnEliminarUsuario;

	    @FXML
	    private TableColumn<Cliente, String> columnaNombre;

	    @FXML
	    private TableColumn<Cliente, String> columnaPrimerApellido;

	    @FXML
	    private TableColumn<Cliente, String> columnaSegundoApellido;

	    @FXML
	    private Label labelApellido1;

	    @FXML
	    private Label labelApellido2;

	    @FXML
	    private Label labelNombre;

	    @FXML
	    private TableView<Cliente> tablaUsuarios;

	    @FXML
	    private TextField txtNombre;

	    @FXML
	    private TextField txtPrimerApellido;

	    @FXML
	    private TextField txtSegundoApellido;
    
    private ObservableList<Cliente> listaClientes;//Es una lista usado para introducir los clientes en la tabla
    
    private int posicionTabla;

    @FXML
    void AnyadirUsuario(ActionEvent event)
    {
    	Cliente c1 =  CrearCliente();
    	LimpiarCampos();
    	
    	listaClientes.add(c1);
    }

    @FXML
    void EditarUsuario(ActionEvent event)
    {
    	Cliente c1 =  CrearCliente();
    	LimpiarCampos();
    	listaClientes.set(posicionTabla, c1);
    }

    @FXML
    private void EliminarUsuario(ActionEvent event)
    {
    	listaClientes.remove(posicionTabla);
    }
    
    
    private Cliente CrearCliente()
    {
    	Cliente c2 = new Cliente();
    	c2.setNombre(txtNombre.getText());
    	c2.setApellido1(txtPrimerApellido.getText());
    	c2.setApellido2(txtSegundoApellido.getText());
    	return c2;
    }
    
    /*
     * Metodo para inicializar la posici�n de la tabla y tambi�n para inicializar la tabla.
     * No es necesario en este metodo crear las columnas ya que han sido creadas en SceneBuilder
     */
    private void Inicializar()
    {
    	posicionTabla = 0;
    	
    	columnaNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
    	columnaPrimerApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido1"));
    	columnaSegundoApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido2"));
    	
    	listaClientes = FXCollections.observableArrayList();
    	tablaUsuarios.setItems(listaClientes);
    }
    
    void LimpiarCampos()
    {
    	txtNombre.setText("");
    	txtPrimerApellido.setText("");
    	txtSegundoApellido.setText("");
    }
    
    /*
     * Cuando se selecciona una fila de la tabla se ejecuta este metodo
     */
    private final ListChangeListener<Cliente> selectorTablaCliente =
    		new ListChangeListener<Cliente>()
    		{
				@Override
				public void onChanged(Change<? extends Cliente> arg0) {
					// TODO Auto-generated method stub
					indicarFilaSeleccionada();
				}
    		};
    
    /*
     * Se construye un cliente a partir de la fila selecionada en la tabla
    */
    private Cliente getTablaClienteSeleccionado()
    {
    	if(tablaUsuarios != null)
    	{
    		List<Cliente> listaNueva = tablaUsuarios.getSelectionModel().getSelectedItems();
    		if(listaNueva.size() == 1)
    		{
    			final Cliente seleccionado = listaNueva.get(0);
    			return seleccionado;
    		}
    	}
    	return null;
    }
    
    /*
     * Es un m�todo que permite escribir en los campos de texto
     * el nombre, primer apellido y segundo apellido del cliente 
     * que se ha seleccionado en la tabla
     */
    private void indicarFilaSeleccionada()
    {
    	final Cliente clienteSeleccionado = getTablaClienteSeleccionado();
    	posicionTabla = listaClientes.indexOf(clienteSeleccionado);
    			
    	if (clienteSeleccionado != null)
    	{
    		txtNombre.setText(clienteSeleccionado.getNombre());
        	txtPrimerApellido.setText(clienteSeleccionado.getApellido1());
        	txtSegundoApellido.setText(clienteSeleccionado.getApellido2());
    	}		
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    	Inicializar();
    	
    	final ObservableList<Cliente> tablaClienteSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItems();
    	tablaClienteSeleccionado.addListener(selectorTablaCliente);
    }
}
